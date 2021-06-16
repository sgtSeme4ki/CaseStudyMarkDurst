package CSV.RailwayPost.service;

import CSV.RailwayPost.model.RailwayPost;
import base.model.BaseRepository;
import base.service.BaseService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RailwayPostsCSVConverterService extends BaseService<RailwayPost> {

    private List<RailwayPost> transientRailwayPosts;

    public RailwayPostsCSVConverterService(BaseRepository<RailwayPost> repository) {
        super(repository);
        this.transientRailwayPosts = super.findAll();
    }

    public void createRailwayPostsInMemory(MultipartFile file) {
        // TODO: Implement merging if already persisted?
        checkIfPersisted();
        this.transientRailwayPosts = convertCSVToBean(file);
    }

    public void createRailwayPostsInDatabase(MultipartFile file) {
        checkIfPersisted();
        this.transientRailwayPosts = convertCSVToBean(file);

        this.transientRailwayPosts.forEach(railwayPost -> super.create(railwayPost));
        log.info("Railway posts successfully persisted");
    }

    private List<RailwayPost> convertCSVToBean(MultipartFile file) {
        List<RailwayPost> railwayPosts = new ArrayList<>();

        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<RailwayPost> csvToBeanBuilder = new CsvToBeanBuilder(reader)
                    .withType(RailwayPost.class)
                    .build();

            railwayPosts = csvToBeanBuilder.parse();
            log.info("CSV-File successfully converted to RailwayPost-Bean");

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return railwayPosts;
    }

    private void checkIfPersisted() {
        if (!super.findAll().isEmpty()) {
            throw new RuntimeException("There are already railway posts in the database, consider deleting the old" +
                    " ones first");
        }
    }
}
