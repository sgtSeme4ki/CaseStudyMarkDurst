package com.DBSystelGmbH.CaseStudyMarkDurst.common.CSV;

import com.DBSystelGmbH.CaseStudyMarkDurst.RailwayPost.model.RailwayPost;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class CSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERs = {"Abk", "Name", "Kurzname", "Typ",
            "Betr-Zust", "Primary location code", "UIC", "RB", "gültig von", "gültig bis",
            "Netz-Key", "Fpl-rel", "Fpl-Gr"
    };

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<RailwayPost> csvToRailwayPosts(InputStream is) {

        final Reader reader1 = new InputStreamReader(new BOMInputStream(is), StandardCharsets.UTF_8);

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(new BOMInputStream(is), StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(reader1,
                     CSVFormat.newFormat(';').withNullString("").withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<RailwayPost> railwayPosts = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                RailwayPost railwayPost = new RailwayPost(
                        csvRecord.get("Abk"),
                        csvRecord.get("Name"),
                        csvRecord.get("Kurzname"),
                        csvRecord.get("Typ"),
                        csvRecord.get("Betr-Zust"),
                        csvRecord.get("Primary location code"),
                        csvRecord.get("UIC"),
                        csvRecord.get("RB"),
                        csvRecord.get("gültig von"),
                        csvRecord.get("gültig bis"),
                        Long.valueOf(Optional.ofNullable(csvRecord.get("Netz-Key")).orElseGet(() -> String.valueOf(0L))),
                        Boolean.parseBoolean(csvRecord.get("Fpl-rel")),
                        Boolean.parseBoolean(csvRecord.get("Fpl-Gr"))
                );

                railwayPosts.add(railwayPost);
            }
            return railwayPosts;

        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}