package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.domain.MemberRepository;
import hello.hellospring.domain.TestMemberFactory;
import hello.hellospring.dto.MemberFindResponse;
import hello.hellospring.dto.MemberJoinRequest;
import hello.hellospring.infra.MemoryMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class MemberServiceTest {
    private MemberRepository memberRepository;
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        this.memberRepository = new MemoryMemberRepository();
        this.memberService = new MemberService(memberRepository);

        this.memberRepository.deleteAll();
    }

    @Nested
    class join_메서드는 {

        @Nested
        class 중복되지_않은_회원이_주어질경우 {

            @Test
            void 생성된_아이디를_리턴한다() {
                MemberJoinRequest memberJoinRequest = new MemberJoinRequest("memberName");

                Long join = memberService.join(memberJoinRequest);

                assertThat(join).isEqualTo(1L);
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
                Member member = TestMemberFactory.create();
                memberRepository.save(member);

                MemberFindResponse memberFindResponse = memberService.findMember(1L);

                assertThat(memberFindResponse).isEqualTo(new MemberFindResponse("memberName"));
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
