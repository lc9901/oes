package com.asher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.asher.oes.AppContext;
import com.asher.oes.Constants;
import com.asher.oes.exception.ParameterException;
import com.asher.oes.model.Pagination;
import com.asher.oes.model.Question;
import com.asher.oes.model.User;
import com.asher.oes.service.QuestionService;
import com.asher.oes.util.FormatIdUtil;
import com.asher.oes.util.StringUtil;

@Controller
@RequestMapping(value=Constants.CONTROLLER_URL_QUESTION)
public class QuestionController extends BaseController {

    private final String QUESTION_DETAIL_JSP = "question/detail";
    private final String EDIT_QUESTION_JSP = "question/edit";
    private final String CREATE_QUESTION_JSP = "question/create";
    private final String QUESTION_LIST_JSP = "question/list";
    private final String QUESTION_LIST_PAGE = "/page/question/list";

    @Autowired
    private QuestionService questionService;

    public void setQuestionService(QuestionService questionService){
        this.questionService = questionService;
    }

    /**
     * Enter the add question page
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView createQuestion() {
        ModelAndView modelAndView = new ModelAndView();
        if (this.getSession(Constants.DRAFT_QUESTION_ID) == null) {
            String id = FormatIdUtil.formatId("Q", questionService.addQuestionId(), 6);
            this.addSession(Constants.DRAFT_QUESTION_ID, id);
        }
        modelAndView.setViewName(CREATE_QUESTION_JSP);

        return modelAndView;
    }

    /**
     * Enter the question list page
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value="list", method = RequestMethod.GET)
    public ModelAndView queryQuestion(
                                      @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                                      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                      @RequestParam(value = "orderBy", defaultValue = "ASC") String orderBy,
                                      @RequestParam(value = "search", defaultValue = "") String search
                                      ) {
        ModelAndView modelAndView = new ModelAndView();
        Pagination pagination =new Pagination();

        pagination.setOrderBy(orderBy);
        pagination.setCurrentPage(currentPage);
        pagination.setPageSize(pageSize);
        pagination.setSearch(search);
        modelAndView.addObject(Constants.PAGINATION, pagination);

        modelAndView.setViewName(QUESTION_LIST_JSP);
        return modelAndView;
    }

    /**
     * Submit questions and go to the question list page
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value="createsubmit", method = RequestMethod.POST)
    public ModelAndView submitQuestion(
                                       @RequestParam(value = "questionID", defaultValue = "") String questionID,
                                       @RequestParam(value = "questionDescription", defaultValue = "") String questionDescription,
                                       @RequestParam(value = "answer", defaultValue = "") String answer,
                                       @RequestParam(value = "optDetailA", defaultValue = "") String optDetailA,
                                       @RequestParam(value = "optDetailB", defaultValue = "") String optDetailB,
                                       @RequestParam(value = "optDetailC", defaultValue = "") String optDetailC,
                                       @RequestParam(value = "optDetailD", defaultValue = "") String optDetailD
                                       ) {
        Question question = new Question();
        ModelAndView modelAndView = new ModelAndView();
        questionID = questionID.substring(1, questionID.length());
        question.setId(Integer.valueOf(questionID));
        question.setDescription(questionDescription.trim());
        User user = (User)this.getSession(Constants.USER);
        question.setUserId(user.getId());
        question.setAnswer(answer);
        question.setOptionA(optDetailA);
        question.setOptionB(optDetailB);
        question.setOptionC(optDetailC);
        question.setOptionD(optDetailD);

        boolean issuccess = false;
        try {
            issuccess = questionService.addQuestion(question);
        } catch (ParameterException e) {

        }
        if (issuccess) {
            this.addSession(Constants.OPERATION, "success");
            this.removeSession(Constants.DRAFT_QUESTION_ID);
        } else {
            this.addSession(Constants.OPERATION, "false");
        }

        RedirectView redirectView = new RedirectView(AppContext.getContextPath() + QUESTION_LIST_PAGE);
        modelAndView.setView(redirectView);

        return modelAndView;
    }

    /**
     * After delete the question, back to the question list page
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public ModelAndView deleteQuestion(
                                       @RequestParam(value = "deletelist", defaultValue ="") String deleteList
                                       ) {
        ModelAndView modelAndView = new ModelAndView();
        int count = questionService.deleteQuestion(deleteList);
        if (count > 0) {
            this.addSession(Constants.OPERATION, "success");
        } else {
            this.addSession(Constants.OPERATION, "false");
        }

        RedirectView redirectView = new RedirectView(AppContext.getContextPath() + QUESTION_LIST_PAGE);
        modelAndView.setView(redirectView);
        return modelAndView;
    }

    /**
     * Enter the required interface according to different parameters (edit)
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "{currentId}", method = RequestMethod.GET)
    public ModelAndView checkQuestionDetail(
                                            @PathVariable int currentId,
                                            @RequestParam(value = "edit", defaultValue = "") String edit
                                            ) {
        Question question = new Question();
        question.setId(currentId);
        try {
            question = questionService.getQuestionDetail(question);
        } catch (ParameterException e) {
            handleException(e);
        }

        ModelAndView modelAndView = new ModelAndView();
        this.addSession(Constants.CURRENT_QUESTION, question);
        if (StringUtil.isNull(edit)) {
            modelAndView.addObject(Constants.CURRENT_ID, currentId);
            modelAndView.setViewName(QUESTION_DETAIL_JSP);
        } else {
            modelAndView.setViewName(EDIT_QUESTION_JSP);
        }

        return modelAndView;
    }

    /**
     * Submit the modification results and return to the details page
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ModelAndView editQuestion(
                                     @RequestParam(value = "questionCurrentID", defaultValue = "") String questionCurrentID,
                                     @RequestParam(value = "questionDescription", defaultValue = "") String questionDescription,
                                     @RequestParam(value = "answer", defaultValue = "") String answer,
                                     @RequestParam(value = "optDetailA", defaultValue = "") String optDetailA,
                                     @RequestParam(value = "optDetailB", defaultValue = "") String optDetailB,
                                     @RequestParam(value = "optDetailC", defaultValue = "") String optDetailC,
                                     @RequestParam(value = "optDetailD", defaultValue = "") String optDetailD
                                     ) {
        ModelAndView modelAndView = new ModelAndView();
        Question question = new Question();
        String questionID = questionCurrentID.substring(1, questionCurrentID.length());
        question.setId(Integer.valueOf(questionID));
        question.setDescription(questionDescription.trim());
        question.setAnswer(answer);
        question.setOptionA(optDetailA);
        question.setOptionB(optDetailB);
        question.setOptionC(optDetailC);
        question.setOptionD(optDetailD);

        boolean issuccess = false;
        try {
            issuccess = questionService.updateQuestion(question);
        } catch (ParameterException e) {
            handleException(e);
        }

        if (issuccess) {
            this.addSession(Constants.OPERATION, "success");
        } else {
            this.addSession(Constants.OPERATION, "false");
        }
        modelAndView.addObject(Constants.CURRENT_ID, Integer.valueOf(questionID).toString());
        RedirectView redirectView = new RedirectView(AppContext.getContextPath() + QUESTION_LIST_PAGE);
        modelAndView.setView(redirectView);

        return modelAndView;
    }

}