package com.battleground.prototype.service;

import com.battleground.prototype.mapper.CommentMapper;
import com.battleground.prototype.model.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by sugin on 15. 8. 13..
 */
@Service
public class CommentService {
    private static final Logger logger = LoggerFactory.getLogger(ArticleService.class);
    private long executionStartTime;
    private long executionEndTime;

    @Resource(name = "commentMapper")
    private CommentMapper commentMapper;

    public List<Comment> getComments(Map<String, Object> params){
        return commentMapper.getComments(params);
    }

    public boolean insertComment(Map<String, Object> params){
        return commentMapper.insertComment(params);
    }
}
