<!-- src/components/SiderBar.vue -->
<template>
    <div class="side-bar">
      <!-- <p>{{ side_bar_text }}</p> -->
      <RoomWindow
          v-for="(room, index) in roomList"
          :key="index"
          :roomname="room.roomName"
          :newestMessage="newestMessage(room.messages)"
          :roomAvatar="room.roomAvatar"
          @random-update-room-avatar = "randomUpdateRoomAvatar"
        />
    </div>
</template>
  
<script setup>
    import{ computed, inject} from 'vue';
    import RoomWindow from './SideBarComponent/RoomWindow.vue';

    const emit = defineEmits(['random-update-room-avatar']);
    const roomInfo = inject('room-info');
    const roomList = computed(() => roomInfo.value.roomList);
    const newestMessage = (messages) => {
        if(messages.length > 0){
            return messages[messages.length-1].content;
        }
        return "";
    };

    const randomUpdateRoomAvatar = (roomName) =>{
        emit('random-update-room-avatar', roomName);
    }

</script>

<style scoped>
.side-bar {
    position: fixed;
    top: 10%;
    bottom: 0%;
    left: 0%;
    right: 75%; /* 侧边栏宽度 */
    background-color: #f5f5f5;
    overflow-y: scroll;
    border-left: 2px solid rgb(83, 76, 76);
    border-bottom: 2px solid rgb(83, 76, 76);
    }
</style>