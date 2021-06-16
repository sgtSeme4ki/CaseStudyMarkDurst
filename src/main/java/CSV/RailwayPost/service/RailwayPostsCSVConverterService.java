package CSV.RailwayPost.service;

import CSV.RailwayPost.model.RailwayPost;
import base.model.BaseRepository;
import base.service.BaseService;

public class RailwayPostsCSVConverterService extends BaseService<RailwayPost> {

    public RailwayPostsCSVConverterService(BaseRepository<RailwayPost> repository) {
        super(repository);
    }
}
