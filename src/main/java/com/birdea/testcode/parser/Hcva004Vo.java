package com.birdea.testcode.parser;

import com.birdea.testcode.util.L;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Nullable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Hcva004Vo {
    @SerializedName("meeting_List")
    public List<Meeting> meetingList;

    public int getSize() {
        if (meetingList == null) {
            return 0;
        }
        return meetingList.size();
    }

    @Nullable
    public List<TimeSlot> buildTimeSlot() {
        if (meetingList == null || meetingList.isEmpty()){
            return null;
        }
        // 시간값으로 sort
        Collections.sort(meetingList);
        //
        List<TimeSlot> timeSlotList = new ArrayList<>();
        //
        TimeSlot timeSlot = new TimeSlot();
        Meeting firstItem = meetingList.get(0);
        timeSlot.setSlotTime(getHour(firstItem.meet_start), firstItem.meet_start, firstItem.meet_end);
        for (Meeting meeting : meetingList) {
            String curSlot = getHour(meeting.meet_start);
            L.msg("curSlot:" +curSlot);
            if (curSlot.equals(timeSlot.slotTimeStart)) {
                L.msg("curSlot.equals(true):" +timeSlot.slotTimeStart+" > addMeeting:"+meeting);
                timeSlot.addMeeting(meeting);
            } else {
                L.msg("curSlot.equals(false):"+timeSlot);
                timeSlot.slotTimeEnd = curSlot;
                timeSlotList.add(timeSlot);
                timeSlot = new TimeSlot();
                timeSlot.setSlotTime(curSlot, meeting.meet_start, meeting.meet_end);
                timeSlot.addMeeting(meeting);
            }
        }
        return timeSlotList;
    }

    private static SimpleDateFormat transFormat1 = new SimpleDateFormat("HH:mm", Locale.US);
    private static SimpleDateFormat transFormat2 = new SimpleDateFormat("a h시", Locale.KOREA);

    private int getHour(long time) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(time));
        return c.get(Calendar.HOUR_OF_DAY);
    }

    private String getHour(String time) {
        try {
            long adaptime = transFormat1.parse(time).getTime();
            return transFormat2.format(new Date(adaptime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("meetingList:").append(meetingList)
                .append(", size:").append(getSize())
                .toString();
    }

    public class TimeSlot {
        public boolean isChecked;
        public long slotStartTime;
        public long slotEndTime;
        public String slotTimeStart;
        public String slotTimeEnd;
        public List<Meeting> slotMeetingList;

        public boolean isOnSlot(long startTime) {
            return (startTime >= slotStartTime && startTime <= slotEndTime);
        }

        public void setSlotTime(String time, String start, String end) {
            slotTimeStart = time;
            try {
                slotStartTime = transFormat1.parse(start).getTime();
                slotEndTime = transFormat1.parse(end).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        public void addMeeting(Meeting meeting) {
            if (slotMeetingList == null) {
                slotMeetingList = new ArrayList<>();
            }
            slotMeetingList.add(meeting);
        }

        public List<Meeting> getMeeting() {
            return slotMeetingList;
        }

        @Override
        public String toString() {
            return new StringBuilder()
                    .append("{ isChecked:").append(isChecked)
                    .append(", slotStartTime:").append(slotStartTime)
                    .append(", slotEndTime:").append(slotEndTime)
                    .append(", slotTimeStart:").append(slotTimeStart)
                    .append(", slotTimeEnd:").append(slotTimeEnd)
                    .append(", slotMeetingList:").append(slotMeetingList)
                    .append("} ")
                    .toString();
        }
    }

    public class Meeting implements Comparable<Meeting>{
        public String conf_cd;
        public String meet_cd;
        public String meet_start;
        public String meet_end;
        public String meet_flag;
        public String req_empno;
        public String req_empnm;
        public String res_empno;
        public String res_empnm;

        @Override
        public String toString() {
            return new StringBuilder()
                    .append("{ conf_cd:").append(conf_cd)
                    .append(", meet_cd:").append(meet_cd)
                    .append(", meet_start:").append(meet_start)
                    .append(", meet_end:").append(meet_end)
                    .append(", meet_flag:").append(meet_flag)
                    .append(", req_empno:").append(req_empno)
                    .append(", req_empnm:").append(req_empnm)
                    .append(", res_empno:").append(res_empno)
                    .append(", res_empnm:").append(res_empnm)
                    .append("} ")
                    .toString();
        }

        @Override
        public int compareTo(Meeting obj) {
            return meet_start.compareTo(obj.meet_start);
        }
    }
}
