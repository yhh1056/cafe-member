package cafeorder.service;

import cafeorder.web.MemberDto;
import cafeorder.web.MemberViewDto;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: yhh1056@naver.com
 * Date: 2021/05/22 Time: 12:25 오전
 */
public interface MemberViewService {

    MemberDto getBy(Long id);

    List<MemberDto> getAllName();

    List<MemberViewDto> getAll();

    String getTotal();

}
