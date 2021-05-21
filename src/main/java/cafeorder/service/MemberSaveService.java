package cafeorder.service;

import cafeorder.web.MemberDto;
import cafeorder.web.WageDto;

/**
 * Created by IntelliJ IDEA
 * User: yhh1056@naver.com
 * Date: 2021/05/22 Time: 12:19 오전
 */
public interface MemberSaveService {

    void addMember(MemberDto memberDto);

    void updateMember(Long id, MemberDto form);

    void deleteMember(Long id);

    void addWage(Long id, WageDto wageDto);

}
