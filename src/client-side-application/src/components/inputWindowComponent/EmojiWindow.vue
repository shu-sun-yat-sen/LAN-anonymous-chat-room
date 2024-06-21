<template>
    <div class="emoji-window">
        <span v-for="emoji in emojis" :key="emoji" @click="selectEmoji(emoji)">{{ emoji }}</span>
    </div>
</template>

<script>
export default {
    data() {
        return {
            emojis: ['😀', '😂', '😍', '😎', '😢'] // 示例表情
        };
    },
    methods: {
        selectEmoji(emoji) {
            this.$emit('select-emoji', emoji);
        }
    }
};
</script>

<style scoped>
.emoji-window {
    position: absolute;
    z-index: 1000; 
    background-color: #f5f5f5;
    margin: 0px;
    padding: 10px 10px;
    /* 为按钮留出空间 */
    border: 1px solid rgb(83, 76, 76);
    
    transform: translateX(-0.5px) translateY(-2.9vw);
    /* 确保弹出窗口在其他内容上方显示 */
    font-size: 1vw;
}

.emoji-window span {
    cursor: pointer;
    margin: 5px;
}
</style>