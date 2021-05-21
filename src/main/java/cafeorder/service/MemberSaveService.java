package cafeorder.service;

import cafeorder.dto.MemberSaveDto;
import cafeorder.dto.WageSaveDto;

/**
 * Created by IntelliJ IDEA
 * User: yhh1056@naver.com
 * Date: 2021/05/22 Time: 12:19 오전
 */
public interface MemberSaveService {

    void addMember(MemberSaveDto memberSaveDto);

    void updateMember(Long id, MemberSaveDto form);

    void deleteMember(Long id);

    void addWage(Long id, WageSaveDto wageSaveDto);

}
