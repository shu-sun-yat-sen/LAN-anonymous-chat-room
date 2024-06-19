<!-- src/App.vue -->
<template>
  <div id="app">
    <header>
      <TopBar @log-in="logIn" @log-out="logOut" @createroom="createRoom" id="top-bar"/>
    </header>
    <main>
      <SideBar />
      <ChatWindow />
      <InputWindow />
    </main>
    <functions>
      <AllEvent ref="allEvent"/>
    </functions>
  </div>
</template>


<script setup>
import TopBar from './components/TopBar.vue'
import SideBar from './components/SideBar.vue'
import ChatWindow from './components/ChatWindow.vue'
import InputWindow from './components/InputWindow.vue'
import AllEvent from './components/AllEvent.vue'

import {ref,computed, provide} from 'vue';
const sideBarText = ref('这里是侧边栏');// 示例
const inputWindowText = ref('这里是输入窗口');// 示例
const chatWindowText = ref('这里是显示消息窗口');// 示例
provide('side_bar_text', sideBarText); // 示例
provide('input_window_text', inputWindowText); // 示例
provide('chat_window_text', chatWindowText); // 示例

// 数据创建函数

const loginInfo = ref(
  {
  isLogIn: false,         //登录状态
  userId: '13738414202',       //用户账户
  userPasswd: 'sdsdsdf33',      //密码
  fakeName: 'sss', //当前假名
  headPhoto: 'https://via.placeholder.com/40',       //头像
  JWT: null    //身份验证
  }
);

const roomInfo = ref(
  { 
    currentRoomName: "sampleRoom1",
    roomList: [
      {
        roomName: "sampleRoom1",
        roomAvatar: 'https://via.placeholder.com/40',
        isLocked: false,
        isIn:true,
        password: "123456",
        messages: [
          {
            senderFakeName: "fakeSender",
            headPhoto: null,
            content: "hello1!",
            avatar: 'https://via.placeholder.com/40',
            time: "fakeTime"
          }
        ]
      },
      {
        roomName: "sampleRoom2",
        roomAvatar: 'https://via.placeholder.com/40',
        isLocked: false,
        isIn:true,
        password: "123456",
        messages: [
          {
            senderFakeName: "fakeSender",
            headPhoto: null,
            content: "hello2!",
            avatar: 'https://via.placeholder.com/40',
            time: "fakeTime"
          }
        ]
      }
    ]
  }
);

const serverInfo = ref(
  {
    serverList:[
      {
        ip: "http://127.0.0.1:8080",
      }
    ]
  }
)

// 消息类型
const messageinfo = computed(() => { 
  const room = roomInfo.value.roomList.find(room => room.roomName === roomInfo.value.currentRoomName);
  return room ? room.messages : [];
});

provide('room-info', roomInfo);
provide('login-info', loginInfo);
provide('server-info', serverInfo);
provide('message-info', messageinfo);

const allEvent = ref(null);
const logIn = () => {
  if (allEvent.value) {
    allEvent.value.logIn();
  }
};
const logOut = () => {
  if (allEvent.value) {
    allEvent.value.logOut();
  }
};
const createRoom = (roomName) => {
  allEvent.value.createRoom(roomName);
}
</script>

<style>

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color:black;
  margin-top: 60px;
}
</style>
