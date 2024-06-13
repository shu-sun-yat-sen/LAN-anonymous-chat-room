<template>
    <div class="chat-bubble">
      <img :src="avatar" alt="Avatar" class="avatar" />
      <div class="content-container">
        <div class="username">{{ username }}</div>
        <div class="content">
          <p>{{ text }}</p>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      text: {
        type: String,
        required: true
      },
      avatar: {
        type: String,
        required: true
      },
      username: {
        type: String,
        required: true
      }
    }
  };
  </script>
  
  <style scoped>
  .chat-bubble {
    display: flex;
    align-items: flex-start;
    margin-bottom: 10px;
    padding: 10px;
  }
  
  .avatar {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    margin-right: 10px;
  }
  
  .content-container {
    display: flex;
    flex-direction: column;
    align-items: flex-start; /* 确保左对齐 */
    max-width: calc(100% - 50px); /* Adjusts width to fit within parent, considering avatar width and margin */
  }
  
  .username {
    font-weight: bold;
    margin-bottom: 5px;
    white-space: nowrap;
  }
  
  .content {
    background-color: white;
    padding: 10px;
    border-radius: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    max-width: 100%;
    word-wrap: break-word;
    text-align: left;
    max-width: 90%;
  }
  </style>