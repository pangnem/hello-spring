package hello.hellospring.infra;

import hello.hellospring.domain.Member;
import hello.hellospring.domain.MemberRepository;
import hello.hellospring.utils.MemberIdGenerator;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryMemberRepository implements MemberRepository {
    private static final Map<Long, Member> store = new ConcurrentHashMap<>();

    @Override
    public Member save(Member member) {
        member.saveTo(store);

        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.matchName(name))
                .findFirst();
    }

    @Override
    public List<Member> findAll() {
        return store.values().stream()
                .toList();
    }

    @Override
    public void deleteAll() {
        store.clear();
        MemberIdGenerator.init();
    }
}
