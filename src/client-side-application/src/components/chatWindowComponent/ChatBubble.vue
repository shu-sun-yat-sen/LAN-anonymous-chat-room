<template>
  <div class="chat-bubble">
    <el-avatar :src="avatar" alt="Avatar" class="avatar" shape="circle" fit="cover" :size="40"></el-avatar>
    <div class="content-container">
      <div class="username">{{ username }}</div>
      <div class="chat-left_triangle"></div>
      <!-- 向上强制移动 -->
      <div class="content">{{ text }}</div>
    </div>
  </div>

</template>

<script>
export default {
  props: {
    text: {
      type: String,
      required: true
    },
    avatar: {
      type: String,
      required: true
    },
    username: {
      type: String,
      required: true
    }
  }
};
</script>

<style scoped>
.chat-bubble {
  display: flex;
  align-items: flex-start;
  /* margin-bottom: 10px; */
  /* padding: 10px; */
}

.avatar {
  /* border-radius: 50%; */
  margin-right: 1%;
}

.content-container {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  /* 确保左对齐 */
  max-width: calc(100% - 50px);
  /* Adjusts width to fit within parent, considering avatar width and margin */
}

.username {
  font-weight: bold;
  margin-bottom: 5px;
  white-space: nowrap;
}

.content {
  background-color: #409EFF;
  padding: 10px;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  /* max-width: 100%; */
  word-wrap: break-word;
  width: fit-content;
  /* height: fit-content; */
  text-align: left;
  max-width: 90%;
  color: #f0f0f0;
  
  transform: translate(0, -10px);
}


/* 发送者 */
.chat-left_triangle {
  height: 5%;
  width: 5%;
  border-width: 6px;
  border-style: solid;
  border-color: transparent #409EFF transparent transparent;
  /* position: relative;
  left: -22px;
  top: 3px; */

  transform: translate(-98%, 100%);
  z-index: 1000;
}

.chat-right_triangle {
  height: 0px;
  width: 0px;
  border-width: 6px;
  border-style: solid;
  border-color: transparent transparent transparent #b2e281;
  position: relative;
  right: -22px;
  top: 3px;
}
</style>