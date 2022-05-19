package hello.hellospring.dto;

import java.util.Objects;

public class MemberJoinResponse {
    private final long id;

    public MemberJoinResponse(long id) {
        this.id = id;
    }

    public long id() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MemberJoinResponse that = (MemberJoinResponse) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
