package cafeorder.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private Set<Wage> wages = new HashSet<>();

    @Embedded
    private Money totalWage = Money.zero();

    public Member(String name) {
        this.name = name;
    }

    public static Member of(String name) {
        return new Member(name);
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void calculateTotalWage() {
        for (Wage wage : wages) {
            totalWage = totalWage.plus(wage.getTotalAmount());
        }
    }

    public Wage findWeek(Week week) {
        return wages.stream().filter(wage -> wage.isEqualsWeek(week)).findAny().orElseGet(Wage::empty);
    }

}
