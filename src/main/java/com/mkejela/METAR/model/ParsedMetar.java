package com.mkejela.METAR.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class ParsedMetar extends Auditable {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer metarDataId;

    @Column(name = "airport_code")
    private String airPortCode;

    @Column(name = "time_of_observation")
    private String timeOfObservation;

    @Column(name = "wind_information")
    private String windInformation;

    @Column(name = "wind_direction_change")
    private String windDirectionVariation;


    @Column(name = "visibility")
    private Double visibility;

    @Column(name = "precipitation_intensity")
    private String precipitationIntensity;

    @Column(name = "runware_visual_range")
    private String runwayVisualRange;

    @Column(name = "cloud_ceiling")
    private String cloudCeiling;
}
