package com.battleground.prototype.mapper;

import com.battleground.prototype.model.Article;

import java.util.List;
import java.util.Map;

/**
 * Created by sugin on 15. 7. 29..
 */
public interface LeftMenuMapper {
    List<Article> getLeftMenu(Map<String, Object> param);
}
