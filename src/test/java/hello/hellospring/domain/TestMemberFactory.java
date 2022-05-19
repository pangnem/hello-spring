package hello.hellospring.domain;

public class TestMemberFactory {
    public static Member create() {
        return new Member(1L, "memberName");
    }
}
