package com.example.demo1.service.DbService;

import com.example.demo1.mapper.DbMapper.GameRepository;
import com.example.demo1.model.GAME;
import com.example.demo1.model.Room;
import com.example.demo1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    @Autowired
    GameRepository gameRepository;

    public void saveGame(GAME game){
        gameRepository.save(game);
    }
    public boolean checkid(String id){
        return gameRepository.existsById(id);
    }
    public void updateUser(GAME game) {
        gameRepository.save(game);
    }
    public Optional<GAME> findbyid(String id){
        return gameRepository.findById(id);
    }
    public List<GAME> findallgames(){
        return gameRepository.findAll();
    }
    public void deletegame(String id){
        gameRepository.deleteById(id);
    }
}
