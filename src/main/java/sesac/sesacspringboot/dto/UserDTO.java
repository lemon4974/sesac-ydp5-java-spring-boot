package sesac.sesacspringboot.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;


@Getter
@Setter
//class 위에 적어주면 아래에 해당하는 모든 변수에 getter와 setter를 만들겠다는 뜻
public class UserDTO {
    private String name;

    private String age;

//    public String getname() {return name;}
    //Alt + insert generator
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getAge() {
//        return age;
//    }
//
//    public void setAge(String age) {
//        this.age = age;
//    }

    // DTO - axios
    @GetMapping("axios/response1")
    @ResponseBody
    public String axiosAPI1(@RequestParam(value = "name") String name, @RequestParam(value = "age") String age){
        String msg = "이름 : " + name + "\n나이 : " + age;
        return msg;
    }

    @GetMapping("axios/response2")
    @ResponseBody
    public String axiosAPI2(UserDTO userDTO){
        String msg = "이름 : " + userDTO.getName() + "\n나이 : " + userDTO.getAge();
        return msg;
    }

    @PostMapping("axios/response3")
    @ResponseBody
    public String axiosAPI3(@RequestParam(value = "name") String name, @RequestParam(value = "age") String age){
        String msg = "이름 : " + name + "\n나이 : " + age;
        return msg;
    }
    @PostMapping("axios/response4")
    @ResponseBody
    public String axiosAPI4(UserDTO userDTO){
        String msg = "이름 : " + userDTO.getName() + "\n나이 : " + userDTO.getAge();
        return msg;
    }
    @PostMapping("axios/response5")
    @ResponseBody
    public String axiosAPI5(@RequestBody UserDTO userDTO){
        String msg = "이름 : " + userDTO.getName() + "\n나이 : " + userDTO.getAge();
        return msg;
    }

}
