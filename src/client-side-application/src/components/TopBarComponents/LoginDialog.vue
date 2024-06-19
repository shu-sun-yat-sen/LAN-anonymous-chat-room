<template>
    <div v-if="visible" class="dialog-overlay">
      <div class="dialog">
        <h3>登录</h3>
        <form @submit.prevent="submitLogin">
            <div class="form-group">
                <label for="id">id:</label>
                <input type="text" id="id" v-model="id" required />
            </div>
            <div class="form-group">
                <label for="username">假名:</label>
                <input type="text" id="fakename" v-model="fakename" required />
            </div>
            <div class="form-group">
                <label for="password">密码:</label>
                <input type="password" id="password" v-model="password" required />
            </div>
            <div class="form-group">
                <button type="submit">登录</button>
                <button type="button" @click="closeDialog">取消</button>
            </div>
        </form>
      </div>
    </div>
</template>

<script setup>
import { ref, inject, defineProps, defineEmits } from 'vue';

const props = defineProps({
  visible: {
    type: Boolean,
    default: false,
  },
});

const emit = defineEmits(['login', 'close']);

const id = ref('');
const fakename = ref('');
const password = ref('');

const loginInfo = inject('login-info');
const submitLogin = () => {
  loginInfo.value.userId = id;
  loginInfo.value.fakeName = fakename;
  loginInfo.value.userPasswd = password;
  emit('login');
  closeDialog();
};

const closeDialog = () => {
  emit('close');
};
</script>

<style scoped>
.dialog-overlay {
  position: fixed;
  top: 20%;
  left: 60%;
  
  background: #ffffff80;
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 10000;
}

.dialog {
  background: #ffffff80;
  padding: 20px;
  border-radius: 8px;
}

</style>
