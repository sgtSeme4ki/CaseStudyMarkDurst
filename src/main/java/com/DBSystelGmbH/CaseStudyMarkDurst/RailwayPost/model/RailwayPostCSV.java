package com.DBSystelGmbH.CaseStudyMarkDurst.RailwayPost.model;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class RailwayPostCSV {


    @CsvBindByName(column = "Abk")
    private String abbreviation;
    @CsvBindByName(column = "Name")
    private String longName;

    @CsvBindByName(column = "Kurzname")
    private String shortName;

    @CsvBindByName(column = "Typ")
    private String type;

    @CsvBindByName(column = "Betr-Zust")
    private String operatingCondition;

    @CsvBindByName(column = "Primary Location code")
    private String primaryLocationCode;

    @CsvBindByName(column = "UIC")
    private String RICS;

    @CsvBindByName(column = "RB")
    private String RB;

    @CsvBindByName(column = "gültig von")
    private String validFrom;

    @CsvBindByName(column = "gültig bis")
    private String validUntil;

    @Id
    @CsvBindByName(column = "Netz-Key")
    private Long netKey;

    @CsvBindByName(column = "Fpl-rel")
    private Boolean scheduleRelevance;

    @CsvBindByName(column = "Fpl-Gr")
    private Boolean scheduleEditingLimit;
}
