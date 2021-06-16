package CSV.RailwayPost.service;

import CSV.RailwayPost.model.RailwayPost;
import CSV.RailwayPost.model.RailwayPostRepository;
import base.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RailwayPostService extends BaseService<RailwayPost> {

    private final RailwayPostRepository railwayPostRepository;

    public RailwayPostService(RailwayPostRepository railwayPostRepository) {
        super(railwayPostRepository);
        this.railwayPostRepository = railwayPostRepository;
    }

    public RailwayPost findByAbbreviation(String abbreviation) {
        return railwayPostRepository.findByAbbreviation(abbreviation);
    }

    public void deleteAll() {
        this.railwayPostRepository.deleteAll();
        log.info("All Railway Posts deleted");
    }
}
