package ui;

import model.AllProjects;
import model.Project;

public class IdkClass {
    private AllProjects allProjects;

    public IdkClass() {
        allProjects = new AllProjects();
    }

    // EFFECTS: creates a new project with the name and creator
    public Project createNewProject(String name, String creator) {


        Project project = new Project(name, creator);
        allProjects.addProject(project);
        return project;

    }


}