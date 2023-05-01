package sg.edu.nus.iss.csf_bgg.repositories;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.csf_bgg.models.Result;

@Repository
public class BoardGameRepo {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;

    private final String C_GAME = "games";
    private final String C_COMMENT = "comments";

    private final String FIND_BY_NAME_SQL = """
        select * from game left join comment on game.gid = comment.gid
        where game.name = ?
        order by game.gid, comment.rating desc 
        limit 5
        """;

    // Using SQL 
    public Optional<List<Result>> findByName (String name){

        List<Result> results = new ArrayList<>();

        try{
            results = jdbcTemplate.query(FIND_BY_NAME_SQL, new PreparedStatementSetter() {

                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    
                    ps.setString(1, name);
                }
                
            }, BeanPropertyRowMapper.newInstance(Result.class));
    
            System.out.println(">>> results length: " + results.size());

            if(results.size() > 0){
                return Optional.of(results);
            }
            else{
                return Optional.empty();
            }

    
        }catch(Exception ex){
            System.out.println(" error !!!");
            ex.printStackTrace();
            return Optional.empty();
        }

    }

    // Using MongoDB
    //     db.games.aggregate([
    //     {
    //         $match: { name: { $regex:"Die macher" , $options: "i"} }
    //     },
    //     {
    //         $lookup: { 
    //             from: "comments",
    //             foreignField: "gid",
    //             localField: "gid",
    //             as: "reviews"       
    //         }
    //     },
    //     {
    //         $unwind:  "$reviews" 
    //     },
    //     {
    //         $sort: { "reviews.rating": -1}
    //     },
    //     {
    //         $limit: 5
    //     }
    //  ])
    public Optional<List<Document>> mongoFindByName(String name){

        try{
            // stages
            MatchOperation matchById = Aggregation.match(Criteria.where("name").regex(name, "i"));
            LookupOperation lookupComments = Aggregation.lookup(C_COMMENT, "gid", "gid", "reviews");
            UnwindOperation unwindByReviews = Aggregation.unwind("reviews");
            SortOperation sortByRating = Aggregation.sort(Direction.DESC, "reviews.rating");// Sort.by(Direction.DESC, "reviews.rating")
            LimitOperation limit = Aggregation.limit(5);

            // pipeline
            Aggregation pipeline = Aggregation.newAggregation(matchById,lookupComments,unwindByReviews,sortByRating,limit);

            // perform query & get result
            AggregationResults<Document> results = mongoTemplate.aggregate(pipeline, C_GAME, Document.class);

            // results.getMappedResults();      // this return List<Document>
            // results.getUniqueMappedResult(); // this return Document, throw error if more than one result

            List<Document> doc = results.getMappedResults();
            // System.out.println(doc);
            // System.out.println(doc.toJson());
            return Optional.of(doc);

        }catch(Exception exception){
            return Optional.empty();
        }

    }
    
    
    
}
