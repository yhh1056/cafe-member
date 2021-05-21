package cafeorder.service;

import cafeorder.domain.Member;
import cafeorder.domain.Wage;
import cafeorder.repository.MemberRepository;
import cafeorder.util.MoneyString;
import cafeorder.web.MemberDto;
import cafeorder.web.MemberViewDto;
import cafeorder.web.WageDto;
import java.util.ArrayList;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * author {yhh1056}
 * Create by {2020/09/29}
 */

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void addMember(MemberDto memberDto) {
        String name = memberDto.getName();
        isExistedName(name);
        memberRepository.save(Member.of(name));
    }

    private void isExistedName(String name) {
        if (memberRepository.existsByName(name)) {
            throw new IllegalArgumentException("같은 이름의 직원이 존재합니다");
        }
    }

    public MemberDto getBy(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(IllegalAccessError::new);

        MemberDto memberDto = new MemberDto();
        memberDto.setId(member.getId());
        memberDto.setName(member.getName());
        return memberDto;
    }

    public List<MemberDto> getAllName() {
        List<MemberDto> dtos = new ArrayList<>();
        for (Member member : memberRepository.findAll()) {
            MemberDto dto = new MemberDto();
            dto.setId(member.getId());
            dto.setName(member.getName());

            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional
    public void updateMember(Long id, MemberDto form) {
        Member member = memberRepository.findById(id)
                .orElseThrow(IllegalAccessError::new);

        member.changeName(form.getName());
    }

    @Transactional
    public void deleteMember(Long id) {
        memberRepository.delete(memberRepository.findById(id)
                .orElseThrow(IllegalAccessError::new));
    }

    @Transactional
    public void addWage(Long id, @Valid WageDto wageDto) {
        Member member = memberRepository.findById(id)
                .orElseThrow(IllegalAccessError::new);

        member.addWage(Wage.create(1, wageDto.getTime1(), wageDto.isCheck1()));
        member.addWage(Wage.create(2, wageDto.getTime2(), wageDto.isCheck2()));
        member.addWage(Wage.create(3, wageDto.getTime3(), wageDto.isCheck3()));
        member.addWage(Wage.create(4, wageDto.getTime4(), wageDto.isCheck4()));
        member.addWage(Wage.create(5, wageDto.getTime5(), wageDto.isCheck5()));

        member.calcTotalWage();
    }

    public List<MemberViewDto> getAll() {
        List<MemberViewDto> dtos = new ArrayList<>();
        for (Member member : memberRepository.findAll()) {
            MemberViewDto dto = new MemberViewDto();
            dto.setId(member.getId());
            dto.setName(member.getName());
            dto.setWage1(MoneyString.of(member.getWages().get(0).getWage()));
            dto.setWage2(MoneyString.of(member.getWages().get(1).getWage()));
            dto.setWage3(MoneyString.of(member.getWages().get(2).getWage()));
            dto.setWage4(MoneyString.of(member.getWages().get(3).getWage()));
            dto.setWage5(MoneyString.of(member.getWages().get(4).getWage()));
            dto.setTotal(member.getTotalWage() + "원");
            dtos.add(dto);
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
