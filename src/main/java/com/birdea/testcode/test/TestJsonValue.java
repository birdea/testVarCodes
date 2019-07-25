package com.birdea.testcode.test;

import com.birdea.testcode.util.L;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * @author seungtae.hwang (birdea@sk.com)
 * @since 2019. 1. 17.
 */
public class TestJsonValue {

    String json1 = "{\"Command\":1,\"Content-Size\":14,\"Content\":\"json1\"}";
    String json2 = "{Command:2,Content-Size:24,Content:json2}";
    String json3 = ""; //{"Command":80,"Content-Size":4,"Content":"0000","heartbeat_in":"20190117182803954","heartbeat_out":"20190117182803996"}
    String json5 = "{\"Command\":6,\"Content-Size\":-8,\"Content\":\"hello\"}";

    public void tests() {
        Gson gson = new Gson();
        Aaa a1 = gson.fromJson(json1, Aaa.class);
        L.msg("result a1: "+a1.toString());
        Aaa a2 = gson.fromJson(json2, Aaa.class);
        L.msg("result a2: "+a2.toString());
        Aaa a3 = new Aaa();
        a3.Command = 7;
        a3.Content_Size = 3;
        a3.Content = "hey";
        String jsonA3 = gson.toJson(a3);
        L.msg("result a3: "+jsonA3);
        Aaa a4 = gson.fromJson(jsonA3, Aaa.class);
        L.msg("result a4: "+a4.toString());
        Aaa a5 = gson.fromJson(json5, Aaa.class);
        L.msg("result a5: "+a5.toString());
    }

    class Aaa {
        int Command = -1;
        @SerializedName("Content-Size")
        int Content_Size = -2;
        String Content;

        @Override
        public String toString() {
            return new StringBuilder()
                    .append("Command=").append(Command).append(", ")
                    .append("Content_Size=").append(Content_Size).append(", ")
                    .append("Content=").append(Content).append(", ")
                    .toString();
        }
    }
}
