package com.example.demo1.control;

import com.example.demo1.model.Result;
import com.example.demo1.model.Room;
import com.example.demo1.model.Talk;
import com.example.demo1.service.DbService.TalkService;
import com.example.demo1.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/room/talk")
public class TalkController {
    @Autowired
    TalkService talkService;
    @PostMapping
    public Result show(HttpServletRequest request, String context){
        String roomname=request.getHeader("roomname");
        Map<String,Object> map = ThreadLocalUtil.get();
        String sendername=(String) map.get("fakename");
        String senderpic=(String) map.get("userpic");
        Talk talk=new Talk(context);
        talk.setChatroomname(roomname);
        talk.setSendername(sendername);
        talk.setSenderpic(senderpic);
        talkService.saveTalk(talk);
        return Result.success(talk);
    }
    @PostMapping("/contextsearch")
    public Result search(HttpServletRequest request,String context){
        String roomname=request.getHeader("roomname");
        List<Talk> out=talkService.searchbycontextinroom(roomname,context);
        return Result.success(out);
    }
}
