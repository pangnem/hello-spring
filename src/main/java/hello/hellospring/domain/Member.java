package hello.hellospring.domain;

import hello.hellospring.dto.MemberFindResponse;
import hello.hellospring.dto.MemberJoinResponse;
import hello.hellospring.utils.MemberIdGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Map;
import java.util.Objects;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    protected Member() {
    }

    public Member(String name) {
        this.name = name;
    }

    Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void saveTo(Map<Long, Member> store) {
        this.id = MemberIdGenerator.generate();

        store.put(id, this);
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

    public Long id() {
        return this.id;
    }
}
