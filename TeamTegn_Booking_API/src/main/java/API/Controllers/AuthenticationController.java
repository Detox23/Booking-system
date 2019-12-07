package API.Controllers;

import API.Configurations.Encryption.EncryptionHandler;
import API.Configurations.TokenProvider;
import API.Models.Models.AuthToken;
import API.Models.Models.LoginUser;
//import API.Services.EmailService.EmailService;
import Shared.ForCreation.Mail;
import Shared.ToReturn.SystemUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EncryptionHandler encryptionHandler;

    @Autowired
    private TokenProvider jwtTokenUtil;


    @RequestMapping(value = "/systemUsers/login", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody LoginUser loginUser) throws AuthenticationException {
        System.out.println(loginUser.getPassword());
        System.out.println(loginUser.getUserName());
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUserName(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }

}