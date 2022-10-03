package br.com.saude.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

import br.com.saude.models.enums.ActivityLevel;
import br.com.saude.models.enums.Sex;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="users")
@NamedNativeQueries({
    @NamedNativeQuery(
        name="listUserProfiles",
        query="SELECT * FROM users",
        resultClass=User.class
    ),
})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="full_name", length=250, nullable=false)
    private String fullName;

    @Column(name="birth_day", nullable=false)
    private LocalDate birthDay;

    @Column(name="weight_", nullable=false)
    private int weight;

    @Column(nullable=false)
    private int height;

    @Column(length=10)
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(name="activity_level", length=20)
    @Enumerated(EnumType.STRING)
    private ActivityLevel activityLevel;

    @OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER, optional=false)
    @JoinColumn(name="goal", referencedColumnName="id")
    private Goals goal;
}
