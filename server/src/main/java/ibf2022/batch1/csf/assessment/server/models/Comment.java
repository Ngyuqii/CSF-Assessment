package ibf2022.batch1.csf.assessment.server.models;

import java.io.Reader;
import java.io.StringReader;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Comment {

    private String commentId = "";

    private String name;
	private Integer rating;
	private String comments;

    //Getters and Setters
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getRating() {
        return rating;
    }
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public String getCommentId() {
        return commentId;
    }
    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }
    

    //Convert JsonObject to Object
	public static Comment createCommment(JsonObject jsonObj) {
		Comment comment = new Comment();
		comment.setName(jsonObj.getString("name"));
		comment.setRating(jsonObj.getInt("rating"));
        comment.setComments(jsonObj.getString("comment"));
        return comment;
    }

    //Convert JsonString to JsonObject
	public static JsonObject fromString(String jsonString) {
        Reader reader = new StringReader(jsonString);
        JsonReader jsonReader = Json.createReader(reader);
        return jsonReader.readObject();
    }

    //Build and return a document object with key-value pairs 
    public Document toDocument() {
        Document doc = new Document();

        doc.put("orderId", getCommentId());
        doc.put("name", getName());
        doc.put("rating", getRating());
        doc.put("comment", getComments());
        return doc;    
    }
    



    
    
}
