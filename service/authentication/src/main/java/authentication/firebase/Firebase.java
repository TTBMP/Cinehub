package authentication.firebase;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.*;
import com.ttbmp.cinehub.app.service.authentication.LoginException;

import java.io.FileInputStream;
import java.io.IOException;


public class Firebase {
    private FirebaseAuth mAuth;

    public String loginFirebase(String email) throws LoginException {
        try {
            connection();
            return mAuth.getUserByEmail(email).getUid();
        } catch (FirebaseAuthException e) {
           throw new LoginException("Unable to login");
        }
    }

    public String registerFirebase(String email, String password) throws LoginException {
        try {
            connection();
            mAuth.createUser(new UserRecord.CreateRequest().setEmail(email).setPassword(password));
            return mAuth.getUserByEmail(email).getEmail();
        } catch (FirebaseAuthException e) {
            throw new LoginException("Unable to register");
        }
    }

    private void connection() throws LoginException {
        try{
        FileInputStream fis = new FileInputStream("C:\\Users\\Ivan\\Documents\\GitHub\\Cinehub\\service\\authentication\\src\\main\\resources\\apiKey.json");
        FirebaseOptions options =  FirebaseOptions.builder()
                .setDatabaseUrl("https://noreply@cinehub-d2abc.firebaseio.com/")
                .setCredentials(GoogleCredentials.fromStream(fis))
                .build();
        FirebaseApp.initializeApp(options);
        mAuth = FirebaseAuth.getInstance();
        }
        catch (IOException e) {
            throw new LoginException("Problem with database connection");

        }
    }


}
