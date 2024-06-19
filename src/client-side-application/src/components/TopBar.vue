<!-- src/components/TopBar.vue -->
  <template>
    <div idd="top-bar">
      <div id="user-bar">
          <img :src="avatar" alt="Avatar" id="avatar" />
          <button @click="handleLoginClick" id="log-button">{{ buttonLabel }}</button>
          <button id="create-room-button" @click="handleAddClick">+</button>
          <!-- <div id="user-name">用户名:{{ userId }}</div> -->
      </div>
      <div id="room-name-bar">
        <p id="room-name">{{ currentRoomName }}</p>
      </div>

      <LoginDialog
      :visible="showLoginDialog"
      @login="handleLogin"
      @close="handleclose"
      />
      <CreateRoomDialog
      :visible="showCreateDialog"
      @close="handlecloseCreateRoom"
      />
    </div>
  </template>
  

  <script setup>
  import{ inject, computed, ref} from 'vue';
  import LoginDialog from './TopBarComponents/LoginDialog.vue';
  import CreateRoomDialog from './TopBarComponents/CreateRoomDialog.vue';

  const emit =  defineEmits(['log-in', 'log-out', "createroom"]);

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

  const showLoginDialog = ref(false);
  const showCreateDialog = ref(false);

  const handleLoginClick = () => {
  // Logic for handling login click can be added here
    if(loginInfo.value.isLogIn){
      emit("log-out");
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
    left:8%;
    top:1.5%;
    height: 7%;
    width: 10%;
    border-color: #fffec4;
  }
  
  #create-room-button{
    position: fixed;
    background-color: #fffec4;
    left:20%;
    top:1.5%;
    height: 6%;
    width: 6%;
    border-color: #fffec4;
  }
  </style>