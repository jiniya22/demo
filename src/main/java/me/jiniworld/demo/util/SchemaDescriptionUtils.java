package me.jiniworld.demo.util;

public class SchemaDescriptionUtils {
    public static final String name = "이름";

    public static class User {
        public static final String type = "사용자 유형(BASIC|OWNER)";
        public static final String email = "이메일";
        public static final String sex = "성별(M|F)";
        public static final String birthDate = "생년월일(yyyy-MM-dd)";
        public static final String phoneNumber = "전화번호";
        public static final String password = "비밀번호";
        public static final String stores = "보유 가게";
    }
    
    public static class Store {
        public static final String business = "가게 업종";
        public static final String user = "가게 소유주";
    }
}
