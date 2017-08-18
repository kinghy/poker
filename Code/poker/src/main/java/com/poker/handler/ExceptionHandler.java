package com.poker.handler;

/**
 * Created by rjt on 16/8/11.
 */
import com.poker.entity.ReturnEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class ExceptionHandler{

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseBody
    public ReturnEntity processUnauthenticatedException(Exception e) {
        e.printStackTrace();
        ReturnEntity ret = new ReturnEntity();
        ret.setResult(ReturnEntity.RETURN_FAILED);
        ret.setError_msg(e.getMessage());
        return ret; //返回一个ReturnEntity
    }

}