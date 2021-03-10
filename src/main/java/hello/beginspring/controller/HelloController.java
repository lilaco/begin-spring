package hello.beginspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    //정적 컨텐츠 방식
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "spring!");
        return "hello";
        //Controller 에서 return 값으로 문자를 반환하면 viewResolver 가 화면을 찾아서 처리한다.
        //resources/templates/'viewName'
    }

    //MVC 방식, thymeleaf 템플릿 엔진
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    //API 방식 (문자)
    @GetMapping("hello-string")
    @ResponseBody //body 부분에 parameter 를 직접 삽입하겠다는 의미?
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; //"hello spring"
        //페이지 소스를 확인하면 문자 그대로가 나타남 (HTML 소스X)
        //쓸일은 별로 없음
    }

    //API 방식 (데이터)
    @GetMapping("hello-api")
    @ResponseBody
    //문자가 아닌 객체를 넘김.(json)
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    //@ResponseBody 사용원리
    //스프링 컨테이너에 @ResponseBody 로 오면, Hello 객체로 넘긴다. 이 객체를 보고 HttpMessageConverter
    // 객체인지 문자인지 판단하여 JsonConverter 나 StringConverter 가 조건에 맞추어 웹 브라우저에게 응답


    static class Hello{ // HelloController 클래스 내부에 Hello 클래스 생성
        private String name;
        //Getter, Setter 는 JAVA bean 규약이라고 한다. 메서드를 통해 접근하게끔 한다.
        //혹은 property 접근방식

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
