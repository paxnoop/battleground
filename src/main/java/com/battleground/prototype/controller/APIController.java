package com.battleground.prototype.controller;

import com.battleground.prototype.model.Article;
import com.battleground.prototype.service.ArticleService;
import com.battleground.prototype.service.LeftMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

/**
 * Created by sugin on 15. 7. 29..
 */
@RestController
public class APIController {

    @Autowired
    @Qualifier("articleService")
    private ArticleService articleService;

    @Autowired
    @Qualifier("leftMenuService")
    private LeftMenuService leftMenuService;

    @RequestMapping(value = "/api/articles", method = RequestMethod.GET)
    public List getArticles(@RequestParam String location1,
                            @RequestParam String location2) throws Exception{
        Map<String, Object> param = new HashMap<>();
        param.put("location1", location1);
        param.put("location2", location2);
        return articleService.getArticles(param);
    }

    @RequestMapping(value = "/api/article", method = RequestMethod.GET)
    public Article getArticle(@RequestParam String a_id) throws Exception{
        Map<String, Object> param = new HashMap<>();
        param.put("a_id", a_id);
        return articleService.getArticle(param);
    }

    @RequestMapping(value = "/api/leftmenu", method = RequestMethod.GET)
    public List getLeftMenus(@RequestParam String location1) throws Exception{
        Map<String, Object> param = new HashMap<>();
        param.put("location1", location1);
        return leftMenuService.getLeftMenu(param);
    }
}
