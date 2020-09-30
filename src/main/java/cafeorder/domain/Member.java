package cafeorder.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * author {yhh1056}
 * Create by {2020/09/29}
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "member_name")
    private String name;

    private int weekTime;

    private int hourlyWage;

    private int totalWage;

    public Member(String name) {
        this.name = name;
    }

    public int calcWeekWage(int time) {
        return hourlyWage * time;
    }

    public boolean equals(String name) {
        return super.equals(name);
    }

    public void addTime(int weekTime, int hourlyWage){
        this.weekTime = weekTime;
        this.hourlyWage = hourlyWage;
    }
}
