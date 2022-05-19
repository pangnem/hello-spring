package hello.hellospring.dto;

import hello.hellospring.domain.Member;

public class MemberJoinRequest {
    private final String name;

    public MemberJoinRequest(String name) {
        this.name = name;
    }

    public String name() {
        return this.name;
    }

    public Member toMember() {
        return new Member(this.name);
    }
}
