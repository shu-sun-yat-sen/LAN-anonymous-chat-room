<template>
    <div>

        <button @click="playChess" class="button">♟️</button>

        <div class="chessAcceptWindow" v-if="showChessAcceptWindow">
            <!-- user "{{ curUser.fakeName }}" invite you to play chess. 
                <el-button type="danger" round @click="closeWindow">acccept</el-button> -->
            <h3>Game</h3>
            <el-form ref="ruleFormRef" :rules="rules" :model="ruleForm" label-width="auto" style="max-width: 600px;">
                <el-form-item label="RoomID" prop="roomID">
                    <el-input v-model="ruleForm.roomID" />
                </el-form-item>
                <el-form-item label="游戏类型" prop="type">
                    <el-input v-model="ruleForm.type" />
                </el-form-item>

                <div style="display: flex; gap: 10px; position: relative; left: 15%;">
                    <router-link target="_blank" :to="generateLink('create')">
                        <el-button type="warning" @click="submitFormCreate($event, ruleFormRef)">创建</el-button>
                    </router-link>
                    <router-link target="_blank" :to="generateLink('create')">
                        <el-button type="primary" @click="submitFormLogin($event, ruleFormRef)">加入</el-button>
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

        // 待加入不登录不开启棋局设置
        const curUser = computed(() => {
            // console.log(loginInfo.value.userId);
            return loginInfo.value ? loginInfo.value : '';
        });

        const ruleFormRef = ref(null);

        const ruleForm = ref({
            roomID: '',
            type: '',
        });

        const rules = {
            roomID: [{ required: true, message: '请输入ID', trigger: 'blur' }],
            type: [{ required: true, message: '请输入假名', trigger: 'blur' }]
        };

        return {
            curUser,
            ruleFormRef,
            ruleForm,
            rules
        }
    },
    data() {
        return {
            showChessAcceptWindow: false,
        };
    },
    methods: {
        generateLink(action) {
        return {
            name: 'ChessGame',
            query: {
            roomID: this.ruleForm.roomID,
            type: this.ruleForm.type,
            action: action
            }
        };
        },
        playChess() {
            this.$emit('start-chess', 'chess');
            this.showChessAcceptWindow = true;
            this.broadcastRequest();
        },
        broadcastRequest() {
            // 棋局已经开始，广播该消息到同一聊天室其他所有玩家聊天框中
            // 具体来说就是将他们的showChessAcceptWindow设置为true，邀请他们进来

            // 这里应该要告诉后端有哪些用户进入了棋局
            // 如果检测到房间内人数=2，那么将showChessAcceptWindow设置为false，同时开始游戏
        },
        closeWindow() {
            // 这里还要通知后端，对方已经接受了邀请
            this.showChessAcceptWindow = false;
        },
        submitFormCreate(event, formEl) {
            if (!formEl) return
            formEl.validate((valid) => {
                if (valid) {
                    this.$emit('create-game', this.ruleForm.roomID);
                    this.closeWindow();
                } else {
                    event.preventDefault();
                    alert('create failed!');
                }
            })
        },
        submitFormLogin(event, formEl) {
            if (!formEl) return
            formEl.validate((valid) => {
                if (valid) {
                    this.$emit('join-game', this.ruleForm.roomID);
                    this.closeWindow();
                } else {
                    event.preventDefault();
                    alert('join the game failed!');
                }
            })
        }
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

.chessAcceptWindow {
    position: absolute;
    left: 20%;

    z-index: 1000;
    background-color: #f0f0f0;
    margin: 0px;
    padding: 15px 15px;
    /* 为按钮留出空间 */
    border: 0px solid rgb(83, 76, 76);

    transform: translateX(0) translateY(-50vh);
    /* 确保弹出窗口在其他内容上方显示 */
    /* font-size: 1vw; */
}
</style>