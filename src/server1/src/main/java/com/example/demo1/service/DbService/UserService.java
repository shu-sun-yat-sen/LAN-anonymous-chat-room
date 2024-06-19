package com.example.demo1.service.DbService;

import com.example.demo1.mapper.DbMapper.UserRepository;
import com.example.demo1.model.User;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    // 保存用户
    public void saveUser(User user) {
        userRepository.save(user);
    }
    //检查id
    public boolean checkid(String id){
        return userRepository.existsById(id);
    }

    // 根据用户ID查找用户
    public Optional<User> findUserById(String userId) {
        return userRepository.findById(userId);
    }

    // 查找所有用户
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    // 更新用户信息
    public void updateUser(User user) {
        userRepository.save(user);
    }

    // 删除用户
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
    //更新头像
    public void updateAvatar(String avatarUrl, String id){
        User user=userRepository.findById(id).get();
        System.out.print(avatarUrl);
        user.setUserpic(avatarUrl);
        updateUser(user);
    }
    public void deleteAllUsers(){
        userRepository.deleteAll();
    }
}
