package hello.core.discount;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.order.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RateDiscountPolicyTest {

    AppConfig appconfig = new AppConfig();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_o() throws Exception {
        //given
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        appconfig.memberService().join(member);

        //when
        Order order = appconfig.orderService().createOrder(1L, "mac M3 PRO", 3600000);
        int discount = order.getDiscountPrice();

        //then
        assertThat(discount).isEqualTo(1000);

    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
    void vip_x() throws Exception {
        //given
        Member member = new Member(2L, "memberBASIC", Grade.BASIC);
        appconfig.memberService().join(member);

        //when
        Order order = appconfig.orderService().createOrder(2L, "mac M3 MAX", 4900000);
        int discount = order.getDiscountPrice();

        //then
        assertThat(discount).isEqualTo(0);

    }
}