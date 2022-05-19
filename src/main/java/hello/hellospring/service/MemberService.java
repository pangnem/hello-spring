package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.domain.MemberRepository;
import hello.hellospring.dto.MemberFindResponse;
import hello.hellospring.dto.MemberJoinRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(MemberJoinRequest memberJoinRequest) {
        validateDuplicate(memberJoinRequest.name());
        Member member = memberRepository.save(memberJoinRequest.toMember());

        return member.toJoinResponse()
                .id();
    }

    public List<MemberFindResponse> findMembers() {
        return memberRepository.findAll().stream()
                .map(Member::toFindResponse)
                .collect(Collectors.toList());
    }

    public MemberFindResponse findMember(long id) {
        return memberRepository.findById(id)
                .map(Member::toFindResponse)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }

    private void validateDuplicate(String name) {
        memberRepository.findByName(name)
                .ifPresent(it -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
