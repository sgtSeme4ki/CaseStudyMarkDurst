package com.DBSystelGmbH.CaseStudyMarkDurst.RailwayPost.model;

import com.DBSystelGmbH.CaseStudyMarkDurst.common.base.model.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class RailwayPost extends BaseEntity {

    @NotBlank
    private String abbreviation;

    @NotBlank
    private String longName;

    @NotBlank
    private String shortName;

    @NotBlank
    private String type;

    @NotBlank
    private String operatingCondition;

    @NotBlank
    private String primaryLocationCode;

    @NotBlank
    private String RICS;

    private String RB;

    @NotBlank
    private String validFrom;

    private String validUntil;

    private Long netKey;

    private Boolean scheduleRelevance;

    private Boolean scheduleEditingLimit;
}
