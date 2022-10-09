package model;

import java.util.ArrayList;

//Represents a server with all accounts having a list of projects
public class AllProjects {
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
        projectArrayList.remove(project);
    }


}
