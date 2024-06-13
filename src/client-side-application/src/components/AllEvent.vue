<!-- src/components/AllEvent.vue -->
<template>
  
</template>

<script setup>
import {inject} from 'vue';
import axios from 'axios';

// 服务器相关
const serverInfo = inject('server-info');
const searchServer = () => {

}

// 登录相关
const loginInfo = inject('login-info');
const register = () => {     //注册
  axios.post(serverInfo.value.serverList[0].ip + '/user/login'
  + '?id=' + loginInfo.value.userId + '&fakename='+ loginInfo.value.fakeName + '&password=' + loginInfo.value.userPasswd
  ).then(response => {
    console.log('登录成功了');
    loginInfo.value.JWT = response.data.data;
    loginInfo.value.isLogIn = true;
    console.log('JWT: ' + loginInfo.value.JWT);
  }, error => {
    console.log('登录出现了错误');
    console.log(error);
  })
}
const logIn = () => {     //登录
  axios.post(serverInfo.value.serverList[0].ip + '/user/login'
  + '?id=' + loginInfo.value.userId + '&password=' + loginInfo.value.userPasswd
  ).then(response => {
    console.log('登录成功了');
    loginInfo.value.JWT = response.data.data;
    loginInfo.value.isLogIn = true;
    console.log('JWT: ' + loginInfo.value.JWT);
  }, error => {
    console.log('登录出现了错误');
    console.log(error);
  })
}

const logOut = () => {
  loginInfo.value.isLogIn = false;
}


// 房间相关
const createRoom = (name, numUser, isIn) => {
  return {
    roomName:name,
    numAliveUser:numUser,
    isIn:isIn
  };
}


//消息相关



defineExpose({
  logIn,
  logOut,
  searchServer
})
</script>