package com.example.demo1.control;

import com.example.demo1.model.Result;
import com.example.demo1.model.Room;
import com.example.demo1.model.Talk;
import com.example.demo1.service.CacheService.TalkMap;
import com.example.demo1.service.DbService.RoomService;
import com.example.demo1.service.DbService.TalkService;
import com.example.demo1.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping
    public Result show(HttpServletRequest request, String context){
        System.out.print("获取发送信息请求,内容：");
        System.out.println(context);
        System.out.println(request);
        String roomname=request.getHeader("roomname");
        Map<String,Object> map = ThreadLocalUtil.get();
        String sendername=(String) map.get("fakename");
        String senderpic=(String) map.get("userpic");
        Talk talk=new Talk(context);
        talk.setChatroomname(roomname);
        talk.setSendername(sendername);
        talk.setSenderpic(senderpic);
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
        List<Talk> alltalk=talkService.findalltalk();
        List<Talk> out=new ArrayList<Talk>();
        for (Talk i:alltalk){
            if(Objects.equals(i.getChatroomname(), roomname)){
                out.add(i);
            }
        }
        return Result.success(out);
    }
    @PostMapping("/contextsearch")
    public Result search(HttpServletRequest request,String context){
        String roomname=request.getHeader("roomname");
        List<Talk> out=talkService.searchbycontextinroom(roomname,context);
        return Result.success(out);
    }

}
