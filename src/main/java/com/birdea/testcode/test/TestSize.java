package com.birdea.testcode.test;

import com.birdea.testcode.util.Base64;
import com.birdea.testcode.util.L;
import org.junit.Test;

/**
 * @author seungtae.hwang(birdea @ sk.com)
 * @since 2018. 7. 18. created by seungtae.hwang
 */
public class TestSize {

    String value = "{\"resultCode\":\"0\",\"errorCode\":null,\"errorMsg\":null}";

    String val2 = "{\"log_ct\":\"checkConnection : AsrState \\u003d WAITING_TRIGGER / APP_STATE_IDLE\",\"log_lel\":\"I\",\"log_typ_cd\":\"D02002\",\"tag\":\"ServiceApp_AladdinAiCloudManager\",\"app_vsn\":\"1.8.4\",\"dvc_nm\":\"DVC_APP_TMAP_ANDROID\",\"fmw_vsn\":\"1701704\",\"log_dt\":1531965049651,\"os\":\"Android\",\"srl_no\":\"NUGU_TMAP_007\",\"svr_typ\":\"STG\"}\u001E{\"log_ct\":\"++ startCardReceived\",\"log_lel\":\"D\",\"log_typ_cd\":\"D02002\",\"tag\":\"ServiceApp_AladdinAiCloudManager\",\"app_vsn\":\"1.8.4\",\"dvc_nm\":\"DVC_APP_TMAP_ANDROID\",\"fmw_vsn\":\"1701704\",\"log_dt\":1531965049661,\"os\":\"Android\",\"srl_no\":\"NUGU_TMAP_007\",\"svr_typ\":\"STG\"}\u001E{\"log_ct\":\"startCardReceived : AI Token \\u003d\\u003d 014E1EC841464C4D9DCDA103912E5399 , ServerConfig \\u003d\\u003d STG\",\"log_lel\":\"D\",\"log_typ_cd\":\"D02002\",\"tag\":\"ServiceApp_AladdinAiCloudManager\",\"app_vsn\":\"1.8.4\",\"dvc_nm\":\"DVC_APP_TMAP_ANDROID\",\"fmw_vsn\":\"1701704\",\"log_dt\":1531965049664,\"os\":\"Android\",\"srl_no\":\"NUGU_TMAP_007\",\"svr_typ\":\"STG\"}\u001E";

    @Test
    public void test() {
        long size = value.getBytes().length;
        L.msg("size:"+size);
        //Base64.getEncoder().encodeToString(value.getBytes());
        String strB64 = Base64.encodeToString(val2.getBytes(), 0);
        L.msg(String.format("Base64 src(%s)->dst(%s)", val2.length(), strB64.length()));

        L.msg("hex:"+byteArrayToHex(strB64.getBytes()));
    }


    String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder();
        for(final byte b: a)
            sb.append(String.format("%02x ", b&0xff));
        return sb.toString();
    }
}
