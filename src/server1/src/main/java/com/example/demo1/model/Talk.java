package com.example.demo1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Talk {
    @Id
    private String time;
    private String chatroomname;
    private String senderid;
    private String sendername;
    private String senderpic;
    private String context;
    private String type;
    public Talk(){}
    public Talk(String context){
        this.context=context;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedString = now.format(formatter);
        this.time=formattedString;

    }
    //GET函数
    public String getType(){return type;}
    public String getChatroomname() {
        return chatroomname;
    }

    public String getContext() {
        return context;
    }

    public String getSendername() {
        return sendername;
    }

    public String getSenderpic() {
        return senderpic;
    }

    public String getSenderid() {
        return senderid;
    }

    public String getTime() {
        return time;
    }
    //SET函数

    public void setType(String type) {
        this.type = type;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    public void setChatroomname(String chatroomname) {
        this.chatroomname = chatroomname;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setSendername(String sendername) {
        this.sendername = sendername;
    }

    public void setSenderpic(String senderpic) {
        this.senderpic = senderpic;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
