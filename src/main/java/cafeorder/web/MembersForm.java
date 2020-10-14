package cafeorder.web;

import cafeorder.domain.Member;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * author {yhh1056}
 * Create by {2020/10/14}
 */

@Getter
@Setter
public class MembersForm {
    private List<Member> members;

    public MembersForm(List<Member> members) {
        this.members = members;
    }
}
