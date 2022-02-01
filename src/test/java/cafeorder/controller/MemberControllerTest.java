package cafeorder.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import cafeorder.domain.Member;
import cafeorder.dto.MemberNameResponse;
import cafeorder.dto.MembersResponse;
import cafeorder.service.MemberService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@ExtendWith(MockitoExtension.class)
class MemberControllerTest {

    @MockBean
    private MemberService memberService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("회원 폼 페이지요청 테스트")
    void formPageTest() throws Exception {
        mockMvc.perform(get("/members/form"))
            .andDo(print())
            .andExpect(model().attributeExists("member"))
            .andExpect(status().isOk())
            .andExpect(view().name("members/form"));
    }

    @Test
    @DisplayName("회원 폼 전송 테스트")
    void postFormTest() throws Exception {
        mockMvc.perform(post("/members/form")
            .param("name", "tester"))
            .andDo(print())
            .andExpect(status().is3xxRedirection());

        verify(memberService, times(1)).addMember("tester");
    }

    @Test
    @DisplayName("회원 정보 조회 테스트")
    void infoPageTest() throws Exception {
        MemberNameResponse response = new MemberNameResponse();
        response.setId(1L);
        response.setName("tester");

        List<MemberNameResponse> names = List.of(response);
        given(memberService.getAllName()).willReturn(names);
        mockMvc.perform(get("/members/info"))
            .andDo(print())
            .andExpect(model().attributeExists("members"))
            .andExpect(model().attribute("members", names))
            .andExpect(status().isOk())
            .andExpect(view().name("members/info"));
    }

    @Test
    @DisplayName("회원 단건 조회 테스트")
    void getMemberTest() throws Exception {
        MemberNameResponse response = new MemberNameResponse();
        response.setId(1L);
        response.setName("tester");

        given(memberService.getById(1L)).willReturn(response);

        mockMvc.perform(get("/members/{id}/form", 1L))
            .andDo(print())
            .andExpect(model().attributeExists("member"))
            .andExpect(model().attribute("member", response))
            .andExpect(view().name("members/update"))
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원 수정 요청 테스트")
    void postMemberTest() throws Exception {
        mockMvc.perform(post("/members/{id}/form", 1L)
            .param("name", "tester"))
            .andDo(print())
            .andExpect(status().is3xxRedirection());

        verify(memberService, times(1)).updateMember(1L, "tester");
    }

    @Test
    @DisplayName("계산 폼 페이지 테스트")
    void calcFormTest() throws Exception {
        mockMvc.perform(get("/members/wage"))
            .andDo(print())
            .andExpect(model().attributeExists("members"))
            .andExpect(model().attributeExists("wage"))
            .andExpect(view().name("members/wage"));
    }

    @Test
    @DisplayName("계산 폼 요청 테스트")
    void postWageFormTest() throws Exception {
        mockMvc.perform(post("/members/wage")
            .param("memberId", "1")
            .param("time1", "12")
            .param("time2", "12")
            .param("time3", "12")
            .param("time4", "12")
            .param("time5", "12")
            .param("check1", "true")
            .param("check2", "true")
            .param("check3", "true")
            .param("check4", "true")
            .param("check5", "true"))
            .andDo(print())
            .andExpect(status().is3xxRedirection());

        verify(memberService, times(1)).addWage(any());
    }

    @Test
    @DisplayName("계산 결과 페이지")
    void totalCalcPageTest() throws Exception {
        List<MembersResponse> response = List.of(new MembersResponse());
        given(memberService.findAll()).willReturn(response);

        mockMvc.perform(get("/members/total"))
            .andDo(print())
            .andExpect(model().attributeExists("members"))
            .andExpect(model().attribute("members", response))
            .andExpect(view().name("members/total"));
    }

}