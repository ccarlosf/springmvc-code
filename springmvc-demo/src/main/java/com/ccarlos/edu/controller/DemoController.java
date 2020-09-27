package com.ccarlos.edu.controller;

import com.ccarlos.edu.pojo.QueryVo;
import com.ccarlos.edu.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("/demo")
public class DemoController {

    // SpringMVC的异常处理机制（异常处理器）
    // 注意：写在这里只会对当前controller类生效
    @ExceptionHandler(ArithmeticException.class)
    public void handleException(ArithmeticException exception,HttpServletResponse response) {
        // 异常处理逻辑
        try {
            response.getWriter().write(exception.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * url: http://localhost:8080/demo/handle01
     */
    @RequestMapping("/handle01")
    public ModelAndView handle01(@ModelAttribute("name") String name) {

        int c = 1/0;


        Date date = new Date();// 服务器时间
        // 返回服务器时间到前端页面
        // 封装了数据和页面信息的 ModelAndView
        ModelAndView modelAndView = new ModelAndView();
        // addObject 其实是向请求域中request.setAttribute("date",date);
        modelAndView.addObject("date", date);
        // 视图信息(封装跳转的页面信息) 逻辑视图名
        modelAndView.setViewName("success");
        return modelAndView;
    }

    /**
     * SpringMVC 对原生servlet api的支持  url：/demo/handle02?id=1
     * <p>
     * 如果要在SpringMVC中使用servlet原生对象，比如HttpServletRequest\HttpServletResponse\HttpSession，直接在Handler方法形参中声明使用即可
     */
    @RequestMapping("/handle02")
    public ModelAndView handle02(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String id = request.getParameter("id");

        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date", date);
        modelAndView.setViewName("success");
        return modelAndView;
    }

    /*
     * SpringMVC 接收简单数据类型参数  url：/demo/handle03?ids=1
     *
     * 注意：接收简单数据类型参数，直接在handler方法的形参中声明即可，框架会取出参数值然后绑定到对应参数上
     * 要求：传递的参数名和声明的形参名称保持一致
     */
    @RequestMapping("/handle03")
    public ModelAndView handle03(@RequestParam("ids") Integer id, Boolean flag) {

        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date", date);
        modelAndView.setViewName("success");
        return modelAndView;
    }

    /*
     * SpringMVC接收pojo类型参数  url：/demo/handle04?id=1&username=zhangsan
     *
     * 接收pojo类型参数，直接形参声明即可，类型就是Pojo的类型，形参名无所谓
     * 但是要求传递的参数名必须和Pojo的属性名保持一致
     */
    @RequestMapping("/handle04")
    public ModelAndView handle04(User user) {

        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date", date);
        modelAndView.setViewName("success");
        return modelAndView;
    }

    /*
     * SpringMVC接收pojo包装类型参数  url：/demo/handle05?phone=13132345123&mail=32131@qq.com&user.id=1&user.name=zhangsan
     * 不管包装Pojo与否，它首先是一个pojo，那么就可以按照上述pojo的要求来
     * 1、绑定时候直接形参声明即可
     * 2、传参参数名和pojo属性保持一致，如果不能够定位数据项，那么通过属性名 + "." 的方式进一步锁定数据
     *
     */
    @RequestMapping("/handle05")
    public ModelAndView handle05(QueryVo queryVo) {

        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date",date);
        modelAndView.setViewName("success");
        return modelAndView;
    }

    /**
     * 绑定日期类型参数
     * 定义一个SpringMVC的类型转换器  接口，扩展实现接口接口，注册你的实现
     * @param birthday
     * @return
     */
    @RequestMapping("/handle06")
    public ModelAndView handle06(Date birthday) {
        Date date = new Date();ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date",date);
        modelAndView.setViewName("success");
        return modelAndView;
    }


    /*
     * restful  get   /demo/handle/15
     */
    @RequestMapping(value = "/handle/{id}",method = {RequestMethod.GET})
    public ModelAndView handleGet(@PathVariable("id") Integer id) {

        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date",date);
        modelAndView.setViewName("success");
        return modelAndView;
    }


    /*
     * restful  post  /demo/handle
     */
    @RequestMapping(value = "/handle",method = {RequestMethod.POST})
    public ModelAndView handlePost(String username) {

        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date",date);
        modelAndView.setViewName("success");
        return modelAndView;
    }


    /*
     * restful  put  /demo/handle/15/lisi
     */
    @RequestMapping(value = "/handle/{id}/{name}",method = {RequestMethod.PUT})
    public ModelAndView handlePut(@PathVariable("id") Integer id,@PathVariable("name") String username) {

        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date",date);
        modelAndView.setViewName("success");
        return modelAndView;
    }


    /*
     * restful  delete  /demo/handle/15
     */
    @RequestMapping(value = "/handle/{id}",method = {RequestMethod.DELETE})
    public ModelAndView handleDelete(@PathVariable("id") Integer id) {

        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date",date);
        modelAndView.setViewName("success");
        return modelAndView;
    }
}
