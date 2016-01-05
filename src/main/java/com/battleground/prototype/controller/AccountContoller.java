package com.battleground.prototype.controller;

import com.battleground.prototype.common.Utils;
import com.battleground.prototype.model.User;
import com.battleground.prototype.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sugin on 16. 1. 6..
 */
@Controller
public class AccountContoller {
    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @RequestMapping(value = "/{location1}/{location2}/login", method = RequestMethod.GET)
    public ModelAndView login(@PathVariable String location1,
                              @PathVariable String location2) {
        ModelAndView model = new ModelAndView();
        model.addObject("location1", location1);
        model.addObject("location2", location2);
        model.setViewName("login");
        return model;
    }

    @RequestMapping(value = "/session", method = RequestMethod.POST)
    public ModelAndView session(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        String location1 = request.getParameter("location1");
        String location2 = request.getParameter("location2");
        Map<String, Object> params = new HashMap<>();
        params.put("user_id",id);
        if(userService.getUserID(params)==null){
            model.addObject("message","아이디가 존재하지 않습니다.");
            model.setViewName("login");
            return model;
        }
        else if(!userService.getUserPW(params).equals(pw)){
            model.addObject("message","비밀번호가 일치하지 않습니다.");
            model.addObject("user_id", id);
            model.setViewName("login");
        }
        else if (userService.getUserID(params)!=null && userService.getUserPW(params).equals(pw)){
            HttpSession httpSession = request.getSession(true);
            User user = userService.getUserInfo(params);
            httpSession.setAttribute("user_id", user.getId());
            httpSession.setAttribute("user_name", user.getName());
            model.addObject("user_id", httpSession.getAttribute("user_id"));
            model.addObject("user_name", httpSession.getAttribute("user_name"));
            model.addObject("location1", location1);
            model.addObject("location2", location2);
            model.addObject("location1Kor", Utils.getKoreanLocationName(location1));
            model.addObject("location2Kor", Utils.getKoreanLocationName(location2));
            if(location1.equals("")){
                model.setViewName("cityhall");
            }
            else{
                model.setViewName(location1);
            }
        }
        return model;
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request){
        ModelAndView model = new ModelAndView();
        model.setViewName("cityhall");
        HttpSession httpSession = request.getSession(false);
        httpSession.invalidate();
        return model;
    }
}
