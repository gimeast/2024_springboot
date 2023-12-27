package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

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
public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository()); //생성자 주입으로 dip를 지켰다.
    }

    private MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy(); //클라이언트 코드를(impl) 수정하지 않고 구성영역만(config) 수정하여 변경할 수 있다.
//        return new FixDiscountPolicy();
    }

}
