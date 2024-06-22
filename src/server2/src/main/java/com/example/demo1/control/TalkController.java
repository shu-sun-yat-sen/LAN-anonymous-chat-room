package com.example.demo1.control;

import com.example.demo1.Alservice.AssistantmyService;
import com.example.demo1.model.Gomoku;
import com.example.demo1.model.Result;
import com.example.demo1.model.Room;
import com.example.demo1.model.Talk;
import com.example.demo1.service.CacheService.TalkMap;
import com.example.demo1.service.DbService.RoomService;
import com.example.demo1.service.DbService.TalkService;
import com.example.demo1.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/room/talk")
public class TalkController {
    @Autowired
    private TalkService talkService;
    @Autowired
    private TalkMap talkMap;
    @Autowired
    private AssistantmyService assistantmyService;
    @PostMapping
    public Result show(HttpServletRequest request, String context){
        System.out.print("获取发送信息请求,内容：");
        System.out.println(context);
        System.out.println(request);
        String judge=context.toLowerCase();

        String roomname=request.getHeader("roomname");
        Map<String,Object> map = ThreadLocalUtil.get();
        String senderid=(String) map.get("id");
        String sendername=(String) map.get("fakename");
        String senderpic=(String) map.get("userpic");
        Talk talk=new Talk(context);
        talk.setChatroomname(roomname);
        talk.setSendername(sendername);
        talk.setSenderpic(senderpic);
        talk.setSenderid(senderid);
        talk.setType("text");
        if(judge.contains("@chatai")){
            String newcontext = context.substring(7);
            List<Talk> out=new ArrayList<Talk>();
            out.add(talk);
            talkMap.writeToMap(talk.getTime(),talk);
            talkService.saveTalk(talk);
            int memoryId = Integer.parseInt(senderid);
            Talk talk1=new Talk(assistantmyService.assistant(memoryId, newcontext));
            talk1.setChatroomname(roomname);
            talk1.setSendername("chatai");
            talk1.setSenderpic("/robot.jpg");
            talk1.setType("text");
            out.add(talk1);
            talkMap.writeToMap(talk1.getTime(),talk1);
            talkService.saveTalk(talk1);
            return Result.success(out);
        }
        talkMap.writeToMap(talk.getTime(),talk);
        talkService.saveTalk(talk);
        return Result.success(talk);
    }
    @GetMapping
    public Result<List<Talk>> list(HttpServletRequest request){
        System.out.println("获取获取信息请求");
        System.out.println(request);
        String roomname=request.getHeader("roomname");
        System.out.println(request.getHeader("roomname"));
        String time=request.getHeader("time");
        if(time==null){
        List<Talk> alltalk=talkService.findalltalk();
        List<Talk> out=new ArrayList<Talk>();
        for (Talk i:alltalk){
            if(Objects.equals(i.getChatroomname(), roomname)){
                out.add(i);
            }
        }
        return Result.success(out);}
        else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime1 = LocalDateTime.parse(time, formatter);
            List<Talk> output=new ArrayList<Talk>();
            List<Talk> timeall=talkService.findalltalk();
            for (Talk talk:timeall){
                LocalDateTime dateTime=LocalDateTime.parse(talk.getTime(),formatter);
                if(dateTime.compareTo(dateTime1)>0&& Objects.equals(talk.getChatroomname(), roomname))
                    output.add(talk);
            }
            return Result.success(output);

        }
    }
    @PostMapping("/contextsearch")
    public Result search(HttpServletRequest request,String context){
        String roomname=request.getHeader("roomname");
        List<Talk> out=talkService.searchbycontextinroom(roomname,context);
        return Result.success(out);
    }
    @PostMapping("/addgame")
    public Result add5game(HttpServletRequest request,String player){

        Map<String,Object> map = ThreadLocalUtil.get();
        String id=(String) map.get("id");
        Gomoku gomoku=new Gomoku(id,player);
        String roomname=request.getHeader("roomname");
        List<Talk> alltalks=talkService.findalltalk();
        for(Talk talk:alltalks){
            if(talk.getType()=="game"&&talk.getChatroomname()==roomname)
                return Result.success(talk);
        }
        String sendername=(String) map.get("fakename");
        String senderpic=(String) map.get("userpic");
        Talk talk=new Talk(gomoku.toString());
        talk.setChatroomname(roomname);
        talk.setSendername(sendername);
        talk.setSenderpic(senderpic);
        talk.setSenderid(id);
        talk.setType("game");
        talkMap.writeToMap(talk.getTime(),talk);
        talkService.saveTalk(talk);
        return Result.success(talk);
    }
    @GetMapping("/getgame")
    public Result getgame(HttpServletRequest request){
        String roomname=request.getHeader("roomname");
        List<Talk> alltalks=talkService.findalltalk();
        for(Talk talk:alltalks){
            if(Objects.equals(talk.getType(), "game") && Objects.equals(talk.getChatroomname(), roomname))
                return Result.success(talk);
        }
        return Result.error("游戏结束了");
    }
    @DeleteMapping("/deletegame")
    public Result deletegame(HttpServletRequest request){
        String roomname=request.getHeader("roomname");
        Map<String,Object> map = ThreadLocalUtil.get();
        String id=(String) map.get("id");
        List<Talk> alltalks=talkService.findalltalk();
        for(Talk talk:alltalks){
            if(Objects.equals(talk.getType(), "game") && Objects.equals(talk.getChatroomname(), roomname))
            {
                Gomoku gomoku=Gomoku.fromString(talk.getContext());
                String[]s=gomoku.getPlayers();
                if (s[0]!=id&&s[1]!=id){
                    return Result.error("你无权删除这个游戏");
                }
                else {
                    talkService.deletetalk(talk);
                    talkMap.deletefromMap(talk.getTime());
                    return Result.success();
                }
            }
        }
        return Result.error("");
    }
    @PostMapping("/changegame")
    public Result changegame(HttpServletRequest request,String context){
    Gomoku gomoku=Gomoku.fromString(context);
    String[] players=gomoku.getPlayers();
    Map<String,Object> map = ThreadLocalUtil.get();
    String id=(String) map.get("id");
    if(id==players[0]||id==players[1]){
        String roomname=request.getHeader("roomname");
        List<Talk> alltalks=talkService.findalltalk();
        for(Talk talk:alltalks) {
            if (Objects.equals(talk.getType(), "game") && Objects.equals(talk.getChatroomname(), roomname)) {
                talk.setContext(context);
                talkService.updateTalk(talk);
                talkMap.updateMap(talk.getTime(), talk);
                return Result.success();
            }

        }

    }

        return Result.success();
    }


}
