package com.DBSystelGmbH.CaseStudyMarkDurst.RailwayPost.controller;

import com.DBSystelGmbH.CaseStudyMarkDurst.RailwayPost.model.RailwayPost;
import com.DBSystelGmbH.CaseStudyMarkDurst.RailwayPost.service.RailwayPostService;
import com.DBSystelGmbH.CaseStudyMarkDurst.common.CSV.CSVHelper;
import com.DBSystelGmbH.CaseStudyMarkDurst.common.base.controller.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("betriebsstellen")
public class RailwayPostController extends BaseController<RailwayPost> {

    private final RailwayPostService railwayPostService;

    public RailwayPostController(RailwayPostService railwayPostService) {
        super(railwayPostService);
        this.railwayPostService = railwayPostService;
    }

    @PostMapping(value = "/csv-upload")
    public ResponseEntity<String> uploadCSVFile(@RequestParam MultipartFile file,
                                                @RequestParam(name = "persist") boolean isToBePersisted) {
        String message = "";

        if (CSVHelper.hasCSVFormat(file)) {
            try {
                if (isToBePersisted) {
                    this.railwayPostService.createRailwayPostsInDatabase(file);
                } else {
                    this.railwayPostService.createRailwayPostsInMemory(file);
                }

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";

                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);

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
