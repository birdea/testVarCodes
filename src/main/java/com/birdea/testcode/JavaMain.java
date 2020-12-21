package com.birdea.testcode;

import com.birdea.testcode.util.L;
//import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

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
//        new FcmAuthTokenManager().doUpdateAuthToken();
//        new TestGetTimerTaskDelay().tests();
        //new TestCompanyComparable().test();

        String title ="로그인 실패";
        String title1 ="";
        String body = "아이디, 비밀번호가 올바르지 않습니다.";
        String body1 = "";
        String composed = String.format(";%s;%s",title1, body1);

        String[] deposed = composed.split(";");

        L.msg("composed:"+composed);
        L.msg("deposed:"+deposed);
        L.msg("deposed.length:"+deposed.length);
        if (deposed.length > 0) {
            L.msg("deposed[0]:"+deposed[0]);
        }
        if (deposed.length > 1) {
            L.msg("deposed[1]:"+deposed[1]);
        }
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
