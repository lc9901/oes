package com.asher.oes.dao;

import java.util.List;

import com.asher.oes.model.Pagination;
import com.asher.oes.model.Question;

public interface QuestionDao {

    public int insertQuestionId();
    public int updateQuestion(final Question question);
    public List<Question> queryQuestionList(Pagination pagination);
    public int getTotalCount(String keyWord);
    public int deleteQuestion(String deletes);
    public Question queryOneQuestion(final Question question);

}