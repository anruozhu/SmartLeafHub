<template>
  <div class="message-list">
    <div class="title">
      <text>日志</text>
    </div>
    <div class="messages">
      <div v-for="(list, i) in lists" :key="i" class="message">
        <div class="message-content">
          <h4>{{list.tit1}}</h4>
          <p>{{list.tit2}}</p>
          <p class="time">{{list.time}}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      title: 'Hello',
      lists: [
        {
          "tit1": "服务通知",
          "tit2": "恭喜你参加的拼团已组成成功了，请您等待卖家发货",
          "time": "08:48"
        },
        {
          "tit1": "优惠促销",
          "tit2": "恭喜你参加的拼团已组成成功了，请您等待卖家发货",
          "time": "08:48"
        },
        {
          "tit1": "账户通知",
          "tit2": "恭喜你参加的拼团已组成成功了，请您等待卖家发货",
          "time": "昨天"
        },
        {
          "tit1": "互动问答",
          "tit2": "恭喜你参加的拼团已组成成功了，请您等待卖家发货",
          "time": "星期六"
        },
        {
          "tit1": "物流助手",
          "tit2": "恭喜你参加的拼团已组成成功了，请您等待卖家发货",
          "time": "星期六"
        },
      ],
    }
  },
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
}

.message-list {
  max-width: 600px;
  margin: 0 auto;
}

.title {
  margin-bottom: 20px; /* 增加标题和消息之间的间距 */
  margin-top: 10px;
  font-weight: 600;
  font-size: 50rpx;
  color: #011108;
  margin-left:10px ;
}

.messages {
  display: grid;
  gap: 20px;
}

.message {
  border-radius: 10px;
  border: 1px solid #ddd;
  background-color: #fff;
  padding: 15px;
}

.message-content {
  text-align: left;
}

.time {
  font-size: 0.8em;
  color: #888;
  margin-top: 5px;
}
</style>
