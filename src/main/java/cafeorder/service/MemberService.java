package cafeorder.service;

import cafeorder.domain.Member;
import cafeorder.dto.MemberNamesResponse;
import cafeorder.dto.MemberSaveDto;
import cafeorder.dto.MemberViewDto;
import cafeorder.repository.MemberRepository;
import cafeorder.dto.WageSaveDto;
import cafeorder.util.MoneyString;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void addMember(String name) {
        if (memberRepository.existsByName(name)) {
            throw new IllegalArgumentException("같은 이름의 직원이 존재합니다");
        }
        memberRepository.save(Member.of(name));
    }

    @Transactional
    public void updateMember(Long id, String name) {
        Member member = memberRepository.findById(id).orElseThrow(IllegalAccessError::new);
        member.changeName(name);
    }

    @Transactional
    public void deleteMember(Long id) {
        memberRepository.delete(memberRepository.findById(id)
            .orElseThrow(IllegalAccessError::new));
    }

    @Transactional
    public void addWage(Long id, @Valid WageSaveDto wageSaveDto) {
        Member member = memberRepository.findById(id)
            .orElseThrow(IllegalAccessError::new);

        // member.getWages().clear();
        // member.addWage(Wage.of(Week.WEEK1, wageSaveDto.getTime1(), wageSaveDto.isCheck1(), member));
        // member.addWage(Wage.of(Week.WEEK2, wageSaveDto.getTime2(), wageSaveDto.isCheck2(), member));
        // member.addWage(Wage.of(Week.WEEK3, wageSaveDto.getTime3(), wageSaveDto.isCheck3(), member));
        // member.addWage(Wage.of(Week.WEEK4, wageSaveDto.getTime4(), wageSaveDto.isCheck4(), member));
        // member.addWage(Wage.of(Week.WEEK5, wageSaveDto.getTime5(), wageSaveDto.isCheck5(), member));

        // member.calcTotalWage();
    }

    public MemberViewDto getById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        MemberViewDto dto = new MemberViewDto();
        dto.setId(member.getId());
        dto.setName(member.getName());
        return dto;
    }

    public List<MemberNamesResponse> getAllName() {
        return memberRepository.findAll().stream()
            .map(this::memberToMemberNameResponse)
            .collect(Collectors.toList());
    }

    private MemberNamesResponse memberToMemberNameResponse(Member member) {
        MemberNamesResponse dto = new MemberNamesResponse();
        dto.setId(member.getId());
        dto.setName(member.getName());
        return dto;
    }

    public List<MemberViewDto> getAll() {
        List<MemberViewDto> dtos = new ArrayList<>();
        for (Member member : memberRepository.findAll()) {
            MemberViewDto dto = new MemberViewDto();
            dto.setId(member.getId());
            dto.setName(member.getName());
            // if (!member.getWages().isEmpty()) {
            //     dto.setWage1(MoneyString.of(member.getWages().get(0).getWage()));
            //     dto.setWage2(MoneyString.of(member.getWages().get(1).getWage()));
            //     dto.setWage3(MoneyString.of(member.getWages().get(2).getWage()));
            //     dto.setWage4(MoneyString.of(member.getWages().get(3).getWage()));
            //     dto.setWage5(MoneyString.of(member.getWages().get(4).getWage()));
            //     dto.setTotal(MoneyString.of(member.getTotalWage()));
            //     dtos.add(dto);
            // }
        }

        return dtos;
    }

    public String getTotal() {
        return MoneyString.of(calcTotal(memberRepository.findAll()));
    }

    private int calcTotal(List<Member> members) {
        return members.stream()
            .mapToInt(Member::getTotalWage)
            .sum();
    }
}
