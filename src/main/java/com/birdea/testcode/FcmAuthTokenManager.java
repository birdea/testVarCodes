package com.birdea.testcode;

import com.birdea.testcode.util.L;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class FcmAuthTokenManager {

    private String fileName = "imhere-71254-firebase-adminsdk-cn7ne-bfd70e2dbd.json";

    private String getAccessToken() throws IOException {
        String SCOPES = "https://www.googleapis.com/auth/firebase.messaging";
        GoogleCredential googleCredential = GoogleCredential
                .fromStream(new FileInputStream(fileName))
                .createScoped(Arrays.asList(SCOPES));
        googleCredential.refreshToken();
        return googleCredential.getAccessToken();
    }

    public void doUpdateAuthToken() {
        L.msg("doUpdateAuthToken - start");
        try {
            String token = getAccessToken();
            L.msg("token: "+ token);
        } catch (IOException e) {
            e.printStackTrace();
        }
        L.msg("doUpdateAuthToken - end");
    }
}
