package BookMyShow.BookMyShow.controllers;

import BookMyShow.BookMyShow.dtos.SignUpUserRequestDto;
import BookMyShow.BookMyShow.dtos.SignUpUserResponseDto;
import BookMyShow.BookMyShow.models.User;
import BookMyShow.BookMyShow.services.SignUpUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private final SignUpUserService signUpUserService;
    @Autowired
    public UserController(SignUpUserService signUpUserService){
        this.signUpUserService = signUpUserService;
    }
    public SignUpUserResponseDto signUpUser(SignUpUserRequestDto requestDto){

        String email =requestDto.getEmail();
        String password = requestDto.getPassword();

        User user = signUpUserService.signUpUser(email,password);

        SignUpUserResponseDto responseDto = new SignUpUserResponseDto();
        responseDto.setUserId(user.getId());

        return responseDto;
    }
}
