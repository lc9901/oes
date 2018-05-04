package com.asher.oes.service.impl;

import java.util.List;

import com.asher.oes.dao.mybaties.QuestionDaoImpl;
import com.asher.oes.exception.ParameterException;
import com.asher.oes.model.Pagination;
import com.asher.oes.model.Question;
import com.asher.oes.service.QuestionService;

public class QuestionServiceImpl implements QuestionService{

    private QuestionDaoImpl questionDao;
    public void setQuestionDao(QuestionDaoImpl questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public int addQuestionId () {
            return questionDao.insertQuestionId();

    }

    @Override
    public boolean addQuestion (Question question) throws ParameterException {
        ParameterException parameterException = new ParameterException();
        if (question == null) {
            parameterException.addErrorTip("questionError", "The current question is empty");
        }
        if (parameterException.hasException()) {
            throw parameterException;
        }
        int result = 0;
        result = questionDao.updateQuestion(question);

        return result > 0;
    }

    @Override
    public List<Question> queryList (Pagination pagination) {
        String keyWord = pagination.getSearch();
        int totalCount = questionDao.getTotalCount(keyWord);
        pagination.setTotalCount(totalCount);
        return questionDao.queryQuestionList(pagination);
    }

    /**
     * delete question
     * @param delete
     * @return if value is '-1' ,delete fail;
     *         if value > 0 , the value of delete question items;
     */
    @Override
    public int deleteQuestion (String delete) {
        return questionDao.deleteQuestion(delete);
    }

    @Override
    public Question getQuestionDetail(Question question) throws ParameterException {

        ParameterException parameterException = new ParameterException();
        if (question == null) {
            parameterException.addErrorTip("questionError", "The current question is empty");
        }
        if (parameterException.hasException()) {
            throw parameterException;
        }
        Question questionResult = new Question();
        questionResult = questionDao.queryOneQuestion(question);

        return questionResult;
    }

    @Override
    public boolean updateQuestion(Question question) throws ParameterException {
        ParameterException parameterException = new ParameterException();
        if (question == null) {
            parameterException.addErrorTip("questionError", "The current question is empty");
        }
        if (parameterException.hasException()) {
            throw parameterException;
        }
        int result = -1;
        result = questionDao.updateQuestion(question);
        return result > 0;
    }
}