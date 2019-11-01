package com.birdea.testcode.parser;

import com.birdea.testcode.util.L;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Hcva004Parse {

    private static final String TAG = "Hcva004Parse";

    final String data = "{\"meeting_List\":[{\"conf_cd\":\"CON_17\",\"meet_cd\":\"M_470\",\"meet_start\":\"13:00\",\"meet_end\":\"13:15\",\"meet_flag\":\"none\",\"req_empno\":\"\",\"req_empnm\":\"\",\"res_empno\":\"\",\"res_empnm\":\"\"},{\"conf_cd\":\"CON_17\",\"meet_cd\":\"M_471\",\"meet_start\":\"13:15\",\"meet_end\":\"13:30\",\"meet_flag\":\"none\",\"req_empno\":\"\",\"req_empnm\":\"\",\"res_empno\":\"\",\"res_empnm\":\"\"},{\"conf_cd\":\"CON_17\",\"meet_cd\":\"M_472\",\"meet_start\":\"13:30\",\"meet_end\":\"13:45\",\"meet_flag\":\"none\",\"req_empno\":\"\",\"req_empnm\":\"\",\"res_empno\":\"\",\"res_empnm\":\"\"},{\"conf_cd\":\"CON_17\",\"meet_cd\":\"M_473\",\"meet_start\":\"13:45\",\"meet_end\":\"14:00\",\"meet_flag\":\"none\",\"req_empno\":\"\",\"req_empnm\":\"\",\"res_empno\":\"\",\"res_empnm\":\"\"},{\"conf_cd\":\"CON_17\",\"meet_cd\":\"M_474\",\"meet_start\":\"14:00\",\"meet_end\":\"14:15\",\"meet_flag\":\"none\",\"req_empno\":\"\",\"req_empnm\":\"\",\"res_empno\":\"\",\"res_empnm\":\"\"},{\"conf_cd\":\"CON_17\",\"meet_cd\":\"M_475\",\"meet_start\":\"14:15\",\"meet_end\":\"14:30\",\"meet_flag\":\"none\",\"req_empno\":\"\",\"req_empnm\":\"\",\"res_empno\":\"\",\"res_empnm\":\"\"},{\"conf_cd\":\"CON_17\",\"meet_cd\":\"M_476\",\"meet_start\":\"14:30\",\"meet_end\":\"14:45\",\"meet_flag\":\"none\",\"req_empno\":\"\",\"req_empnm\":\"\",\"res_empno\":\"\",\"res_empnm\":\"\"},{\"conf_cd\":\"CON_17\",\"meet_cd\":\"M_477\",\"meet_start\":\"14:45\",\"meet_end\":\"15:00\",\"meet_flag\":\"none\",\"req_empno\":\"\",\"req_empnm\":\"\",\"res_empno\":\"\",\"res_empnm\":\"\"}]}";

    public void parse() {
        L.msg(TAG, "parse start() "+data);
        Hcva004Vo svcVo = new Gson().fromJson(data, Hcva004Vo.class);
        L.msg(TAG, "Hcva004Vo:"+svcVo);
        //- build time slot item list
        final List<Hcva004Vo.TimeSlot> timeSlotList = svcVo.buildTimeSlot();
        L.msg(TAG, "timeSlotList(pre):"+timeSlotList);
        timeSlotList.get(0).isChecked = true;
        makeMeetCdArray(timeSlotList);
    }

    public void makeMeetCdArray(List<Hcva004Vo.TimeSlot> timeSlotList) {
        List<String> meetCodes = new ArrayList<>();
        for (Hcva004Vo.TimeSlot slot : timeSlotList) {
            if (slot.isChecked) {
                List<Hcva004Vo.Meeting> meetings = slot.getMeeting();
                for (Hcva004Vo.Meeting meet : meetings) {
                    meetCodes.add(meet.meet_cd);
                }
            }
        }
        L.msg(TAG, "repackageData(raw): "+meetCodes);
        Gson gson = new Gson();
        String json = gson.toJson(meetCodes);
        L.msg(TAG, "repackageData(json): "+json);
    }
}
