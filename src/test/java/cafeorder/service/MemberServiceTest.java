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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

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
    MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        mockRepository();
    }

    private void mockRepository() {
        List<Member> members = new ArrayList<>();
        members.add(new Member("testName1"));
        members.add(new Member("testName2"));

        given(memberRepository.findAll()).willReturn(members);

    }

    @Test
    @DisplayName("")
    void getAll() {
        assertEquals(2, memberService.getAll().size());
    }

}