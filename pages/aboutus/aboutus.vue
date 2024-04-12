<template>
	<view>
		<!-- 图标 --> 
		<view class="up">
			<image src='/static/logo.png' class='logo'></image>
		</view>
		<!-- 内容 -->
		<view class='info'>
			<view class="item">
				<text class='zixun_biaoti'>软件名称</text>
				<text class='zixun_content'>xxxx</text>
			</view>
			<view class="item">
				<text class='zixun_biaoti'>版本信息</text>
				<text class='zixun_content'>xxxx</text>
			</view>
		</view>
	</view>
</template>

<script>
</script>

<style>
	 /* page{ */
		/* 设置背景图片 */  
		/* background-image: url('../../static/background-image.jpg');  */
		/* 背景图片覆盖整个容器 */  
		/* background-size: cover;  */
		/* 根据需要设置背景位置 */  
		/* background-position: center; */
	/* } */
	.up {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}
	.logo {
		height: 200rpx;
		width: 200rpx;
		margin-top: 200rpx;
		margin-left: auto;
		margin-right: auto;
		margin-bottom: 50rpx;
	}
	.info {
		display: flex;
		flex-direction: column;
		align-items: center;
		margin-top: 35px;
	}
	.item {
		display: flex;
		justify-content: space-between;
		width: 90%; /* 控制内容宽度 */
		margin-top: 15px;
		background-color: white;
		/* opacity: 0.8; */
		border-radius: 10px;
	}
	.zixun_biaoti {
		padding: 10px;/*内边距*/
		padding-right: 20px; /* 控制标题右边距离 */
		font-weight: 550;
		font-size: 15px;
	}
	.zixun_content {
		padding: 10px;/*内边距*/
		color: #D0D0D0;
		font-weight: 550;
		font-size: 15px;
	}
</style>
