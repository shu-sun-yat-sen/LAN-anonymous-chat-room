<template>
    <div>
        <!-- <p>{{ myUserId }}</p> -->
        <!-- <router-link :target="'_blank'" :to="`/ChessGame/${myUserId}`"> -->
        <router-link target="_blank" :to="{ name: 'ChessGame' }">
            <button @click="playChess" class="button">♟️</button>
            <div class="chessAcceptWindow" v-if="showChessAcceptWindow">
                user "{{ curUser.fakeName }}" invite you to play chess. 
                <el-button type="danger" round @click="closeWindow">acccept</el-button>
            </div>
        </router-link>
    </div>
</template>

<script setup>
import { inject, computed} from 'vue';

const loginInfo = inject('login-info');

// 待加入不登录不开启棋局设置
const curUser = computed(() => {
    // console.log(loginInfo.value.userId);
    return loginInfo.value ? loginInfo.value : '';
});

</script>

<script>

export default {
    data() {
        return {
            showChessAcceptWindow: false,
        };
    },
    methods: {
        playChess() {
            this.$emit('start-chess', 'chess');

            // 这里要加入一个判断，如果对方不在线，不显示;如果发起邀请的用户的ID和当前用户ID相同，不显示
            // 只有两个用户ID不同时候才显示
            if(1 == 1){
                this.showChessAcceptWindow = true; 
            }

            this.broadcastRequest();
        },
        broadcastRequest(){
            // 棋局已经开始，广播该消息到同一聊天室其他所有玩家聊天框中
            // 具体来说就是将他们的showChessAcceptWindow设置为true，邀请他们进来

            // 这里应该要告诉后端有哪些用户进入了棋局
            // 如果检测到房间内人数=2，那么将showChessAcceptWindow设置为false，同时开始游戏
        },
        closeWindow(){
            this.showChessAcceptWindow = false;
        }
    },
    
};
</script>

<style scoped>
.button {
    border: none;
    position: absolute;
    top: 4%;
    left: 5.1vw;
    bottom: 4%;

    /* padding: 0.6% 0.6%; */
    text-align: center;
    background-color: #f5f5f5;
    color: rgb(7, 7, 7);
    font-size: 1vw;
    cursor: pointer;
    /* border-radius: 0.1%; */
    /* 可选：添加阴影来增加视觉效果 */
    /* box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2); */
}

.button:hover {
    background-color: #494a4b;
    /* 鼠标悬停时的背景颜色 */
}

.button:active {
    background-color: #004494;
    /* 按钮被点击时的背景颜色 */
    /* 可选：调整阴影来模拟按钮按下的效果 */
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.2);
}

.chessAcceptWindow{
    position: absolute;
    right:10%;

    z-index: 1000; 
    background-color: #f5f5f5;
    margin: 0px;
    padding: 10px 10px;
    /* 为按钮留出空间 */
    border: 1px solid rgb(83, 76, 76);
    
    transform: translateX(0) translateY(-102%);
    /* 确保弹出窗口在其他内容上方显示 */
    font-size: 1vw;
}
</style>