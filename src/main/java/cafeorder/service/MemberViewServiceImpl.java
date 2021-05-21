package cafeorder.service;

import cafeorder.domain.Member;
import cafeorder.repository.MemberRepository;
import cafeorder.util.MoneyString;
import cafeorder.dto.MemberViewDto;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by IntelliJ IDEA
 * User: yhh1056@naver.com
 * Date: 2021/05/22 Time: 12:25 오전
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberViewServiceImpl implements MemberViewService {

    private final MemberRepository memberRepository;

    @Override
    public MemberViewDto getBy(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(IllegalAccessError::new);

        MemberViewDto dto = new MemberViewDto();
        dto.setId(member.getId());
        dto.setName(member.getName());
        return dto;
    }

    @Override
    public List<MemberViewDto> getAllName() {
        List<MemberViewDto> dtos = new ArrayList<>();
        for (Member member : memberRepository.findAll()) {
            MemberViewDto dto = new MemberViewDto();
            dto.setId(member.getId());
            dto.setName(member.getName());

            dtos.add(dto);
        }
        return dtos;
    }

    @Override
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
            dto.setTotal(MoneyString.of(member.getTotalWage()));
            dtos.add(dto);
        }

        return dtos;
    }

    @Override
    public String getTotal() {
        return MoneyString.of(calcTotal(memberRepository.findAll()));
    }

    private int calcTotal(List<Member> members) {
        return members.stream()
                .mapToInt(Member::getTotalWage)
                .sum();
    }

}
