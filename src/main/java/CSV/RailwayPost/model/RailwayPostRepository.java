package CSV.RailwayPost.model;

import base.model.BaseRepository;

public interface RailwayPostRepository extends BaseRepository<RailwayPost> {
    void deleteAll();
}
