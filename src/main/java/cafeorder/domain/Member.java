package cafeorder.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * author {yhh1056}
 * Create by {2020/09/29}
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wage> wages = new ArrayList<>();

    private int totalWage;

    public Member(String name) {
        this.name = name;
    }

    public static Member of(String name) {
        return new Member(name);
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void addWage(Wage wage) {
        this.wages.add(wage);
        wage.addMember(this);
    }

    public void calcTotalWage() {
        for (Wage wage : wages) {
            this.totalWage += wage.getWage();
        }
    }

}
