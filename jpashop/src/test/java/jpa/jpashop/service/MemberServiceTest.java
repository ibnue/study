package jpa.jpashop.service;

import jpa.jpashop.domain.Member;
import jpa.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
//  @Autowired EntityManager em;

    @Test
//  @Rollback(false)
    void join() {
        // given
        Member member = new Member();
        member.setName("Eunbi");

        // when
        Long saveId = memberService.join(member);

        // then
        // em.flush();
        Member saveMember = memberService.findOne(saveId);
        assertThat(saveMember).isEqualTo(member);
    }

    @Test
    void duplicateMember() {
        // given
        Member memberA = new Member();
        memberA.setName("Eunbi");

        Member memberB = new Member();
        memberB.setName("Eunbi");

        // when
        memberService.join(memberA);

        // then
        assertThrows(IllegalStateException.class,
                () -> memberService.join(memberB));
    }
}