package cafeorder.service;

import cafeorder.domain.Member;
import cafeorder.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * author {yhh1056}
 * Create by {2020/09/29}
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository repository;

    @Test
    @DisplayName("직원 등록")
    void add() {
        Member member = new Member("직원1");
        memberService.add(member);

        assertEquals(1, repository.findByName("직원1").size());
    }

//    @Test
//    @DisplayName("근무 시간 추가")
//    void addTime() {
//        Member member = new Member("직원1");
//        memberService.add(member);
//
//        memberService.addTime(member.getName(), 30, 3000);
//
//        List<Member> list = repository.findAll();
//
//        assertEquals("직원1", list.get(0).getName());
//        assertEquals(30, list.get(0).getTime());
//    }
}