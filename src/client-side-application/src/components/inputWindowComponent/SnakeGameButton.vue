<template>
    <div>
        <!-- <router-link to="external" target="_blank"> -->
        <button @click="redirectToSnake" class="button">🐍</button>
        <!-- </router-link> -->
    </div>
</template>

<script>
export default {
    name: 'SnakeGameButton',
    methods: {
        redirectToSnake() {
            // 打开一个新窗口
            const newWindow = window.open('', '_blank');
            if(newWindow){
                newWindow.location.href = 'http://localhost:8082/';
            }
            else{
                alert('请允许弹出窗口');
            }
            // window.location.href = 'http://localhost:8082/';
        }
    },
    props: {
    },
};
</script>

<style scoped>
/* 隐藏原始的文件输入元素 */
#file-upload {
    display: none;
}

.button {
    border: none;
    position: absolute;
    left: 7.4vw;
    top: 4%;
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