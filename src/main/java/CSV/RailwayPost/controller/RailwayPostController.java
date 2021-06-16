package CSV.RailwayPost.controller;

import CSV.RailwayPost.model.RailwayPost;
import CSV.RailwayPost.service.RailwayPostService;
import CSV.RailwayPost.service.RailwayPostsCSVConverterService;
import base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("betriebsstellen/")
public class RailwayPostController extends BaseController<RailwayPost> {

    private final RailwayPostsCSVConverterService railwayPostsCSVConverterService;
    private final RailwayPostService railwayPostService;

    public RailwayPostController(RailwayPostsCSVConverterService railwayPostsCSVConverterService,
                                 RailwayPostService railwayPostService) {
        super(railwayPostService);
        this.railwayPostsCSVConverterService = railwayPostsCSVConverterService;
        this.railwayPostService = railwayPostService;
    }

    @PostMapping("csv-upload")
    public void uploadCSVFile(@RequestParam("file") MultipartFile csvFile,
                              @RequestParam(name = "persist") boolean isToBePersisted) {
        if (isToBePersisted) {
            this.railwayPostsCSVConverterService.createRailwayPostsInDatabase(csvFile);
        } else {
            this.railwayPostsCSVConverterService.createRailwayPostsInMemory(csvFile);
        }
    }

    @GetMapping("abk/{abbreviation}")
    public RailwayPost findByAbbreviation(@PathVariable String abbreviation) {
        return railwayPostService.findByAbbreviation(abbreviation);
    }

    @DeleteMapping
    public void deleteAll() {
        this.railwayPostService.deleteAll();
    }
}
