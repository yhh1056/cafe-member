package cafeorder.service;

import cafeorder.domain.Member;
import cafeorder.domain.Wage;
import cafeorder.dto.MemberSaveDto;
import cafeorder.repository.MemberRepository;
import cafeorder.dto.WageSaveDto;
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
    public void addMember(MemberSaveDto memberSaveDto) {
        String name = memberSaveDto.getName();
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
    public void updateMember(Long id, MemberSaveDto form) {
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
    public void addWage(Long id, @Valid WageSaveDto wageSaveDto) {
        Member member = memberRepository.findById(id)
                .orElseThrow(IllegalAccessError::new);

        member.getWages().clear();
        member.addWage(Wage.create(1, wageSaveDto.getTime1(), wageSaveDto.isCheck1()));
        member.addWage(Wage.create(2, wageSaveDto.getTime2(), wageSaveDto.isCheck2()));
        member.addWage(Wage.create(3, wageSaveDto.getTime3(), wageSaveDto.isCheck3()));
        member.addWage(Wage.create(4, wageSaveDto.getTime4(), wageSaveDto.isCheck4()));
        member.addWage(Wage.create(5, wageSaveDto.getTime5(), wageSaveDto.isCheck5()));

        member.calcTotalWage();
    }
}
