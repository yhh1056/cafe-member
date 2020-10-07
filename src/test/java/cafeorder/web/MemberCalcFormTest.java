package cafeorder.web;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * author {yhh1056}
 * Create by {2020/10/07}
 */
class MemberCalcFormTest {
    @Test
    void createTimeListForm() {
        MemberCalcForm form = new MemberCalcForm();
        form.setTime2(30);
        form.setTime3(30);
        List<Integer> timeListForm = form.createTimeListForm();

        assertEquals(timeListForm.get(1), 30);
    }
}