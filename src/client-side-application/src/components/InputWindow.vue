<template>
    <aside class="input-window">
        <!-- <p>{{ inputWindowText }}</p> -->
        <div class="topWindow">
            <EmojiButton @send-emoji="handleSendEmoji"/>
            <FileUploadButton @upload-file="handleUploadFile"/> 
            <ChessButton @start-chess="handleChess" @create-game="handleCreateGame" @join-game="handleJoinGame"/>
        </div>
        <div class="bottomWindow">
            <TextInput @send-text="handleSendText" :emojiMessage="emojiMessage" @clearEmojiMessage="clearEmojiMessage"/>
        </div>    
    </aside>
</template>

<script setup>
// import {inject} from 'vue';
// const inputWindowText = inject('input_window_text');
</script>

<script>
import EmojiButton from './inputWindowComponent/EmojiButton.vue';
import TextInput from './inputWindowComponent/TextInput.vue';
import FileUploadButton from './inputWindowComponent/FileUploadButton.vue';
import ChessButton from './inputWindowComponent/ChessButton.vue';

export default {
    name: 'InputWindow',
    components: {
        EmojiButton,
        TextInput,
        FileUploadButton,
        ChessButton
    },
    data() {
        return {
            text: 'hello world',
            emojiMessage : '',
        }
    },
    props: {
        inputWindowText: String,
    },
    methods: {
        handleSendText(text) {
            // console.log('发送文本:', text);
            // 注意要判断长度为不为0
            if(text.length <= 0){
                alert('发送内容不能为空');
            }
            else{
                this.$emit('sendtext', text);
            }
        },
        handleUploadFile(file) {
            // console.log('上传文件:', file);
            this.$emit('send-file', file);
        },
        handleSendEmoji(emoji) {
            // console.log('发送表情:', emoji);
            this.emojiMessage = emoji;
        },
        handleChess(chess){
            console.log('开始下棋:', chess);
        },
        clearEmojiMessage(){
            this.emojiMessage = '';
        },
        handleCreateGame(gameRoomID, gameType){
            this.$emit('create-game', gameRoomID, gameType);
        },
        handleJoinGame(gameRoomID){
            this.$emit('join-game', gameRoomID);
        }
    }
}
</script>

<style scoped>
.input-window {
    position: fixed;
    top: 80%;
    bottom: 0%;
    left: 25%;
    right: 0%;
    color: black;
    background-color: #f5f5f5;
    border: 1px solid rgb(83, 76, 76);
    /* 黑色线框 */
}

.bottomWindow {
    position: relative;
    top: 0.2%;
    width: 100%;
    height: 100%;
}

.topWindow {
    position: relative;
    width: 100%;
    height: 25%;
}

.topWindow::before {
    content: "";
    display: block;
    width: 100%;
    height: 1px;
    /* 线条高度 */
    background-color: #f5f5f5;
    /* 线条颜色 */
    position: relative;
    top: 100%;
    /* 距离顶部 50px 处绘制横线 */
    left: 0;
}
</style>