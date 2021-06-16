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

    private List<RailwayPost> convertCSVToBean(MultipartFile file) {

        // TODO: Inconsistenc Handly:
        //  if persisted == true || transienRailwayPosts != null || !findAll.isEmpty()-> there are already railway posts in database,
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


}
