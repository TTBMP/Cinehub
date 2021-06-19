package com.ttbmp.cinehub.service.authentication;

import com.google.firebase.auth.UserRecord;
import lombok.Data;

/**
 * @author Fabio Buracchi
 */
@Data
public class FirebaseSession {

    private String uid;
    private String email;
    private String sessionToken;

    public FirebaseSession(UserRecord loggedUserRecord) {
        this.uid = loggedUserRecord.getUid();
        this.email = loggedUserRecord.getEmail();
        this.sessionToken = loggedUserRecord.getUid();
    }

}
