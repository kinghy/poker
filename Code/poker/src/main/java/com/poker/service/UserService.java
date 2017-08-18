package com.poker.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.poker.dao.UsersDAO;
import com.poker.entity.LoginEntity;
import com.poker.entity.ReturnEntity;
import com.poker.util.EncryptUtils;
import com.poker.vo.UserVO;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by rjt on 16/8/10.
 */
@Transactional
public class UserService {
    private UsersDAO userDao;

    static private String DES_KEY = "www.poker.com";


    public UsersDAO getUserDao() {
        return userDao;
    }

    public void setUserDao(UsersDAO userDao) {
        this.userDao = userDao;
    }

    public LoginEntity addUser(String username,String pwd,String nickname){
        UserVO user = new UserVO();
        user.setUsername(username);
        user.setNickname(nickname);
        user.setPwd(pwd);
        ReturnEntity ret = userDao.insertUser(user);
        LoginEntity entity = new LoginEntity();
        entity.setUid(user.getId().toString());
        entity.setNickname(user.getNickname());
        entity.setSessionid(encodeSessionid(user.getId().toString()));
        return entity;
    }

    public LoginEntity login(String username,String pwd){
        UserVO user = new UserVO();
        user.setUsername(username);
        UserVO ret = userDao.getUser(user);
        LoginEntity entity = new LoginEntity();
        if(ret!=null && ret.getUsername().equals(username) && ret.getPwd().equals(pwd)){
            entity.setUid(ret.getId().toString());
            entity.setNickname(ret.getNickname());
            entity.setSessionid(encodeSessionid(ret.getId().toString()));
        }else {
            entity.setResult(ReturnEntity.RETURN_FAILED);
            entity.setError_code("110006");
            entity.setError_msg("用户名或密码错误");
        }
        return entity;
    }

    public ReturnEntity removeUser(String id){
        UserVO user = new UserVO();
        user.setId(Integer.valueOf(id));
        ReturnEntity retEntity = userDao.deleteUser(user);
        return retEntity;
    }


    static public String encodeSessionid(String uid){
        Date date = new Date();
        String ret = "";
        try {
            ret = EncryptUtils.Encrypt3DES(uid+";"+date.getTime(),DES_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    static public boolean isSessionidValid(String uid,String sessionid){
        boolean ret = false;
        try {
            String plainText = EncryptUtils.Decrypt3DES(sessionid,DES_KEY);
            String[] strs = plainText.split(";");
            if (uid.equals(strs[0])){
                ret = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;

    }


}