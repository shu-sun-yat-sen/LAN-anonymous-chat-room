package com.example.demo1.control;

import com.example.demo1.model.Result;
import com.example.demo1.model.Room;
import com.example.demo1.model.User;
import com.example.demo1.service.CacheService.RoomMap;
import com.example.demo1.service.DbService.RoomService;
import com.example.demo1.utils.JwtUtil;
import com.example.demo1.utils.Md5Util;
import com.example.demo1.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.h2.command.dml.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomMap roomMap;
    @PostMapping
    public Result add(String roomName){
        System.out.print("接收到创建房间请求: roomName:");
        System.out.println(roomName);
        if(roomService.checkroomname(roomName))
            return Result.error("群名已存在");
        Map<String,Object> map=ThreadLocalUtil.get();
        String id=(String)map.get("id");
        Room room=new Room(roomName);
        room.setRoomOwnerId(id);
        room.setMembersId(id);
        room.setPassWord(Md5Util.getMD5String(id));
        roomMap.writeToMap(room.getRoomName(),room);
        roomService.saveRoom(room);
        return Result.success();
    }
    @GetMapping
    public Result<List<String>> list(){
//        System.out.println("接收到获取房间请求");
        List<String> output=new ArrayList<String>();
        List<Room> medie=roomService.findAllRooms();
        for (Room room:medie){
            output.add(room.getRoomName());
        }
        return Result.success(output);
    }
    @GetMapping("/roominfo")
    public  Result<Room> roomshow(String roomName){
        System.out.println("接收到获取房间信息请求");
        System.out.println(roomName);
        Map<String,Object> map =ThreadLocalUtil.get();
        String id=(String) map.get("id");
        if(Objects.equals(roomService.findRoomByRoomName(roomName).get().getRoomOwnerId(), id))
            return Result.success(roomService.findRoomByRoomName(roomName).get());
        else
        { Room room=new Room(roomName);
          Room  roomnow=roomService.findRoomByRoomName(roomName).get();
            room.setRoomOwnerId(roomnow.getRoomOwnerId());
            room.setNumofpeople(roomnow.getNumofpeople());
            return Result.success(room);}

    }
    @PostMapping("/roominfo")
    public Result join(HttpServletRequest request, String password){
        System.out.print("接收到更新房间信息请求, request: ");
        System.out.println(request);
        System.out.print("password: ");
        System.out.println(password);
        String roomname=request.getHeader("roomname");
        Room room=roomService.findRoomByRoomName(roomname).get();
        List<String> roomnowmembersid=room.getMembersId();
        Map<String,Object> map =ThreadLocalUtil.get();
        String id=(String) map.get("id");
        String fakename=(String) map.get("fakename");
        String userpic=(String) map.get("userpic");
        User u=new User(id,fakename,"");
        u.setUserpic(userpic);
        for(String mem:roomnowmembersid){
            if(Objects.equals(mem, id)){
                return Result.success(u);
            }
        }
        if (Objects.equals(password, room.getPassWord())){
            room.setMembersId(id);
            int num=room.getNumofpeople()+1;
            room.setNumofpeople(num);
            roomMap.updateMap(room.getRoomName(),room);
            return Result.success(u);
        }
        else
            return Result.error("密码不正确");
    }

    @PutMapping("/roominfo")
    public Result update(HttpServletRequest request,String roomnanme,String owenerid){
        String roomname=request.getHeader("roomname");
        Room room=roomService.findRoomByRoomName(roomname).get();
        String id=room.getRoomOwnerId();
        Map<String,Object> map =ThreadLocalUtil.get();
        String id1=(String) map.get("id");
        if(!Objects.equals(id1, id)){
            return Result.error("你没有这个权限");
        }
        if (Objects.equals(owenerid, null)){
            if(roomService.checkroomname(roomname)&& !Objects.equals(roomname, room.getRoomName()))
            {
                return Result.error("群名已存在");}
            else{
                room.setRoomName(roomname);
                roomMap.updateMap(room.getRoomName(),room);
                return Result.success();}
        }
        String phoneRegex = "^1[3456789]\\d{9}$";
        // 定义邮箱地址的正则表达式
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern phonePattern = Pattern.compile(phoneRegex);
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher phoneMatcher = phonePattern.matcher(owenerid);
        Matcher emailMatcher = emailPattern.matcher(owenerid);
        //匹配电话邮箱
        if (emailMatcher.matches()||phoneMatcher.matches()) {

            if(roomService.checkroomname(roomname)&& !Objects.equals(roomname, room.getRoomName()))
            {
                return Result.error("群名已存在");}
            else{
                room.setRoomName(roomname);
                roomMap.updateMap(room.getRoomName(),room);
                return Result.success();}
        }
        else {
            return Result.error("电话或邮箱的格式不对");
        }
    }
    @DeleteMapping("/roominfo")
    public Result delete(HttpServletRequest request){
        String roomname=request.getHeader("roomname");
        Room room=roomService.findRoomByRoomName(roomname).get();
        if(roomService.checkroomname(room.getRoomName())){
            Map<String,Object> map =ThreadLocalUtil.get();
            String id=(String) map.get("id");
            if(Objects.equals(id, roomService.findRoomByRoomName(room.getRoomName()).get().getRoomOwnerId()))
            {roomService.deleteRoom(room.getRoomName());
                roomMap.deletefromMap(room.getRoomName());
            return Result.success();}
            else
                return Result.error("你没有这个权限");
        }
        else {
            return Result.error("没有那个群");
        }
    }

    @PostMapping("/addmenber")
    public Result addmembers(HttpServletRequest request,String id){
        String roomname=request.getHeader("roomname");
        Room room=roomService.findRoomByRoomName(roomname).get();
        Map<String,Object> map =ThreadLocalUtil.get();
        String idowener=(String) map.get("id");

        if(!Objects.equals(idowener, room.getRoomOwnerId())){
            return Result.error("你没有这个权限");
        }
        List<String> roomnowmembersid=room.getMembersId();
        String phoneRegex = "^1[3456789]\\d{9}$";
        // 定义邮箱地址的正则表达式
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern phonePattern = Pattern.compile(phoneRegex);
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher phoneMatcher = phonePattern.matcher(id);
        Matcher emailMatcher = emailPattern.matcher(id);
        //匹配电话邮箱
        if (emailMatcher.matches()||phoneMatcher.matches()) {
            for(String mem:roomnowmembersid){
                if(Objects.equals(mem, id)){
                    return Result.error("该用户已在聊天室");
                }
            }
            int num=room.getNumofpeople()+1;
            room.setNumofpeople(num);
            room.setMembersId(id);
            roomMap.updateMap(room.getRoomName(),room);
            return Result.success();
        }
        else {
            return Result.error("电话或邮箱的格式不对");
        }


    }
    @PostMapping("/deletemember")
    public Result deletemembers(HttpServletRequest request,String id){
        String roomname=request.getHeader("roomname");
        Room room=roomService.findRoomByRoomName(roomname).get();
        Map<String,Object> map =ThreadLocalUtil.get();
        String idowener=(String) map.get("id");
        if(!Objects.equals(idowener, room.getRoomOwnerId())){
            return Result.error("你没有这个权限");
        }
        if (id==room.getRoomOwnerId()){
            return Result.error("群主不可删");
        }
        List<String> roomnowmembersid=room.getMembersId();
        for(String mem:roomnowmembersid){
            if(Objects.equals(mem, id)){
                room.deleteMembersId(id);
                roomMap.updateMap(room.getRoomName(),room);
                return Result.success();
            }
        }
        return Result.error("没有该用户");

    }


}
