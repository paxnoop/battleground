package com.battleground.prototype.service;


import com.battleground.prototype.mapper.ArticleMapper;
import com.battleground.prototype.model.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sugin on 15. 7. 29..
 */
@Service
public class ArticleService {
    private static final Logger logger = LoggerFactory.getLogger(ArticleService.class);
    private long executionStartTime;
    private long executionEndTime;

    @Resource(name = "articleMapper")
    private ArticleMapper articleMapper;

    public List<Article> getArticles(Map<String, Object> param) throws Exception{
        List<Article> articles = articleMapper.getAllArticles(param);
        return articles;
    }



}
