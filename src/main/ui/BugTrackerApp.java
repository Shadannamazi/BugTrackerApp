package ui;

import model.AllProjects;
import model.Bug;
import model.Project;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class BugTrackerApp {

    private Scanner input;

    AllProjects newServer = new AllProjects();

    Bug bug;

    public BugTrackerApp() {
        runBugTracker();
    }

    private void runBugTracker() {
        boolean keepGoing = true;
        String command = null;

        input = new Scanner(System.in).useDelimiter("\n");

        while (keepGoing) {

            displayMenu();

            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }

        }

    }

    private void processCommand(String command) {

        if (command.equals("c")) {
            Project myProject = createNewProject();
            newServer.addProject(myProject);

        } else if (command.equals("r")) {
            if (newServer.getProjectArrayList().size() > 0) {
                Project removeThisProject = removeProject(newServer.getProjectArrayList());
                newServer.removeProject(removeThisProject);
            } else {
                System.out.println("No projects available");
            }


        } else if (command.equals("v")) {

            if (newServer.getProjectArrayList().size() > 0) {

                viewListOfProjects(newServer);
                System.out.println("Enter the number of the project you want to select: ");
                int numberSelectedProject = input.nextInt();
                Project selectedProject = findProject(newServer.getProjectArrayList(), numberSelectedProject);

                boolean keepGoingBug = true;
                while (keepGoingBug) {
                    displayBugMenu();
                    command = input.next();
                    command = command.toLowerCase();

                    if (command.equals("a")) {
                        Bug newBug = createNewBug();
                        selectedProject.addBug(newBug);

                    } else if (command.equals("r")) {
                        viewHistory(selectedProject);
                        Bug removeThisBug = removeBug(selectedProject.getBugArrayList());
                        selectedProject.removeBug(removeThisBug);

                    } else if (command.equals("h")) {
                        viewHistory(selectedProject);

                    } else if (command.equals("b")) {
                        goBack();

                    }
                }
            } else {
                System.out.println("No projects available");
            }

        } else {
            System.out.println("Selection not valid...");
        }
    }


    private Project createNewProject() {
        Project project = new Project(addProjectName(), addProjectCreator());
        return project;
    }

    private Project removeProject(ArrayList<Project> projectArrayList) {
        viewListOfProjects(newServer);
        System.out.println("Enter the Project number that you want to remove: ");
        int projectNumberRemove = input.nextInt();
        //find the bug in the list then remove it
        return findProject(projectArrayList,projectNumberRemove);
    }

    private Project findProject(ArrayList<Project> projectList, int number) {

        return projectList.get((number - 1));
    }

    private String addProjectName() {
        System.out.println("Enter Project Name: ");
        String name = input.next();
        return name;
    }

    private String addProjectCreator() {
        System.out.println("Enter Project Creator: ");
        String creator = input.next();
        return creator;
    }

    private void viewListOfProjects(AllProjects server) {
        ArrayList<Project> projectList = server.getProjectArrayList();
        System.out.println("   Title   Creator     ");
        for (int i = 0; i < projectList.size(); i++) {
            Project project = projectList.get(i);
            System.out.println((i + 1) + ": " + project.getName() + "    " + project.getCreator());
        }

    }

    private Bug createNewBug() {
        String title = addBugTitle();
        String assignee = addBugAssignee();
        String publisher = addBugPublisher();
        String severityLevel = addBugSeverity();
        bug = new Bug(title, assignee, publisher, severityLevel);
        return bug;
    }


    private Bug removeBug(ArrayList<Bug> bugArrayList) {
        System.out.println("Enter Bug number that you want to remove: ");
        int bugNumberRemove = input.nextInt();
        //find the bug in the list then remove it
        return findBug(bugArrayList,bugNumberRemove);
    }

    private Bug findBug(ArrayList<Bug> bugsList, int number) {
        //loop through array to find the bug
        return bugsList.get(number - 1);
    }

    private void viewHistory(Project project) {
        if (project.getBugArrayList().size() > 0) {
            ArrayList<Bug> bugsList = project.getBugArrayList();
            System.out.println("   Title    Publisher    Assignee    Severity Level");
            for (int i = 0; i < bugsList.size();i++) {
                Bug bug = bugsList.get(i);
                System.out.println((i + 1) + ": " + bug.getTitle() + "    " + bug.getPublisher() + "    "
                        + bug.getAssignee() + "    " + bug.getSeverityLevel());
            }
        } else {
            System.out.println("No Bugs available");
        }


    }

    private void goBack() {
        runBugTracker();
    }

    private String addBugTitle() {
        System.out.println("Enter Bug Title: ");
        String title = input.next();
        return title;
    }

    private String addBugAssignee() {
        System.out.println("Enter Bug Assignee: ");
        String assignee = input.next();
        return assignee;

    }

    private String addBugSeverity() {
        System.out.println("Enter Bug Severity Level: ");
        String severity = input.next();
        return severity;

    }

    private String addBugPublisher() {
        System.out.println("Enter Bug Publisher: ");
        String publisher = input.next();
        return publisher;

    }


    private void displayBugMenu() {
        System.out.println("Select from");
        System.out.println("\ta -> Add new Bug");
        System.out.println("\tr -> Remove Bug");
        System.out.println("\th -> View history of Bugs");
        System.out.println("\tb -> Go Back");

    }

    private void displayMenu() {
        System.out.println("Select from");
        System.out.println("\tc -> Create New Project");
        System.out.println("\tr -> Remove Project");
        System.out.println("\tv -> View and Select Projects");


    }
}
