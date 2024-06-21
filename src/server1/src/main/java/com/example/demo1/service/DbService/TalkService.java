package com.example.demo1.service.DbService;

import com.example.demo1.mapper.DbMapper.TalkRepository;
import com.example.demo1.model.Talk;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class TalkService {
    @Autowired
    TalkRepository talkRepository;
    public void saveTalk(Talk talk){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedString = now.format(formatter);
        talk.setTime(formattedString);
        talkRepository.save(talk);
    }
    public List<Talk> findalltalk(){
        return talkRepository.findAll();
    }
    public List<Talk> findcontextbyroomname(String roomname){
        List<Talk> context=talkRepository.findAll();
        List<Talk> out=new ArrayList<Talk>();
        for(Talk i:context){
            if(Objects.equals(i.getChatroomname(), roomname))
                out.add(i);
        }
        return out;

    }
    public static boolean hasIntersection(String s1, String s2) {
        Set<Character> set1 = new HashSet<>();
        Set<Character> set2 = new HashSet<>();

        for (char c : s1.toCharArray()) {
            set1.add(c);
        }
        for (char c : s2.toCharArray()) {
            set2.add(c);
        }

        // 计算交集大小
        set1.retainAll(set2);
        return !set1.isEmpty();
    }
    public List<Talk> searchbycontextinroom(String roomname ,String context){
        List<Talk> med=findcontextbyroomname(context);
        List<Talk> out=new ArrayList<Talk>();
        for (Talk i:med){
            if (hasIntersection(i.getContext(),context))
                out.add(i);
        }
        return  out;
    }
    public void deleteAllTalks(){
        talkRepository.deleteAll();
    }

}
