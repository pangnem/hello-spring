package hello.hellospring.domain;

import hello.hellospring.dto.MemberFindResponse;
import hello.hellospring.dto.MemberJoinResponse;
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

    @Nested
    class toJoinResponse {

        @Test
        void 회원가입_응답_리턴() {
            Member member = TestMemberFactory.create();

            MemberJoinResponse memberJoinResponse = member.toJoinResponse();
            
            assertThat(memberJoinResponse).isEqualTo(new MemberJoinResponse(1L));
        }
    }

    @Nested
    class toFindResponse {

        @Test
        void 멤버조회_응답_리턴() {
            Member member = TestMemberFactory.create();

            MemberFindResponse memberFindResponse = member.toFindResponse();

            assertThat(memberFindResponse).isEqualTo(new MemberFindResponse(1L, "memberName"));
        }
    }

}
