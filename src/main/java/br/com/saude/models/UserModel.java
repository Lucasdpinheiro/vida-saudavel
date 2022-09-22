package br.com.saude.models;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="saude", schema="users")
public class UserModel implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String id;

    @Column(name="full_name", length=250, nullable=false)
    private String fullName;

    @Column(name="birth_day",length=50, nullable=false)
    private String birthDay;

    @Column(nullable=false)
    private int weight;

    @Column(nullable=false)
    private int height;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="goal", referencedColumnName="id")
    private String goal;


    

}
