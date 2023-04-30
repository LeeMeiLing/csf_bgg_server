package sg.edu.nus.iss.csf_bgg.repositories;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.csf_bgg.models.Result;

@Repository
public class BoardGameRepo {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String FIND_BY_NAME_SQL = """
        select * from game left join comment on game.gid = comment.gid
        where game.name = ?
        order by game.gid, comment.rating desc 
        limit 5
        """;

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
    
    
    
}
