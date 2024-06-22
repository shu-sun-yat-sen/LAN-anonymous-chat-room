package com.example.demo1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.sql.Update;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Entity
public class Room {

    @Id
    @NotEmpty
    private String roomName;
    @NotEmpty(groups = Update.class)
    private String roomOwnerId;
    private String roompic;
    private Integer numofpeople;
    private String passWord;
    private String membersId;
    // 无参构造函数
    public Room() {
    }
    // 构造函数
    public Room(String roomName) {
        this.roomName = roomName;
        numofpeople=1;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setRoompic(@NotEmpty(groups = Update.class) String roompic) {
        this.roompic = roompic;
    }

    public void setNumofpeople(Integer numofpeople) {
        this.numofpeople = numofpeople;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setRoomOwnerId(String roomOwnerId) {
        this.roomOwnerId = roomOwnerId;
    }

    public void setMembersId(String membersId) {
        if (this.membersId==null)
            this.membersId = membersId;
        else
            this.membersId+=","+membersId;
    }

    // Getter 方法

    public  String getRoompic() {
        return roompic;
    }

    public Integer getNumofpeople(){return numofpeople;}
    public String getRoomName() {
        return roomName;
    }

    public String getPassWord() {
        return passWord;
    }

    public List<String> getMembersId() {
       String[] item= membersId.split(",");

        return Arrays.asList(item);
    }
    public String gettureMemberid(){
        return membersId;
    }
    public void  deleteMembersId(String id){
        String[] item= membersId.split(",");
        String newmembersId="";
        for(int i=0;i<item.length;i++){
            newmembersId+=item[i];
            if(i!=item.length-1){
                newmembersId+=",";
            }
        }
        membersId=newmembersId;
    }
    public String getRoomOwnerId() {
        return roomOwnerId;
    }
    //其他
    public interface Add {

    }
    public interface Update{

    }
}