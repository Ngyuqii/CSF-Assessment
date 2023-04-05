package ibf2022.batch1.csf.assessment.server.repositories;

import javax.swing.text.Document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import jakarta.json.JsonObject;

@Repository
public class MovieRepository {

	@Autowired
    private MongoTemplate template;

	
	//Method to convert json object to bson and insert into Mongodb
    //Datatbase moviereviews, Collection comments
    //db.comments.insert({Document})
    public void saveOrder ( comment) {
		Document doc = Document.parse(comment.toString());
        template.insert(doc, "comment");
        
    }
	public int countComments(Object param) {
		return 0;
	}

}
