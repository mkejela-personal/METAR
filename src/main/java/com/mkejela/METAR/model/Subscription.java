package com.mkejela.METAR.model;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Subscription extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "icao_code")
    private String icaoCode;

    @Column(name = "is_active")
    private Boolean isActive;

}
