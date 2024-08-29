package com.sparta.aibusinessproject.domain;

public enum UserRoleEnum {
    CUSTOMER(Authority.CUMSTOMER),
    MANAGER(Authority.MANAGER),
    MASTER(Authority.MASTER),
    OWNER(Authority.OWNER);

    private final String authority;

    UserRoleEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    // 앞에 ROLE_을 붙이는 게 spring security의 관례..
    public static class Authority {
        public static final String CUMSTOMER = "ROLE_CUSTOMER";
        public static final String OWNER = "ROLE_OWNER";
        public static final String MANAGER = "ROLE_MANAGER";
        public static final String MASTER = "ROLE_MASTER";
    }
}
