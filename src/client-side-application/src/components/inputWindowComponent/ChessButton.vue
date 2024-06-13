<template>
    <div>
        <button @click="playChess" class="button">♟️</button>
    </div>
</template>

<script>

export default {
    data() {
        return {
        };
    },
    methods: {
        playChess() {
            this.$emit('start-chess', 'chess');
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
</style>