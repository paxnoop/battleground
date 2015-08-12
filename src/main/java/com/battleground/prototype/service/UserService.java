package com.battleground.prototype.service;

import com.battleground.prototype.mapper.UserMapper;
import com.battleground.prototype.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by sugin on 15. 8. 12..
 */
@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(ArticleService.class);
    private long executionStartTime;
    private long executionEndTime;

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    public User getUserInfo(Map<String, Object> params){
        return userMapper.getUserInfo(params);
    }

    public String getUserID(Map<String, Object> params){
        return userMapper.getUserID(params);
    }

    public String getUserPW(Map<String, Object> params){
        return userMapper.getUserPW(params);
    }
}
