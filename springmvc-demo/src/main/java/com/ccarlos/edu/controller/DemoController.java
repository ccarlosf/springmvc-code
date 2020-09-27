package com.ccarlos.edu.controller;

import com.ccarlos.edu.pojo.QueryVo;
import com.ccarlos.edu.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

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

    @RequestMapping("/handle07")
    // 添加@ResponseBody之后，不再走视图解析器那个流程，而是等同于response直接输出数据

    public @ResponseBody User handle07(@RequestBody User user) {

        // 业务逻辑处理，修改name为张三丰
        user.setName("张三丰");
        return user;
    }

    /**
     * 文件上传
     * @return
     */
    @RequestMapping(value = "/upload")
    public ModelAndView upload(MultipartFile uploadFile, HttpSession session) throws IOException {

        // 处理上传文件
        // 重命名，原名123.jpg ，获取后缀
        String originalFilename = uploadFile.getOriginalFilename();// 原始名称
        // 扩展名  jpg
        String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1, originalFilename.length());
        String newName = UUID.randomUUID().toString() + "." + ext;

        // 存储,要存储到指定的文件夹，/uploads/yyyy-MM-dd，考虑文件过多的情况按照日期，生成一个子文件夹
        String realPath = session.getServletContext().getRealPath("/uploads");
        String datePath = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        File folder = new File(realPath + "/" + datePath);

        if(!folder.exists()) {
            folder.mkdirs();
        }


        // 存储文件到目录
        uploadFile.transferTo(new File(folder,newName));


        // TODO 文件磁盘路径要更新到数据库字段

        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date",date);
        modelAndView.setViewName("success");
        return modelAndView;
    }
}
