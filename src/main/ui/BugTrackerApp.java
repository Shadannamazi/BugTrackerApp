package ui;

import model.AllProjects;
import model.Bug;
import model.Project;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

// Bug Tracker Application
public class BugTrackerApp {
    private Scanner input;

    AllProjects newServer = new AllProjects();
    Bug bug;

    // EFFECTS: runs the Bug Tracker application
    public BugTrackerApp() {
        runBugTracker();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
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

    // MODIFIES: this
    // EFFECTS: processes user command
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

                    } else if (command.equals("m")) {
                        viewHistory(selectedProject);
                        Bug markThisBug = markFixedBug(selectedProject.getBugArrayList());
                        markThisBug.fixBug();

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

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private Project createNewProject() {
        Project project = new Project(addProjectName(), addProjectCreator());
        return project;
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private Project removeProject(ArrayList<Project> projectArrayList) {
        viewListOfProjects(newServer);
        System.out.println("Enter the Project number that you want to remove: ");
        int projectNumberRemove = input.nextInt();
        //find the bug in the list then remove it
        return findProject(projectArrayList,projectNumberRemove);
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private Project findProject(ArrayList<Project> projectList, int number) {
        return projectList.get((number - 1));
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private String addProjectName() {
        System.out.println("Enter Project Name: ");
        String name = input.next();
        return name;
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private String addProjectCreator() {
        System.out.println("Enter Project Creator: ");
        String creator = input.next();
        return creator;
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void viewListOfProjects(AllProjects server) {
        ArrayList<Project> projectList = server.getProjectArrayList();
        System.out.println("   Title   Creator     ");
        for (int i = 0; i < projectList.size(); i++) {
            Project project = projectList.get(i);
            System.out.println((i + 1) + ": " + project.getName() + "    " + project.getCreator());
        }

    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private Bug createNewBug() {
        String title = addBugTitle();
        String assignee = addBugAssignee();
        String publisher = addBugPublisher();
        String severityLevel = addBugSeverity();
        bug = new Bug(title, assignee, publisher, severityLevel);
        return bug;
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private Bug removeBug(ArrayList<Bug> bugArrayList) {
        System.out.println("Enter Bug number that you want to remove: ");
        int bugNumberRemove = input.nextInt();
        //find the bug in the list then remove it
        return findBug(bugArrayList,bugNumberRemove);
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private Bug findBug(ArrayList<Bug> bugsList, int number) {
        //loop through array to find the bug
        return bugsList.get(number - 1);
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void viewHistory(Project project) {
        if (project.getBugArrayList().size() > 0) {
            ArrayList<Bug> bugsList = project.getBugArrayList();
            System.out.println("   Title    Publisher    Assignee    Severity Level     Fixed");
            for (int i = 0; i < bugsList.size();i++) {
                Bug bug = bugsList.get(i);
                System.out.println((i + 1) + ": " + bug.getTitle() + "    " + bug.getPublisher() + "    "
                        + bug.getAssignee() + "    " + bug.getSeverityLevel() + "    " + bug.getFixed());
            }
        } else {
            System.out.println("No Bugs available");
        }


    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private Bug markFixedBug(ArrayList<Bug> bugArrayList) {
        System.out.println("Enter Bug number that you want to mark as fixed: ");
        int bugNumberFix = input.nextInt();
        //find the bug in the list then fix it
        return findBug(bugArrayList,bugNumberFix);
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void goBack() {
        runBugTracker();
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private String addBugTitle() {
        System.out.println("Enter Bug Title: ");
        String title = input.next();
        return title;
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private String addBugAssignee() {
        System.out.println("Enter Bug Assignee: ");
        String assignee = input.next();
        return assignee;
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private String addBugSeverity() {
        System.out.println("Enter Bug Severity Level: ");
        String severity = input.next();
        return severity;
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private String addBugPublisher() {
        System.out.println("Enter Bug Publisher: ");
        String publisher = input.next();
        return publisher;
    }

    // EFFECTS: displays the Bug menu of options to user
    private void displayBugMenu() {
        System.out.println("Select from");
        System.out.println("\ta -> Add new Bug");
        System.out.println("\tr -> Remove Bug");
        System.out.println("\th -> View history of Bugs");
        System.out.println("\tm -> Mark fixed Bug");
        System.out.println("\tb -> Go Back");
    }

    // EFFECTS: displays the main menu of options to user
    private void displayMenu() {
        System.out.println("Select from");
        System.out.println("\tc -> Create New Project");
        System.out.println("\tr -> Remove Project");
        System.out.println("\tv -> View and Select Projects");
    }
}
