package cafeorder.service;

import cafeorder.domain.Member;
import cafeorder.domain.Wage;
import cafeorder.domain.Week;
import cafeorder.dto.MemberNameResponse;
import cafeorder.dto.MemberNamesResponse;
import cafeorder.dto.MemberViewDto;
import cafeorder.dto.MembersResponse;
import cafeorder.dto.WageRequest;
import cafeorder.repository.MemberRepository;
import cafeorder.util.MoneyString;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static cafeorder.domain.Week.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void addMember(String name) {
        existedName(name);
        memberRepository.save(Member.of(name));
    }

    @Transactional
    public void updateMember(Long id, String name) {
        existedName(name);
        Member member = memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        member.changeName(name);
    }

    @Transactional
    public void deleteMember(Long id) {
        memberRepository.delete(memberRepository.findById(id)
            .orElseThrow(IllegalArgumentException::new));
    }

    @Transactional
    public void addWage(WageRequest wageRequest) {
        Member member = memberRepository.findById(wageRequest.getMemberId()).orElseThrow(IllegalArgumentException::new);
        member.getWages().clear();

        createWage(member, WEEK1, wageRequest.getTime1(), wageRequest.isCheck1());
        createWage(member, WEEK2, wageRequest.getTime2(), wageRequest.isCheck2());
        createWage(member, WEEK3, wageRequest.getTime3(), wageRequest.isCheck3());
        createWage(member, WEEK4, wageRequest.getTime4(), wageRequest.isCheck4());
        createWage(member, WEEK5, wageRequest.getTime5(), wageRequest.isCheck5());
        member.calculateTotalWage();
    }

    public MemberNameResponse getById(Long id) {
        return memberRepository.findById(id)
            .map(this::memberToNameResponse)
            .orElseThrow(IllegalArgumentException::new);
    }

    public List<MemberNamesResponse> getAllName() {
        return memberRepository.findMembers().stream()
            .map(this::memberToMemberNameResponse)
            .collect(Collectors.toList());
    }

    public List<MembersResponse> findAll() {
        return memberRepository.findAll().stream()
            .map(this::memberToResponse)
            .collect(Collectors.toList());
    }

    private MembersResponse memberToResponse(Member member) {
        MembersResponse response = new MembersResponse();
        response.setName(member.getName());
        response.setWaget1(member.findWeek(WEEK1).toString());
        response.setWaget2(member.findWeek(WEEK2).toString());
        response.setWaget3(member.findWeek(WEEK3).toString());
        response.setWaget4(member.findWeek(WEEK4).toString());
        response.setWaget5(member.findWeek(WEEK5).toString());
        response.setTotal(member.getTotalWage().toString());
        return response;
    }

    private void createWage(Member member, Week week, int time, boolean holiday) {
        Wage.builder().week(week).workTime(time).holidayPay(holiday).member(member).build();
    }

    private MemberNameResponse memberToNameResponse(Member member) {
        MemberNameResponse response = new MemberNameResponse();
        response.setId(member.getId());
        response.setName(member.getName());
        return response;
    }

    private MemberNamesResponse memberToMemberNameResponse(Member member) {
        MemberNamesResponse dto = new MemberNamesResponse();
        dto.setId(member.getId());
        dto.setName(member.getName());
        return dto;
    }

    private void existedName(String name) {
        if (memberRepository.existsByName(name)) {
            throw new IllegalArgumentException("같은 이름의 직원이 존재합니다");
        }
    }

}
