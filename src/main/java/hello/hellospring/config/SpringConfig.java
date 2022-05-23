package hello.hellospring.config;

import hello.hellospring.domain.MemberRepository;
import hello.hellospring.infra.JpaMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {
    private final EntityManager entityManager;

    public SpringConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JpaMemberRepository(entityManager);
    }
}
