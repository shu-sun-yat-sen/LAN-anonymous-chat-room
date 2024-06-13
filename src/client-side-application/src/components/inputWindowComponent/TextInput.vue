<template>
    <div>
        <textarea v-model="text" placeholder="输入文本" class="input-box" @keydown="handleKeydown"> </textarea>
        <button v-on:click="sendText" class="button">发送(Enter)</button>
    </div>
</template>

<script>
export default {
    data() {
        return {
            text: ''
        };
    },
    props: {
        emojiMessage: String
    },
    watch: {
        emojiMessage(newVal) {
            if(newVal){
                this.handleMessage();
            }
        }
    },
    methods: {
        sendText() {
            this.$emit('send-text', this.text);
            this.text = '';
        },
        handleKeydown(event) {
            if (event.key === "Enter" && !event.ctrlKey) {
                event.preventDefault(); // 防止默认的换行行为
                this.sendText();
            } else if (event.key === "Enter" && event.ctrlKey) {
                // 插入换行符
                this.text += "\n";
            }
        },
        handleMessage(){
            this.text += this.emojiMessage;
            this.$emit('clearEmojiMessage');
        }
    }
};
</script>

<style scoped>
.input-box {
    position: absolute;
    top: 0%;
    left: 0%;
    width: 98%;
    padding: 0vw 0.5vw;

    /* margin: 10px; */
    /*行间距 */
    /* line-height: 2px; */

    font-family: Arial, sans-serif;
    /* 字体 */
    font-size: 1vw;
    /* 字体大小 */
    /* word-wrap: break-word;
    max-width: 100%; */
    /* 为按钮留出空间 */
    /* margin: 10px; */
    height: 4vw;
    /* 初始高度 */
    border: none;
    /* 去除边框 */
    outline: none;
    /* 去除点击边框 */
    background-color: #f5f5f5;
    resize: none;
    /* 禁用手动调整大小 */
    overflow-y: auto;
    /* 内容过多时显示垂直滚动条 */
    z-index: 1;
}

.button {
    border: none;
    position: absolute;
    
    right: 1%;
    bottom: 30%;
    padding: 0.8% 1.2%;
    background-color: #E9E9E9;
    color: #07C160;
    font-size: 1vw;
    margin: 0.3%;
    cursor: pointer;
    
    width: 10%;
    /* 可选：添加阴影来增加视觉效果 */
    /* box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2); */

    /* 确保按钮不会被覆盖掉  */
    z-index: 1000;
    opacity: 1;
}

.button:hover {
    background-color: #494a4b;
    /* 鼠标悬停时的背景颜色 */
}

.button:active {
    background-color: #004494;
    /* 按钮被点击时的背景颜色 */
    /* 可选：调整阴影来模拟按钮按下的效果 */
    box-shadow: 0 0.1% 0.3% rgba(0, 0, 0, 0.2);
}
</style>