<!-- src/App.vue -->
<template>
  <router-view />
  <div id="app" v-if="showExtraContent">
    <header>
      <TopBar @log-in="logIn" @register="handleRegister" @log-out="logOut" @createroom="createRoom" @random-change-user-avatar="randomChangeAvatar" id="top-bar"/>
    </header>
    <main>
      <SideBar @random-update-room-avatar="randomUpdataRoomAvatar"/>
      <ChatWindow />
      <InputWindow @sendtext = "handleSendText" @send-file = "handleSendFile" @create-game="handleCreateGame" @join-game="handleJoinGame"/>
    </main>
    <functions>
      <AllEvent ref="allEvent"/>
    </functions>
  </div>
</template>


<script setup>
import TopBar from './components/TopBar.vue';
import SideBar from './components/SideBar.vue';
import ChatWindow from './components/ChatWindow.vue';
import InputWindow from './components/InputWindow.vue';
import AllEvent from './components/AllEvent.vue';
import { useRoute } from 'vue-router';

import {ref,computed, provide} from 'vue';
const sideBarText = ref('这里是侧边栏');// 示例
const inputWindowText = ref('这里是输入窗口');// 示例
const chatWindowText = ref('这里是显示消息窗口');// 示例
provide('side_bar_text', sideBarText); // 示例
provide('input_window_text', inputWindowText); // 示例
provide('chat_window_text', chatWindowText); // 示例

// 下棋用
// 获取当前路由
const route = useRoute();
// 根据路由的元字段设置 showExtraContent
const showExtraContent = ref(route.meta.showExtraContent);


// 数据创建函数

const loginInfo = ref(
  {
  isLogIn: false,         //登录状态
  userId: '137',       //用户账户
  userPasswd: 'sdsdsdf33',      //密码
  fakeName: 'sss', //当前假名
  headPhoto: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',       //头像
  JWT: null    //身份验证
  }
);

const roomInfo = ref(
  { 
    currentRoomName: "sampleRoom1",
    roomList: [
      {
        roomName: "sampleRoom1",
        roomAvatar: 'https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png',
        isLocked: false,
        isIn:true,
        password: "123456",
        messages: [
          {
            senderID: '137',
            type:"text",
            senderFakeName: "fakeSender",
            content: "hello1!aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
            avatar: 'https://via.placeholder.com/40',
            time: "fakeTime"
          }
        ]
      },
      {
        roomName: "sampleRoom2",
        roomAvatar: 'https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png',
        isLocked: false,
        isIn:true,
        password: "123456",
        messages: [
          {
            senderID: '137',
            type:"text",
            senderFakeName: "fakeSender",
            content: "hello2!aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
            avatar: 'https://via.placeholder.com/40',
            time: "fakeTime"
          },
          {
            senderID: '138',
            type:"text",
            senderFakeName: "fakeSender",
            content: "hello2!aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
            avatar: 'https://via.placeholder.com/40',
            time: "fakeTime"
          },
          {
            senderID: '138',
            type:"text",
            senderFakeName: "fakeSender",
            content: "hello2!aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
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
);

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

const handleRegister = () => {
  if (allEvent.value) {
    allEvent.value.register();
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

const handleSendText = (message) => {
  allEvent.value.sendMessage(message);
};

const handleSendFile = (file) => {
  allEvent.value.handleFileUpload(file);
};

const randomChangeAvatar = () => {
  allEvent.value.randomChangeAvatar();
};

const randomUpdataRoomAvatar = (roomName) => {
  allEvent.value.randomUpdataRoomAvatar(roomName);
};

const handleCreateGame = (gameRoomID, gameType) => {
  allEvent.value.createGame(gameRoomID, gameType);
};

const handleJoinGame = (gameRoomID) => {
  allEvent.value.joinGame(gameRoomID);
};

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
