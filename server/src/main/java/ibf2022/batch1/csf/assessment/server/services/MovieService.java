package ibf2022.batch1.csf.assessment.server.services;

import java.io.StringReader;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ibf2022.batch1.csf.assessment.server.models.Comment;
import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.repositories.MovieRepository;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;;

@Service
public class MovieService {

	@Autowired
    private MovieRepository movieRepo;
	
	//URL = https://api.nytimes.com/svc/movies/v2/reviews/search.json?query=minions&api-key={API_Key}
	public static final String REVIEWS_API = "https://api.nytimes.com/svc/movies/v2/reviews/search.json";
    
	@Value("${reviewsapi.key}")
	private String reviewsKey;

	//Method to call on external API and return a list of Review objects
	public List<Review> searchReviews(String query) {

		String apiUrl = UriComponentsBuilder.fromUriString(REVIEWS_API)
                .queryParam("query", query)
                .queryParam("api-key", reviewsKey)
                .toUriString();

        RestTemplate template = new RestTemplate();
        
        RequestEntity<Void> request = RequestEntity.get(apiUrl)
                .accept(MediaType.APPLICATION_JSON)
                .build();

		ResponseEntity<String> response = null;
			try {
				response = template.exchange(request, String.class);
			}
				catch (RestClientException ex) {
					ex.printStackTrace();
					return Collections.emptyList();
				}

		String payload = response.getBody();

		JsonReader reader = Json.createReader(new StringReader(payload));
        JsonObject reviewResponse = reader.readObject();
        JsonArray jsonArr = reviewResponse.getJsonArray("results");
        return jsonArr.stream()
            .map(v -> (JsonObject)v)
            .map(v -> Review.createReview(v))
            .toList();

	}

	//Method to save bson into Mongodb
    //Generate and return commentId
    public String save(Comment comment) {
		String commentId = UUID.randomUUID().toString().substring(0, 8);
		comment.setCommentId(commentId);
		movieRepo.save(comment);
		return commentId;
	}

}
