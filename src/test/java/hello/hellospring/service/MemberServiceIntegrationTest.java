package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.domain.MemberRepository;
import hello.hellospring.domain.MemberTest;
import hello.hellospring.dto.MemberFindResponse;
import hello.hellospring.dto.MemberJoinRequest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;

    @Nested
    class join_메서드는 {

        @Nested
        class 중복되지_않은_회원이_주어질경우 {

            @Test
            void 생성된_아이디를_리턴한다() {
                MemberJoinRequest memberJoinRequest = new MemberJoinRequest("memberName");

                Long join = memberService.join(memberJoinRequest);

                assertThat(join).isGreaterThan(0L);
            }
        }

        @Nested
        class 중복된_회원이_주어질경우 {

            @Test
            void IllegalStateException을_던진다() {
                MemberJoinRequest memberJoinRequest = new MemberJoinRequest("memberName");
                memberRepository.save(memberJoinRequest.toMember());

                assertThatIllegalStateException()
                        .isThrownBy(() -> memberService.join(memberJoinRequest))
                        .withMessage("이미 존재하는 회원입니다.");
            }
        }

    }

    @Nested
    class findMembers_메서드는 {

        @Test
        void 모든_멤버_조회응답을_리턴한다() {
            memberRepository.save(new Member("memberName1"));
            memberRepository.save(new Member("memberName2"));
            memberRepository.save(new Member("memberName3"));

            List<MemberFindResponse> members = memberService.findMembers();

            assertThat(members).hasSize(3);
        }
    }

    @Nested
    class findMember_메서드는 {

        @Nested
        class 존재하는_아이디가_주어질경우 {

            @Test
            void 찾은_회원_조회응답을_리턴한다() {
                Member member = MemberTest.MEMBER2;
                memberRepository.save(member);

                MemberFindResponse memberFindResponse = memberService.findMember(member.id());

                assertThat(memberFindResponse).isEqualTo(new MemberFindResponse(member.id(), "memberName"));
            }
        }

        @Nested
        class 존재하지않는_아이디가_주어질경우 {

            @Test
            void IllegalArgumentException을_던진다() {
                assertThatIllegalArgumentException()
                        .isThrownBy(() -> memberService.findMember(1L))
                        .withMessage("존재하지 않는 회원입니다.");
            }

        }
    }
}
