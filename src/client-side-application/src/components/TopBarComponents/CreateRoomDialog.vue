<template>
  <div v-if="visible" class="dialog-overlay">
    <div class="dialog">
      <h3>Create Room</h3>
      <!-- <form @submit.prevent="submitCreate">
            <div class="form-group">
                <label for="roomname">房间名:</label>
                <input type="text" id="roomname" v-model="roomname" required />
            </div>
            <div class="form-group">
              <button type="submit">创建</button>
              <button type="button" @click="closeDialog">取消</button>
            </div>
        </form> -->
      <el-form ref="ruleFormRef" :rules="rules" :model="ruleForm" label-width="auto" style="max-width: 600px">
        <el-form-item label="房间名" prop="roomname">
          <el-input v-model="ruleForm.roomname" @keyup.enter="handleEnterKey" />
          <!-- <input type="text"> -->
        </el-form-item>
        

        <div style="position: relative;left:10%;">
          <el-button type="primary" @click.prevent="submitCreateForm(ruleFormRef)">创建</el-button>
          <el-button @click.prevent="closeDialog">取消</el-button>
        </div>
      </el-form>

    </div>
  </div>
</template>

<script setup>
import { ref, inject } from 'vue';

const props = defineProps({
  visible: {
    type: Boolean,
    default: false,
  },
});

const emit = defineEmits(['createroom', 'close']);


// 改造版本
const ruleFormRef = ref(null);

const ruleForm = ref({
  roomname: '',
});

const rules = {
  roomname: [
    { required: true, message: '请输入房间名', trigger: 'blur' },
  ],
};

const handleEnterKey = (event) => {
  event.preventDefault();
};

const submitCreateForm = async (formEl) => {
  if (!formEl) return
  await formEl.validate((valid) => {
    if (valid) {
      console.log('id: ', ruleForm.value.roomname, ' submitted successfully!');
      emit('createroom', ruleForm.value.roomname);
      closeDialog();
    } else {
      alert('login failed!');
    }
  })
};
// end

// const submitCreate = () => {
//   emit('createroom', roomname.value);
//   closeDialog();
// };

const closeDialog = () => {
  emit('close');
};
</script>

<style scoped>
.dialog-overlay {
  position: fixed;
  top: 20%;
  left: 40%;

  background: #ffffff80;
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 10000;
}

.dialog {
  background: #f0f0f0;
  padding: 20px;
  border-radius: 8px;
}
</style>
