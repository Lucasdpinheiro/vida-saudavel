package br.com.saude.models;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="full_name", length=250, nullable=false)
    private String fullName;

    @Column(name="birth_day", nullable=false)
    private Date birthDay;

    @Column(name="weight_", nullable=false)
    private int weight;

    @Column(nullable=false)
    private int height;

    @OneToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="goal", referencedColumnName="id")
    private Goals goal;
}
