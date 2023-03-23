package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class helloController {
    @GetMapping("hello") //정적 컨텐츠 방식
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "helloTemp";
    }

    @GetMapping("hello-mvc") //동적 컨텐츠 방식
    public String helloMVC(@RequestParam(value = "name", required = true ) String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }
    @GetMapping("hello-string") //html 생성 없이 바로 html에 내려줌
    @ResponseBody
    public String helloString(@RequestParam("name")String name){
        return "hello" + name; //"hello spring"
    }

    @GetMapping("hello-api") //API 방식 -> default 반환 방식은 json 형식으로 반환, (xml 방식은 <> </> 태그 방식, 따로 설정 가능)
    @ResponseBody
    public Hello helloApi(@RequestParam("name")String name){
        Hello hello = new Hello();
        hello.setName(name); //{value:name}
        return hello;
        // HttpMessageConverter:
        // 문자가 반환 -> StringHttpMessageConverter가 그대로 반환
        // 객체가 반환 -> MappingJackson2httpMessageConverter가 json으로 변환하게 반환
    }

    static class Hello{
        private String name; //key

        //getter,setter/property 접근 방식 -> private에 접근하기 위한 방식, java와 스프링빈 표준 방식
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

    }
}
