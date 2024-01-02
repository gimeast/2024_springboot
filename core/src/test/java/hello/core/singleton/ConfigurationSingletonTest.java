package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        //given
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        //when
        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        //then
        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberRepository1).isSameAs(memberRepository2).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        //given
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        //when

        //then
        System.out.println("bean = " + bean.getClass()); //class hello.core.AppConfig$$SpringCGLIB$$0
        // 순수한 클래스가 아닌 스프링이 CGLIB라는 바이트코드 조작 라이브러리를 사용해서
        // AppConfig 클래스를 상속받은 임의의 다른 클래스를 만들고,
        // 그 클래스를 스프링 빈으로 등록한 것이다
    }
}
