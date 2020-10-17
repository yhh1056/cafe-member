package cafeorder.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    private int oneWeekTime;
    private int twoWeekTime;
    private int ThreeWeekTime;
    private int FourWeekTime;
    private int FiveWeekTime;

    public void changeInfo(int[] timeListForm) {
        oneWeekTime = timeListForm[0];
        twoWeekTime = timeListForm[1];
        ThreeWeekTime = timeListForm[2];
        FourWeekTime = timeListForm[3];
        FiveWeekTime = timeListForm[4];
    }
}
