package cafeorder.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberViewDto {

    private Long id;
    private String name;
    private String wage1;
    private String wage2;
    private String wage3;
    private String wage4;
    private String wage5;

    private String total;

}
