package com.DBSystelGmbH.CaseStudyMarkDurst.RailwayPost.controller;

import com.DBSystelGmbH.CaseStudyMarkDurst.RailwayPost.model.RailwayPost;
import com.DBSystelGmbH.CaseStudyMarkDurst.RailwayPost.service.RailwayPostService;
import com.DBSystelGmbH.CaseStudyMarkDurst.RailwayPost.service.RailwayPostsCSVConverterService;
import com.DBSystelGmbH.CaseStudyMarkDurst.common.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("betriebsstellen")
public class RailwayPostController extends BaseController<RailwayPost> {

    private final RailwayPostService railwayPostService;

    public RailwayPostController(RailwayPostsCSVConverterService railwayPostsCSVConverterService,
                                 RailwayPostService railwayPostService) {
        super(railwayPostService);
        this.railwayPostService = railwayPostService;
    }

    @PostMapping("/csv-upload")
    public void uploadCSVFile(@RequestBody MultipartFile csvFile,
                              @RequestParam(name = "persist") boolean isToBePersisted) {
        if (isToBePersisted) {
            this.railwayPostService.createRailwayPostsInDatabase(csvFile);
        } else {
            this.railwayPostService.createRailwayPostsInMemory(csvFile);
        }
    }

    @GetMapping("/abk/{abbreviation}")
    public RailwayPost findByAbbreviation(@PathVariable String abbreviation) {
        return railwayPostService.findByAbbreviation(abbreviation);
    }

    @DeleteMapping
    public void deleteAll() {
        this.railwayPostService.deleteAll();
    }
}
