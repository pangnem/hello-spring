package hello.hellospring.dto;

import java.util.Objects;

public class MemberFindResponse {
    private final String name;

    public MemberFindResponse(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MemberFindResponse that = (MemberFindResponse) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
