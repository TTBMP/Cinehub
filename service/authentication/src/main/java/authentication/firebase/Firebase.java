package authentication.firebase;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.*;

import java.io.FileInputStream;
import java.io.IOException;


public class Firebase {
    private FirebaseAuth mAuth;

    public String loginFirebase(String email) throws FirebaseException {
        try {
            connection();
            return mAuth.getUserByEmail(email).getUid();
        } catch (FirebaseAuthException e) {
           throw new FirebaseException("Unable to login");
        }
    }

    public String registerFirebase(String email, String password) throws FirebaseException {
        try {
            connection();
            mAuth.createUser(new UserRecord.CreateRequest().setEmail(email).setPassword(password));
            return mAuth.getUserByEmail(email).getEmail();
        } catch (FirebaseAuthException e) {
            throw new FirebaseException("Unable to register");
        }
    }

    private void connection() throws FirebaseException {
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
            throw new FirebaseException("Problem with database connection");

        }
    }


}
