package br.com.saude.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="saude", schema="goals")
public class Goals {
    private int id;
    private int hydration = 0;
    private BigDecimal weightLoss = new BigDecimal(0);
}
