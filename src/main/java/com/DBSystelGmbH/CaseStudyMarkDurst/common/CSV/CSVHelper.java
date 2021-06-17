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
                        csvRecord.get("Abk").trim().replaceAll(" +", " "),
                        csvRecord.get("Name").trim().replaceAll(" +", " "),
                        csvRecord.get("Kurzname").trim().replaceAll(" +", " "),
                        csvRecord.get("Typ").trim().replaceAll(" +", " "),
                        csvRecord.get("Betr-Zust").trim().replaceAll(" +", " "),
                        csvRecord.get("Primary location code").trim().replaceAll(" +", " "),
                        csvRecord.get("UIC").trim().replaceAll(" +", " "),
                        csvRecord.get("RB").trim().replaceAll(" +", " "),
                        csvRecord.get("gültig von").trim().replaceAll(" +", " "),
                        csvRecord.get("gültig bis").trim().replaceAll(" +", " "),
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