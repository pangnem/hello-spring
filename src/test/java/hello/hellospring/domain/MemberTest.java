package hello.hellospring.domain;

import hello.hellospring.dto.MemberFindResponse;
import hello.hellospring.dto.MemberJoinResponse;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberTest {
    public static final Member MEMBER1 = new Member(1L, "memberName");
    public static final Member MEMBER2 = new Member("memberName");

    @Nested
    class matchName {

        @Nested
        class withExistName {

            @Test
            void returnsTrue() {
                boolean actual = MEMBER1.matchName("memberName");

                assertThat(actual).isTrue();
            }
        }

        @Nested
        class withNotExistName {

            @Test
            void returnsFalse() {
                boolean actual = MEMBER1.matchName("xxxxx");

                assertThat(actual).isFalse();
            }
        }

    }

    @Nested
    class toJoinResponse {

        @Test
        void 회원가입_응답_리턴() {
            MemberJoinResponse memberJoinResponse = MEMBER1.toJoinResponse();
            
            assertThat(memberJoinResponse).isEqualTo(new MemberJoinResponse(1L));
        }
    }

    @Nested
    class toFindResponse {

        @Test
        void 멤버조회_응답_리턴() {
            MemberFindResponse memberFindResponse = MEMBER1.toFindResponse();

            assertThat(memberFindResponse).isEqualTo(new MemberFindResponse(1L, "memberName"));
        }
    }

}
