package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        //한번 만든 객체는 지워줘야함 왜냐하면 테스트는 순서에 영향을 받으면 안되기 때문(의존관계가 없어야함)
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() { //과감하게 한국말로 해도 됨
        //given
        Member member = new Member();
        member.setName("sameName");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("sameName");

        Member member2 = new Member();
        member2.setName("sameName");

        //when
        memberService.join(member1);

        //then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("already exists.");
/*
        try{
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("already exist.");
        }
*/

    }

    @Test
    void findMembers(){
    }

    @Test
    void findOne() {
    }
}