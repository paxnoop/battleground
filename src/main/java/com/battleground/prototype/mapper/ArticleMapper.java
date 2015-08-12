package com.battleground.prototype.mapper;

import com.battleground.prototype.model.Article;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by sugin on 15. 7. 29..
 */
public interface ArticleMapper {
    List<Article> getArticleTitles(Map<String, Object> param);
    Article getArticle(Map<String, Object> param);
    boolean insertArticle(Map<String, Object> map);
    void addClickCount(Map<String, Object> map);
}
