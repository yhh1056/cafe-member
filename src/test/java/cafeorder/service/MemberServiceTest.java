package cafeorder.service;

import cafeorder.domain.Member;
import cafeorder.domain.Wage;
import cafeorder.domain.Week;
import cafeorder.dto.WageRequest;
import cafeorder.repository.MemberRepository;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("MemberService 테스트")
class MemberServiceTest {

    @InjectMocks
    MemberService memberService;

    @Mock
    MemberRepository memberRepository;

    @Test
    @DisplayName("직원 추가 테스트")
    void addMemberTest() {
        String name = "tester";

        // given
        given(memberRepository.existsByName(name)).willReturn(false);

        // when
        memberService.addMember(name);

        // then
        then(memberRepository).should().save(any());

    }

    @Test
    @DisplayName("이름 중복 테스트")
    void existedMemberTest() {
        String name = "tester";

        // given
        given(memberRepository.existsByName(name)).willReturn(true);

        // when
        assertThrows(IllegalArgumentException.class,
            () -> memberService.addMember("tester"));
    }

    @Test
    @DisplayName("이름 변경 테스트")
    void changeNameTest() {
        Member mockMember = mock(Member.class);

        given(memberRepository.findById(1L)).willReturn(Optional.of(mockMember));

        memberService.updateMember(1L, "updateName");

        then(mockMember).should().changeName("updateName");
    }

    @Test
    @DisplayName("이름 변경시 중복될 경우 테스트")
    void changeNameDuplicateTest() {
        given(memberRepository.existsByName("updateName")).willReturn(true);

        assertThrows(IllegalArgumentException.class,
            () -> memberService.updateMember(1L, "updateName"));
    }

    @Test
    @DisplayName("주급 추가 테스트")
    void addWageTest() {
        Member mockMember = mock(Member.class);
        mockMember.changeName("sadadas");

        WageRequest wageRequest = new WageRequest();
        wageRequest.setMemberId(1L);

        given(memberRepository.findById(1L)).willReturn(Optional.of(mockMember));

        memberService.addWage(wageRequest);

        then(mockMember).should().calculateTotalWage();
    }

}