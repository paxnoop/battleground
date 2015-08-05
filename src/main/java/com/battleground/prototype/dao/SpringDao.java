package com.battleground.prototype.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by sugin on 15. 7. 31..
 */

@Repository
public class SpringDao {
    @Autowired
    private SqlSession sql;

    @SuppressWarnings("unchecked")
    public ArrayList<Map<String, Object>> queryTest() throws SQLException {
        return (ArrayList)sql.selectList("sql.queryTest");
    }
}
