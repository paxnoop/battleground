package com.battleground.prototype.mapper;

import com.battleground.prototype.model.Comment;

import java.util.List;
import java.util.Map;

/**
 * Created by sugin on 15. 8. 13..
 */
public interface CommentMapper {
    List<Comment> getComments(Map<String, Object> params);
    boolean insertComment(Map<String, Object> params);
}
