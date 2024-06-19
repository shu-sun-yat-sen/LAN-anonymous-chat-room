package com.example.demo1.service.DbService;

import com.example.demo1.mapper.DbMapper.RoomRepository;
import com.example.demo1.model.Room;
import com.example.demo1.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;
    // 保存聊天室
    public void saveRoom(Room room) {
        Map<String,Object> map= ThreadLocalUtil.get();
        room.setRoomOwnerId((String) map.get("id"));
        roomRepository.save(room);
    }
    //检查id
    public boolean checkroomname(String roomName){
        return roomRepository.existsById(roomName);
    }

    // 根据聊天室名字查找聊天室
    public Optional<Room> findRoomByRoomName(String roomName) {
        return roomRepository.findById(roomName);
    }
    public List<Room> findRoomsByOwerner(String owernerid){
        List<Room> u=roomRepository.findAll();
        List<Room> out = new ArrayList<Room>();
        for(Room room:u){
            if(Objects.equals(room.getRoomOwnerId(), owernerid)){
                out.add(room);
            }
        }
        return out;
    }


    // 查找所有聊天室
    public List<Room> findAllRooms() {
        return roomRepository.findAll();
    }

    // 更新聊天室信息
    public void updateRoom(Room room) {
        roomRepository.save(room);
    }

    // 删除聊天室
    public void deleteRoom(String roomName) {
        roomRepository.deleteById(roomName);
    }
    public  void deleteAllRooms(){
        roomRepository.deleteAll();
    }
}
