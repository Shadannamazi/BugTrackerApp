package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a project having a name,a creator, and an arraylist of bugs
public class Project implements Writable {

    private String name;                  //project name
    private String creator;               //project creator
    private ArrayList<Bug> bugArrayList;  //arraylist of bugs for project

    //REQUIRES: projectName and projectCreator has a non-zero length
    //EFFECTS: name on project is set to projectName; creator on project is set to projectCreator;
    // name on project is set to projectName; Creates an arraylist to store the bugs
    public Project(String projectName, String projectCreator) {
        this.name = projectName;
        this.creator = projectCreator;
        this.bugArrayList = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public String getCreator() {
        return this.creator;
    }

    public ArrayList<Bug> getBugArrayList() {
        return bugArrayList;
    }

    //EFFECTS: adds bug to arraylist of bugs
    //MODIFIES: this
    public void addBug(Bug bug) {
        bugArrayList.add(bug);
    }

    //EFFECTS: adds bug to arraylist of bugs
    //MODIFIES: this
    public void removeBug(Bug bug) {
        bugArrayList.remove(bug);
    }

    public int getSizeBugList() {
        return bugArrayList.size();
    }

    //EFFECTS: converts project to Json
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        //json.put("Project", projectToJson());
        json.put("name", name);
        json.put("creator", creator);
        json.put("bugArrayList", bugArrayListToJson());
        return json;
    }

    /*private JSONArray projectToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Bug b : bugArrayList) {
            jsonArray.put(b.toJson());
        }
        return jsonArray;
    }*/

    private JSONArray bugArrayListToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Bug b : bugArrayList) {
            jsonArray.put(b.toJson());
        }
        return jsonArray;
    }







}

