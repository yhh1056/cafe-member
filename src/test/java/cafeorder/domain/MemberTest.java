package cafeorder.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * author {yhh1056}
 * Create by {2020/09/30}
 */
class MemberTest {

    @Test
    @DisplayName("이름으로 같은지 확인")
    void test() {
        String name = "디노";

        Member member = new Member("디노");

        assertTrue(member.equals(name));
    }

    @Test
    @DisplayName("시간 추가")
    void addTime() {
        String name = "디노";

        Member member = new Member("디노");

        member.addTime(30, 8000);

        assertEquals(30, member.getTime());
    }

}