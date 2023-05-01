package sg.edu.nus.iss.csf_bgg.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class MongoResult {
    
    private Integer gid;
    private String name;
    private Integer year;
    private Integer ranking;
    private Integer usersRated;
    private String url;
    private String image;
    
    public Integer getGid() {
        return gid;
    }
    public void setGid(Integer gid) {
        this.gid = gid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }
    public Integer getRanking() {
        return ranking;
    }
    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }
    public Integer getUsersRated() {
        return usersRated;
    }
    public void setUsersRated(Integer usersRated) {
        this.usersRated = usersRated;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    @Override
    public String toString() {
        return "MongoResult [gid=" + gid + ", name=" + name + ", year=" + year + ", ranking=" + ranking
                + ", usersRated=" + usersRated + ", url=" + url + ", image=" + image + "]";
    }
    
    public JsonObjectBuilder toJson(){
        
        return Json.createObjectBuilder()
            .add("gid", this.gid)
            .add("name",this.name)
            .add("year",this.year)
            .add("ranking",this.ranking)
            .add("usersRated",this.usersRated)
            .add("url", this.url)
            .add("image", this.image);
    }
    
}
