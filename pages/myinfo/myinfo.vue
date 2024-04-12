<template>
	<view>
		<view class="u-m-t-20" style="opacity: 0.8;margin-top: 50px;">
			<u-cell-group v-for="(item, index) in list" :key="index">  
			    <u-cell-item :title="item.title" @click="editItem(index)" :title-style="{'fontSize': '35rpx'}" :required="item.required" :arrow="item.arrow">  
			        <view v-if="!isEditing || isEditing !== index">  
			          {{ item.value }}
			        </view>  
			        <u-input v-else v-model="list[index].value" placeholder="请输入新值" input-align="right" :clearable="false" @input="monitor(index) " @blur="checkNull(index)"></u-input>  
			    </u-cell-item> 
			</u-cell-group>  
			<view v-if="startInput" class="check">
				<image v-if="error==='A'" src="../../static/错误.png" class="status-icon" mode="widthFix" style="width: 16px;width: 16px;"/> 
				<image v-if="error==='B'" src="../../static/通过.png" class="status-icon" mode="widthFix" style="width: 16px;width: 16px;"/>
				<text v-if="error==='A'" class="error-text" style="color: red;">昵称为2~12位中文、英文或数字</text>
				<text v-if="error==='B'" class="error-text" style="color: #36ab60;">输入正确</text>
				<image v-if="error==='C'" src="../../static/错误.png" class="status-icon" mode="widthFix" style="width: 16px;width: 16px;"/>
				<text v-if="error==='C'" class="error-text" style="color: red;">邮箱格式不正确</text>
			</view>
			<view style="width: 100%;height: 100%;margin-left: 20%;margin-top:10px; display: flex;">
			 	<view style="margin-right: 80px;">
			 		<button @click="saveItem()" size="mini">保存</button>  
			 	</view>
				<view>
			 		<button @click="cancelEdit" size="mini">取消</button>  
			 	</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				list:[
					{
						title: '手机号',
						value: '',
						required:true,
						arrow:false
					},
					{
						title: '昵称',
						value: '',
						required:true,
						arrow:true
					},
					{
						title: '邮箱',
						value: '',
						required:false,
						arrow:true
					},
					{
						title: '地址',
						value: '',
						required:false,
						arrow:true
					}
				],
				isEditing: null ,// 当前正在编辑的cell的索引  
				error:"",
				startInput:false
			}
		},
		// onLoad(data){
		// 	console.log(data)
		// 	this.list[0].value=data.phoneNumber
		// 	this.list[1].value=data.nickname
		// 	this.list[2].value=data.email
		// 	this.list[3].value=data.address
		// 	console.log(this.list[1].value)
		// 	if(this.list[1].value==''||this.list[1].value==null){
		// 		this.list[1].value==''
		// 	}else if(this.list[2].value==''||this.list[2].value==null){
		// 		this.list[2].value==''
		// 	}else if(this.list[3].value==''||this.list[3].value==null){
		// 		this.list[3].value==''
		// 	}
		// },
		onShow(){
			const value=uni.getStorageSync('login_user')
			console.log('show',value)
			this.list[0].value=uni.getStorageSync('login_user_phoneNumber')
			if(value.nickname==null||value.nickname==''){
				this.list[1].value=''
			}else{
				this.list[1].value=value.nickname
			}
			if(value.email==null||value.email==''){
				this.list[2].value=''
			}else{
				this.list[2].value=value.email
			}
			if(value.address==null||value.address==''){
				this.list[3].value=''
			}else{
				this.list[3].value=value.address
			}
			console.log('final',this.list[3].value)
		},
		methods: {
			editItem(index) {  
				if(index==0){
					uni.showToast({
						title:'手机号不能修改',
						icon:"none",
						duration:2000
					})
				}
			    this.isEditing = index;  
			},  
			monitor(index){
				switch(index){
					case 1:
						this.startInput=true
						if(this.list[1].value==''||this.list[1].value==null){
							this.startInput=false
						}else if(!(/^[\u4e00-\u9fa5a-zA-Z0-9]{2,12}$/.test(this.list[1].value))){
							this.error='A'
						}else{
							this.error='B'
						}
						break;
					case 2:
						this.startInput=true
						if(this.list[2].value==''||this.list[2].value==null){
							this.startInput=false
						}else if(!(/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(this.list[2].value))){
							this.error='C'
						}else{
							this.error='B'
						}
						break;
					default:
						return;
				}
			},
			checkNull(index){
				if(this.list[index].value==''||this.list[index].value==null){
					const user=uni.getStorageSync('login_user')
					const value=this.list[index].title
					this.list[index].value=user.value
				}
				this.startInput=false
			},
			saveItem() {  
				const token=uni.getStorageSync('login_user_token');
			    // 这里可以添加保存逻辑，例如发送到后端  
				if(this.list[1].value==''||this.list[1].value==null){
					uni.showToast({
						title: '昵称不能为空',
						icon: "none",
						duration: 2000
					});
					this.cancelEdit()
					return;
				}else if(!(/^[\u4e00-\u9fa5a-zA-Z0-9]{2,12}$/.test(this.list[1].value))){
					uni.showToast({
						title: '请填写正确的昵称',
						icon: "none",
						duration: 2000
					});
					return;
				}else if(!(/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(this.list[2].value))){
					uni.showToast({
						title: '请填写正确的邮箱格式',
						icon: "none",
						duration: 2000
					});
					return;
				}else{
					uni.request({
						url:'http://139.155.150.150:8070/secure/SetUserInfo',
						method:"POST",
						header:{
							'Authorization': token
						},
						data:{
							nickname:this.list[1].value,
							email:this.list[2].value,
							address:this.list[3].value
						},
						success: (res) => {
							console.log('修改',res)
							if(res.data.code*1==200){
								uni.showToast({
									title: '修改成功',
									icon: 'none',
									duration: 2000
								});
								// this.cancelEdit()
								console.log('修改后返回的',res.data.data)
								uni.setStorageSync('login_user',res.data.data)
								const login_user=uni.getStorageSync('login_user')
								console.log('login_user',login_user)
							}else{
								this.$u.toast("修改失败")
							}
						},
						fail: (err) => {
							console.error('请求失败',err);
						}
					})
				}
			},  
			cancelEdit() {  
				const value=uni.getStorageSync('login_user')
				this.list[1].value=value.nickname
				this.list[2].value=value.email
				this.list[3].value=value.address
			    this.isEditing = null; // 取消编辑  
			}
		}
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
	.check{
		  display: flex;
		  margin-top: 5px;
		  width: 100%;
		  margin-left: 10px;
	}
</style>
