package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); //XXX: OCP 위반, DIP도 지키지않음.
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); //XXX: OCP 위반, DIP도 지키지않음.

//    private DiscountPolicy discountPolicy; //OCP와 DIP 모두 지킴 그러나 실행시 NullPointerException이 난다.

    //인터페이스에만 의존하도록 만든다.!!
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);

        if(member != null) {
            int discountPrice = discountPolicy.discount(member, itemPrice);
            return new Order(memberId, itemName, itemPrice, discountPrice);
        }

        return null;
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
