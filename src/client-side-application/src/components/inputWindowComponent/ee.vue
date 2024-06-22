<template>
    <div>
        <button @click="playChess" class="button">♟️</button>
        <div class="chessAcceptWindow" v-if="showChessAcceptWindow">
            <h3>Game</h3>
            <el-form ref="ruleFormRef" :rules="rules" :model="ruleForm" label-width="auto" style="max-width: 600px;">
                <el-form-item label="RoomID" prop="roomID">
                    <el-input v-model="ruleForm.roomID" />
                </el-form-item>
                <el-form-item label="游戏类型" prop="type">
                    <el-input v-model="ruleForm.type" />
                </el-form-item>
                <div style="display: flex; gap: 10px; position: relative; left: 15%;">
                    <router-link :to="{ name: 'ChessGame', params: { userId: userId } }" target="_blank">
                        <el-button type="warning" @click="handleClick">创建</el-button>
                    </router-link>
                    <router-link :to="{ name: 'ChessGame', params: { userId: userId } }" target="_blank">
                        <el-button type="primary" @click="handleClick">加入</el-button>
                    </router-link>
                    <el-button @click.prevent="closeWindow">取消</el-button>
                </div>
            </el-form>
        </div>
    </div>
</template>

<script>
import { ref, inject, computed } from 'vue';

export default {
    setup() {
        const loginInfo = inject('login-info');
        const userId = computed(() => {
            return loginInfo.value.userId ? loginInfo.value.userId : '';
        });

        const ruleFormRef = ref(null);

        const ruleForm = ref({
            roomID: '',
            type: '',
        });

        const rules = {
            roomID: [{ required: true, message: '请输入ID', trigger: 'blur' }],
            type: [{ required: true, message: '请输入类型', trigger: 'blur' }]
        };

        const showChessAcceptWindow = ref(false);
        const pass = ref(false);

        const playChess = () => {
            showChessAcceptWindow.value = true;
            broadcastRequest();
        };

        const broadcastRequest = () => {
            // 棋局已经开始，广播该消息到同一聊天室其他所有玩家聊天框中
            // 这里应该要告诉后端有哪些用户进入了棋局
        };

        const closeWindow = () => {
            showChessAcceptWindow.value = false;
        };

        const submitFormCreate = (formEl) => {
            return new Promise((resolve) => {
                if (!formEl) return;
                formEl.validate((valid) => {
                    if (valid) {
                        console.log('roomID: ', ruleForm.value.roomID, ' create successfully!');
                        pass.value = true;
                        closeWindow();
                    } else {
                        pass.value = false;
                    }
                    resolve(pass.value);
                });
            });
        };

        const submitFormLogin = (formEl) => {
            return new Promise((resolve) => {
                if (!formEl) return;
                formEl.validate((valid) => {
                    if (valid) {
                        console.log('roomID: ', ruleForm.value.roomID, ' join successfully!');
                        pass.value = true;
                        closeWindow();
                    } else {
                        pass.value = false;
                    }
                    resolve(pass.value);
                });
            });
        };

        const sleep = (ms) => {
            return new Promise(resolve => setTimeout(resolve, ms));
        };

        const handleClick = async (event) => {
            await sleep(500);
            const isValid = await submitFormCreate(ruleFormRef.value);
            if (!isValid) {
                event.preventDefault();
            }
        };

        return {
            userId,
            ruleFormRef,
            ruleForm,
            rules,
            showChessAcceptWindow,
            pass,
            playChess,
            closeWindow,
            submitFormCreate,
            submitFormLogin,
            sleep,
            handleClick
        };
    }
};
</script>

<style scoped>
.button {
    border: none;
    position: absolute;
    top: 4%;
    left: 5.1vw;
    bottom: 4%;
    text-align: center;
    background-color: #f5f5f5;
    color: rgb(7, 7, 7);
    font-size: 1vw;
    cursor: pointer;
}
.button:hover {
    background-color: #494a4b;
}
.button:active {
    background-color: #004494;
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.2);
}
.chessAcceptWindow {
    position: absolute;
    left: 20%;
    z-index: 1000;
    background-color: #f0f0f0;
    margin: 0px;
    padding: 15px 15px;
    border: 0px solid rgb(83, 76, 76);
    transform: translateX(0) translateY(-50vh);
}
</style>
