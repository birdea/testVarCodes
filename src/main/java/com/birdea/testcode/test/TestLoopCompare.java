package com.birdea.testcode.test;

import com.birdea.testcode.util.L;
import org.junit.Test;

/**
 * @author seungtae.hwang(birdea @ sk.com)
 * @since 2018. 7. 17. created by seungtae.hwang
 */
public class TestLoopCompare {

    @Test
    public void test() {
        RequestId r1 = new RequestId(1);
        RequestId r2 = new RequestId(2);
        RequestId r3 = new RequestId(3);
        RequestId r4 = new RequestId(4);
        RequestId r5 = new RequestId(5);
        RequestId r6 = new RequestId(6);
        RequestId r7 = new RequestId(7);
        RequestId r8 = new RequestId(8);
        RequestId r9 = new RequestId(9);
        RequestId r0 = new RequestId(0);

        RequestId last;
        last = latestRequestId(r1,r2,r3,r4);
        L.msg("result(1,2,3,4):"+last.getTimeStamp());
        last = latestRequestId(r1,r2,r3,r4,r0);
        L.msg("result(1,2,3,4,0):"+last.getTimeStamp());
        last = latestRequestId(r2,r3,r4,r0,r9,r8);
        L.msg("result(2,3,4,0,9,8):"+last.getTimeStamp());
        last = latestRequestId(null);
        L.msg("result(null):"+(last==null));
        last = latestRequestId(r2);
        L.msg("result(2):"+last.getTimeStamp());
        last = latestRequestId(r2,r7);
        L.msg("result(2,7):"+last.getTimeStamp());
        last = latestRequestId(r7,r2);
        L.msg("result(7,2):"+last.getTimeStamp());
        last = latestRequestId(r7,r2,r3);
        L.msg("result(7,2,3):"+last.getTimeStamp());
        last = latestRequestId(r7,null,r3);
        L.msg("result(7,null,3):"+last.getTimeStamp());
        last = latestRequestId(r3,null,r7);
        L.msg("result(3,null,7):"+last.getTimeStamp());
        last = latestRequestId(null,null,r7);
        L.msg("result(null,null,7):"+last.getTimeStamp());
        last = latestRequestId(null,null,null);
        L.msg("result(null,null,null):"+(last==null));
    }

    public static RequestId latestRequestId(RequestId... ids) {
        if (ids == null || ids.length < 1) {
            return null;
        }
        RequestId recentId = ids[0];
        for (RequestId id : ids) {
            if (recentId == null) {
                recentId = id;
            }
            if (id != null && recentId != null && id.getTimeStamp() > recentId.getTimeStamp()) {
                recentId = id;
            }
        }
        return recentId;
    }

    class RequestId {
        private String mDeviceSerialNumber = "";
        private String mType = "";
        private String mAction = "";
        private String mDate = "";
        public long mTimeStamp = 0;

        RequestId(long timeStamp) {
            mTimeStamp = timeStamp;
        }

        public long getTimeStamp() {
            return mTimeStamp;
        }
    }

}
