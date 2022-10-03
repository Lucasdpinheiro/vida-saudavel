package br.com.saude.models;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="hydration")
public class HydrationRecord extends Record {

    @Column(name="water_intake", nullable=false)
    private int waterIntake;
}
