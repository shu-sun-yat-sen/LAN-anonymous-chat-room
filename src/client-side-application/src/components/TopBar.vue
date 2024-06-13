<!-- src/components/TopBar.vue -->
<template>
    <div id="user-bar">
        <img :src="avatar" alt="Avatar" id="avatar" />
        <button @click="handleLoginClick" id="log-button">{{ buttonLabel }}</button>
        <!-- <div id="user-name">用户名:{{ userId }}</div> -->
    </div>
    <div id="room-name-bar">
      <p id="room-name">{{ currentRoomName }}</p>
    </div>
  </template>
  

  <script setup>
  import{ inject,computed } from 'vue';
  const emit =  defineEmits(['log-in', 'log-out']);

  const avatar = computed(
    () => {
      return loginInfo.value.headPhoto ? loginInfo.value.headPhoto : 'https://via.placeholder.com/40';
    }
  );

  const loginInfo = inject('login-info');
  const roomInfo = inject('room-info');
  const currentRoomName = computed(() => {
    return roomInfo.value.currentRoomName ? roomInfo.value.currentRoomName : '';
  });
  const buttonLabel = computed(() => {
    return loginInfo.value.isLogIn ? '登出' : '登录';
});



  const handleLoginClick = () => {
  // Logic for handling login click can be added here
    if(loginInfo.value.isLogIn){
      emit("log-out");
    }
    else {
      emit("log-in");
    }
  };
  </script>
  
  <style scoped>
  #user-bar {
    position: fixed;
    top: 0%;
    left: 0%;
    right: 75%;
    bottom: 90%; /* 设置顶部栏的高度 */
    background-color: hsl(0, 2%, 20%);
    color: #000000;  /*字体颜色*/
    z-index: 1000; /* 确保顶部栏在其他元素之上 */
  }

  #room-name-bar {
    position: fixed;
    top: 0%;
    left: 15%;
    right:0%;
    bottom: 90%;
    border-bottom: 1px solid hsl(0, 3%, 47%);
    background-color: #f5f5f5;
  }

  #room-name {
    text-align:center;
    color:#000000;
  }

  #avatar {
    position: fixed;
    left: 1%;
    top: 1%;
    height: 8%;
  }

  #log-button {
    position: fixed;
    background-color: #fffec4;
    left:12%;
    top:1.5%;
    height: 7%;
    width: 10%;
    border-color: #fffec4;
  }

  </style>