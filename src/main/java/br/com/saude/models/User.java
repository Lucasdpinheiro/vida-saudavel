package br.com.saude.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.sql.Date;

import javax.persistence.*;

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
    private BigDecimal weight;

    @Column(nullable=false)
    private int height;

    @OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER, optional=false)
    @JoinColumn(name="goal", referencedColumnName="id")
    private Goals goal;
}
