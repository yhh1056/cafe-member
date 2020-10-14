package cafeorder.service;

import cafeorder.domain.Member;
import cafeorder.domain.Time;
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
        isExistedTime(times, member);
        member.calcWage();

        memberRepository.save(member);
    }

    private void isExistedTime(int[] times, Member member) {
        if (member.getTime() != null) {
            Time time = member.getTime();
            time.changeInfo(times, 8590);
        } else {
            member.addTimeInfo(new Time(times, 8590));
        }
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

    @Transactional
    public void updateMember(MemberForm form) {
        Member member = memberRepository.findById(form.getId());
        member.updateInfo(form.getName());

        memberRepository.save(member);
    }

    @Transactional
    public void delete(Long id) {
        Member member = memberRepository.findById(id);
        memberRepository.delete(member);
    }
}
