package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component // Configuration 파일에서 @Bean으로 직접 등록하는 방식이 더 좋다. (AOP를 쓰겠다는 것을 명시)
public class TimeTraceAop {

    @Around("execution(* hello.hellospring.service..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START:" + joinPoint.toString() + " ");
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END:" + joinPoint + " " + timeMs + "ms");
        }
    }
}
