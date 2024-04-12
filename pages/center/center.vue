<template>
	<view>
		<view>
			<view class="u-flex user-box u-p-l-30 u-p-r-20 u-p-b-30" style="opacity: 0.8;" @click="modify()">
				<view class="u-m-r-10" style="margin-top: 15px;margin-left: 10px;">
					<u-avatar :src="pic" size="160"></u-avatar>
				</view>
				<view class="u-flex-1">
					<view class="u-font-18 u-p-b-20" style="margin-left: 22px;font-size: 17px;" v-if="user.nickname">{{user.nickname}}</view>
				</view>
				<view class="u-m-l-10 u-p-10">
					<u-icon name="arrow-right" color="#969799" size="28"></u-icon>
				</view>
			</view>
			
			
			<view class="u-m-t-20" style="opacity: 0.8;margin-top: 50px;">
				<u-cell-group>
					<u-cell-item icon="edit-pen" title="修改密码" :title-style="{'fontSize': '35rpx'}" @click="toPasswdModify(user.phoneNumber)"></u-cell-item>
					<u-cell-item icon="info-circle" title="关于" :title-style="{'fontSize': '35rpx'}" @click="toAboutus"></u-cell-item>
					<u-cell-item icon="file-text" title="意见反馈" :title-style="{'fontSize': '35rpx'}" @click="toFeedback"></u-cell-item>
				</u-cell-group>
			</view>
			
			<view class="u-m-t-20" style="opacity: 0.8;">
				<u-cell-group>
					<u-cell-item icon="man-delete" title="退出" @click="quit" :arrow="false" :title-style="{'fontSize': '35rpx'}"></u-cell-item>
				</u-cell-group>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				user: {
					nickname:'',
					phoneNumber:'',
					email:'',
					address:''
				},
				pic:'../../static/loho.png'
			}
		},
		onLoad() {
		},
		onShow() {
			this.getUserInfo();
			this.user=uni.getStorageSync('login_user')
			console.log('address',this.user.address)
			const value=uni.getStorageSync('login_user')
			if(value){  //有用户信息
				console.log('value',value.address)
				this.user.phoneNumber=uni.getStorageSync('login_user_phoneNumber')
				if(this.user.nickname==null||this.user.nickname==''){
					this.user.nickname=this.user.phoneNumber
					console.log('user',this.user.address)
				}
			}
			const value1=uni.getStorageSync('login_user')
			console.log('最终store',value1)
			// uni.setStorageSync("login_user",this.user)
		},
		methods: {
			quit(){
				//清空用户信息
				uni.setStorageSync('login_user',"")
				uni.setStorageSync("login_user_token","")
				//界面切换
				uni.reLaunch({
					url:'/pages/Sign_In/Sign_In'
				})
			},
			toAboutus(){
				uni.navigateTo({
					url:'/pages/aboutus/aboutus'
				})
			},
			toFeedback(){
				uni.navigateTo({
					url:'/pages/feedback/feedback'
				})	
			},
			toPasswdModify(phoneNumber){
				uni.navigateTo({
					url:'/pages/passwdModify/passwdModify?phoneNumber='+this.user.phoneNumber
				})
			},
			// modify(user){
			// 	const param1=this.user.nickname
			// 	const param2=this.user.phoneNumber
			// 	const param3=this.user.email
			// 	const param4=this.user.address
			// 	const url=`/pages/myinfo/myinfo?nickname=${param1}&phoneNumber=${param2}&email=${param3}&address=${param4}`
			// 	uni.navigateTo({
			// 		url: url
			// 	})
			// },
			
			modify(phoneNumber){
				const url='/pages/myinfo/myinfo?phoneNumber='+this.user.phoneNumber
				uni.navigateTo({
					url: url
				})
			},
			
			getUserInfo(){
				const token=uni.getStorageSync('login_user_token')
				console.log(`${uni.getStorageSync('login_user_token')}`)
				uni.request({
					url:'http://139.155.150.150:8070/secure/GetUserInfo',
					method:"GET",
					header:{
						'Authorization': token //自定义请求头信息
						// 'content-type':'application/json',
						// 'Authorization':`Bearer ${uni.getStorageSync('login_user_token')}`
					},
					success: (res) => {
						console.log('返回后获取的',res.data.data)
						if(res.data.code*1==200){
							if(res.data.data.nickname==null){
								res.data.data.nickname=''
							}
							if(res.data.data.email==null){
								res.data.data.email=''
							}
							if(res.data.data.address==null){
								res.data.data.address=''
							}
							uni.setStorageSync('login_user',res.data.data)
							this.user=res.data.data
							console.log('返回赋值后的user',this.user)
						}else{
							console.log('错误')
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
	// cover-image {  
	//     width: 100%;  
	//     height: 100vh;  
	// }  
page{
	/* 设置背景图片 */  
	background-image: url('../../static/background-image.jpg');  
	/* 背景图片覆盖整个容器 */  
	background-size: cover;  
	/* 根据需要设置背景位置 */  
	background-position: center; 
}

.camera{
	width: 54px;
	height: 44px;
	
	&:active{
		background-color: #ededed;
	}
}
	.user-box{
		background-color: #fff;
	}
</style>
