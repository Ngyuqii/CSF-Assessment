package ibf2022.batch1.csf.assessment.server.repositories;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.batch1.csf.assessment.server.models.Comment;

@Repository
public class MovieRepository {

	@Autowired
    private MongoTemplate template;

	
	//Method to convert json object to bson and insert into Mongodb
    //Datatbase moviereviews, Collection comments
    //db.comments.insert({Document})
    public void save (Comment comment) {
		Document doc = comment.toDocument();
        System.out.printf(">>>DOC: %s", doc);
        template.insert(doc, "comment");
        
    }
	
	public int countComments(Object param) {
		return 0;
	}

}
