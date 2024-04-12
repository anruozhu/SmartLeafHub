<template>
	<view id="contianer">
		<view class="pagetitle">密码修改</view>
			<view class="password">
				<view class="contentuser">
					<view class="user" style="color: #b4b4b4;">
						手机号
					</view>
					<view class="contentuserText">
						<input v-model="modify_user.phoneNumber" type="number" maxlength="11" class="input" placeholder="请输入手机号" disabled="true"/>
						<u-toast ref="uToast"></u-toast>
						<u-verification-code :seconds="seconds" ref="uCode" 
						@change="codeChange"></u-verification-code>
						<view style="width:60%;font-size: 12px;margin-top: 10px;">
							<button @click="getCode" size="mini" :disabled="getCodeisWaiting">{{getCodeText}}</button>
						</view>
					</view>
				</view>
				<view class="border"></view>
			</view>
			<view class="password">
				<view class="contentuser">
					<view class="user">
						验证码
					</view>
					<view class="contentuserText">
						<input v-model="modify_user.code" type="number" class="input" placeholder="请输入验证码"/>
					</view>
				</view>
				<view class="border"></view>
			</view>
				
			<view class="password">
				<view class="contentuser">
					<view class="user">
						新密码
					</view>
					<view class="contentuserText">
						<input v-model="modify_user.password" type="password" v-show="eyeVisible[0]"  class="input" placeholder="请输入密码" @input="checkPasswd()"/>
						<input v-model="modify_user.password" type="text" v-show="!eyeVisible[0]" class="input" placeholder="请输入密码" @input="checkPasswd()"/>
						<u-icon name="eye-fill" v-if="!eyeVisible[0]" size="28"
						@click="togglePasswordVisibility(0)"></u-icon>
						<u-icon name="eye-off" size="28" v-if="eyeVisible[0]"
							@click="togglePasswordVisibility(0)"></u-icon>
					</view>
				</view>
				<view class="border"></view>
			</view>
				
			<view class="password">
				<view class="contentuser">
					<view class="user">
						确认密码
					</view>
					<view class="contentuserText">
						<input v-model="modify_user.repassword" type="password" v-show="eyeVisible[1]" class="input" placeholder="请再次输入新密码" @input="checkRepasswd()" :disabled="disabled"/>
						<input v-model="modify_user.repassword" type="text" v-show="!eyeVisible[1]" class="input" placeholder="请再次输入新密码" @input="checkRepasswd()" :disabled="disabled"/>
						<u-icon name="eye-fill" v-if="!eyeVisible[1]" size="28"
							@click="togglePasswordVisibility(1)"></u-icon>
						<u-icon name="eye-off" size="28" v-if="eyeVisible[1]"
							@click="togglePasswordVisibility(1)"></u-icon>
					</view>
				</view>
				<view class="border"></view>
			</view>
			<view class="password">
				<view  v-if="startInput" class="check">
					<image v-if="error==='A'" src="../../static/错误.png" class="status-icon" mode="widthFix" style="width: 17px;width: 17px;"/> 
					<image v-if="error==='B'" src="../../static/通过.png" class="status-icon" mode="widthFix" style="width: 17px;width: 17px;" />
					<text v-if="error==='A'" class="error-text" style="color: red;">密码必须包含数字、字母和特殊字符，且长度为8-16位</text>
					<text v-if="error==='B'" class="error-text" style="color: #36ab60;">输入正确</text>
					<image v-if="error==='C'" src="../../static/错误.png" class="status-icon" mode="widthFix" style="width: 17px;width: 17px;" />
					<text v-if="error==='C'" class="error-text" style="color: red;">两次输入的密码不一致!</text>
				</view>
			</view>
		<view style="margin-top: 60rpx;"><button class="button" @tap="signPut()">提交</button></view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				eyeVisible: [true, true], // 控制眼睛图标显示状态的数组
				eyefill: true,
				eyeoff: false,
				getCodeText: '获取验证码',
				tips: '',
				seconds: 60,
				getCodeisWaiting: false,
				disabled:true,
				error:"",
				startInput:false,
				modify_user:{
					phoneNumber:'',
					code: '',
					password:'',
					repassword:''
				}
			};
		},
		onShow() {
			const value=uni.getStorageSync('login_user_phoneNumber')
			//根据用户登录的信息进行不同页面的展示
			if(value){  //有用户信息
				this.modify_user.phoneNumber=value
			}
		},		
		methods: {
			Timer() {},
			getCode() {
				const token=uni.getStorageSync('login_user_token');
				console.log('start')
				console.log(token)
				console.log('end')
				uni.hideKeyboard() //隐藏已经显示的软键盘，如果软键盘没有显示则不做任何操作。
				if (this.getCodeisWaiting) {
					return;
				}
				this.getCodeText = "发送中..." //发送验证码
				this.getCodeisWaiting = true;
				console.log(this.modify_user.phoneNumber)
				let url=`http://139.155.150.150:8070/api/msm/UpdateSend?phoneNumber=${this.modify_user.phoneNumber}`;
				uni.request({
					url:url,
					method:"GET",
					header:{
						'Authorization': token
					},
					success: (res) => {
						console.log(res)
						if(res.data.code*1==200){
							this.setTimer(); //调用定时器方法
							uni.showToast({
								title: '验证码已发送',
								icon: "none"
							}); 
						}
					},
					fail: function(err){
						uni.showToast({
							title: '验证码发送失败',
							icon: "none"
						}); 
						console.log('验证码发送失败',err)
					}
				})
			},
			//setTimer： 需要每隔一段时间执行一件事的的时候就需要使用SetTimer函数
			setTimer() {
				let holdTime = 60; //定义变量并赋值
				this.getCodeText = "重新获取(60)"
				//setInterval（）是一个实现定时调用的函数，可按照指定的周期（以毫秒计）来调用函数或计算表达式。
				//setInterval方法会不停地调用函数，直到 clearInterval被调用或窗口被关闭。
				this.Timer = setInterval(() => {
					if (holdTime <= 0) {
						this.getCodeisWaiting = false;
						this.getCodeText = "获取验证码"
						clearInterval(this.Timer); //清除该函数
						return; //返回前面
					}
					this.getCodeText = "重新获取(" + holdTime + ")"
						holdTime--;
				}, 1000)
			},
			codeChange(text) {
				this.tips = text;
			},
			
			checkPasswd(){
				this.startInput=true;
				const regex = /^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*?~`()<>.,/;'":]).{8,16}$/;  
				if((this.modify_user.password==''||this.modify_user.password==null) && (this.modify_user.repassword==''||this.modify_user.repassword==null)){
					this.startInput=false
				}else if(this.modify_user.password==''||this.modify_user.password==null){
					this.startInput=false
				}else if(!regex.test(this.modify_user.password)){
					this.error = 'A'
				}else{
					this.error = 'B'
					this.disabled=false
				}
			},
			
			
			checkRepasswd(){
				this.startInput=true;
				if((this.modify_user.password==''||this.modify_user.password==null) && (this.modify_user.repassword==''||this.modify_user.repassword==null)){
					this.startInput=false
				}else if(this.modify_user.repassword==''||this.modify_user.repassword==null){
					this.startInput=false
				}else if(this.modify_user.password == this.modify_user.repassword){
					this.error = 'B'
				}else{
					this.error = 'C'
				}
			},
			
			togglePasswordVisibility(index) {
				// 使用 $set手动更新
				this.$set(this.eyeVisible, index, !this.eyeVisible[index]);
			},
			
			signPut() {
				const token=uni.getStorageSync("login_user_token")
				uni.hideKeyboard() //隐藏已经显示的软键盘，如果软键盘没有显示则不做任何操作。
				//示例验证码，实际使用中应为请求服务器比对验证码是否正确。
				if (this.modify_user.code == '' || this.modify_user.code == null) {
					uni.showToast({
						title: '验证码不能为空',
						icon: 'none',
						duration: 2000
					})
				} else if (this.modify_user.password == '' || this.modify_user.password == null) {
					uni.showToast({
						title: '新密码不能为空',
						icon: 'none',
						duration: 2000
					})
				} else if (this.modify_user.repassword == '' || this.modify_user.repassword == null) {
					uni.showToast({
						title: '确认密码不能为空',
						icon: 'none',
						duration: 2000
					})
				} else if(!(/^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*?~`()<>.,/;'":]).{8,16}$/.test(this.modify_user.password))) { 
					uni.showToast({
						title: '请填写正确的密码',
						icon: "none",
						duration: 2000
					});
				}else {
					let url=`http://139.155.150.150:8070/secure/UpdatePassword?phoneNumber=${this.modify_user.phoneNumber}&NewPassword=${this.modify_user.password}&code=${this.modify_user.code}`;
					uni.request({
						url:url,
						method:"POST",
						header:{
							'Authorization': token //自定义请求头信息
						},
						success: (res) => {
							if(res.data.code*1==200){
								uni.showToast({
									title: '密码修改成功,请重新登录',
									icon: 'none',
									duration: 2000
								});
								uni.setStorageSync('login_user','')
								uni.setStorageSync('login_user_token','')
								uni.setStorageSync('login_user_phoneNumber','')
								uni.reLaunch({
									url:'/pages/Sign_In/Sign_In'
								})
							}else{
								this.$u.toast("修改失败，请检测验证码")
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



<style scoped>
	page{
		/* 设置背景图片 */  
		background-image: url('../../static/background-image.jpg'); 
		/* 背景图片覆盖整个容器 */  
		background-size: cover;  
		/* 根据需要设置背景位置 */  
		background-position: center; 
		/* height: 100%; */
	}
	
	#contianer {
		padding-top: 1rpx;
		position: relative;
		height: 100%;
	}
	.pagetitle{
		padding-top: 150rpx;
		display: flex;
		justify-content: flex-start;
		margin-left: 50rpx;
		font-weight: 500;
		font-size: 50rpx;
		color: #011108;
		letter-spacing: 5rpx;
		margin-bottom: 50rpx;

	}
	
	.input{
		// background-color: red;
		height: 100rpx;
	}
	.user {
		font-size: 32rpx;
		font-family: Inter, Inter;
		font-weight: 400;
		color: #333333;
		padding-left: 5rpx;
	}
 
	.contentuser {
		display: flex;
		padding-right: 20rpx;
		box-sizing: border-box;
		justify-content: space-between;
		align-items: center;
		height: 116rpx;
	}
 
	.contentuserText {
		display: flex;
		width: 500rpx;
		/* background-color: aqua; */
		font-size: 28rpx;
		font-family: Inter, Inter;
		padding-right: 15rpx;
		font-weight: 400;
		color: #666666;
	}

	.border {
		width: 656rpx;
		height: 1rpx;
		opacity: 1;
		border-bottom: 0.5rpx solid #D2D2D2;
 
	}
 
	.password {
		box-sizing: border-box;
		padding-left: 40rpx;
	}

	.button {
		width: 90%;
		font-size: 32rpx;
		height: 80rpx;
		color: #fff;
		border-radius: 68rpx;
		margin-top: 82rpx;
		text-align: center;
		line-height: 80rpx;
		background-color: #00bfff;
		margin-left: auto;
		margin-right: auto;

	}
	
	.check{
		  display: flex;
		  margin-top: 5px;
		  width: 100%;
	}
	.status-icon {
		 width: 10px; /* 根据你的icon大小调整 */  
		 height: 10px; /* 根据你的icon大小调整 */    
	}  
	.error-text {  
		font-size: 13px;
		margin-left: 7px;
	}
</style>
