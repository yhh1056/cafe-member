package cafeorder.service;

import cafeorder.domain.Member;
import cafeorder.domain.Time;
import cafeorder.domain.Wage;
import cafeorder.repository.MemberRepository;
import cafeorder.web.MemberForm;
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

    public Member findOne(Long id) {
        return memberRepository.findById(id);
    }

    @Transactional
    public void add(Member member) {
        isExisted(member.getName());
        memberRepository.save(member);
    }

    private void isExisted(String name) {
        List<Member> members = memberRepository.findByName(name);
        for (Member member : members) {
            if (member.equals(name)) {
                throw new IllegalArgumentException("같은 이름의 직원이 존재합니다");
            }
        }
    }

    public List<Member> getAll() {
        return memberRepository.findAll();
    }

    @Transactional
    public void addTime(Long id, int[] times) {
        Member member = memberRepository.findById(id);
        Time time = member.getTime();
        time.changeInfo(times);

        memberRepository.save(member);
    }

    @Transactional
    public void addWage(Long id, boolean[] checks) {
        Member member = findOne(id);
        Wage wage = member.getWage();
        wage.changeInfo(checks);
    }

    @Transactional
    public void updateMember(Long id, MemberForm form) {
        Member member = memberRepository.findById(id);
        member.updateInfo(form.getName());

        memberRepository.save(member);
    }

    @Transactional
    public void delete(Long id) {
        Member member = memberRepository.findById(id);
        memberRepository.delete(member);
    }

    public int getTotal() {
        List<Member> members = memberRepository.findAll();
        return calcTotal(members);
    }

    private int calcTotal(List<Member> members) {
        int total = 0;
        for (Member member : members) {
            total += member.getTotalWage();
        }
        return total;
    }
}
