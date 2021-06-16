package com.DBSystelGmbH.CaseStudyMarkDurst.RailwayPost.model;

import com.DBSystelGmbH.CaseStudyMarkDurst.common.base.model.BaseRepository;

public interface RailwayPostRepository extends BaseRepository<RailwayPost> {
    void deleteAll();

    RailwayPost findByAbbreviation(String abbreviation);
}
