package com.battleground.prototype.service;


import com.battleground.prototype.mapper.ArticleMapper;
import com.battleground.prototype.mapper.LeftMenuMapper;
import com.battleground.prototype.model.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by sugin on 15. 7. 29..
 */
@Service
public class LeftMenuService {
    private static final Logger logger = LoggerFactory.getLogger(LeftMenuService.class);
    private long executionStartTime;
    private long executionEndTime;

    @Resource(name = "leftMenuMapper")
    private LeftMenuMapper leftMenuMapper;

    public List<Article> getLeftMenu(Map<String, Object> param) throws Exception{
        List<Article> articles = leftMenuMapper.getLeftMenu(param);
        return articles;
    }



}
