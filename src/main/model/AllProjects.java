package model;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

//Represents a server with all accounts having a list of projects
public class AllProjects implements Writable {
    private ArrayList<Project> projectArrayList;  //list of projects created

    //EFFECTS: Creates an arraylist of projects to store the projects created which is empty at first
    public AllProjects() {
        this.projectArrayList = new ArrayList<>();
    }

    public ArrayList<Project> getProjectArrayList() {
        return projectArrayList;
    }

    //EFFECTS: adds project to arraylist of projects
    //MODIFIES: this
    public void addProject(Project project) {
        projectArrayList.add(project);
    }

    //EFFECTS: removes project from arraylist of projects
    //MODIFIES: this
    public void removeProject(Project project) {
        if (projectArrayList.contains(project)) {
            projectArrayList.remove(project);
        }
    }

    public int getNumberAllProjects() {
        return projectArrayList.size();
    }

    //EFFECTS: converts all Projects to Json
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("numOfAllProjects", getNumberAllProjects());
        json.put("All Projects", allProjectsToJson());
        return json;
    }

    // EFFECTS:converts each project in allProjects to Json
    private JSONArray allProjectsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Project p : projectArrayList) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }
}
