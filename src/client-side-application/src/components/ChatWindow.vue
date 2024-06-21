<template>
    <div class="chat-window">
      <div ref="scrollBox" @mousewheel="handleScroll" class="scroll-box">
        <ChatBubble
          v-for="(message, index) in items"
          :key="index"
          :text="message.content" 
          :avatar="message.avatar"
          :username="message.senderFakeName"
          :same="isUser(message)"
        />
      </div>
    </div>
</template>
  
<script>
import { computed, inject, onMounted, ref } from 'vue';
import ChatBubble from './chatWindowComponent/ChatBubble.vue';

export default {
  components: {
    ChatBubble
  },
  setup() {
    const chat_window_text = inject('chat_window_text');
    const messageInfo = inject('message-info');
    const loginInfo = inject('login-info');

    const items = computed(
      () => {
        return messageInfo.value;
      }
    );
    
    // 是否用户发出
    const isUser = (curMessage) => {
      // console.log(loginInfo.value.userId,' ', curMessage.senderID);
      return loginInfo.value.userId == curMessage.senderID;
    };

    // 创建一个对 scrollBox 的引用
    const scrollBox = ref(null);

    const handleScroll = (e) => {
      // 使用 scrollBox.value 访问 DOM 元素
      if (scrollBox.value) {
        // deltaY 属性在向下滚动时返回正值，向上滚动时返回负值，否则为0
        scrollBox.value.scrollLeft += e.deltaY;
      }
    };
    
    // 在组件挂载时将滚动条设置到最底部
    onMounted(() => {
      if (scrollBox.value) {
        scrollBox.value.scrollTop = scrollBox.value.scrollHeight;
      }
    });

    return {
      chat_window_text,
      items,
      scrollBox, // 需要返回 scrollBox 引用，使其在模板中可用
      handleScroll,
      isUser
    };
  }
};
</script>

<style scoped>
.chat-window {
  position: fixed;
  top: 10%;
  left: 25%;
  right: 0%;
  bottom: 20%;
  background-color: #f5f5f5;
  padding: 20px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  height: calc(100% - 30%);
  max-height: 80%;
}

.messages {
  flex: 1;
  overflow-y: auto;
  padding: 10px;
  background-color: white;
  border-radius: 10px;
  box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.1);
}

.scroll-box {
  top: 10%;
  width: 100%;
  max-height: 90%;
  overflow-y: scroll;
  padding: 0px;
  background-color: transparent;
}

/* 自定义滚动条样式 */
.scroll-box::-webkit-scrollbar {
  width: 12px;
}

.scroll-box::-webkit-scrollbar-track {
  background: #f1f1f1;
  border-radius: 10px;
}

.scroll-box::-webkit-scrollbar-thumb {
  background: #888;
  border-radius: 10px;
}

.scroll-box::-webkit-scrollbar-thumb:hover {
  background: #555;
}
</style>