package com.asher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.asher.oes.AppContext;
import com.asher.oes.Constants;
import com.asher.oes.exception.ParameterException;
import com.asher.oes.exception.ServiceException;
import com.asher.oes.model.Json;
import com.asher.oes.model.User;
import com.asher.oes.service.UserService;

@Controller
@RequestMapping(Constants.CONTROLLER_URL_LOGIN)
public class UserController extends BaseController {

    private final String LOGIN_JSP = "login";
    private final String QUESTION_LIST_PAGE = "/page/question/list";
    private final String LOGIN_PAGE = "/page/login/login";

    @Autowired
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Json login(@RequestBody User user) {
        Json result = new Json();
        String userName = user.getUserName();
        String password = user.getPassword();

        try {
            user = userService.login(userName, password);
            user.setPassword(null);
            this.addSession(Constants.USER, user);
            result.setStatus("SUCCESS");
            result.setData(AppContext.getContextPath() + QUESTION_LIST_PAGE);

        } catch (ParameterException e) {
            result.setStatus("ERROR");
            result.setData("UserName and Password is required");
        } catch (ServiceException e) {
            result.setStatus("ERROR");
            result.setData(e.getMessage());
        }
        return result;
    }

    @RequestMapping(value="login", method = RequestMethod.GET)
    public ModelAndView toLogin()  {
        ModelAndView modelAndView = new ModelAndView();
        if (this.getSession(Constants.USER) != null) {
            RedirectView redirectView = new RedirectView(AppContext.getContextPath() + QUESTION_LIST_PAGE);
            modelAndView.setView(redirectView);
        } else {
            modelAndView.setViewName(LOGIN_JSP);
        }
        return modelAndView;
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ModelAndView logout() {
        ModelAndView modelAndView = new ModelAndView();
        this.invalidate();
        RedirectView redirectView = new RedirectView(AppContext.getContextPath() + LOGIN_PAGE);
        modelAndView.setView(redirectView);
        return modelAndView;
    }
}