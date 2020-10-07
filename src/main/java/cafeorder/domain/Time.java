package cafeorder.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * author {yhh1056}
 * Create by {2020/10/07}
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Time {

    @Id
    @GeneratedValue
    @Column(name = "time_id")
    private Long id;

    @OneToOne(mappedBy = "time", cascade = CascadeType.ALL)
    private Member member;

    @Max(100)
    @Min(0)
    private int oneWeekTime;

    @Max(100)
    @Min(0)
    private int twoWeekTime;

    @Max(100)
    @Min(0)
    private int ThreeWeekTime;

    @Max(100)
    @Min(0)
    private int FourWeekTime;

    @Max(100)
    @Min(0)
    private int FiveWeekTime;

    private int hourlyWage;

    public Time(int[] times, int hourlyWage) {
        oneWeekTime = times[0];
        twoWeekTime = times[1];
        ThreeWeekTime = times[2];
        FourWeekTime = times[3];
        FiveWeekTime = times[4];
        this.hourlyWage = hourlyWage;
    }

    public void changeInfo(int[] timeListForm, int hourlyWage) {
        oneWeekTime = timeListForm[0];
        twoWeekTime = timeListForm[1];
        ThreeWeekTime = timeListForm[2];
        FourWeekTime = timeListForm[3];
        FiveWeekTime = timeListForm[4];
        this.hourlyWage = hourlyWage;
    }

}
