<template>
  <div :class="{'chat-bubble': !same,'chat-bubble-reverse': same}">
    <el-avatar :src="avatar" alt="Avatar" class="avatar" shape="circle" fit="cover" :size="40"></el-avatar>
    <div :class="{'c1': !same,'c1-reverse': same}">
      <div class="username">{{ username }}</div>
      <div :class="{'chat-right_triangle': same,'chat-left_triangle': !same}"></div>
      <div :class="{'content-container':!same,'content-container-reverse':same}">
        <!-- 向上强制移动 -->
        <div :class="{'myContent': same, 'content': !same}">
          <!-- 根据 type 的取值,条件渲染 -->
          <div v-if="type==='text'">{{ content }}</div>
          <div v-else-if="type==='img'">
            <el-image class="limited-size-image" :src="content"/>
          </div>
          <div v-else-if="type==='doc'" class="doc-type-layout">
            <el-avatar :src="fileicon" 
                        alt="Avatar" class="avatar" 
                        shape="square" 
                        fit="cover" 
                        size="40" />
            <downloadlink :fileUrl="content" linkText=""/>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
import Downloadlink from './Downloadlink.vue';
export default {
  components: { Downloadlink },
  props: {
    content: {
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
    },
    same: {
      type: Boolean,
      required: true
    },
    type: {
      type: String,
      required: true
    },
  },
  computed: {
    fileicon(){
      return this.same? require('@/assets/file_green.png') : require('@/assets/file_blue.png')
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
  max-width: 100%;
}

.chat-bubble-reverse{
  display: flex;
  align-items: flex-start;
  flex-direction: row-reverse;
  max-width: 100%;
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
  max-width: 100%;
  /* Adjusts width to fit within parent, considering avatar width and margin */
}


.content-container-reverse {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  
  /* 确保左对齐 */
  max-width: 100%;
  /* Adjusts width to fit within parent, considering avatar width and margin */
}

.c1 {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  /* 确保左对齐 */
  max-width: calc(100% - 50px);
}

.c1-reverse {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  /* 确保左对齐 */
  max-width: calc(100% - 50px);
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

.limited-size-image {
  max-width: 300px;
  max-height: 200px;
  width: auto;
  height: auto;
}

/* 发送者 */
.chat-left_triangle {
  height: 5%;
  width: 5%;
  border-width: 8px;
  border-style: solid;
  border-color: transparent #409EFF transparent transparent;
  /* position: relative;
  left: -22px;
  top: 3px; */

  transform: translate(-100%, 100%);
  z-index: 10;
}

/* 客户 */
.chat-right_triangle {
  height: 5%;
  width: 5%;
  border-width: 8px;
  border-style: solid;
  border-color: transparent transparent transparent #67C23A;
  /* position: relative;
  left: -22px;
  top: 3px; */

  transform: translate(100%, 100%);
  z-index: 10;
}

.myContent {
  background-color: #67C23A;
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

.doc-type-layout {
  display: flex;
  justify-content: flex-start; /* 从左往右排列 */
  align-items: center; /* 在高度方向居中对齐 */
  gap: 10px;
}
</style>