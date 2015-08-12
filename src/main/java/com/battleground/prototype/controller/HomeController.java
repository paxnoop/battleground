package com.battleground.prototype.controller;

import java.text.DateFormat;
import java.util.*;

import com.battleground.prototype.common.Utils;
import com.battleground.prototype.model.Breadcrumb;
import com.battleground.prototype.model.User;
import com.battleground.prototype.service.ArticleService;
import com.battleground.prototype.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.google.common.collect.Sets;

import javax.activation.CommandMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Handles requests for the application home page.
 */
@RestController
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String home() {
//        return "redirect:/cityhall/all";
//	}

    @Autowired
    @Qualifier("articleService")
    private ArticleService articleService;

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView model = new ModelAndView();
        model.setViewName("login");
        return model;
    }

    @RequestMapping(value = "/session", method = RequestMethod.POST)
    public ModelAndView session(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        System.out.println(id);
        System.out.println(pw);


        Map<String, Object> params = new HashMap<>();
        params.put("user_id",id);
        System.out.println(userService.getUserID(params));
        System.out.println(userService.getUserPW(params));
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
            model.setViewName("cityhall");
        }
        return model;
    }

    @RequestMapping(value = "/cityhall", method = RequestMethod.GET)
    public ModelAndView locations(HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);
        ModelAndView model = new ModelAndView();
        if(httpSession !=null){
            model.setViewName("cityhall");
            model.addObject("user_id", httpSession.getAttribute("user_id"));
            model.addObject("user_name", httpSession.getAttribute("user_name"));
        }
        model.setViewName("cityhall");
        return model;
    }

    @RequestMapping(value = "/{location1}/{location2}", method = RequestMethod.GET)
    public ModelAndView locations(@PathVariable String location1,
                                  @PathVariable String location2,
                                  HttpServletRequest request) {
        HttpSession httpSession = request.getSession();

        ModelAndView model = new ModelAndView();
        model.setViewName(location1);
        model.addObject("location1", location1);
        model.addObject("location2", location2);
        model.addObject("location1Kor", Utils.getKoreanLocationName(location1));
        model.addObject("location2Kor", Utils.getKoreanLocationName(location2));
        return model;
    }

    @RequestMapping(value = "/{location1}/{location2}/battle_page", method = RequestMethod.GET)
    public ModelAndView battlePage(@PathVariable String location1,
                                   @PathVariable String location2,
                                   @RequestParam String a_id,
                                   HttpServletRequest request) throws Exception{
        HttpSession httpSession = request.getSession();

//        System.out.println(httpSession.getAttribute("location1"));
        System.out.println(httpSession.getId());
        ModelAndView model = new ModelAndView();
        model.setViewName("battle_page");
        model.addObject("location1", location1);
        model.addObject("location2", location2);
        model.addObject("location1Kor", Utils.getKoreanLocationName(location1));
        model.addObject("location2Kor", Utils.getKoreanLocationName(location2));
        model.addObject("a_id", a_id);
        Map<String, Object> params = new HashMap<>();
        params.put("a_id",a_id);
        articleService.addClickCount(params);
        return model;
    }

    @RequestMapping(value = "/{location1}/{location2}/regist", method = RequestMethod.GET)
    public ModelAndView registPage(@PathVariable String location1, @PathVariable String location2) {
        ModelAndView model = new ModelAndView();
        model.setViewName("battle_page_regist");
        model.addObject("location1", location1);
        model.addObject("location2", location2);
        model.addObject("location1Kor", Utils.getKoreanLocationName(location1));
        model.addObject("location2Kor", Utils.getKoreanLocationName(location2));
        return model;
    }

    @RequestMapping(value="/regist" , method = RequestMethod.POST)
    public ModelAndView registCompletePage(@RequestParam String writer,
                                     @RequestParam String title,
                                     @RequestParam String body,
                                     @RequestParam String location1,
                                     @RequestParam String location2) throws Exception{
        ModelAndView model = new ModelAndView();
        model.setViewName(location1);
        model.addObject("location1", location1);
        model.addObject("location2", location2);
        model.addObject("location1Kor", Utils.getKoreanLocationName(location1));
        model.addObject("location2Kor", Utils.getKoreanLocationName(location2));

        Map<String, Object> params = new HashMap<>();
        params.put("writer", writer);
        params.put("title", title);
        params.put("body", body);
        params.put("writer_id", "admin");
        params.put("location1", location1);
        params.put("location2", location2);
        articleService.insertArticle(params);
        return model;
    }





    /**
     * Breadcrumb 설정
     * @param strs
     * @return
     */
    private List<Breadcrumb> getBreadcrumb(String... strs) {
        Set<String> mustReplacePage = Sets.newHashSet("dashboard", "overview");
        List<Breadcrumb> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("/click");

        for (int i = 0; i < strs.length; i++) {
            sb.append("/" + strs[i]);
            if ((i + 1) == strs.length) {
                if (mustReplacePage.contains(strs[i])) {
                    list.add(new Breadcrumb(sb.toString(), capitalize(strs[i]), true));
                } else {
                    list.add(new Breadcrumb(sb.toString(), strs[i], true));
                }
            } else {
                list.add(new Breadcrumb(sb.toString(), capitalize(strs[i]), false));
            }
        }
        return list;
    }

    /**
     * 문자열의 첫 글자만 대문자로 치환
     * @param str
     * @return
     */
    private String capitalize(String str) {
        String ret = str;
        if (!StringUtils.isEmpty(str)) {
            if (str.length() == 1) {
                ret = str.toUpperCase();
            } else {
                ret = str.substring(0, 1).toUpperCase() + str.substring(1);
            }
        }
        return ret;
    }
}
