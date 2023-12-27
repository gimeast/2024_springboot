package hello.core.discount;

import hello.core.member.Member;

/**
 * packageName     :   hello.core.discount
 * fileName        :   DiscountPolicy
 * author          :   donguk
 * date            :   23. 12. 27.
 * description     :   할인 정책
 * =================================================
 * DATE       |     Author      |       NOTE
 * 23. 12. 27.       donguk              최초 생성
 */
public interface DiscountPolicy {
    /**
     * @Method         : discount
     * @Description    : 할인
     * @Author         : donguk
     * @Date           : 2023. 12. 27.
     * @return         : 할인 대상 금액
     */
    int discount(Member member, int price);
}
