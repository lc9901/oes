package com.asher.oes.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import com.asher.oes.model.User;
import com.asher.oes.util.StringUtil;

public class UserDaoImpl {

    private JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User getUser(final String userName) {
        if (StringUtil.isNull(userName)) {
            return null;
        }
        RowMapper<User> rowMapper = new RowMapper<User>() {

            @Override
            public User mapRow(ResultSet rs, int rowNumber) throws SQLException {
                User users= new User();
                users.setId(rs.getInt("id"));
                users.setUserName(rs.getString("user_name"));
                users.setPassword(rs.getString("password"));
                return users;
            }
        };
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement prepareStatement = conn.prepareStatement("SELECT * FROM user WHERE user_name = ?");
                prepareStatement.setString(1, userName);
                return prepareStatement;
            }
        };
        List<User>userList = jdbcTemplate.query(preparedStatementCreator, rowMapper);


        return userList.get(0);
    }

    public int updateUserLoginTime(User user) {
        final int userId = user.getId();
        int count = 0;
        count = jdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                String sql = "UPDATE user SET  last_login_time = NOW() WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, userId);
                return preparedStatement;

            }
        });

        return count;

    }
}