package CSV.RailwayStation.model;

import base.model.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class RailwayStation extends BaseEntity {

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

    @NotNull
    private Long netKey;
    private Boolean scheduleRelevance;
    private Boolean scheduleEditingLimit;
}
