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
import org.springframework.web.servlet.view.RedirectView;

import com.asher.oes.AppContext;
import com.asher.oes.Constants;
import com.asher.oes.model.Json;
import com.asher.oes.model.User;
import com.asher.oes.util.SessionUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml", "classpath:oes-mvc.xml"})
public class UserControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

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
    public void testLogin() {
        UserController userController = (UserController) this.applicationContext.getBean("userController");
        User user = new User();
        user.setUserName("AsherChen");
        user.setPassword("123");
        Json json = userController.login(user);
        Assert.assertEquals("/oes/page/question/list" , json.getData());
        Assert.assertNotNull(SessionUtil.getSession(Constants.USER));
    }

    @Test
    public void testLogout() {
        UserController userController = (UserController) this.applicationContext.getBean("userController");
        ModelAndView modelAndView = userController.logout();
        RedirectView redirectView = (RedirectView)modelAndView.getView();
        Assert.assertEquals("/oes/page/login/login", redirectView.getUrl());
        Assert.assertNull(SessionUtil.getSession(Constants.USER));
    }
}