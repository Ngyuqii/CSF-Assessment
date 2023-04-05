package ibf2022.batch1.csf.assessment.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.batch1.csf.assessment.server.models.Comment;
import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.services.MovieService;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {

	@Autowired
	private MovieService movieSvc;

	@GetMapping(path="/reviews/{movie}")
    @ResponseBody
    public ResponseEntity<String> callAPI(@PathVariable String movie) {

        //Check variable passed from client side
        System.out.printf(">>>> Movie: %s", movie);
        
        //Call on external API and return a list of Review objects
        List<Review> reviewsRetrieved = movieSvc.searchReviews(movie);

		//Convert review objects to json objects and add to arrbuilder
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        reviewsRetrieved.stream()
                .map(v -> v.toJsonObj())
                .forEach(v -> {
                    arrBuilder.add(v);
                });

		//Return jsonArray > stringify
        return ResponseEntity.ok(arrBuilder.build().toString());
    }

	@PostMapping(path="/comment", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveComment(@RequestBody String payload) {

        System.out.printf(">>>Payload: %s /n", payload);

        JsonObject jsonObj = Comment.fromString(payload);
        Comment comment = Comment.createCommment(jsonObj);

        try{String commentId = movieSvc.save(comment);

        JsonObject resp = Json.createObjectBuilder()
            .add("commentId", commentId)
            .build();
        
        return ResponseEntity.ok(resp.toString());
        }
        catch (Exception e){
        
            return ResponseEntity.ok(e.toString());
        }

    }

}
