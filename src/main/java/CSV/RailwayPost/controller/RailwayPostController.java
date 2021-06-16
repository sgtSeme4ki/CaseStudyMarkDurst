package CSV.RailwayPost.controller;

import CSV.RailwayPost.model.RailwayPost;
import CSV.RailwayPost.service.RailwayPostsCSVConverterService;
import base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("betriebsstellen/")
public class RailwayPostController extends BaseController<RailwayPost> {

    private final RailwayPostsCSVConverterService railwayPostsCSVConverterService;

    public RailwayPostController(RailwayPostsCSVConverterService railwayPostsCSVConverterService) {
        super(railwayPostsCSVConverterService);
        this.railwayPostsCSVConverterService = railwayPostsCSVConverterService;
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

    @DeleteMapping
    public void deleteAll(){
        this.railwayPostsCSVConverterService.deleteAll();
    }
}
