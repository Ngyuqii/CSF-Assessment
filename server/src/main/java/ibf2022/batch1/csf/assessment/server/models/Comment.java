package ibf2022.batch1.csf.assessment.server.models;

import java.io.Reader;
import java.io.StringReader;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Comment {

    private String commentId = "";

    private String movieTitle;
    private String name;
	private Integer rating;
	private String comments;

    //Constructor
    public Comment() {
    }

    //Getters
    public String getCommentId() {
        return commentId;
    }
    public String getMovieTitle() {
        return movieTitle;
    }
    public String getName() {
        return name;
    }
    public Integer getRating() {
        return rating;
    }
    public String getComments() {
        return comments;
    }

    //Setters
    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }
    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }

    //Convert JsonString to JsonObject
	public static JsonObject fromString(String jsonString) {
        Reader reader = new StringReader(jsonString);
        JsonReader jsonReader = Json.createReader(reader);
        return jsonReader.readObject();
    }

    //Convert JsonObject to Comment Object
	public static Comment createCommment(JsonObject jsonObj) {
		Comment comment = new Comment();
        comment.setMovieTitle(jsonObj.getString("movieTitle"));
		comment.setName(jsonObj.getString("name"));
		comment.setRating(jsonObj.getInt("rating"));
        comment.setComments(jsonObj.getString("comment"));
        return comment;
    }

    //Build and return a document object with key-value pairs 
    public Document toDocument() {
        Document doc = new Document();
        doc.put("commentId", getCommentId());
        doc.put("movieTitle", getMovieTitle());
        doc.put("name", getName());
        doc.put("rating", getRating());
        doc.put("comment", getComments());
        return doc;    
    }
    
}