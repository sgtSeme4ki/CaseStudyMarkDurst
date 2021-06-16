package CSV.RailwayStation.model;

import base.model.BaseEntity;
import com.opencsv.bean.CsvBindByName;
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
    @CsvBindByName(column = "Abk")
    private String abbreviation;

    @NotBlank
    @CsvBindByName(column = "Name")
    private String longName;

    @NotBlank
    @CsvBindByName(column = "Kurzname")
    private String shortName;

    @NotBlank
    @CsvBindByName(column = "Typ")
    private String type;

    @NotBlank
    @CsvBindByName(column = "Betr-Zust")
    private String operatingCondition;

    @NotBlank
    @CsvBindByName(column = "Primary Location code")
    private String primaryLocationCode;

    @NotBlank
    @CsvBindByName(column = "UIC")
    private String RICS;

    @CsvBindByName(column = "RB")
    private String RB;

    @NotBlank
    @CsvBindByName(column = "gültig von")
    private String validFrom;

    @CsvBindByName(column = "gültig bis")
    private String validUntil;

    @NotNull
    @CsvBindByName(column = "Netz-Key")
    private Long netKey;

    @CsvBindByName(column = "Fpl-rel")
    private Boolean scheduleRelevance;

    @CsvBindByName(column = "Fpl-Gr")
    private Boolean scheduleEditingLimit;
}
