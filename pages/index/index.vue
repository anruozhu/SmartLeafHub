<template>
	<view>
		<view class="u-flex u-p-l-30 u-p-r-20 u-p-b-30">
			<view class="u-m-r-10" style="margin-top: 20px;">
				<u-avatar :src="pic" size="140"></u-avatar>
			</view>
			<view class="u-flex-1" style="margin-top: 20px;">
				<view class="u-font-18 u-p-b-20" style="margin-left: 20px;font-weight:bold;font-size: 17px;">欢迎来到智慧烟草种植基地</view>
				<view class="u-font-14 u-tips-color" style="margin-left: 20px;font-size: 13px;">{{ currentDate }}</view>
			</view>
			<view class="u-m-l-10 u-p-10" style="margin-top: -30px;" @click="toLog">
				<u-icon name="bell-fill" color="#969799" size="40"></u-icon>
			</view>
		</view>
		<view class="weather-card" v-if="now">  
			<image class="weather-icon" :src="`/static/icons/${now.icon}.svg`" alt="QWeather Icons"></image>
			<view class="weather-info">  
				<text class="weather-text">{{now.text}} | </text>  
				<text class="temp-text"> 气温{{now.temp}}°C | </text>  
				<text class="wind-text"> {{now.windDir}}{{now.windScale}}级 | </text>  
				<text class="humidity-text"> 湿度{{now.humidity}}%</text> 
			</view>    
		</view>
		<view class="wrap" style="margin-top: 15px;">
			<u-swiper :list="list"></u-swiper>
		</view>
		<view style="opacity: 0.8;">
			<u-card title="实时数据" title-size="title_size">
				<view class="" slot="body" style="margin: 0px;">
					<view class="u-body-item u-flex u-border-bottom u-col-between u-p-t-0">
						<view class="data" style="margin-left: 0px;margin-right: 5px;text-align: center;">
							<image src="../../static/光照.png" style="width: 60rpx;height: 60rpx;margin-top: 10px;"></image>
							<view style="text-align: center;font-size: 10px;margin-top: 3px;">光照强度</view>
							<view style="text-align: center;font-size: 10px;margin-top: 3px;">{{light_intensity}}Lux</view>
						</view>
						<view class="data" style="margin-right: 5px;text-align: center;">
							<image src="../../static/温度.png" style="width: 60rpx;height: 60rpx;margin-top: 10px;"></image>
							<view style="text-align: center;font-size: 10px;margin-top: 3px;">温度</view>
							<view style="text-align: center;font-size: 10px;margin-top: 3px;">{{temperature}}℃</view>
						</view>
						<view class="data" style="margin-right: 0px;text-align: center;">
							<image src="../../static/湿度.png" style="width: 60rpx;height: 60rpx;margin-top: 10px;"></image>
							<view style="text-align: center;font-size: 10px;margin-top: 3px;">湿度</view>
							<view style="text-align: center;font-size: 10px;margin-top: 3px;">{{humidity}}%</view>
						</view>
					</view>
				</view>
			</u-card>
		</view>
		<view style="opacity: 0.8;">
			<u-card title="状态显示" title-size="title_size">
				<view class="" slot="body" style="margin: 0px;">
					<view class="u-body-item u-flex u-border-bottom u-col-between u-p-t-0">
						<view class="data" style="margin-left: 0px;margin-right: 5px;">
							<image src="../../static/灯泡.png" style="width: 60rpx;height: 60rpx;margin-top:10px;"></image>
							<view class="status1">
								<view class="status1-tip">自动化状态:</view>
								<view class="status">
									<image v-if="isClosed1" src="../../static/关.png"></image>
									<image v-else src="../../static/开.png" ></image>
								</view>
							</view>
							<view class="status1">
								<view class="status1-tip">LED灯状态:</view>
								<view class="status">
									<image v-if="isClosed2" src="../../static/关.png" ></image>
									<image v-else src="../../static/开.png" ></image>
								</view>
							</view>
							<view class="status1">
								<view class="status1-tip" >当前挡位:  {{light.light_level}}</view>
							</view>
						</view>
						<view class="data" style="margin-right: 5px;">
							<image src="../../static/风扇.png" style="width: 60rpx;height: 60rpx;margin-top:10px;"></image>
							<view class="status1">
								<view class="status1-tip">自动化状态:</view>
								<view class="status">
									<image v-if="isClosed3" src="../../static/关.png" ></image>
									<image v-else src="../../static/开.png" ></image>
								</view>
							</view>
							<view class="status1">
								<view class="status1-tip">风扇状态:</view>
								<view class="status">
									<image v-if="isClosed4" src="../../static/关.png" ></image>
									<image v-else src="../../static/开.png" ></image>
								</view>
							</view>
							<view class="status1">
								<view class="status1-tip">当前挡位:  {{fan.fanLevel}}</view>
							</view>
						</view>
						<view class="data" style="margin-right: 0px;">
							<image src="../../static/水泵.png" style="width: 60rpx;height: 60rpx;margin-top:10px;"></image>
							<view class="status1">
								<view class="status1-tip">自动化状态:</view>
								<view class="status">
									<image v-if="isClosed5" src="../../static/关.png" ></image>
									<image v-else src="../../static/开.png"></image>
								</view>
							</view>
							<view class="status1">
								<view class="status1-tip">水泵状态:</view>
								<view class="status">
									<image v-if="isClosed6" src="../../static/关.png"></image>
									<image v-else src="../../static/开.png"></image>
								</view>
							</view>
							<view class="status1">
								<view class="status1-tip"></view>
							</view>
						</view>
					</view>
				</view>
			</u-card>
		</view>
		<!-- <view style="width: 100%;font-size: 40px;height: 50px;">{{now.text}}</view> -->
	</view>
</template>

<script>
	export default {
		data() {
			return {
				light_intensity:'',
				temperature:'',
				humidity:'',
				pic:'../../static/loho.png',
				show:true,
				currentDate: '',
				title_size:33,
				isClosed1: false,
				isClosed2: false,
				isClosed3: false,
				isClosed4: false,
				isClosed5: false,
				isClosed6: false,
				list: [{
						image: '../../static/烟草1.jpeg',
						title: ''
					},
					{
						image: '../../static/烟草2.jpeg',
						title: ''
					}
				],
				now:{
					icon:'',
					temp:'',
					text:'',
					windDir:'',
					windScale:'',
					humidity:''
				},
					light:{
						lightMode:'',
						light_level:''
					},
					fan:{
						fanMode:'',
						fanLevel:''
					},
					pump:{
						pumpCtrlState:'',
						pumpPowerState:''
					}
			}
		},
		onLoad() {
			this.getWeather()
			this.updateCurrentDate()
			console.log(this.now)
		},
		onShow() {
			this.getHumidity()
			this.getLightIntensity()
			this.getTemperature()
			this.getState()
		},
		methods: {
			getWeather(){
				const that=this;
				uni.getLocation({
					type: 'gcj02',
					success: (res) =>{
						let longitude = res.longitude.toFixed(2);
						let latitude = res.latitude.toFixed(2);
						console.log(longitude)
						console.log(latitude)
						uni.request({
							url: `https://devapi.qweather.com/v7/weather/now?location=${longitude},${latitude}&key=448cf88b3f704b5daecf846a16463c19`,
							method: 'GET',
							dataType: "json",
							success: (res) =>{
								// this.now=res.data.now
								console.log("res",res.data.now)
								// console.log("successin",this.now)
								
								// this.now=res.data.now
								// console.log(this.now)
									that.now.icon=res.data.now.icon
									console.log(that.now.icon)
									that.now.temp=res.data.now.temp
									that.now.text=res.data.now.text
									that.now.windDir=res.data.now.windDir
									that.now.windScale=res.data.now.windScale
									that.now.humidity=res.data.now.humidity
								
							},
							fail: (err) => {
								console.log(err)
							}
						})
					}
				})
			},
			updateCurrentDate() {  
			    const now = new Date();  
			    const year = now.getFullYear();  
			    const month = String(now.getMonth() + 1).padStart(2, '0'); // 月份是从0开始的，所以要加1，并用padStart补零  
			    const date = String(now.getDate()).padStart(2, '0'); // 用padStart补零  
			    const days = ['日', '一', '二', '三', '四', '五', '六'];  
			    const dayOfWeek = days[now.getDay()];  
			    this.currentDate = `${year}年${month}月${date}日  星期${dayOfWeek}`;  
			},
			getTemperature(){
				const token=uni.getStorageSync('login_user_token');
				console.log(token)
					uni.request({
						url:'http://139.155.150.150:8070/secure/temperature',
						method:'GET',
						header:{
							'Authorization': token //自定义请求头信息
						},
						success: (res) => {
							console.log(res)
							if(res.data.code*1==200){
								this.temperature=res.data.data.temperature
							}
						},
						fail: (err) => {
							console.error('请求失败',err);
						}
					})
			},
			getLightIntensity(){
				const token=uni.getStorageSync('login_user_token');
				uni.request({
					url:'http://139.155.150.150:8070/secure/light-intensity',
					method:'GET',
					header:{
						'Authorization': token 
					},
					success: (res) => {
						if(res.data.code*1==200){
							this.light_intensity=res.data.data.lightIntensity
						}
					},
					fail: (err) => {
						console.error('请求失败',err);
					}
				})
			},
			getHumidity(){
				const token=uni.getStorageSync('login_user_token');
				uni.request({
					url:'http://139.155.150.150:8070/secure/humidity',
					method:'GET',
					header:{
						'Authorization':token  //自定义请求头信息
					},
					success: (res) => {
						if(res.data.code*1==200){
							this.humidity=res.data.data.humidity
						}
					},
					fail: (err) => {
						console.error('请求失败',err);
					}
				})
			},
			toLog(){
				uni.navigateTo({
					url:'/pages/log/log'
				})
			},
			getState(){
				const token=uni.getStorageSync('login_user_token');
				uni.request({
					url:'http://139.155.150.150:8070/secure/get_device_state',
					method:"GET",
					header:{
						'Authorization':token  //自定义请求头信息
					},
					success: (res) => {
						if(res.data.code*1==200){
							this.light.lightMode=res.data.data.lightMode
							this.light.light_level=res.data.data.light_level
							this.fan.fanMode=res.data.data.fanMode
							this.fan.fanLevel=res.data.data.fanLevel
							this.pump.pumpCtrlState=res.data.data.pumpCtrlState
							this.pump.pumpPowerState=res.data.data.pumpPowerState
							if(this.light.lightMode==1){
								this.isClosed2=false
							}else if(this.light.lightMode==0){
								this.isClosed2=true
							}
							if(this.fan.fanMode==1){
								this.isClosed4=false
							}else if(this.fan.fanMode==0){
								this.isClosed4=true
							}
							if(this.pump.pumpCtrlState==1){
								this.isClosed6=false
							}else if(this.pump.pumpCtrlState==0){
								this.isClosed6=true
							}
						}
					},
					fail: (err) => {
						console.error('请求失败',err);
					}
					
				})
			}
		}
	}
</script>

<style lang="scss"> 
page{
	/* 设置背景图片 */  
	background-image: url('../../static/background-image.jpg');  
	/* 背景图片覆盖整个容器 */  
	background-size: cover;  
	/* 根据需要设置背景位置 */  
	background-position: center; 
}

.weather-card {  
  display: flex;  
  align-items: center;  
  padding: 20rpx;  
  border-radius: 20rpx;  
  background-color: #fff;  
  width: 92%;
  // margin-top: 10px;
  // margin-left: 10px;
  margin: 0 auto;
  opacity: 0.8;
}  
  
.weather-icon {  
  width: 70rpx;  
  height: 70rpx;  
  margin-right: 40rpx;  
  margin-left: 20rpx;
}  
  
.weather-info {  
  flex: 1;  
  white-space: normal; 
}  
  
.weather-text,  
.temp-text,  
.wind-text,  
.humidity-text {  
  margin-bottom: 10rpx;  
  font-weight: bold;
  margin-left: 5px;
} 
 
 .u-card-wrap { 
 		background-color: $u-bg-color;
 		padding: 1px;
 	}
 	
 	.u-body-item {
 		font-size: 32rpx;
 		color: #333;
 		padding: 20rpx 10rpx;
 	}
 		
 	.u-body-item image {
 		width: 120rpx;
 		flex: 0 0 120rpx;
 		height: 120rpx;
 		border-radius: 8rpx;
 		margin-left: 12rpx;
 	}
	.data{
		width: 100px;
		// height: 80px;
		border-radius: 10px;
		flex: 1;
		text-align:center;
	}
	.status{
		// height: 13px;
		// margin-left: 35px;
		// margin-top: -40px;
		// text-align: center; 
		height: 14px; /* 你可以设置具体的高度值，这里使用 rpx 单位 */  
		width: 33px;
		text-align: left;
		
		image {
			width: 10px;
			height: 10px;
			margin-top: -1px;
		}
	}
	.status1{
		display: flex;
		flex: 1;
		text-align: center;
		height: 14px;
		width: 100%;
		margin-top: 4px;
	}
	.status1-tip{
		text-align: center;
		font-size: 10px;
		margin-top: 3px;
		display: flex;
		padding-left: 10px;
		width: 70px;
	}
	.wrap {
		padding: 20rpx;
		border-radius: 20rpx;
		background-color: #fff;  
		width: 92%;
		height: 100%;
		// margin-top: 10px;
		// margin-left: 10px;
		// margin-right: 10px;
		margin: 0 auto;
		opacity: 0.8;
	}
</style>
