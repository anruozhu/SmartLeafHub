<template>
	<view class="container">
		<view class="card">
			<view class="card-title">问题和意见（200字以内）<text class="card-must">*</text></view>
			<view class="card-body">
				<textarea placeholder="请填写您的功能建议,感谢您的支持" maxlength="200" v-model="opinion" :disable="disabled" @input="monitor" ></textarea>
			</view>
		</view>
		<view class="card">
			<view class="card-title">联系方式</view>
			<view class="card-body">
				<input placeholder="留下联系方式,可能更快的解决问题~" v-model="phoneNumber" type="number" maxlength="11"/>
			</view>
		</view>
		<view class="button" @click="submit">提交</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				opinion:'',
				phoneNumber:'',
				disabled:false
			}
		},
		onLoad() {

		},
		methods: {
			monitor(){
				const res=/^.{0,400}$/
				if(!res.test(this.opinion)){
					uni.showToast({
						'title':'不能超过200个字',
						icon:"none",
						duration:2000
					})
					this.disabled=true
				}
			},
			
			submit(){
				const token=uni.getStorageSync('login_user_token')
				if (this.opinion == '' || this.opinion == null) {
					uni.showToast({
						title: '问题和意见不能为空',
						icon: 'none',
					})
				}else if((this.phoneNumber)&&(!(/^1(3|4|5|6|7|8|9)\d{9}$/.test(this.phoneNumber)))) { //校验手机号码是否有误
					uni.showToast({
						title: '请填写正确手机号码',
						icon: "none",
						duration: 2000
					});
				}else{
					console.log(this.phoneNumber)
					let url=`http://139.155.150.150:8070/secure/opinion?phone_number=${this.phoneNumber}&description=${this.opinion}`;
					uni.request({
						url:url,
						method:"POST",
						header:{
							'Authorization': token //自定义请求头信息
						},
						success: (res) => {
							if(res.data.code*1==200){
								uni.showToast({
									title: '意见反馈成功,请等候处理',
									icon: 'none',
									duration: 2000
								});
								this.opinion=''
								this.phoneNumber=''
							}else{
								this.$u.toast("意见反馈失败，请重试")
							}
						},
						fail: (err) => {
							console.error('请求失败',err);
						}
					})
				}
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
	.container {
		// background: #FAFAFA;
		min-height: 100vh;
		width: 100vw;
		
		.card{
			width: 710rpx;
			margin: 30rpx auto;
			
			.card-title{
				font-size: 15px;
				color: #555;
				padding: 15rpx;
				
				.card-must{
					color: red;
				}
			}
			
			.card-body{
				background: #fff;
				border-radius: 12rpx;
				padding: 15rpx;
				margin-top: 5px;
				
				textarea{
					height: 300rpx;
					font-size: 14px;
				}
				
				input{
					font-size: 14px;
					height: 70rpx;
				}
				
			}
		}
		
		.button{
			width: 500rpx;
			margin: 20rpx auto;
			border-radius: 40rpx;
			height: 80rpx;
			font-size: 16px;
			font-weight: bold;
			background-color: #55aaff;
			text-align: center;
			line-height: 80rpx;
			color: #fff;
			margin-top: 20px;
			&:active{
				background-color: #3d7bb8;
			}
		}
	}
</style>