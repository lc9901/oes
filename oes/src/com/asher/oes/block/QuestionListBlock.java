package com.asher.oes.block;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import com.asher.commen.BlockAbstract;
import com.asher.oes.Constants;
import com.asher.oes.model.Pagination;
import com.asher.oes.model.Question;
import com.asher.oes.service.QuestionService;

public class QuestionListBlock extends BlockAbstract{

    QuestionService questionService;

    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    protected void execute(PageContext pageContext) {
        HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        Pagination pagination  = (Pagination)request.getAttribute(Constants.PAGINATION);

        List<Question> questionList = questionService.queryList(pagination);
        int currentPage = pagination.getCurrentPage();

        if (currentPage > pagination.getPageCount()) {
            pagination.setCurrentPage(currentPage);
        }

        if (pagination.getPageCount() < currentPage) {
            pagination.setCurrentPage(pagination.getPageCount());
        }

        request.setAttribute(Constants.QUESTION_LIST, questionList);
        request.setAttribute(Constants.PAGINATION, pagination);

    }
}