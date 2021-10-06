package top.renhongchen.community.advice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import top.renhongchen.community.exception.CustomizeAppException;
import top.renhongchen.community.exception.CustomizeErrorCode;
import top.renhongchen.community.exception.CustomizeHardException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomizeExceptionHandler {   //拦截系统错误，防止传给用户

    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model) {
        if (e instanceof CustomizeAppException) {
            //处理应用异常
            String message = CustomizeErrorCode.getMessageByCode(e.getMessage());
            Map<String,String> map = new HashMap<>();
            map.put(e.getMessage(),message);
            ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
            modelAndView.addAllObjects(map);
            return modelAndView;
        }
        if (e instanceof CustomizeHardException) {
            //处理页面异常
            model.addAttribute("message", e.getMessage());
        } else {
            model.addAttribute("message", "服务冒烟了，要不然你稍后再试试！！！"); //系统异常
            System.out.println(e.getMessage());
        }
        return new ModelAndView("error");
    }
}
