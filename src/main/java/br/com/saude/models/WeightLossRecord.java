package br.com.saude.models;

import java.math.BigDecimal;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="weightloss")
public class WeightLossRecord extends Record {

    @Column(name="current_weight", nullable=false)
    private BigDecimal currentWeight;
}
