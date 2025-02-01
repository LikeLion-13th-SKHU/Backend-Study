package org.springbootdeveloper2.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller // @RestController쓰는 Blog 컨트롤러와 달리 리턴 값이 뷰의 이름
public class ExampleController {

    @GetMapping("/thymeleaf/example")
    public String thymeleafExample(Model model) { // model = 뷰쪽으로 값을 넘겨주는 객체
        Person examplePerson = new Person(); // person 클래스로부터 examplePerson 객체 생성
        examplePerson.setId(1L);
        examplePerson.setName("홍길동");
        examplePerson.setAge(11);
        examplePerson.setHobbies(List.of("운동", "독서"));

        model.addAttribute("person", examplePerson); // 모델에 값 저장 - examplePerson 객체
        model.addAttribute("today", LocalDate.now());

        return "example"; // 뷰의 이름 리턴 - example.html

    }

    @Setter
    @Getter
    class Person {
        private Long id;
        private String name;
        private int age;
        private List<String> hobbies;
    }
}
