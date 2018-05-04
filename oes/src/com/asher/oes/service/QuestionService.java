package com.asher.oes.service;

import java.util.List;

import com.asher.oes.exception.ParameterException;
import com.asher.oes.model.Pagination;
import com.asher.oes.model.Question;

public interface QuestionService {

    public int addQuestionId();
    public boolean addQuestion (Question question) throws ParameterException;
    public List<Question> queryList (Pagination pagination);
    public int deleteQuestion (String delete);
    public Question getQuestionDetail(Question question) throws ParameterException;
    public boolean updateQuestion(Question question) throws ParameterException;

}
