<template>
	<view>
		<view style="opacity: 0.8;">
			<u-card :title="title" :title-size="title_size">
				<view class="" slot="body">
					<view class="main"> 
						<div id="echarts" ref="dom" ></div>
					</view>
				</view>
			</u-card>
		</view>
		
		<view class="stastics">
			<view class="block1">
				<view class="light-value" style="color: deepskyblue;">{{stat.min}}Lux</view>
				<view class="value-lable">最低值</view>
			</view>
			<view class="block1">
				<view class="light-value" style="color: forestgreen;">{{stat.avg}}Lux</view>
				<view class="value-lable">平均值</view>
			</view>
			<view class="block2">
				<view class="light-value" style="color: red;">{{stat.max}}Lux</view>
				<view class="value-lable">最大值</view>
			</view>
		</view>
		
		<view style="opacity: 0.8;">
			<u-card :title="title1" :title-size="title_size">
				<view class="" slot="body">
					<view class="u-body-item u-flex u-border-bottom u-col-between u-p-t-0" style="display: flex;">
						<view class="img">
							<image src="../../static/光照.png" style="width: 100rpx;height: 100rpx;margin-top: 0px;margin-left: 2px;"></image>
						</view>
						<view class="yujing">
							<view style="margin-top: 0px;">光照强度： {{alert.alertData}}Lux</view>
							<view style="margin-top: 10px;">预警： {{alert.alertMessage}}</view>
							<view style="margin-top: 10px;">上次异常时间： {{alert.alertTime}}</view>
						</view>
					</view>
				</view>
			</u-card>
		</view>
		
		<view style="opacity: 0.8;">
			<u-card :title="title2" :title-size="title_size">
				<view class="" slot="body">
					<view class="u-body-item u-flex u-border-bottom u-col-between u-p-t-0">
						<view style="display: flex;margin-top: 5px;flex: 1;">
							<view class="switch_wrap">
								<view class="switch">LED灯自动化开关 </view>
								<view style="margin-left: 20px;">
									<u-switch v-model="checked" active-color="green" size="30"></u-switch>
								</view>
							</view>
							<view class="switch_wrap">
								<view class="switch">LED灯手动开关 </view>
								<view style="margin-left: 20px;">
									<u-switch v-model="checked1"  active-color="green" size="30" @change="changeSwitch"></u-switch>
								</view>
							</view>
						</view>
					</view>
					<view style="display: flex;width: 100%;margin-top: 10px;font-size: 12px;">
						<view style="text-align: left;width: 50%;">挡位调节</view>
						<view style="text-align: right;width: 50%;">数值：{{value}}</view>
					</view>
					<view style="width: 100%;margin-top: 15px;">
						<u-slider v-model="value" :disabled="bin" @end="changePicker"></u-slider>
					</view>
					<view style="width: 100%;height: 8px;"></view>  
				</view>
			</u-card>
		</view>
		<!-- 为了使页面底部有点空余，不让最后一个卡片底部与页面底部贴合 -->
		<view style="width: 100%;height: 8px;"></view>  
	</view>
</template>

<script>
	import * as echarts from 'echarts';
	export default {
		onLoad() {    
		}, 
		data() {
			return {
				stat:{
					min:'',
					max:'',
					avg:''
				},
				ctl:{
					light_level:'',
					light_mode:''
				},
				title:"过去7日 日均光照趋势图",
				title1:"预警",
				title2:"LED灯控制区",
				checked: false,
				checked1: false,
				trend:['','','','','','',''],
				light:{
					lightMode:'',
					light_level:''
				},
				alert:{
					alertData:'',
					alertMessage:'',
					alertTime:''
				},
				bin:true,
				mode:'',
				value: '',
				title_size: 33,
				//以下为echarts图表的设置，暂时是为了展示而设
				myCharts: {},
				option: {
				   xAxis: {
				       type: "category",
				       data: ["7日", "6日", "5日", "4日","3日","2日","1日"],
				   },
				   yAxis: {
				       type: "value",
				   },
				   series: [
				       {
				           type: "line",
				           data: []
				       }
				   ]
				}
			}
		},
		onShow(){
			this.getLightIntensityMath()
			this.getLightState()
			this.getTrends()
			this.getAlerts()
			this.option.series[0].data=this.trend
			console.log('trend',this.trend) 
			console.log('data',this.option.series[0].data) 
			this.setECharts();
			this.resizeChart();  
			window.addEventListener('resize', this.resizeChart); // 监听窗口尺寸变化 
		},
		methods:{
			showStatus(){
				const token=uni.getStorageSync('login_user_token')
				uni.request({
					url:'http://139.155.150.150:8070/secure/getLightStatus',
					method:'GET',
					header:{
						'Authorization': token //自定义请求头信息
					},
					success: (res) => {
						console.log(res.data)
						if(res.data.code*1==200){
							this.ctl.light_level=res.data.data.light_level
							this.ctl.light_mode=res.data.data.light_mode
						}else{
							console.log('错误')
						}
					},
					fail: (err) => {
						console.error('请求失败',err);
					}
				})
			},
			setECharts() {
			    //获取dom元素
			    let dom = this.$refs.dom;
			    this.myCharts = echarts.init(dom, null, {
					width: 300,   //初始设置画布宽度
					height: 235  //初始设置画布高度
				});
			    //设置echarts展示到对应的dom元素中
			    this.myCharts.setOption(this.option)
			},
			resizeChart() {  
			    if (this.myCharts) {  
					this.myCharts.resize(); // 调整图表尺寸以适应容器  
			    }  
			},
			getLightIntensityMath(){
				const token=uni.getStorageSync('login_user_token');
				console.log(token)
					uni.request({
						url:'http://139.155.150.150:8070/secure/light-intensity-math',
						method:'GET',
						header:{
							'Authorization': token //自定义请求头信息
						},
						success: (res) => {
							console.log(res)
							if(res.data.code*1==200){
								this.stat.min=res.data.data.min.toFixed(2)
								this.stat.avg=res.data.data.avg.toFixed(2)
								this.stat.max=res.data.data.max.toFixed(2)
							}
						},
						fail: (err) => {
							console.error('请求失败',err);
						}
					})
			},
			getLightState(){
				const token=uni.getStorageSync('login_user_token');
				uni.request({
					url:'http://139.155.150.150:8070/secure/getLightStatus',
					method:"GET",
					header:{
						'Authorization':token  //自定义请求头信息
					},
					success: (res) => {
						if(res.data.code*1==200){
							this.light.lightMode=res.data.data.lightMode
							this.light.light_level=res.data.data.light_level
							if(this.light.lightMode==1){
								this.checked1=true
								this.bin=false
							}else if(this.light.lightMode==0){
								this.checked1=false
								this.bin=true
							}
							this.value=this.light.light_level
						}
					},
					fail: (err) => {
						console.error('请求失败',err);
					}
					
				})
			},
			getTrends(){
				const token=uni.getStorageSync('login_user_token');
				uni.request({
					url:'http://139.155.150.150:8070/secure/light-intensity-week',
					method:"GET",
					header:{
						'Authorization':token  //自定义请求头信息
					},
					success: (res) => {
						if(res.data.code*1==200){
							console.log(res.data)
							this.trend[0]=res.data.data[6].lightIntensity
							this.trend[1]=res.data.data[5].lightIntensity
							this.trend[2]=res.data.data[4].lightIntensity
							this.trend[3]=res.data.data[3].lightIntensity
							this.trend[4]=res.data.data[2].lightIntensity
							this.trend[5]=res.data.data[1].lightIntensity
							this.trend[6]=res.data.data[0].lightIntensity
						}
					},
					fail: (err) => {
						console.error('请求失败',err);
					}
					
				})
			},
			getAlerts(){
				const token=uni.getStorageSync('login_user_token');
				uni.request({
					url:'http://139.155.150.150:8070/secure/alert-info',
					method:"GET",
					header:{
						'Authorization':token  //自定义请求头信息
					},
					data:{
						alertType:'1'
					},
					success: (res) => {
						if(res.data.code*1==200){
							console.log(res.data)
							this.alert.alertData=res.data.data.alertData
							this.alert.alertMessage=res.data.data.alertMessage
							this.alert.alertTime=res.data.data.alertTime
							const transedTime=this.formatISO8601Date(this.alert.alertTime)
							this.alert.alertTime=transedTime
						}
					},
					fail: (err) => {
						console.error('请求失败',err);
					}
					
				})
			},
			changeSwitch(e){
				console.log(e)
				this.checked1=e
				if(this.checked1==false){
					this.bin=true
				}else{
					this.bin=false
					const token=uni.getStorageSync('login_user_token');
					let url=`http://139.155.150.150:8070/secure/lightctl?light_level=${this.value}&light_mode=1`;
					uni.request({
						url:url,
						method:"GET",
						header:{
							'Authorization':token  //自定义请求头信息
						},
						success: (res) => {
							if(res.data.code*1==200){
								console.log('成功')
							}
						},
						fail: (err) => {
							console.error('请求失败',err);
						},
					})
				}
			},
			changePicker(e){
				const token=uni.getStorageSync('login_user_token');
				let url=`http://139.155.150.150:8070/secure/lightctl?light_level=${this.value}&light_mode=1`;
				uni.request({
					url:url,
					method:"GET",
					header:{
						'Authorization':token  //自定义请求头信息
					},
					success: (res) => {
							console.log('成功')
					},
					fail: (err) => {
						console.error('请求失败',err);
					},
				})
			},
			formatISO8601Date(isoString) {  
			  // 使用Date对象解析ISO 8601格式的字符串  
			  const date = new Date(isoString);  
			  
			  // 获取年、月、日、时、分、秒  
			  const year = date.getFullYear();  
			  const month = String(date.getMonth() + 1).padStart(2, '0'); // 月份从0开始，所以需要+1  
			  const day = String(date.getDate()).padStart(2, '0');  
			  const hours = String(date.getHours()).padStart(2, '0');  
			  const minutes = String(date.getMinutes()).padStart(2, '0');  
			  const seconds = String(date.getSeconds()).padStart(2, '0');  
			  
			  // 拼接成新的日期时间字符串  
			  const formattedDate = `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;  
			  return formattedDate;  
			} 
		},
		mounted() {
		    //在页面初始换时，使echarts展示出来
		},
		beforeDestroy() {  
		    window.removeEventListener('resize', this.resizeChart); // 移除监听器  
		    if (this.myCharts) {  
		      this.myCharts.dispose(); // 销毁 echarts 实例  
		    }  
		},
	}
</script>

<style>
	page{
		/* 设置背景图片 */  
		background-image: url('../../static/background-image.jpg'); 
		/* 背景图片覆盖整个容器 */  
		background-size: cover;  
		/* 根据需要设置背景位置 */  
		background-position: center; 
	}

	#echarts {  
		justify-content: center;
		align-items: center;
		height: 180px;
		width:100% ;
		margin-top: -65px; 
		padding-left: 15px;
	} 
	
	.stastics {
		width: 92%;
		height: 100px;
		margin: 0 auto;
		border-radius: 20rpx;
		display: flex;
		flex:1;
		background-color:white;
		opacity:0.8;		
	}
	
	.block1 {
		width: 33.3%;
		height: 75%;
		border-right: solid;
		text-align: center;
		margin-top: 15px;
		border-color: gainsboro;
	}
	
	.block2 {
		width: 33.3%;
		height: 75%;
		text-align: center;
		margin-top: 15px;
	}
	
	.light-value {
		font-size: 21px;
	}
	
	.value-lable {
		font-size: 13px;
		margin-top:18px;
		font-weight: bold;
	}
	
	.u-body-item {
		padding: 20rpx 10rpx;
	}
		
	.u-body-item image {
		width: 120rpx;
		flex: 0 0 120rpx;
		height: 100rpx;
		border-radius: 8rpx;
		margin-left: 12rpx;
	}
	.img {
		/* width: 52px;
		height: 44px; */
		width: 25%;
		height: 100%;
		text-align: center;
	}
	.yujing {
		margin-left: 20px;
		text-align: left;
		width: 75%;
		font-size: 12px;
	}
	.switch_wrap{
		display: flex;
		width: 50%;
		text-align: center;
	}
	.switch {
		font-size: 12px;
	}
</style>
