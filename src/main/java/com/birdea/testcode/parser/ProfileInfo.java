package com.birdea.testcode.parser;

public class ProfileInfo {
    public String uid = "";
    public String empno = "";
    public String depnm = "";
    public String profile_image = "";
    private String thumbnail_image = ""; // 일반 메세지 응답 필드명
    private String thumbnail_url = ""; // PUSH 메세지 응답 필드명
    public String name = "";
    public String telephoneNumber = "";
    public String companyName = "";

    @Override
    public String toString() {
        return new StringBuilder().append("uid:").append(uid)
                .append(", empno:").append(empno)
                .append(", depnm:").append(depnm)
                .append(", profile_image:").append(profile_image)
                .append(", thumbnail_image:").append(thumbnail_image)
                .append(", thumbnail_url:").append(thumbnail_url)
                .append(", name:").append(name)
                .append(", telephoneNumber:").append(telephoneNumber)
                .append(", companyName:").append(companyName)
                .toString();
    }
}
