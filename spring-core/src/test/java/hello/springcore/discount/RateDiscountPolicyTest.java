package hello.springcore.discount;

import hello.springcore.member.Grade;
import hello.springcore.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다")
    void vipDiscount() {
        //given
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        //when
        int discountPrice = discountPolicy.discount(member, 20000);

        //then
        assertThat(discountPrice).isEqualTo(2000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다")
    void basicDiscount() {
        //given
        Member member = new Member(2L, "memberBASIC", Grade.BASIC);

        //when
        int discountPrice = discountPolicy.discount(member, 20000);

        //then
        assertThat(discountPrice).isEqualTo(0);
    }
}