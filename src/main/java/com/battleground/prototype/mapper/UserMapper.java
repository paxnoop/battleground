package com.battleground.prototype.mapper;

import com.battleground.prototype.model.User;

import java.util.Map;

/**
 * Created by sugin on 15. 8. 12..
 */
public interface UserMapper {
    String getUserID(Map<String, Object> param);
    String getUserPW(Map<String, Object> param);
    User getUserInfo(Map<String, Object> param);
}
