package cafeorder.service;

import cafeorder.domain.Member;
import cafeorder.domain.Time;
import cafeorder.repository.MemberRepository;
import cafeorder.web.MemberCalcForm;
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
    public void add(Member member) {
        isExisted(member);
        memberRepository.save(member);
    }

    private void isExisted(Member member) {
        List<Member> findMember = memberRepository.findByName(member.getName());
        if (member.equals(findMember)) {
            throw new IllegalArgumentException("같은 이름의 직원이 존재합니다");
        }
    }

    public List<Member> getAll() {
        return memberRepository.findAll();
    }

    @Transactional
    public void addTime(String name, int hourlyWage, MemberCalcForm form) {
        Member member = getFindNameMember(name);
        if (member.getTime() == null) {
            Time time = new Time(form.createTimeListForm(), hourlyWage);
            member.addTimeInfo(time);
        } else {
            Time time = member.getTime();
            time.changeInfo(form.createTimeListForm(), hourlyWage);
        }
        member.calcWage();
        memberRepository.save(member);
    }

    private Member getFindNameMember(String name) {
        List<Member> members = memberRepository.findByName(name);
        for (Member findMember : members) {
            if (findMember.equals(name)) {
                return findMember;
            }
        }
        throw new IllegalStateException("존재하지 않는 직원입니다.");
    }
}
