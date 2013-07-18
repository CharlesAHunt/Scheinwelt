package models;

import org.codehaus.jackson.annotate.JsonProperty;
import net.vz.mongodb.jackson.*;

/**
 * User: Charles
 * Date: 7/17/13
 */

@MongoCollection(name = "profiles")
public class Profile {

    private String id;

    @ObjectId
    @JsonProperty("_id")
    public String getId() {
        return id;
    }

    @ObjectId
    @JsonProperty("_id")
    public void setId(String id) {
        this.id = id;
    }

    public Profile() {   }

}