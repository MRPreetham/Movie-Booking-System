package BookMyShow.BookMyShow.services;

import BookMyShow.BookMyShow.models.User;
import BookMyShow.BookMyShow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SignUpUserService {
    private final UserRepository userRepository;
    @Autowired
    public SignUpUserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public User signUpUser(String email,String password){
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            User user = new User();
            user.setEmail(email);
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(password));
            return userRepository.save(user);
        }
        return userRepository.save(userOptional.get());
    }
}
