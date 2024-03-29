package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * packageName     :   hello.core
 * fileName        :   AppConfig
 * author          :   donguk
 * date            :   23. 12. 27.
 * description     :   객체의 생성과 연결은 AppConfig가 담당한다. DIP완성: 추상에만 의존하면 되고 구체 클래스를 몰라도 된다.
 * =================================================
 * DATE       |     Author      |       NOTE
 * 23. 12. 27.       donguk              최초 생성
 */
@Configuration //@Configuration을 붙이면 CGLIB 기술을 사용해서 싱글톤을 보장하지만 @Configuration을 붙이지 않으면 스프링 컨테이너가 관리하지 않게 되고 싱글톤이 적용되지 않는다.
public class AppConfig {

    /*
    의존관계를 설정한다.
     */
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository()); //생성자 주입으로 dip를 지켰다.
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("==========call============"); //한번만 호출된다..
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
//        return null;
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy(); //클라이언트 코드를(impl) 수정하지 않고 구성영역만(config) 수정하여 변경할 수 있다.
//        return new FixDiscountPolicy();
    }

}
