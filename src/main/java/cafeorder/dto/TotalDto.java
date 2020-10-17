package cafeorder.dto;

import lombok.Getter;

import java.text.DecimalFormat;

/**
 * author {yhh1056}
 * Create by {2020/10/14}
 */
@Getter
public class TotalDto {
    private int total;

    public TotalDto(int total) {
        this.total = total;
    }

    public String getTotal() {
        DecimalFormat formatter = new DecimalFormat("###,###");
        return formatter.format(total) + "원";
    }
}
