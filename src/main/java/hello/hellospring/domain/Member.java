package hello.hellospring.domain;

import hello.hellospring.dto.MemberFindResponse;
import hello.hellospring.dto.MemberJoinResponse;
import hello.hellospring.utils.MemberIdGenerator;

import java.util.Map;
import java.util.Objects;

public class Member {
    private final Long id;
    private final String name;

    public Member(String name) {
        this(MemberIdGenerator.generate(), name);
    }

    Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void saveTo(Map<Long, Member> store) {
        store.put(this.id, this);
    }

    public boolean matchName(String name) {
        return Objects.equals(this.name, name);
    }

    public MemberJoinResponse toJoinResponse() {
        return new MemberJoinResponse(this.id);
    }

    public MemberFindResponse toFindResponse() {
        return new MemberFindResponse(this.id, this.name);
    }
}
