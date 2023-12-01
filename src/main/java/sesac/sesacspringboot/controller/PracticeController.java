package sesac.sesacspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PracticeController {

        @GetMapping("/api2")
        public String getAqi() {
            return "practice";
        }

//
//        @GetMapping("/introduce2")
//        public String getIntroduce2(@RequestParam String name, @RequestParam String age, Model model) {
//            model.addAttribute("name", name);
//            model.addAttribute("age", age);
//            return "response";
//        }
//
//        ////////////////////////////////
//        // post로 값을 전달할 때 그 값을 controller에서 받는 방법은 @RequestParam
//        @PostMapping("/post/response1")
//        public String postResponse1(@RequestParam(value = "name") String name, Model model) {
//            model.addAttribute("name"); // model에 담아 전달
//            return "response";
//        }
//
//        @PostMapping("/post/response2")
//        public String postResponse2(@RequestParam(value = "name", required = false) String name, Model model) {
//            model.addAttribute("name"); // model에 담아 전달
//            return "response";
//        }


    }



