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

// 游戏相关
const createGame = (gameRoomId, gameType) => {
  axios({
    method: 'post',
    url: serverInfo.value.serverList[0].ip + '/game/addgame',
    headers:{
      Authorization: loginInfo.value.JWT,
    },
    params:{
      id:gameRoomId,
      chatroom:roomInfo.value.currentRoomName,
      gameid:gameType
    }
  }).then(response => {
    if(response.data.code === 0){
      console.log('创建游戏成功', gameRoomId);
    } else{
      console.log('创建游戏失败', gameRoomId);
      alert(response.data.message);
    }
  }, error => {
    console.log('创建游戏失败');
    alert(error);
  });
};

const joinGame = (gameRoomId) => {
  axios({
    method: 'post',
    url: serverInfo.value.serverList[0].ip + '/game/joingame',
    headers:{
      Authorization: loginInfo.value.JWT,
    },
    params:{
      id:gameRoomId
    }
  }).then(response => {
    if(response.data.code === 0){
      console.log('加入游戏成功', gameRoomId);
    } else{
      console.log('加入游戏失败', gameRoomId);
      alert(response.data.message);
    }
  }, error => {
    console.log('加入游戏失败');
    alert(error);
  });
};


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
    if(response.data.code === 0){
      console.log('注册成功了', response);
      alert("注册成功");
    } else{
      console.log('注册出现了错误');
    }
  }, error => {
    console.log('注册出现了错误', response.data.message);
  })
}


const logIn = () => {     //登录
  axios({
    method: 'post',
    url: serverInfo.value.serverList[0].ip + '/user/login',
    data:{},
    params:{
      id:loginInfo.value.userId,
      password:loginInfo.value.userPasswd
    }
  }).then(response => {
    if(response.data.data != null){
      console.log('登录成功了');
      loginInfo.value.JWT = response.data.data;
      loginInfo.value.isLogIn = true;
      console.log('JWT: ' + loginInfo.value.JWT);
      alert("登录成功");
    } else{
      console.log('登录出现了错误');
    }
  }, error => {
    console.log('登录出现了错误');
    console.log(error);
  });
};

const randomChangeAvatar = () => {
  axios({
    method: 'post',
    url: serverInfo.value.serverList[0].ip + '/user/randomupdate',
    headers:{
      Authorization: loginInfo.value.JWT,
    }
  }).then(response => {
    if(response.data.code === 0){
      console.log('更换用户头像成功成功了');
    } else{
      console.log('更换用户头像成功错误');
    }
  }, error => {
    console.log('更换用户头像成功错误');
  });
};



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

const createInitRoom = (roomName, roomAvatar) => {
  return {
        roomName: roomName,
        roomAvatar: roomAvatar,
        isLocked: null,
        isIn:null,
        password: null,
        messages: [
          
        ]
      }
};

const roomInfo = inject('room-info');


const joinRoom = (roomName) => {
  axios({
      method: 'post',
      url: serverInfo.value.serverList[0].ip + '/room/roominfo',
      headers:{
        Authorization: loginInfo.value.JWT,
        roomname: roomName
      },
      params: {
        password: '88888888'
      }
    }).then(response => {
      // console.log("加入房间成功：", roomName)
    }, error => {
      // console.log("加入房间失败：", roomName,loginInfo.value.JWT )
    });
};

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
      if (response.data != null && response.data.data != null){
        // console.log(response.data);
        for(let i=0;i<response.data.data.length;i++){
          let currentRoom = response.data.data[i];
          let roomName = currentRoom.roomName;
          let pic = serverInfo.value.serverList[0].ip + '/' +currentRoom.roompic;
          joinRoom(roomName);
          if(roomInfo.value.roomList.find(room => room.roomName === roomName) === undefined){
            roomInfo.value.roomList.push(createInitRoom(roomName, pic));
            console.log('添加了一个房间');
            console.log(roomName);
          } else{
            let index = roomInfo.value.roomList.findIndex(room => room.roomName === roomName);
            roomInfo.value.roomList[index].roomAvatar = pic;
          }
        }
      }
    }, error => {
      console.log('拉取所有房间失败');
    });
  }
}

const randomUpdataRoomAvatar = (roomName) => {
  let index = roomInfo.value.roomList.findIndex(room => room.roomName === roomName);
  let oldPic = roomInfo.value.roomList[index].roomAvatar;
  axios({
      method: 'post',
      url: serverInfo.value.serverList[0].ip + '/room/randomupdate',
      headers:{
        Authorization: loginInfo.value.JWT,
        roomname: roomName
      }
    }).then(response => {
      if(response.data.code === 0){
        let index = roomInfo.value.roomList.findIndex(room => room.roomName === roomName);
        let newPic = roomInfo.value.roomList[index].roomAvatar;
        console.log("更改房间头像成功, roomName:", roomName, oldPic, newPic);
      } else{
        console.log("更改房间头像失败, roomName:", roomName);
      }

    }, error => {
      console.log("更改房间头像失败, roomName:", roomName);
    });
};

//消息相关
const sendMessage = (message) => {  //发送文本消息
  if(loginInfo.value.isLogIn){
    axios({
      method: 'post',
      url: serverInfo.value.serverList[0].ip + '/room/talk',
      params:{
        context: message,
      },
      headers:{
        Authorization: loginInfo.value.JWT,
        roomname: roomInfo.value.currentRoomName
      }
    }).then(response => {
      console.log('通过axios发送消息成功',message);
    }, error => {
      console.log('发送消息失败');
    });
  }
};

const createMessage = (senderID, type, senderName, content, avatar, time) => {
  return {
    senderID:senderID,
    type:type,
    senderFakeName: senderName,
    content: content,
    avatar: serverInfo.value.serverList[0].ip + '/' + avatar,
    time:time
  }
};

const createMessageList = (roomName, messageList) => {
  try{
    let result = [];
    for(let i=0;i<messageList.length;i++){
      let message = messageList[i];
      let id = message.senderid;
      let context = message.context;
      let senderName = message.sendername;
      let senderpic = message.senderpic;
      let time = message.time;
      let type = message.type;
      result.push(createMessage(id, type, senderName, context, senderpic, time));
    }
    let index = roomInfo.value.roomList.findIndex(room => room.roomName === roomName);
    roomInfo.value.roomList[index].messages = result;
  } catch (error){
    console.log('处理消息时出现错误', messageList);
  }
};

const getMessage = () => {
  if(loginInfo.value.isLogIn){
    for(let i=0;i<roomInfo.value.roomList.length;i++){
      let roomName = roomInfo.value.roomList[i].roomName;
      axios({
        method: 'get',
        url: serverInfo.value.serverList[0].ip + '/room/talk',
        headers:{
          Authorization: loginInfo.value.JWT,
          roomname: roomName
        }
      }).then(response => {
        createMessageList(roomName, response.data.data);
      }, error => {
        console.log('获取消息失败,roomname:', roomName);
      });    
    }
  };
};

const getUserHeadPhoto = () => {
  if(loginInfo.value.isLogIn){
    axios({
      method: 'get',
      url: serverInfo.value.serverList[0].ip + '/user/userinfo',
      headers:{
        Authorization: loginInfo.value.JWT,
      }
    }).then(response => {
      loginInfo.value.headPhoto = serverInfo.value.serverList[0].ip + '/' + response.data.data.userpic;
    }, error => {
      console.log('获取头像失败');
    });    
  };
};

const handleFileUpload = async (file) => {
    console.log('File name:', file.name);
    console.log('File size:', file.size);
    console.log('File type:', file.type);
    console.log('Last modified:', file.lastModified);

    const formData = new FormData();
    formData.append('file', file);

    try {
        const response = await axios.post(serverInfo.value.serverList[0].ip +'/room/talk/upload', formData, {
          headers:{
          Authorization: loginInfo.value.JWT,
          roomName: roomInfo.value.currentRoomName
        }
        });
        console.log('File uploaded successfully', response.data);
    } catch (error) {
        console.error('Error uploading file', error);
    }
};

let intervalIdRoom;
let intervalIdMessage;
let intervalIdUserpic;

onMounted(() => {
  // 设置定时任务，每秒更新一次时间
  intervalIdRoom = setInterval(getRoomInfos, 1000);
  intervalIdMessage = setInterval(getMessage, 1000);
  intervalIdUserpic = setInterval(getUserHeadPhoto, 1000);
});

onBeforeUnmount(() => {
  // 清除定时任务
  clearInterval(intervalIdRoom);
  clearInterval(intervalIdMessage);
  clearInterval(intervalIdUserpic);
});

defineExpose({
  logIn,
  register,
  logOut,
  searchServer,
  createRoom,
  sendMessage,
  handleFileUpload,
  randomChangeAvatar,
  randomUpdataRoomAvatar,
  joinGame,
  createGame
})
</script>