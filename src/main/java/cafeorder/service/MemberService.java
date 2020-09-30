package cafeorder.service;

import cafeorder.domain.Member;
import cafeorder.repository.MemberRepository;
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
        List<Member> members = memberRepository.findByName(member.getName());
        if (!members.isEmpty()) {
            throw new IllegalArgumentException("같은 이름의 직원이 존재합니다");
        }
    }

    public List<Member> getAll() {
        return memberRepository.findAll();
    }

    @Transactional
    public void addTime(String name, int weekTime, int hourlyWage) {
        List<Member> members = memberRepository.findAll();
        for (Member member : members) {
            if (member.equals(name)) {
                member.addTime(weekTime, hourlyWage);
                memberRepository.save(member);
            }
        }
    }
}
