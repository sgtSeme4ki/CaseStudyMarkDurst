package CSV.RailwayPost.service;

import CSV.RailwayPost.model.RailwayPost;
import CSV.RailwayPost.model.RailwayPostRepository;
import base.service.BaseCSVConverterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
public class RailwayPostsCSVConverterService extends BaseCSVConverterService<RailwayPost> {

    private final RailwayPostRepository railwayPostRepository;

    private List<RailwayPost> transientRailwayPosts;

    public RailwayPostsCSVConverterService(RailwayPostRepository railwayPostRepository) {
        super(railwayPostRepository);
        this.railwayPostRepository = railwayPostRepository;
        this.transientRailwayPosts = super.findAll();
    }

    public void createRailwayPostsInMemory(MultipartFile file) {
        createEntitiesInMemory(file);
    }

    public void createRailwayPostsInDatabase(MultipartFile file) {
        createEntitiesInDatabase(file);
    }
}
