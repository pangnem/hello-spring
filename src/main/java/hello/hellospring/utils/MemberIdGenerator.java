package hello.hellospring.utils;

import java.util.concurrent.atomic.AtomicLong;

public class MemberIdGenerator {
    private static final long INCREASE_VALUE = 1;
    private static final long INITIAL_VALUE = 0;

    private static AtomicLong sequence;

    static {
        sequence = new AtomicLong(INITIAL_VALUE);
    }

    public static Long generate() {
        increaseSequence();

        return sequence.get();
    }

    private static void increaseSequence() {
        sequence.set(sequence.get() + INCREASE_VALUE);
    }

    public static void init() {
        sequence = new AtomicLong(INITIAL_VALUE);
    }
}
