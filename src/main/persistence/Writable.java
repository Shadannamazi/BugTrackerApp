package persistence;

//// This class has been created with help of Writable interface in JsonSerializationDemo
//https://github.students.cs.ubc.ca/Project/JsonSerializationDemo/blob/master/src/main/persistence/Writable.java

import org.json.JSONObject;

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
