package sg.edu.nus.iss.csf_bgg.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Review {
    
    private String cId;
    private String user;
    private Integer rating;
    private String cText;

    public String getcId() {
        return cId;
    }
    public void setcId(String cId) {
        this.cId = cId;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public Integer getRating() {
        return rating;
    }
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    public String getcText() {
        return cText;
    }
    public void setcText(String cText) {
        this.cText = cText;
    }

    @Override
    public String toString() {
        return "Review [cId=" + cId + ", user=" + user + ", rating=" + rating + ", cText=" + cText + "]";
    }

    public JsonObject toJson(){
        return Json.createObjectBuilder()
            .add("cId",this.cId)
            .add("user",this.user)
            .add("rating",this.rating)
            .add("cText",this.cText)
            .build();
    }

    
    
}
