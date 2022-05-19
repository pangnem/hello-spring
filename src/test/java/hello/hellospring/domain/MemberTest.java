package hello.hellospring.domain;

import hello.hellospring.TestMemberFactory;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberTest {

    @Nested
    class matchName {

        @Nested
        class withExistName {

            @Test
            void returnsTrue() {
                Member member = TestMemberFactory.create();

                boolean actual = member.matchName("memberName");

                assertThat(actual).isTrue();
            }
        }

        @Nested
        class withNotExistName {

            @Test
            void returnsFalse() {
                Member member = TestMemberFactory.create();

                boolean actual = member.matchName("xxxxx");

                assertThat(actual).isFalse();
            }
        }

    }

}
