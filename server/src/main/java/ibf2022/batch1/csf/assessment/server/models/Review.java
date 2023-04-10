package ibf2022.batch1.csf.assessment.server.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Review {

	private String title; // display_title
	private String rating; // mpaa_rating
	private String byline; // byline
	private String headline; // headline
	private String summary; // summary_short 
	private String reviewURL; // link.url
	private String image = null; // multimedia.src
	private int commentCount = 0;

	//Constructor
	public Review() { }

	//Getters and Setters
	public void setTitle(String title) { this.title = title; }
	public String getTitle() { return this.title; }

	public void setRating(String rating) { this.rating = rating; }
	public String getRating() { return this.rating; }

	public void setByline(String byline) { this.byline = byline; }
	public String getByline() { return this.byline; }

	public void setHeadline(String headline) { this.headline = headline; }
	public String getHeadline() { return this.headline; }

	public void setSummary(String summary) { this.summary = summary; }
	public String getSummary() { return this.summary; }

	public void setReviewURL(String reviewURL) { this.reviewURL = reviewURL; }
	public String getReviewURL() { return this.reviewURL; }

	public void setImage(String image) { this.image = image; }
	public String getImage() { return this.image; }
	public boolean hasImage() { return null != this.image; }

	public void setCommentCount(int commentCount) { this.commentCount = commentCount; };
	public int getCommentCount() { return this.commentCount; }

	//Method to convert JsonObj to Article Object
    public static Review createReview(JsonObject json) {
        Review review = new Review();
        review.setTitle(json.getString("display_title"));
        review.setRating(getValue(json, "mpaa_rating"));
		review.setByline(getValue(json, "byline"));
		review.setHeadline(getValue(json, "headline"));
		review.setSummary(getValue(json, "summary_short"));
		review.setReviewURL(getNestedValue(json, "link", "url"));
		review.setImage(getNestedValue(json, "multimedia", "src"));
        return review;
    }

    //Method to get value of key in json object if not null
    //Else return NA
    private static String getValue(JsonObject json, String k) {
        if (json.containsKey(k) && !json.isNull(k)) {
            return json.getString(k);
        }
        else {
            return "NA";
        }      
    }

	//Method to get nested value in json object and return NA if error
	private static String getNestedValue(JsonObject json, String outer, String inner) {
		try {
			return json.getJsonObject(outer).getString(inner);
		}
		catch (Exception e) {
			return "NA";
		}
	}

    //Method to build json object
    public JsonObject toJsonObj() {
        return Json.createObjectBuilder()
            .add("title", getTitle())
            .add("rating", getRating())
			.add("byline", getByline())
			.add("headline", getHeadline())
			.add("summary", getSummary())
            .add("url", getReviewURL())
			.add("image", getImage())
			.add("commentCount", getCommentCount())
            .build();
    }

	@Override
	public String toString() {
		return "Review{title:%s, rating:%s}".formatted(title, rating);
	}
}
