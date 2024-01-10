package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        //given
        MemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "tester", Grade.VIP));
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());

        //when
        Order order = orderService.createOrder(1L, "itemA", 30000);

        //then
        assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }

}