package CSV.RailwayPost.controller;

import CSV.RailwayPost.model.RailwayPost;
import CSV.RailwayPost.service.RailwayPostsCSVConverterService;
import base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("betriebsstellen/")
public class RailwayPostController extends BaseController<RailwayPost> {

    private final RailwayPostsCSVConverterService railwayPostsCSVConverterService;

    public RailwayPostController(RailwayPostsCSVConverterService railwayPostsCSVConverterService) {
        super(railwayPostsCSVConverterService);
        this.railwayPostsCSVConverterService = railwayPostsCSVConverterService;
    }
}
