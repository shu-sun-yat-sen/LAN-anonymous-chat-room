<template>
  <div v-if="visible" class="dialog-overlay">
    <div class="dialog">
      <h3>Log in</h3>
      <!-- <form @submit.prevent="submitLogin">
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
      </form> -->
      <!-- <el-form :rules="rules" :model="ruleForm" label-width="auto" style="max-width: 600px"> -->
      <el-form ref="ruleFormRef" :rules="rules" :model="ruleForm" label-width="auto" style="max-width: 600px;opacity: 1;">
        <el-form-item label="ID" prop="id">
          <el-input v-model="ruleForm.id" />
        </el-form-item>
        <el-form-item label="假名" prop="fakename">
          <el-input v-model="ruleForm.fakename" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="ruleForm.password" />
        </el-form-item>
        <div style="position: relative;left:10%;">
            <el-button type="primary" @click.prevent="submitForm(ruleFormRef)">登录</el-button>
            <el-button @click="closeDialog">取消</el-button>
        </div>
      </el-form>

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

// const id = ref('');
// const fakename = ref('');
// const password = ref('');

// 改造版本
const ruleFormRef = ref(null);

const ruleForm = ref({
  id: '',
  fakename: '',
  password: '',
});
const rules = {
  id: [{ required: true, message: '请输入ID', trigger: 'blur' }],
  fakename: [{ required: true, message: '请输入假名', trigger: 'blur' }],
  password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
};

const loginInfo = inject('login-info');

const submitForm = async (formEl) => {
  if (!formEl) return
  await formEl.validate((valid) => {
    if (valid) {
      console.log('id: ', ruleForm.value.id, ' submitted successfully!');
      loginInfo.value.userId = ruleForm.value.id;
      loginInfo.value.fakeName = ruleForm.value.fakename;
      loginInfo.value.userPasswd = ruleForm.value.password;
      emit('login');
      closeDialog();
    } else {
      alert('login failed!');
    }
  })
};
// end


// const submitLogin = () => {
//   loginInfo.value.userId = id;
//   loginInfo.value.fakeName = fakename;
//   loginInfo.value.userPasswd = password;
//   emit('login');
//   closeDialog();
// };

const closeDialog = () => {
  emit('close');
};
</script>


<script>

</script>

<style scoped>
.dialog-overlay {
  position: fixed;
  top: 20%;
  left: 40%;

  /* background: #ffffff80; */
  display: flex;
  justify-content: center;
  align-items: center;

  opacity: 1;
  z-index: 10000;
}

.dialog {
  background: #f0f0f0;
  padding: 20px;
  border-radius: 8px;
  /* Set opacity to 0 */
  opacity: 1; 
}
</style>
