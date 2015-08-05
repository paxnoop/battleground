package com.battleground.prototype.controller;

import com.battleground.prototype.dao.SpringDao;
import com.battleground.prototype.model.Article;
import com.battleground.prototype.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by sugin on 15. 7. 29..
 */
@RestController
public class APIController {

    @Autowired
    @Qualifier("articleService")
    private ArticleService articleService;

    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public List getArticles() throws Exception{
        return articleService.getArticles(null);
    }
}
