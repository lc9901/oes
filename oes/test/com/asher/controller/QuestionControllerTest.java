package com.asher.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;

import com.asher.oes.AppContext;
import com.asher.oes.Constants;
import com.asher.oes.util.SessionUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml", "classpath:oes-mvc.xml"})
public class QuestionControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Before
    public void setUp() throws Exception {
        AppContext.setContextPath("/oes");
        AppContext appContext = AppContext.getContext();
        appContext.addObject(Constants.APP_CONTEXT_SESSION, new MockHttpSession());
    }

    @After
    public void tearDown() throws Exception {
        AppContext appContext= AppContext.getContext();
        appContext.clear();
    }

    @Test
    public void testCreateQuestion(){
        QuestionController questionController= (QuestionController)this.applicationContext.getBean("questionController");
        ModelAndView createQuestion = questionController.createQuestion();
        Assert.assertNotNull(SessionUtil.getSession(Constants.DRAFT_QUESTION_ID));
    }
}