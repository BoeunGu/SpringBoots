package hello.springmvc.basic.request;


import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller

public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={},age={}", username, age);

        response.getWriter().write("ok");
    }

    //2번째 버전

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username")String memberName,
                                 @RequestParam("age") int memberAge){

        log.info("username={},age={}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age){

        log.info("username={},age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age){

        log.info("username={},age={}", username, age);
        return "ok";
    }


    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age){

        log.info("username={},age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-defalut")
    public String requestParamDefault(
            @RequestParam(required = true,defaultValue = "guest") String username,
            @RequestParam(required = false,defaultValue = "-1") Integer age){

        log.info("username={},age={}", username, age);
        return "ok";
    }
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String,Object>paramMap){

        log.info("username={},age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";

    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){
        log.info("username={}, age={}", helloData.getUsername(),helloData.getAge());
                return "OK";

    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    //@ResponseBody를 사용하면 응답결과를 HTTP 메세지 바디에 직접 담아서 전달할 수 있다.
    public String modelAttributeV2(HelloData helloData){ //@ModelAttribute 생략 가능 (String, Integer,int는 @RequestParam으로 인식)
        log.info("username={}, age={}", helloData.getUsername(),helloData.getAge());
        return "OK";

    }



}
