package com.poker.entity;

import com.poker.vo.UserVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rjt on 16/8/30.
 */
public class UserEntity extends ReturnEntity{
    List<UserVO> records = new ArrayList<UserVO>();

    public List<UserVO> getRecords() {
        return records;
    }

    public void setRecords(List<UserVO> records) {
        this.records = records;
    }
}
