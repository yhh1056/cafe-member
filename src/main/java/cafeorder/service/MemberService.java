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

    private final MemberRepository repository;

    @Transactional
    public void add(Member member) {
        isExisted(member);
        repository.save(member);
    }

    private void isExisted(Member member) {
        List<Member> members = repository.findByName(member.getName());
        if (!members.isEmpty()) {
            throw new IllegalArgumentException("같은 이름의 직원이 존재합니다");
        }

    }
}
