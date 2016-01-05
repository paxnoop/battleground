package com.battleground.prototype.controller;

import java.text.DateFormat;
import java.util.*;

import com.battleground.prototype.common.Utils;
import com.battleground.prototype.model.Breadcrumb;
import com.battleground.prototype.model.User;
import com.battleground.prototype.service.ArticleService;
import com.battleground.prototype.service.CommentService;
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
@Controller
public class LocationController {

//	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String home() {
//        return "redirect:/cityhall/all";
//	}

    @Autowired
    @Qualifier("articleService")
    private ArticleService articleService;

    @Autowired
    @Qualifier("commentService")
    private CommentService commentService;

    @RequestMapping(value = "/cityhall", method = RequestMethod.GET)
    public ModelAndView locations(HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);
        ModelAndView model = new ModelAndView();
        model.setViewName("cityhall");
        if(httpSession !=null){
            model.addObject("user_id", httpSession.getAttribute("user_id"));
            model.addObject("user_name", httpSession.getAttribute("user_name"));
        }
        return model;
    }

    @RequestMapping(value = "/{location1}/{location2}", method = RequestMethod.GET)
    public ModelAndView locations(@PathVariable String location1,
                                  @PathVariable String location2,
                                  HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        HttpSession httpSession = request.getSession(false);
        if(httpSession != null){
            model.addObject("user_id", httpSession.getAttribute("user_id"));
            model.addObject("user_name", httpSession.getAttribute("user_name"));
        }
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
        ModelAndView model = new ModelAndView();
        HttpSession httpSession = request.getSession(false);
        if(httpSession != null){
            model.addObject("user_id", httpSession.getAttribute("user_id"));
            model.addObject("user_name", httpSession.getAttribute("user_name"));
        }
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
    public ModelAndView registPage(@PathVariable String location1, @PathVariable String location2, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        HttpSession httpSession = request.getSession(false);
        if(httpSession == null){
            model.setViewName("login");
            model.addObject("message","로그인 후 글쓰기가 가능합니다.");
            return model;
        }
        if(httpSession != null){
            if(httpSession.getAttribute("user_id") == null){
                model.setViewName("login");
                model.addObject("message","로그인 후 글쓰기가 가능합니다.");
                return model;
            }
            model.addObject("user_id", httpSession.getAttribute("user_id"));
            model.addObject("user_name", httpSession.getAttribute("user_name"));
        }
        model.setViewName("battle_page_regist");
        model.addObject("location1", location1);
        model.addObject("location2", location2);
        model.addObject("location1Kor", Utils.getKoreanLocationName(location1));
        model.addObject("location2Kor", Utils.getKoreanLocationName(location2));
        return model;
    }

    @RequestMapping(value="/regist_article" , method = RequestMethod.POST)
    public String registCompletePage(@RequestParam String writer,
                                     @RequestParam String writer_id,
                                     @RequestParam String title,
                                     @RequestParam String body,
                                     @RequestParam String location1,
                                     @RequestParam String location2,
                                     HttpServletRequest request) throws Exception{
        HttpSession httpSession = request.getSession(false);
        if(httpSession != null){
            if(httpSession.getAttribute("user_id").equals(writer_id)){
                Map<String, Object> params = new HashMap<>();
                params.put("writer", writer);
                params.put("title", title);
                params.put("body", body);
                params.put("writer_id", writer_id);
                params.put("location1", location1);
                params.put("location2", location2);
                articleService.insertArticle(params);
            }
        }

        return "redirect:/"+location1+"/"+location2;
    }

    @RequestMapping(value="/regist_comment" , method = RequestMethod.POST)
    public String registComment(@RequestParam String writer,
                                     @RequestParam String writer_id,
                                     @RequestParam String a_id,
                                     @RequestParam String copy,
                                     @RequestParam String location1,
                                     @RequestParam String location2,
                                     HttpServletRequest request) throws Exception{
        HttpSession httpSession = request.getSession(false);
        if(httpSession != null && !writer_id.equals("") && writer_id != null){
            Map<String, Object> params = new HashMap<>();
            params.put("writer", writer);
            params.put("writer_id", writer_id);
            params.put("a_id", a_id);
            params.put("copy", copy);
            commentService.insertComment(params);
        }
        return "redirect:/"+location1+"/"+location2+"/battle_page?a_id="+a_id;
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
