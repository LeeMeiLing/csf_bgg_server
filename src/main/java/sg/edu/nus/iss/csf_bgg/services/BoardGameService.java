package sg.edu.nus.iss.csf_bgg.services;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import sg.edu.nus.iss.csf_bgg.models.MongoResult;
import sg.edu.nus.iss.csf_bgg.models.Result;
import sg.edu.nus.iss.csf_bgg.models.Review;
import sg.edu.nus.iss.csf_bgg.repositories.BoardGameRepo;

@Service
public class BoardGameService {
    
    @Autowired
    BoardGameRepo bgRepo;

    public Optional<List<Result>> findByName(String name){

        // check result is present and convert to json format

        return bgRepo.findByName(name);
        
    }

    public Optional<JsonObject> mongoFindByName(String name){

        Optional<List<Document>> documents = bgRepo.mongoFindByName(name);

        if(documents.isPresent()){

            MongoResult result = new MongoResult();
            Document doc = documents.get().get(0);
            result.setGid(doc.getInteger("gid"));
            result.setName(doc.getString("name"));
            result.setYear(doc.getInteger("year"));
            result.setRanking(doc.getInteger("ranking"));
            result.setUsersRated(doc.getInteger("users_rated"));
            result.setUrl(doc.getString("url"));
            result.setImage(doc.getString("image"));

            JsonObjectBuilder responseB = result.toJson();
            JsonArrayBuilder arrB = Json.createArrayBuilder();

            for(Document d:  documents.get()){
                Review review = new Review();
                Document temp = (Document) d.get("reviews");
                review.setcId(temp.getString("c_id"));
                review.setUser(temp.getString("user"));
                review.setRating(temp.getInteger("rating"));
                review.setcText(temp.getString("c_text"));
                arrB.add(review.toJson());
            }

            JsonArray arr = arrB.build();

            JsonObject response = responseB.add("reviews", arr).build();
            System.out.println(">>>in service, response: " + response.toString());

            return Optional.of(response);
        }

        return Optional.empty();



        

    }
}
