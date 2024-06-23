package com.example.demo1.control;

import com.example.demo1.model.GAME;
import com.example.demo1.model.GameCore.BoardGame;
import com.example.demo1.model.GameCore.Go;
import com.example.demo1.model.GameCore.Gomoku;
import com.example.demo1.model.GameCore.Reversi;
import com.example.demo1.model.Result;
import com.example.demo1.service.CacheService.GameMap;
import com.example.demo1.service.DbService.GameService;
import com.example.demo1.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/game")
public class GAMEController {
    @Autowired
    private GameService gameService;
    @Autowired
    private GameMap gameMap;
    @PostMapping("/addgame")
    public Result addgame(String id, String chatroom, int gameid){
        System.out.print("收到创建游戏请求：");
        System.out.print(id);
        System.out.print(" chatroom:");
        System.out.print(chatroom);
        System.out.print(" gameid:");
        System.out.println(gameid);
        if(gameService.checkid(id))
            return Result.error("该游戏唯一标识已存在");
        Map<String, Object> map = ThreadLocalUtil.get();
        String sender=(String) map.get("id");
        String [] players=new String[2];
        players[0]=sender;
        GAME game=new GAME(gameid);
        game.setId(id);
        game.setChatroom(chatroom);
        game.setPlayers(players[0]);
        game.setIsend(0);
        if(gameid==1){
            BoardGame gomoku=new Gomoku(players);
            game.setBoard(Arrays.deepToString(gomoku.getBoard()));
            game.setTurn(gomoku.getTurn());
            game.setWinner(gomoku.getWinner());
        }
        if(gameid==2){
            BoardGame gomoku=new Go(players);
            game.setBoard(Arrays.deepToString(gomoku.getBoard()));
            game.setTurn(gomoku.getTurn());
            game.setWinner(gomoku.getWinner());
        }
        if(gameid==3){
            BoardGame gomoku=new Reversi(players);
            game.setBoard(Arrays.deepToString(gomoku.getBoard()));
            game.setTurn(gomoku.getTurn());
            game.setWinner(gomoku.getWinner());
        }
        gameService.saveGame(game);
        gameMap.writeToMap(game.getId(),game);
        System.out.print("创建游戏成功：");
        System.out.println(id);
        return Result.success(game);
    }
    @PostMapping("/joingame")
    public Result joingame(String id){
        //这id是游戏的唯一标识
        System.out.print("收到加入游戏请求：");
        System.out.println(id);
        GAME game=gameService.findbyid(id).get();
        String[] players=game.getPlayers().split(",");
        if(players.length>1)
            return Result.error("已有两个人在玩，但你可观战");
        Map<String, Object> map = ThreadLocalUtil.get();
        String sender=(String) map.get("id");
        game.setPlayers(players[0]+","+sender);
        gameService.updateUser(game);
        gameMap.updateMap(game.getId(),game);
        System.out.print("加入游戏成功：");
        System.out.println(id);
        return Result.success(game);
    }
    @PostMapping("/leavegame")
    public Result leavegame(String id){
        GAME game=gameService.findbyid(id).get();
        String[] players=game.getPlayers().split(",");
        Map<String, Object> map1 = ThreadLocalUtil.get();
        String sender1=(String) map1.get("id");
        for (String player:players)
        {
            if(Objects.equals(sender1, player))
            {   gameService.deletegame(id);
                gameMap.deletefromMap(id);
                return Result.success("有人走了，删除当前游戏");
            }
        }
        return Result.success("观众走了");
    }
    @PostMapping("/play")
    public Result play(String id,int row,int col){
        System.out.print("接收到落子请求: ");
        System.out.print(row);
        System.out.print(",");
        System.out.println(col);
        GAME game=gameService.findbyid(id).get();
        Map<String, Object> map = ThreadLocalUtil.get();
        String sender=(String) map.get("id");
        String[] players=game.getPlayers().split(",");
        if(!Objects.equals(sender, players[0]) && !Objects.equals(sender, players[1]))
            return Result.error("你不是参与者");
        String[] player1s=game.getPlayers().split(",");
        if(player1s.length<2)
            return Result.error("人都没到齐");
        if((!Objects.equals(players[0], sender) && game.getTurn()==0)||((!Objects.equals(players[1], sender) &&game.getTurn()==1))){
            return Result.error("不是你下现在");
        }
        game.setPlayers(players[0]+","+players[1]);
        String str = game.getBoard().substring(2, game.getBoard().length() - 1);


        // 根据外部数组的分隔符（方括号对之间的逗号）分割字符串
        String[] rows = str.split(", \\[");
        System.out.print(rows[0]);
        // 初始化二维数组
        int[][] result = new int[rows.length][];

        // 遍历行并解析每行的元素
        for (int i = 0; i < rows.length; i++) {
            // 移除每行首尾的方括号
            rows[i] = rows[i].trim().substring(0, rows[i].length() - 1);

            // 根据内部数组的分隔符（逗号）分割字符串
            String[] cols = rows[i].split(",");

            // 将字符串转换为整数并存储在二维数组中
            int[] rowArray = new int[cols.length];
            for (int j = 0; j < cols.length; j++) {
                rowArray[j] = Integer.parseInt(cols[j].trim());
            }

            result[i] = rowArray;
        }

        int newwinner=-1;
        int gameid=game.getGameid();
        int turn=game.getTurn();
         turn=(turn==1?0:1);
        if(gameid==1){
            BoardGame gomoku=new Gomoku(players);
            gomoku.setBoard(result);
            gomoku.setActor(turn);
            String actor=gomoku.getActor();
            int[][] action = {{row, col}};
            if(!gomoku.move(actor,action))
                return Result.error("这个地方不能放棋子");
            if (gomoku.isEnd())
                game.setIsend(1);
            else
                game.setIsend(0);
            game.setBoard(Arrays.deepToString(gomoku.getBoard()));
            newwinner=gomoku.getWinner();
        }
        if (gameid==2){
            BoardGame gomoku=new Go(players);
            gomoku.setBoard(result);
            gomoku.setActor(turn);
            String actor=gomoku.getActor();
            int[][] action = {{row, col}};
            if(!gomoku.move(actor,action))
                return Result.error("这个地方不能放棋子");
            if (gomoku.isEnd())
                game.setIsend(1);
            else
                game.setIsend(0);
            game.setBoard(Arrays.deepToString(gomoku.getBoard()));
            newwinner=gomoku.getWinner();
        }
        if (gameid==3){
            BoardGame gomoku=new Reversi(players);
            gomoku.setBoard(result);
            gomoku.setActor(turn);
            String actor=gomoku.getActor();
            int[][] action = {{row, col}};
            if(!gomoku.move(actor,action))
                return Result.error("这个地方不能放棋子");
            if (gomoku.isEnd())
                game.setIsend(1);
            else
                game.setIsend(0);
            game.setBoard(Arrays.deepToString(gomoku.getBoard()));
            newwinner=gomoku.getWinner();
        }
        game.setTurn(turn);
        game.setWinner(newwinner);
        gameService.updateUser(game);
        gameMap.updateMap(game.getId(),game);
        return Result.success(game);
    }
    @GetMapping("/showallgames")
    public Result showallgames(){
        return Result.success(gameService.findallgames());
    }
    @GetMapping("/showgame")
    public Result showgame(String id){
        GAME game=gameService.findbyid(id).get();
        if (game==null)
            return Result.error("游戏已经结束了");
        return Result.success(game);
    }
}
