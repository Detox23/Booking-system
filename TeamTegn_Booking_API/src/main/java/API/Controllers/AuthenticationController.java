package API.Controllers;

import API.Configurations.TokenProvider;
import API.Models.Models.AuthToken;
import API.Models.Models.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;


    /**
     * POST request method that allows user to login. And get access to the API's features.
     * The method requires from the user to provide username and password in a request body.
     * Then, the system creates a LoginUser object and checks the credentials against information in a database.
     * The password is hashed and compared to the one from a database, where userName matches.
     *
     * @param loginUser <LoginUser> Object of login user that allows to log in. Required fields of the object:
     *                  ~ userName <String>
     *                  ~ password <String>
     * @return If successfully, returns an token that authorize a user. Otherwise error with appreciate message.
     * @throws AuthenticationException if the credentials are wrong the exception is throw.
     */
    @RequestMapping(value = "/systemUsers/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody @Valid LoginUser loginUser) throws AuthenticationException {
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
