<template>
    <div>
        <button @click="clickUpload" class="button">📁</button>
        <input id="file-upload" ref="fileInput" type="file" @change="uploadFile" />
    </div>
</template>

<script>
export default {
    name: 'FileUploadButton',
    methods: {
        clickUpload() {
            this.$refs.fileInput.click();
        },
        uploadFile(event) {
            const file = event.target.files[0];
            this.$emit('upload-file', file);
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
    position:absolute;
    left: 2.8vw;
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
    background-color: #494a4b; /* 鼠标悬停时的背景颜色 */
}

.button:active {
    background-color: #004494; /* 按钮被点击时的背景颜色 */
    /* 可选：调整阴影来模拟按钮按下的效果 */
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.2);
}
</style>