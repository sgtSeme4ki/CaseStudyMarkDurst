package com.DBSystelGmbH.CaseStudyMarkDurst.common.base.service;

import com.DBSystelGmbH.CaseStudyMarkDurst.common.base.model.BaseEntity;
import com.DBSystelGmbH.CaseStudyMarkDurst.common.base.model.BaseRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public abstract class BaseCSVConverterService<T extends BaseEntity> extends BaseService<T> {

    private final BaseRepository<T> repository;

    @Getter
    @Setter
    protected List<T> transientEntities;

    public BaseCSVConverterService(BaseRepository<T> repository) {
        super(repository);
        this.repository = repository;
        this.transientEntities = repository.findAll();

    }

    public void createEntitiesInMemory(MultipartFile file) {
        checkIfPersisted();
        this.transientEntities = convertCSVToBean(file);
    }

    public void createEntitiesInDatabase(MultipartFile file) {
        checkIfPersisted();
        this.transientEntities = convertCSVToBean(file);

        this.transientEntities.forEach(repository::save);
        log.info("Entities successfully persisted");
    }

    private List<T> convertCSVToBean(MultipartFile file) {
        List<T> entities = new ArrayList<>();

        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<T> csvToBeanBuilder = new CsvToBeanBuilder(reader)
                    .withType(BaseEntity.class)
                    .withSeparator(';')
                    .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)
                    .build();

            entities = csvToBeanBuilder.parse();
            log.info("CSV-File successfully converted to Entity-Bean");

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return entities;
    }


    private void checkIfPersisted() {
        if (!repository.findAll().isEmpty()) {
            throw new RuntimeException("There are already Entities in the database, consider deleting the old" +
                    " ones first");
        }
    }
}
