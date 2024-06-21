<!-- src/components/TopBar.vue -->
<template>
  <div idd="top-bar">
    <div id="user-bar">
      <!-- <img :src="avatar" alt="Avatar" id="avatar" /> -->
       <el-avatar :src="avatar" alt="Avatar" id="avatar" shape="circle" fit="cover" @dblclick="randomChangeAvatar"></el-avatar>
      <div>
        <el-button type="primary" round @click.prevent="handleLoginClick" id="log-button">{{ buttonLabel }}</el-button>
        <el-button type="primary" circle id="create-room-button" @click.prevent="handleAddClick">+</el-button>
      </div>
      <!-- <div id="user-name">用户名:{{ userId }}</div> -->
    </div>
    <div id="room-name-bar">
      <!-- <p id="room-name">{{ currentRoomName }}</p> -->
      <p id="title">🏡{{ currentRoomName }}</p>
    </div>

    <LoginDialog :visible="showLoginDialog" @login="handleLogin" @close="handleclose" @register="handleRegister"/>
    <CreateRoomDialog :visible="showCreateDialog" @close="handlecloseCreateRoom" @createroom="handleCreate" />
  </div>
</template>


<script setup>
import { inject, computed, ref} from 'vue';
import LoginDialog from './TopBarComponents/LoginDialog.vue';
import CreateRoomDialog from './TopBarComponents/CreateRoomDialog.vue';

const emit = defineEmits(['log-in', 'log-out', 'createroom', 'random-change-user-avatar']);

const avatar = computed(
  () => {
    return loginInfo.value.headPhoto ? loginInfo.value.headPhoto : 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png';
  }
);

const loginInfo = inject('login-info');
const roomInfo = inject('room-info');
const currentRoomName = computed(() => {
  return roomInfo.value.currentRoomName ? roomInfo.value.currentRoomName : '';
});
const buttonLabel = computed(() => {
  return loginInfo.value.isLogIn ? 'logout' : 'login';
});

const showLoginDialog = ref(false);
const showCreateDialog = ref(false);

const handleLoginClick = () => {
  // Logic for handling login click can be added here
  if (loginInfo.value.isLogIn) {
    emit('log-out');
  }
  else {
    showLoginDialog.value = true;
  }
};

const handleAddClick = () => {
  showCreateDialog.value = true;
};

const handleLogin = () => {
  emit('log-in');
  showLoginDialog.value = false;
};

const handleRegister = () => {
  emit('register');
  showLoginDialog.value = false;
};

const handleCreate = (roomName) => {
  emit('createroom', roomName);
  showCreateDialog.value = false;
};

const handleclose = () => {
  showLoginDialog.value = false;
};

const handlecloseCreateRoom = () => {
  showCreateDialog.value = false;
};

const randomChangeAvatar = () => {
  emit('random-change-user-avatar');
};

</script>

<script lang="ts" setup>

</script>

<style scoped>
#user-bar {
  position: fixed;
  top: 0%;
  left: 0%;
  right: 75%;
  bottom: 90%;
  /* 设置顶部栏的高度 */
  background-color: hsl(0, 2%, 20%);
  color: #000000;
  /*字体颜色*/
  z-index: 1000;
  /* 确保顶部栏在其他元素之上 */
}

#room-name-bar {
  position: fixed;
  top: 0%;
  left: 25%;
  right: 0%;
  bottom: 90%;
  border-bottom: 1px solid hsl(0, 3%, 47%);
  background-color: #544c4c;
}

#room-name {
  text-align: center;
  color: #000000;
  font-family: 'Arial';
  font-size: 1.3vw;
}

#avatar {
  position: fixed;
  left: 1%;
  top: 2%;
  /* bottom: 1%; */
  /* height: 8%; */
}

#title {
  position: relative;
  top: 10%;
  bottom: 10%;

  /* display: block; */
  margin: auto;
  width: 40%;
  padding: 10px;
  font-family: 'Arial';
  border-radius: 15px;
  /* 圆角 */
  background-color: #409EFF;
  /* 背景颜色，可根据需要调整 */
  font-weight: bold;
  /* 字体加粗 */
  font-size: 1.8vw;
  /* 字体大小，可根据需要调整 */
  text-align: center;
  /* 文字居中 */
  /* box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); 阴影，可选 */
  color: #f0f0f0;
  /* 文字颜色，可根据需要调整 */
}

#log-button {
  position: fixed;
  /* background-color: #fffec4; */
  left: 8%;
  top: 1.5%;
  height: 7%;
  width: 7%;
  font-size: 1.2vw;
  /* border-color: #fffec4; */
}

#create-room-button {
  position: fixed;
  /* background-color: #fffec4; */
  left: 20.5%;
  top: 2.5%;
  right: 0%;
  
  font-size: 1.2vw;
  width: 2.5vw;
  height: 2.5vw;
  /* border-color: #fffec4; */
}
</style>