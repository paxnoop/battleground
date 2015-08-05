package com.battleground.prototype.controller;

import java.text.DateFormat;
import java.util.*;

import com.battleground.prototype.model.Breadcrumb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.google.common.collect.Sets;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
        return "redirect:/cityhall";
	}

    @RequestMapping(value = "/{location}", method = RequestMethod.GET)
    public ModelAndView locations(@PathVariable String location) {
        ModelAndView model = new ModelAndView();


        model.setViewName(location);
        model.addObject("location", location);

        return model;
    }

    @RequestMapping(value="/sample/openSampleList.do")
    public ModelAndView openSampleList() throws Exception{
        ModelAndView mv = new ModelAndView("");
        logger.debug("test");

        return mv;
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
