package authentication.mock;


import authentication.firebase.Firebase;
import com.ttbmp.cinehub.app.service.authentication.AuthenticationService;
import com.ttbmp.cinehub.app.service.authentication.LoginException;


/**
 * @author Fabio Buracchi and Palmieri Ivan
 */
public class MockAuthenticationService implements AuthenticationService {

    private final Firebase firebase = new Firebase();

    @Override
    public Integer sigIn() {
        return 1;
    }

    @Override
    public String register(String email, String password) throws LoginException {
        return firebase.registerFirebase(email,password);
    }

    @Override
    public String sigInFireBase() throws LoginException {
        return firebase.loginFirebase("prova@fabietto.json ");
    }

}
