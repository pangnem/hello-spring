package hello.hellospring.domain;

import java.util.Map;
import java.util.Objects;

public class Member {
    private final Long id;
    private final String name;

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void saveTo(Map<Long, Member> store) {
        store.put(this.id, this);
    }

    public boolean matchName(String name) {
        return Objects.equals(this.name, name);
    }
}
