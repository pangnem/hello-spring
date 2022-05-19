package hello.hellospring;

import hello.hellospring.domain.Member;

public class TestMemberFactory {
    public static Member create() {
        return new Member(1L, "memberName");
    }
}
