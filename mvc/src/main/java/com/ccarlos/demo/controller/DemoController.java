package com.ccarlos.demo.controller;

import com.ccarlos.demo.service.IDemoService;
import com.ccarlos.edu.mvcframework.annotations.LagouAutowired;
import com.ccarlos.edu.mvcframework.annotations.LagouController;
import com.ccarlos.edu.mvcframework.annotations.LagouRequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@LagouController
@LagouRequestMapping("/demo")
public class DemoController {


    @LagouAutowired
    private IDemoService demoService;


    /**
     * URL: /demo/query?name=lisi
     * @param request
     * @param response
     * @param name
     * @return
     */
    @LagouRequestMapping("/query")
    public String query(HttpServletRequest request, HttpServletResponse response,String name) {
        return demoService.get(name);
    }
}
