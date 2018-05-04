package com.asher.oes.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.asher.oes.enums.QuestionEnum;
import com.asher.oes.model.Pagination;
import com.asher.oes.model.Question;
import com.asher.oes.util.StringUtil;

public class QuestionDaoImpl {

    private JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Insert and Get question id at the same time
     * @return id
     */
    public int insertQuestionId() {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                String sql = "INSERT INTO question (`question_status`, `create_time`) VALUES (?, NOW())";
                PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, QuestionEnum.DRAFT.toString());

                return preparedStatement;
            }
        }, keyHolder);

        int questionId = keyHolder.getKey().intValue();

        return questionId;
    }

    /**
     *update question and option by user.
     * @param question
     * @param optMap
     * @return
     */
    public int updateQuestion(final Question question) {

        int count = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
               String sql = "UPDATE question SET question_status = '" + QuestionEnum.ACTIVE + "', user_id = ? , description = ?, answer = ?, update_time = NOW() ,option_a = ?, option_b = ?, option_c = ?, option_d = ? WHERE id = ?";
               PreparedStatement preparedStatement = conn.prepareStatement(sql);
               preparedStatement.setInt(1, question.getUserId());
               preparedStatement.setString(2, question.getDescription());
               preparedStatement.setString(3, question.getAnswer());
               preparedStatement.setString(4, question.getOptionA());
               preparedStatement.setString(5, question.getOptionB());
               preparedStatement.setString(6, question.getOptionC());
               preparedStatement.setString(7, question.getOptionD());
               preparedStatement.setInt(8, question.getId());
               return preparedStatement;
            }
        });

        return count;
    }

    /**
     * get Question list
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public List<Question> queryQuestionList(Pagination pagination) {
        List<Question> questionList = new ArrayList<Question>();

        String sql = "SELECT `id`, `description`, `answer` FROM `question` WHERE question_status = '" + QuestionEnum.ACTIVE + "' ORDER BY id " + pagination.getOrderBy() + " LIMIT " + pagination.getOffSet() + ", " + pagination.getPageSize();
        RowMapper<Question> rowMapper = new RowMapper<Question>() {
            @Override
            public Question mapRow(ResultSet rs, int rowNumber) throws SQLException {
                Question question =new Question();
                int id = rs.getInt("id");
                String description = rs.getString("description");
                String answer = rs.getString("answer");
                question.setId(id);
                question.setDescription(description);
                question.setAnswer(answer);

              return question;
            }
        };
        questionList = jdbcTemplate.query(sql, rowMapper);

        return questionList;
    }

    /**
     * get total count about question list
     * @return
     */
    public int getTotalCount(String keyWord) {
        String sql = "SELECT COUNT(id) FROM `question` WHERE question_status = '" + QuestionEnum.ACTIVE + "'" + (StringUtil.isNull(keyWord) ? "": "AND description LIKE '%" +keyWord+ "%' OR id LIKE '%" + keyWord + "%'");
        int queryForInt = jdbcTemplate.queryForInt(sql);

        return queryForInt;
    }

    public int deleteQuestion(String deletes) {
        final String[] deleteList = deletes.split(",");
        String where = "";
        for (int i = 0; i < deleteList.length - 1; i++) {
            where += "?, ";
        }
        where += "?";

        final String sql = "UPDATE question SET question_status = '" + QuestionEnum.DELETE + "' WHERE id IN ( " + where + " )";
        int count = -1;
        count = jdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                for (int i = 0; i < deleteList.length; i++) {
                    preparedStatement.setString(i+1, deleteList[i]);
                }
                return preparedStatement;
            }
        });
        return count;
    }

    public Question queryOneQuestion(final Question question) {
        final String sql = "SELECT `id`, `description`, `answer`, `option_a`, `option_b`, `option_c`, `option_d` FROM `question` WHERE id = ?";
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, question.getId());
                return preparedStatement;
            }
        };
        RowMapper<Question> rowMapper = new RowMapper<Question>() {

            @Override
            public Question mapRow(ResultSet rs, int rowNumber) throws SQLException {
                Question questions = new Question();
                questions.setId(rs.getInt("id"));
                questions.setDescription(rs.getString("description"));
                questions.setAnswer(rs.getString("answer"));
                questions.setOptionA(rs.getString("option_a"));
                questions.setOptionB(rs.getString("option_b"));
                questions.setOptionC(rs.getString("option_c"));
                questions.setOptionD(rs.getString("option_d"));
                return questions;
            }
        };
        return jdbcTemplate.query(preparedStatementCreator, rowMapper).get(0);
    }
}