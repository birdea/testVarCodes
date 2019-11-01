package com.birdea.testcode.parser;

import com.birdea.testcode.util.L;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PushData {

    private String TAG = "PushData";

    public void parse() {
        Map<String, String> map = new HashMap<>();
        map.put("meetingInfo", "{\"meet_cd\":\"\"}");
        map.put("time", "1569240948000");
        map.put("type", "PUSH002");
        map.put("profileInfo", "{\"uid\":\"SKT.1112327\",\"thumbnail_url\":\"http:\\/\\/here.softworks.co.kr:9035\\/api\\/thumb.if?uid=SKT.1112327&upddt=0.000000\"}");
        map.put("conferenceInfo", "{\"conf_nm\":\"190923 행사\",\"conf_cd\":\"CON_23\"}");

        // show map data
        Iterator<String> keys = map.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            L.d(TAG, "push map > key : " + key + ", value : " + map.get(key));
        }

        L.d(TAG, "---");
        // parse
        final String type = map.get("type");
        String pushData = getJsonStringFromMap(map);

        pushData = pushData.replace("\\\"", "\"");
        L.i(TAG, "type:"+type+", pushData:"+pushData);

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(pushData);
        try {
            String time = element.getAsJsonObject().get("time").getAsString();
            L.d(TAG, "--- time:"+time);
            JsonObject meetingInfo = element.getAsJsonObject().get("meetingInfo").getAsJsonObject();
            L.d(TAG, "--- meetingInfo:"+meetingInfo);
            JsonObject profileInfo = element.getAsJsonObject().get("profileInfo").getAsJsonObject();
            L.d(TAG, "--- profileInfo:"+profileInfo);
            JsonObject conferenceInfo = element.getAsJsonObject().get("conferenceInfo").getAsJsonObject();
            L.d(TAG, "--- conferenceInfo:"+conferenceInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getJsonStringFromMap(Map<String, String> map) {
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            jsonObject.addProperty(key, value);
        }
        return gson.toJson(jsonObject);
    }
}
