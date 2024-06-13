<template>
    <div>
        <button @click="toggleEmojiWindow" class="button">🤗</button>
        <EmojiWindow v-if="showEmojiWindow" @select-emoji="selectEmoji" />
    </div>
</template>

<script>
import EmojiWindow from './EmojiWindow.vue';

export default {
    components: {
        EmojiWindow
    },
    data() {
        return {
            showEmojiWindow: false
        };
    },
    methods: {
        toggleEmojiWindow() {
            this.showEmojiWindow = !this.showEmojiWindow;
        },
        selectEmoji(emoji) {
            this.$emit('send-emoji', emoji);
            this.showEmojiWindow = false;
        }
    },
    props: {

    },
};
</script>

<style scoped>
.button {
    border: none;
    position: absolute;
    top: 4%;
    left: 0.5vw;
    bottom: 4%;

    text-align: center;
    /* padding: 0.6% 0.6%; */
    background-color: #f5f5f5;
    color: white;
    font-size: 1vw;
    cursor: pointer;
    /* border-radius: 0.5%; */
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
</style>