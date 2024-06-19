<template>
    <div v-if="visible" class="dialog-overlay">
      <div class="dialog">
        <h3>创建房间</h3>
        <form @submit.prevent="submitCreate">
            <div class="form-group">
                <label for="roomname">房间名:</label>
                <input type="text" id="roomname" v-model="roomname" required />
            </div>
        </form>
      </div>
      <div class="form-group">
          <button type="submit">创建</button>
          <button type="button" @click="closeDialog">取消</button>
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

const emit = defineEmits(['createroom', 'close']);

const roomname = ref('');

const submitCreate = () => {
  emit('createroom', roomname);
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
