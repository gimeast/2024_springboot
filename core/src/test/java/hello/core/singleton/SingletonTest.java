package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        //given
        AppConfig appConfig = new AppConfig();

        //when
        //1 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        //2 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //then
        System.out.println(memberService1);
        System.out.println(memberService2);

        //memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        //given
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        //when
        singletonService1.setName("홍길동");

        //then
        System.out.println("singletonService1:" + singletonService1);
        System.out.println("singletonService2:" + singletonService2);

        System.out.println("singletonService2.name:" + singletonService2.getName());

        assertThat(singletonService1).isSameAs(singletonService2);
        assertThat(singletonService1).isEqualTo(singletonService2);
        // same -> == 주소값 비교
        // equal -> java의 equals 문자열 비교
    }
    
    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        //given
//        AppConfig appConfig = new AppConfig();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //when
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        //then
        System.out.println(memberService1);
        System.out.println(memberService2);

        //memberService1 != memberService2
        assertThat(memberService1).isSameAs(memberService2);
        
    }
}
