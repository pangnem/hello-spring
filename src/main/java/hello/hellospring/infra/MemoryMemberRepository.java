package hello.hellospring.infra;

import hello.hellospring.domain.Member;
import hello.hellospring.domain.MemberRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MemoryMemberRepository implements MemberRepository {
    private static final Map<Long, Member> store = new ConcurrentHashMap<>();
    private static final AtomicLong sequence = new AtomicLong(0);

    @Override
    public Member save(Member member) {
        increaseSequence();
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
    }

    private void increaseSequence() {
        sequence.set(sequence.get());
    }
}
