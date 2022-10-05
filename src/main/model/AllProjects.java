package model;

import java.util.ArrayList;

public class AllProjects {
    private ArrayList<Project> projectArrayList;

    public AllProjects() {
        this.projectArrayList = new ArrayList<>();
    }

    public void addProject(Project project) {
        projectArrayList.add(project);
    }

    public void removeProject(Project project) {
        projectArrayList.remove(project);
    }

    public ArrayList<Project> getProjectArrayList() {
        return projectArrayList;
    }
}
