package cafeorder.service;

import cafeorder.domain.Member;
import cafeorder.domain.Wage;
import cafeorder.repository.MemberRepository;
import cafeorder.dto.MemberDto;
import cafeorder.dto.WageDto;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * author {yhh1056}
 * Create by {2020/09/29}
 */

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberSaveServiceImpl implements MemberSaveService {

    private final MemberRepository memberRepository;

    @Override
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

    @Override
    @Transactional
    public void updateMember(Long id, MemberDto form) {
        Member member = memberRepository.findById(id)
                .orElseThrow(IllegalAccessError::new);

        member.changeName(form.getName());
    }

    @Override
    @Transactional
    public void deleteMember(Long id) {
        memberRepository.delete(memberRepository.findById(id)
                .orElseThrow(IllegalAccessError::new));
    }

    @Override
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
}
