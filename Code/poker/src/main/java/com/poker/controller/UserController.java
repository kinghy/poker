package com.poker.controller;

import com.poker.entity.LoginEntity;
import com.poker.entity.ReturnEntity;
import com.poker.service.UserService;
import com.poker.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by rjt on 16/8/10.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource(name="userService")
    private UserService service;

//    @ResponseBody
//    @RequestMapping(value="/register",method = RequestMethod.POST)
//    public LoginEntity register(
//            @RequestParam("email") String email,
//            @RequestParam("pwd") String pwd,
//            @RequestParam("fullname") String fullname){
//        LoginEntity retEntity = service.addUser(email, pwd, fullname);
//        return retEntity;
//    }

//    @ResponseBody
//    @RequestMapping(value="/login",method = RequestMethod.POST)
//    public LoginEntity register(
//            @RequestParam("email") String email,
//            @RequestParam("pwd") String pwd){
//        LoginEntity retEntity = service.login(email, pwd);
//        return retEntity;
//    }

    @ResponseBody
    @RequestMapping(value="/register",method = RequestMethod.POST)
    public LoginEntity register(@RequestBody UserVO user){

        LoginEntity retEntity = service.addUser(user.getUsername(), user.getPwd(), user.getNickname());
        return retEntity;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public LoginEntity saveUser(@RequestBody UserVO user) {
        System.out.println(user);
        LoginEntity retEntity = service.login(user.getUsername(), user.getPwd());
        return retEntity;
    }

    @ResponseBody
    @RequestMapping(value="/remove",method = RequestMethod.POST)
    public ReturnEntity remove(
            @RequestParam("uid") String uid){
        ReturnEntity retEntity = service.removeUser(uid);
        return retEntity;
    }

}
