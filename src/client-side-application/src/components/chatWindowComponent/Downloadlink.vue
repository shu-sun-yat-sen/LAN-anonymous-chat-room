<template>
    <div>
      <a :href="fileUrl" :download="fileName" @click="downloadFile">
        {{ fileName }}
      </a>
    </div>
  </template>
  
  <script>
  export default {
    name: 'Downloadlink',
    props: {
      fileUrl: {
        type: String,
        required: true
      }
    },
    methods: {
      downloadFile(event) {
        // 检查浏览器是否支持 download 属性
        if (event.target.download) {
          event.preventDefault();
          this.downloadUsingAnchor();
        }
      },
      downloadUsingAnchor() {
        const link = document.createElement('a');
        link.href = this.fileUrl;
        link.download = this.fileName || 'download';
        link.click();
      }
    },
    computed:{
        fileName(){
            return this.fileUrl.split("/").pop();
        }
    }
  }
  </script>
  
  <style scoped>
  a {
    color: #f0f0f0;
    text-decoration: underline;
    cursor: pointer;
  }
  </style>
  