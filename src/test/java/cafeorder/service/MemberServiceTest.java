package cafeorder.service;

import cafeorder.domain.Member;
import cafeorder.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * author {yhh1056}
 * Create by {2020/09/29}
 */
@ExtendWith(MockitoExtension.class)
@Transactional
class MemberServiceTest {

    @InjectMocks
    MemberService memberService;
    @Mock
    MemberRepository repository;

    @BeforeEach
    void setUp() {

    }

    @Test
    @DisplayName("직원 등록")
    void add() {
        Member member = new Member("직원1");
        memberService.add(member);

//        when(repository.save(any())).

        assertEquals(member, repository.findById(1L));
    }

    @Test
    @DisplayName("직원 등록")
    void isExistedName() {
        Member member = new Member("직원1");
        memberService.add(member);

        assertThrows(IllegalStateException.class, () -> memberService.add(member));
    }

//    @Test
//    void timeDto() {
//        List<Member> members = memberService.getAll();
//        TimeDto dto = new TimeDto(members);
//    }
}