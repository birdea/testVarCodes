package com.birdea.testcode.parser;

import com.birdea.testcode.util.L;
import com.google.gson.Gson;

public class JsonParserExample {

    public static final String result = "result:{\"result\":\"true\",\"errorCode\":\"\",\"errorMsg\":\"\",\"dataSet\":[],\"dataSetMulti\":{\"authResultMsg\":\"등록되지 않은 사용자입니다.\",\"authResult\":\"N\",\"companyResult\":\"\",\"subjectResult\":\"\",\"profileResult\":\"\",\"privacyResult\":\"\",\"faceResult\":\"\"}}";

    String profile = "{\"uid\":\"SKT.P105800\",\"telephoneNumber\":\"01052874602\",\"name\":\"김호균\",\"thumbnail_url\":\"http:\\/\\/here.softworks.co.kr:9035\\/api\\/thumb.if?uid=SKT.P105800&upddt=1570090219\"}";

    public void doTest() {
        ProfileInfo profileInfo = new Gson().fromJson(profile, ProfileInfo.class);
        L.msg("profile: " + profile);
    }
}
