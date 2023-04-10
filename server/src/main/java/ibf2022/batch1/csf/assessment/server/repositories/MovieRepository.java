package ibf2022.batch1.csf.assessment.server.repositories;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ibf2022.batch1.csf.assessment.server.models.Comment;

@Repository
public class MovieRepository {

	@Autowired
    private MongoTemplate template;

    //Method to find document count for movieTitle field matching the movieName parameter
    //db.comments.find({movieTitle: 'Minions: The Rise of Gru'}).count()
	public int countComments(String movieName) {

        Criteria c = Criteria.where("movieTitle").is(movieName);
        Query q = Query.query(c);
        long count = template.count(q, "comments");
		return (int)count;
	}

    //Method to convert json object to bson and insert into Mongodb
    //Datatbase moviereviews, Collection comments
    //db.comments.insertOne({movieTitle: 'Minions: The Rise of Gru', name: 'Ace', rating: 5, comment: 'Great comedy'})
    public void saveComment (Comment comment) {
		Document doc = comment.toDocument();
        System.out.printf(">>>Document: %s", doc);
        template.insert(doc, "comments");
    
    }

}