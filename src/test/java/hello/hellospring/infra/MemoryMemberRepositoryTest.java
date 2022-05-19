package hello.hellospring.infra;

import hello.hellospring.domain.Member;
import hello.hellospring.domain.MemberRepository;
import hello.hellospring.domain.TestMemberFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {
    private MemberRepository memoryMemberRepository = new MemoryMemberRepository();

    @AfterEach
    void tearDown() {
        memoryMemberRepository.deleteAll();
    }

    @Test
    @DisplayName("save와 findAll 테스트")
    void save_findAll() {
        Member member = TestMemberFactory.create();

        memoryMemberRepository.save(member);

        assertThat(memoryMemberRepository.findAll()).hasSize(1);
    }

    @Test
    void findById() {
        Member member = TestMemberFactory.create();
        memoryMemberRepository.save(member);

        Optional<Member> actual = memoryMemberRepository.findById(1L);

        assertThat(actual).isEqualTo(Optional.of(member));
    }


    @Test
    void findByName() {
        Member member = TestMemberFactory.create();
        memoryMemberRepository.save(member);

        Optional<Member> actual = memoryMemberRepository.findByName("memberName");

        assertThat(actual).isEqualTo(Optional.of(member));
    }
}
