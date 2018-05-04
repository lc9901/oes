package com.asher.oes.dao.mybaties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.asher.oes.dao.QuestionDao;
import com.asher.oes.model.Pagination;
import com.asher.oes.model.Question;

public class QuestionDaoImpl extends SqlSessionDaoSupport implements QuestionDao {

    private static final String CLASS_NAME = Question.class.getName();
    private static final String SQL_ID_INT_INSERT_QUESTION_ID = ".insertQuestionId";
    private static final String SQL_ID_INT_UPDATE_QUESTION = ".updateQuestion";
    private static final String SQL_ID_LIST_QUERY_QUESTION_LIST = ".queryQuestionList";
    private static final String SQL_ID_INT_GET_TOTAL_COUNT = ".getTotalCount";
    private static final String SQL_ID_INT_DELETE_QUESTION = ".deleteQuestion";
    private static final String SQL_ID_INT_QUERY_ONE_QUESTION = ".queryOneQuestion";

    @Override
    public int insertQuestionId() {
        Question question = new Question();
        getSqlSession().insert(CLASS_NAME + SQL_ID_INT_INSERT_QUESTION_ID, question);
        return question.getId();
    }

    @Override
    public int updateQuestion(Question question) {
        return getSqlSession().update(CLASS_NAME + SQL_ID_INT_UPDATE_QUESTION, question);
    }

    @Override
    public List<Question> queryQuestionList(Pagination pagination) {
        int offSet = pagination.getOffSet();
        int pageSize = pagination.getPageSize();
        String orderBy = pagination.getOrderBy();
        String search = pagination.getSearch();
        Map<String, Object> paramerMap = new HashMap<String, Object>();
        paramerMap.put("offSet", offSet);
        paramerMap.put("pageSize", pageSize);
        paramerMap.put("orderBy", orderBy);
        paramerMap.put("search", search);
        return getSqlSession().selectList(CLASS_NAME + SQL_ID_LIST_QUERY_QUESTION_LIST, paramerMap);
    }

    @Override
    public int getTotalCount(String keyWord) {
        return getSqlSession().selectOne(CLASS_NAME + SQL_ID_INT_GET_TOTAL_COUNT, keyWord);
    }

    @Override
    public int deleteQuestion(String deletes) {
        return getSqlSession().update(CLASS_NAME + SQL_ID_INT_DELETE_QUESTION, deletes);
    }

    @Override
    public Question queryOneQuestion(Question question) {
        return getSqlSession().selectOne(CLASS_NAME + SQL_ID_INT_QUERY_ONE_QUESTION, question);
    }

}