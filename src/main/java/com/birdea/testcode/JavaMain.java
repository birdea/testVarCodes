package com.birdea.testcode;

import com.birdea.testcode.parser.Hcva004Parse;
import com.birdea.testcode.parser.JsonParserExample;
import com.birdea.testcode.parser.PushData;
import com.birdea.testcode.test.TestHalloweenaParty;
import com.birdea.testcode.util.L;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author seungtae.hwang(birdea @ sk.com)
 * @since 2018. 7. 10. created by seungtae.hwang
 */
public class JavaMain {

    public static void main(String[] args) {
        String token = "na";
//        new TestHalloweenaParty().tests();
//        new JsonParserExample().doTest();
//        new PushData().parse();
//        new Hcva004Parse().parse();
        new FcmAuthTokenManager().doUpdateAuthToken();
    }

    // result:{"result":"true","errorCode":"","errorMsg":"","dataSet":[],"dataSetMulti":{"authResultMsg":"등록되지 않은 사용자입니다.","authResult":"N","companyResult":"","subjectResult":"","profileResult":"","privacyResult":"","faceResult":""}}

    private static boolean 너는소수입니까(int value) {
        if (value == 0 || value == 1){
            return false;
        }
        int count = 0;
        for (int i = 2; i < value; i++) {
            if (value % i == 0) {
                count++;
            }
            if (count > 0) {
                return false;
            }
        }
        return (count == 0);
    }

    private static void log(String msg) {
        System.out.println(msg);
    }
}