<template>
  <div class="gobang">
    <canvas id="gobang" width="900" height="600"></canvas>
  </div>
</template>

<script>
import axios from 'axios';
import { computed, inject ,ref} from 'vue';
/**
 *  写在前面：
 * 棋盘大小为15*15
 *  drawCheckerboard根据chessBoard,chessBoardHeight,chessBoardWidth，画棋盘和上面的棋子
 *  drawChess,drawText都是工具函数，不用理
 * drawResult当isOver为true时调用就行
 * handleClick(event)会发送棋子信息给后端，然后等待后端处理
 * 前端有用的就这些
 */
export default {
  name: "ChessGame",
  setup() {
    const playerInfo = ref({
      JWT:null,
      playerID: null
    });
    const serverInfo = inject('server-info');
    // 待加入不登录不开启棋局设置

    const curGame = ref(
      {
        gameId: "id1",  //唯一标识一个游戏
        gameType: "1", //游戏类型
        roomName: "room1", //所属的房间
        isIn: true, //自己是否在房间内
        //一维存储的棋盘格，1代表黑子，0无，-1白子
        chessBoard: [
          1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
          1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        ],
        chessBoardHeight: 15,
        chessBoardWidth: 15,
        turntoId: "id1", //当前轮到谁走
        whiteTurn: false, //当前是否轮到白子走,true是白子
        isOver: false, //游戏是否结束
        winnerId: "id1", //获胜者的id
      }
    );

    return {
      // 游戏信息
      curGame,
      playerInfo,
      serverInfo
    }
  },
  data() {
    return {
      ctx: null,
      // winGame: false,
      // whiteTurn: false, // 白棋轮；true-黑棋轮, 开始要决定好
      resultArr: [] // 记录棋子位置的数组, 棋盘
    };
  },
  mounted() {
    this.curGame.gameId = this.$route.query.roomID;
    this.curGame.gameType = this.$route.query.type;
    this.playerInfo.JWT = this.$route.query.JWT;
    this.playerInfo.playerID = this.$route.query.playerID;
    console.log("启动：", this.curGame.gameId, this.curGame.gameType);
    console.log("JWT", this.playerInfo.JWT);
    console.log("playerID", this.playerInfo.playerID);
    let _this = this;
    let container = document.getElementById("gobang");

    container.addEventListener("click", _this.handleClick);

    _this.ctx = container.getContext("2d");
    _this.ctx.translate(70, 70);

    for (var i = 0; i < this.curGame.chessBoardHeight; i++) {
      _this.resultArr.push(new Array(this.curGame.chessBoardWidth).fill(0));
    }

    _this.drawCheckerboard();

    // 告诉后端我要开始下棋了
    console.log('准备好了，可以开始下棋了');
    this.intervalId = setInterval(this.listenOtherChessLocation, 300);
  },
  beforeDestroy() {
    // 清除定时器
    if (this.intervalId) {
      clearInterval(this.intervalId);
    }
  },
  computed: {
    chessText() {
      return this.curGame.whiteTurn ? '白棋' : '黑棋';
    }
  },
  methods: {
    drawCheckerboard() {
      // 画棋盘, 收到后端的棋盘信息后，画棋盘
      let _this = this;
      _this.ctx.beginPath();
      _this.ctx.fillStyle = "#fff";
      _this.ctx.rect(0, 0, 30 * this.curGame.chessBoardHeight, 30 * this.curGame.chessBoardWidth);
      _this.ctx.fill();

      // console.log(this.curGame.chessBoardHeight, this.curGame.chessBoardWidth);
      for (var i = 0; i < this.curGame.chessBoardHeight; i++) {
        _this.ctx.beginPath();
        _this.ctx.strokeStyle = "#D6D1D1";
        _this.ctx.moveTo(15 + i * 30, 15); //垂直方向画15根线，相距30px;
        _this.ctx.lineTo(15 + i * 30, 29 * this.curGame.chessBoardHeight);
        _this.ctx.stroke();
        _this.ctx.moveTo(15, 15 + i * 30); //水平方向画15根线，相距30px;棋盘为14*14；
        _this.ctx.lineTo(29 * this.curGame.chessBoardWidth, 15 + i * 30);
        _this.ctx.stroke();

        
        for(var j = 0; j < this.curGame.chessBoardWidth; j++){
          _this.resultArr[i][j] = this.curGame.chessBoard[i * this.curGame.chessBoardWidth + j];
          this.drawChess(i, j);
        }
      }
      _this.drawText();
    },

    drawChess(x, y) {
      // 画棋盘上的棋子，仅此而已
      let _this = this;
      // let xLine = Math.round((x - 15) / 30); // 竖线第x条
      // let yLine = Math.round((y - 15) / 30); // 横线第y条
      let xLine = x;
      let yLine = y;
      if (_this.resultArr[xLine][yLine] == 0) {
        return;
      }
      let grd = _this.ctx.createRadialGradient(
        xLine * 30 + 15,
        yLine * 30 + 15,
        4,
        xLine * 30 + 15,
        yLine * 30 + 15,
        10
      );
      // 原话this.whiteTurn ? "#fff" : "#4c4c4c"
      grd.addColorStop(0, _this.resultArr[xLine][yLine] == -1 ? "#fff" : "#4c4c4c");
      grd.addColorStop(1, _this.resultArr[xLine][yLine] == -1 ? "#dadada" : "#000");
      _this.ctx.beginPath();
      _this.ctx.fillStyle = grd;
      _this.ctx.arc(
        xLine * 30 + 15,
        yLine * 30 + 15,
        10,
        0,
        2 * Math.PI,
        false
      );
      _this.ctx.fill();
      _this.ctx.closePath();

      // _this.setResultArr(xLine, yLine);
      // 将检查胜负逻辑转移到后端
      // _this.checkResult(xLine, yLine); 
    },

    // setResultArr(m, n) {
    //   let _this = this;
    //   _this.resultArr[m][n] = _this.whiteTurn ? 1 : 2; // 白棋为-1；黑棋为1

    // },

    drawText() {
      let _this = this;
      _this.ctx.clearRect(29 * this.curGame.chessBoardWidth + 60, 0, 300, 70);
      _this.ctx.fillStyle = "#fff";
      _this.ctx.font = "20px Arial";
      const turnToId = this.curGame.turntoId;

      _this.ctx.fillText('本轮：' + turnToId + "("+ _this.chessText + ")" , 29 * this.curGame.chessBoardWidth + 70, 35);
    },
    
    drawResult() {
      // 结束时候调用即可
      let _this = this;
      _this.ctx.fillStyle = "#ff2424";
      _this.ctx.font = "20px Arial";
      const winnerId = this.curGame.winnerId;
      _this.ctx.fillText(winnerId + " ( "+_this.chessText+" ) " + '胜！', this.curGame.chessBoardWidth * 29 + 70, 70);

      // 游戏胜利后移除点击事件监听器
      document.getElementById("gobang").removeEventListener("click", _this.handleClick);
    },

    handleClick(event) {
      // 还没到自己的轮，不能下棋
      // if (this.whiteTurn !== this.userTurn) {
      //   return;
      // }

      let x = event.offsetX - 70;
      let y = event.offsetY - 70;
      if (x < 15 || x > 435 || y < 15 || y > 435) {
        // 点出界的
        return;
      }
      let xLine = Math.round((x - 15) / 30); // 竖线第x条
      let yLine = Math.round((y - 15) / 30); // 横线第y条
      if (this.resultArr[xLine][yLine] !== 0) {
        // 点已经有棋子的位置
        return;
      }

      // this.drawChess(x, y);

      // 告诉后端这里下了棋子
      this.tellChessLocation(xLine, yLine);

      // if (this.winGame) {
      //   // 已经结束了
      //   this.drawResult();
      //   return;
      // }
      // this.whiteTurn = !this.whiteTurn;
      // this.drawText();
    },

    // to-do: 前后端通信
    tellChessLocation(xLine, yLine) {
      // 告诉后端我下了一步棋
      console.log("尝试落子", xLine, yLine);
      axios({
        method: 'post',
        url: this.serverInfo.serverList[0].ip + '/game/play',
        headers:{
          Authorization: this.playerInfo.JWT,
        },
        params:{
          id: this.curGame.gameId,
          row: yLine,
          col:xLine
        }
      }).then(response => {
        if(response.data.code === 0){
          console.log('落子成功');
        } else{
          console.log('落子失败');
          alert(response.data.message);
        }
      }, error => {
        console.log('落子失败');
        alert(error);
      });
    },

    parseBoard(boardString) {
      try {
        // 将字符串解析为JavaScript对象
        let boardArray = JSON.parse(boardString);
        let h = boardArray.length;
        let w = boardArray[0].length;
        let output = [];
        for(let i=0;i<w;i++){
          for(let j=0;j<h;j++){
            let value = boardArray[j][i];
            if(value === -1){
              output.push(0);
            }
            if(value === 0){
              output.push(-1);
            }
            if(value === 1){
              output.push(1);
            }
          }
        }
        return output;
      } catch (error) {
        console.error("Error parsing board string:", error);
        return null;
      }
    },
    
    splitStringByComma(str) {
      return str.split(',');
    },
    // turntoId: "id1", //当前轮到谁走
    // whiteTurn: false, //当前是否轮到白子走,true是白子
    // isOver: false, //游戏是否结束
    // winnerId: "id1", //获胜者的id
    listenOtherChessLocation() {
      // console.log("JWT", this.playerInfo.JWT);
      axios({
        method: 'get',
        url: this.serverInfo.serverList[0].ip + '/game/showgame',
        headers:{
          Authorization: this.playerInfo.JWT,
        },
        params:{
          id: this.curGame.gameId
        }
      }).then(response => {
        if(response.data.code === 0){
          // console.log('获取棋面成功', this.curGame.gameId);
          // console.log(response.data.data)
          let newGameInfo = response.data.data;
          this.curGame.chessBoard = this.parseBoard(newGameInfo.board);
          this.drawCheckerboard();
          this.curGame.turntoId = this.splitStringByComma(newGameInfo.players)[newGameInfo.turn];
          this.curGame.isOver = newGameInfo.winner === -1 ? true:false;
          this.curGame.winnerId = newGameInfo.winner === -1 ? null:this.splitStringByComma(newGameInfo.players)[newGameInfo.winner];
          // console.log(this.curGame.turntoId);
        } else{
          console.log('获取棋面失败', this.curGame.gameId, response.data.message);
          alert(response.data.message);
        }
      }, error => {
        console.log('获取棋面失败');
        alert(error);
      });
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.gobang {
  background: #2a4546;
  display: flex;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>