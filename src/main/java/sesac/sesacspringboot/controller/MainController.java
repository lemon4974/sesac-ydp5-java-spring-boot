package sesac.sesacspringboot.controller;

import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sesac.sesacspringboot.dto.UserDTO;
import sesac.sesacspringboot.vo.UserVO;

@Controller
// @RestController : Conytroller 면서 모든 메서드가 @ResponseBody를 갖도록 하는 친구
public class MainController {
    @GetMapping("/api")
    public String getAqi(){return "request";}

    // 1) get: ?key=value
    // 검색 또는 해시태그 기능에 많이 사용
    // /get/response1?name=abc&age=14
    @GetMapping("/get/response1") // 물음표 전까지의 url 작성해줄 것
    public String getResponse1(@RequestParam(value="name") String name, Model model) { //name 안의 abc는 requestParam으로 받는다
        // @Requestparam : 요청의 파라미터를 매개변수로 받을 때 적는 어노테이션
        // value = ? 뒤에 넘어온 key
        // required 값을 설정할 수 있다. = true/ false -> 기본값은 true
        // required가  true로 되어있으면 ?뒤에 해당하는 key가 없을 경우 메소드를 찾지 못한다. 400 error
        model.addAttribute("name", name);
        return "response";
    }
    @GetMapping("/get/response2")
    public String getResponse2(@RequestParam(value="name", required = false) String name, Model model) {
        //물음표 뒤에 name이란 키가 없어도 실행 가능
        model.addAttribute("name", name);
        return "response";
    }

    // /get/response3/이름/나이
    // uri에 변수가 없어올 때 그 값을 가져오는 방법
    @GetMapping("/get/response3")
    public String getResponse3(@PathVariable(value="name") String n, //name을 받아 n이라는 변수명으로 사용하겠다.
                                   @PathVariable String age,
                                   Model model

    ) {
        model.addAttribute("name", n);
        model.addAttribute("age", age);


        return "response";
    }

    @GetMapping({"/get/response4/{name}","/get/response4/{name}/{age}" })
    public String getResponse4(@PathVariable(value="name") String n, //name을 받아 n이라는 변수명으로 사용하겠다.
                               @PathVariable String age,
                               Model model
    ) {
        // @Pathvariable에 required설정이 가능하나, 기본값은 true
        // @Pathvariable에 required설정할 때는 GetMapping에도 url도 같이 설정해줘야 한다.
        // required 값이 false인 친구는 뒤로 가야한다.
        model.addAttribute("name", n);
        model.addAttribute("age", age);
        return "response"; //리턴에는 템플릿 파일의 이름


    }

    @GetMapping({"/introduce/{name}"})
    public String getResponse(@PathVariable String name, Model model){
        model.addAttribute("name");
        return "response";
    }

    @GetMapping("/introduce2")
    public String getIntroduce2(@RequestParam String name, @RequestParam String age, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "response";
    }

    ////////////////////////////////
    // post로 값을 전달할 때 그 값을 controller에서 받는 방법은 @RequestParam
    @PostMapping("/post/response1")
    public String postResponse1(@RequestParam(value = "name") String name,  Model model) {
        model.addAttribute("name"); // model에 담아 전달
        return "response";
    }

    @PostMapping("/post/response2")
    public String postResponse2(@RequestParam(value = "name", required = false) String name,  Model model) {
        model.addAttribute("name"); // model에 담아 전달
        return "response";
    }

    @PostMapping("/post/response3")
    @ResponseBody // res.send (이전까지는 res.render)
    // return 하는 문자열의 template 파일을 불러오는 게 아니라
    // return 하는 문자열 그대로 값을 전달하는 것

    public String postResponse3(@RequestParam(value = "name", required = false) String name,  Model model) {
        model.addAttribute("name"); // model에 담아 전달
        return "response";
    }

    // form 실습
    @PostMapping("/post/responsePractice")
    public String postResponseP(@RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "gender", required = false) String gender,
                                @RequestParam(value = "year", required = false) String year,
                                @RequestParam(value = "interest", required = false) String interest,

                                Model model){
        model.addAttribute("name", name);
        model.addAttribute("gender", gender);
        model.addAttribute("year", year);
        model.addAttribute("interest", interest);
        return "response";
    }

    /////DTO
    @GetMapping("/dto/response1")
    @ResponseBody
    public String dtoResponse(@ModelAttribute UserDTO userDTO){
        // 변수로 값을 하나씩 가져오는 게 아니라 객체에 값을 담아서 가져오고 싶을 때 사용하는 방법
        // @ModelAttribute : html 폼 데이터를 컨트롤러로 전달할 때 객체에 매핑해주는 친구
        // 매핑 = setter 함수를 실행한다.
        // -> ?name=&age= -> setName() setAge()

        String msg = "이름 : "+userDTO.getName()+", 나이 "+userDTO.getAge();
        return msg;
    }
    @PostMapping("/dto/response2")
    @ResponseBody
    public  String dtoResponse2(UserDTO userDTO){
        // 아무것도 없는 상태 = @ModelAttribute 상태
        String msg = "이름 : "+userDTO.getName()+", 나이 "+userDTO.getAge();
        return msg;
    }

    @PostMapping("/dto/response3")
    @ResponseBody
    public String dtoResponse3(@RequestBody UserDTO userDTO) {
        // @RequestBody : 요청의 본문에 있는 데이터(body)를 받아와서 객체에 매핑( 필드값에 값을 주입 )
        // 전달받은 요청의 형식이 json 또는 xml 일 때만 실행이 가능
        // 일반폼전송 = www-x-form-urlencoded
        String msg = "이름 : "+userDTO.getName()+", 나이 "+userDTO.getAge();
        return msg;
    }
    // Q. get 방식 - vo = null ( modelattribute가 setter함수를 실행하기 때문 )
    // post 방식 - vo = null
    // post 방식 - vo - requestbody = 오류
    @GetMapping("/vo/response1")
    @ResponseBody
    public String voResponse1(UserVO userVO) {
        String msg = "이름 : "+userVO.getName()+", 나이 "+userVO.getAge();
        return msg;
    }

    @PostMapping("/vo/response2")
    @ResponseBody
    public String voResponse2(UserVO userVO) {
        String msg = "이름 : "+userVO.getName()+", 나이 "+userVO.getAge();
        return msg;
    }
    @PostMapping("/vo/response3")
    @ResponseBody
    public String voResponse3(@RequestBody UserVO userVO) {
        String msg = "이름 : "+userVO.getName()+", 나이 "+userVO.getAge();
        return msg;
    }

    @PostMapping("/vo/response4")
    @ResponseBody
    public String voResponse4(@RequestBody UserVO userVO) {
        String msg = "이름 : "+userVO.getName()+", 나이 "+userVO.getAge();
        return msg;
    }

//    @PostMapping()

    // axios - get - (RequestParam) - ? > o
    // axios get, dto > o
    // axios post - (RequestParam) -> required가 false일 때는 null, true 일 때는 x
    // axios post - requestbody x dto -> null
    // axios post - requestbody o dto -> o

    // axios - get - request
//    Get 일반 : 가능
//    GET VO : null // model을 이용하겠다의 의미이기 때문. 하지만 VO는 setter 없음
//    Post 일반 : 실패
//    Post VO - RequestBody X: null = @ModelAttribute
//    Post VO - RequestBody O: 가능 => @RequestBody를 이용하면 setter함수가 없어도 실행이 된다.

}
