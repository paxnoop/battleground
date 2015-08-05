package com.battleground.prototype.mapper;

import com.battleground.prototype.model.Article;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by sugin on 15. 7. 29..
 */
public interface ArticleMapper {
    List<Article> getAllArticles(Map<String, Object> param);
}
