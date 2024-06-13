package com.example.demo1.control;

import com.example.demo1.model.Result;
import com.example.demo1.model.User;
import com.example.demo1.service.DbService.UserService;
import com.example.demo1.utils.JwtUtil;
import com.example.demo1.utils.Md5Util;
import com.example.demo1.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public Result register(String id,String fakename,String password){


      if(!userService.checkid(id)){
          // 定义电话号码的正则表达式
          if (Objects.equals(id, null)){
              return Result.error("邮箱或电话不能为空");
          }
          String phoneRegex = "^1[3456789]\\d{9}$";
          // 定义邮箱地址的正则表达式
          String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
          Pattern phonePattern = Pattern.compile(phoneRegex);
          Pattern emailPattern = Pattern.compile(emailRegex);
          Matcher phoneMatcher = phonePattern.matcher(id);
          Matcher emailMatcher = emailPattern.matcher(id);
          //匹配电话
          if (phoneMatcher.matches()){
              User s=new User(id,fakename, Md5Util.getMD5String(password) );
              s.setCookie("0success");
              userService.saveUser(s);
              return Result.success();
          } //匹配邮箱
          else if (emailMatcher.matches()) {
              User s=new User(id,fakename,Md5Util.getMD5String(password));
              s.setCookie("1success");
              userService.saveUser(s);
              return Result.success();
          }
          else {
              return Result.error("电话或邮箱的格式不对");
          }
      }else {
          return Result.error("用户名被占用");
      }

    }
    @PostMapping("/login")
    public Result login(String id,String password){
        System.out.print("接收到登录请求: id:");
        System.out.print(id);
        System.out.print(", password: ");
        System.out.print(password);
        if(!userService.checkid(id)){
            return Result.error("用户不存在");
        }
        else {
            User u=userService.findUserById(id).get();
            if(Objects.equals(u.getPasswd(),Md5Util.getMD5String(password) )){
                Map<String,Object> claims=new HashMap<>();
                claims.put("id",u.getUserId());
                claims.put("fakename",u.getFakeName());
                claims.put("userpic",u.getUserpic());
                String token=JwtUtil.genToken(claims);
                return Result.success(token);
            }
            return Result.error("密码不正确");
        }
    }
    @GetMapping("/userinfo")
    public Result<User> userinfo(){
        Map<String,Object> map= ThreadLocalUtil.get();
        String id=(String)map.get("id");
        User user=userService.findUserById(id).get();
        return Result.success(user);
    }
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        String id=user.getUserId();
        if(!userService.checkid(id)){
            // 定义电话号码的正则表达式
            if (Objects.equals(id, null)){
                return Result.error("邮箱或电话不能为空");
            }
            String phoneRegex = "^1[3456789]\\d{9}$";
            // 定义邮箱地址的正则表达式
            String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
            Pattern phonePattern = Pattern.compile(phoneRegex);
            Pattern emailPattern = Pattern.compile(emailRegex);
            Matcher phoneMatcher = phonePattern.matcher(id);
            Matcher emailMatcher = emailPattern.matcher(id);
            //匹配电话
            if (phoneMatcher.matches()){
                userService.updateUser(user);
                return Result.success();
            } //匹配邮箱
            else if (emailMatcher.matches()) {
                userService.updateUser(user);
                return Result.success();
            }
            else {
                return Result.error("电话或邮箱的格式不对");
            }
        }else {
            return Result.error("用户名被占用");
        }

    }
    @GetMapping("/updateAvatar")
    public Result<List<File>> show(){
        File folder = new File("src/userpic");
        File[] files = folder.listFiles();
        List<File> out=new ArrayList<File>();
        for(File i:files){
            out.add(i);
        }
        return Result.success(out);
    }
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam String avatarUrl){
       Map<String,Object> map= ThreadLocalUtil.get();
       String id=(String) map.get("id");
       userService.updateAvatar(avatarUrl,id);
       return Result.success();

    }


}
