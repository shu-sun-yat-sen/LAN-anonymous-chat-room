<template>
    <div :style="dynamicStyle" @click="handleClick" class="room-window">
      <img :src="roomAvatar" alt="Avatar" class="avatar" />
      <div class="info">
        <p class="room-name">{{ truncateString(roomname, 11) }}</p>
        <p class="newest-message">{{ truncateString(newestMessage, 9) }}</p>
      </div> 
    </div>
</template>


<script setup>
    import { computed, inject} from 'vue';

    const props = defineProps({
        roomname: {
            type: String,
            required: true
        },
        newestMessage: {
            type: String,
            required: true
        },
        roomAvatar: {
            type: String,
            required: true
        }
    });

    const truncateString = (str, len) => {
        if (str.length > len) {
            return str.slice(0, len) + '...';
        }
        return str;
    };


    const roomInfo = inject("room-info");


    const handleClick = () => {
        roomInfo.value.currentRoomName = props.roomname;
    }

    const dynamicStyle = computed(() => (
         {
            display: 'flex',
            alignItems: 'fex-start',
            left : 'inherit',
            right: 'inherit',
            height: '15%',
            borderBottom: '0.5px solid rgb(83, 76, 76)',
            backgroundColor: roomInfo.value.currentRoomName === props.roomname ? 'rgb(187, 186, 186)' : '#f5f5f5',
        }
    ));
</script>


<style scoped>

.room-window{
    display: flex;
    align-items: flex-start;
    left:inherit;
    right: inherit;
    height: 15%;
    border-bottom: 0.5px solid rgb(83, 76, 76);
    /* background-color: rgb(187, 186, 186); */
    align-items: center;
}

.avatar{
    height: 60%;
    margin-left: 5%;
    margin-top: 6%;
}

.info {
  display: flex;
  flex-direction: column;
  flex: 1; /* Allows the info to take remaining space */
}

.room-name {
    margin: 0;
    font-size: 1em;
    font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
    color: black;
}

.newest-message{
    font-size: 1em;
    margin: 0;
    color: hwb(180 37% 61%);
}

</style>

