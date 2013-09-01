package models;

import org.codehaus.jackson.annotate.JsonProperty;
import org.mongojack.MongoCollection;
import org.mongojack.ObjectId;

/**
 * User: Charles
 * Date: 7/17/13
 */

@MongoCollection(name = "profiles")
class Profile {

    val id: String

    @ObjectId
    @JsonProperty("_id")
    def getId() {
        id
    }

    @ObjectId
    @JsonProperty("_id")
    def setId(id: String) {
        this.id = id
    }

}