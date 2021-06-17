package com.DBSystelGmbH.CaseStudyMarkDurst.RailwayPost.service;

import com.DBSystelGmbH.CaseStudyMarkDurst.RailwayPost.model.RailwayPost;
import com.DBSystelGmbH.CaseStudyMarkDurst.RailwayPost.model.RailwayPostRepository;
import com.DBSystelGmbH.CaseStudyMarkDurst.common.CSV.CSVHelper;
import com.DBSystelGmbH.CaseStudyMarkDurst.common.base.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RailwayPostService extends BaseService<RailwayPost> {

    private final RailwayPostRepository railwayPostRepository;
    private List<RailwayPost> transientRailwayPosts = new ArrayList<>();

    public RailwayPostService(RailwayPostRepository railwayPostRepository) {
        super(railwayPostRepository);
        this.railwayPostRepository = railwayPostRepository;
    }

    public RailwayPost findByAbbreviation(String abbreviation) {

        if (findAll().isEmpty()) {
            if (this.transientRailwayPosts.isEmpty()) {
                throw new EntityNotFoundException("There are now railway posts persisted");
            }
            Optional<RailwayPost> matchingObject = this.transientRailwayPosts.stream().filter(
                    railwayPost -> railwayPost.getAbbreviation().equals(abbreviation.toUpperCase())).findFirst();
            if (matchingObject.isPresent()) {
                return matchingObject.get();
            } else {
                throw new EntityNotFoundException("Railway post with abbreviation " + abbreviation + " does not exist");
            }
        }
        return railwayPostRepository.findByAbbreviation(abbreviation);
    }

    public void deleteAll() {
        this.railwayPostRepository.deleteAll();
        log.info("All Railway Posts deleted");
    }

    public void createRailwayPostsInMemory(MultipartFile file) {
        checkIfPersisted();
        try {
            this.transientRailwayPosts = convertCSVToBean(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void createRailwayPostsInDatabase(MultipartFile file) {
        checkIfPersisted();
        try {
            this.transientRailwayPosts = convertCSVToBean(file);

            this.transientRailwayPosts.forEach(railwayPostRepository::save);
            log.info("Railway posts successfully persisted");
        } catch (IOException e) {

        }
    }

    private List<RailwayPost> convertCSVToBean(MultipartFile file) throws IOException {
        List<RailwayPost> entities = new ArrayList<>();

        try {
            entities = CSVHelper.csvToRailwayPosts(file.getInputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
//        entities = CSVUtils.read(RailwayPost.class, file);


//        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
//            CsvToBean<RailwayPost> csvToBeanBuilder = new CsvToBeanBuilder(reader)
//                    .withType(RailwayPost.class)
//                    .build();
//
//            entities = csvToBeanBuilder.parse();
//            log.info("CSV-File successfully converted to Entity-Bean");
//
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        }
        return entities;
    }


    private void checkIfPersisted() {
        if (!railwayPostRepository.findAll().isEmpty()) {
            throw new RuntimeException("There are already Entities in the database, consider deleting the old" +
                    " ones first");
        }
    }
}
