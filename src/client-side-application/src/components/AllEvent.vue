<!-- src/components/AllEvent.vue -->
<template>
  
</template>

<script setup>
import {inject, onMounted, onBeforeUnmount} from 'vue';
import axios from 'axios';

// 服务器相关
const serverInfo = inject('server-info');
const searchServer = () => {

}

// 登录相关
const loginInfo = inject('login-info');

const register = () => {     //注册
  axios({
    method: 'post',
    url: serverInfo.value.serverList[0].ip + '/user/register',
    data:{},
    params:{
      id:loginInfo.value.userId,
      fakename:loginInfo.value.fakeName,
      password:loginInfo.value.userPasswd
    }
  }).then(response => {
    console.log('注册成功了');
  }, error => {
    console.log('注册出现了错误');
    console.log(error);
  })
}


const logIn = () => {     //登录
  register();
  axios({
    method: 'post',
    url: serverInfo.value.serverList[0].ip + '/user/login',
    data:{},
    params:{
      id:loginInfo.value.userId,
      password:loginInfo.value.userPasswd
    }
  }).then(response => {
    console.log('登录成功了');
    loginInfo.value.JWT = response.data.data;
    loginInfo.value.isLogIn = true;
    console.log('JWT: ' + loginInfo.value.JWT);
  }, error => {
    console.log('登录出现了错误');
    console.log(error);
  });
}

const logOut = () => {
  loginInfo.value.isLogIn = false;
}


// 房间相关
const createRoom = (roomName) => { //创建房间
  axios({
    method: 'post',
    url: serverInfo.value.serverList[0].ip + '/room',
    headers:{
      Authorization: loginInfo.value.JWT,
    },
    params:{
      roomName:roomName
    }
  }).then(response => {
    console.log('创建房间成功');
    console.log(roomName);
  }, error => {
    console.log('创建失败');
    console.log(error);
  });
};

const createInitRoom = (roomName) => {
  return {
        roomName: roomName,
        roomAvatar: 'https://via.placeholder.com/40',
        isLocked: null,
        isIn:null,
        password: null,
        messages: [
          
        ]
      }
};

const roomInfo = inject('room-info');


const getRoomInfos = () => {
  // console.log('开始拉取房间');
  if(loginInfo.value.isLogIn){
    axios({
      method: 'get',
      url: serverInfo.value.serverList[0].ip + '/room',
      headers:{
        Authorization: loginInfo.value.JWT,
      }
    }).then(response => {
      // console.log('拉取所有房间成功');
      // console.log(response.data.data);
      for(let i=0;i<response.data.data.length;i++){
        let roomName = response.data.data[i];
        if(roomInfo.value.roomList.find(room => room.roomName === roomName) === undefined){
          roomInfo.value.roomList.push(createInitRoom(roomName));
          console.log('添加了一个房间');
          console.log(roomName);
        }
      }
    }, error => {
      console.log('拉取所有房间失败');
      // console.log(error);
    });
  }
}

//消息相关
const sendMessage = (message) => {

}

let intervalId;

onMounted(() => {
  // 设置定时任务，每秒更新一次时间
  intervalId = setInterval(getRoomInfos, 1000);
});

onBeforeUnmount(() => {
  // 清除定时任务
  clearInterval(intervalId);
});

defineExpose({
  logIn,
  logOut,
  searchServer,
  createRoom
})
</script>