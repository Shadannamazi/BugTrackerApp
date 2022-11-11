package ui;

import model.AllProjects;
import model.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class AllProjectsFrame extends AllFrames implements ActionListener {

    protected ProjectFrame newProjectFrame;

    //////////
    ArrayList<JButton> wasteButtonsAllProjects = new ArrayList<>();
    ArrayList<JButton> wasteButtonsRemove = new ArrayList<>();
    protected JComponent viewAllProjectsPanel = new JPanel();
    protected JComponent removeProjectPanel = new JPanel();
    protected JComponent createProjectPanel = new JPanel();


    protected JFrame frame = new JFrame();
    protected JPanel mainPanel;
    protected JTextField fieldProjectName = new JTextField();
    protected JTextField fieldProjectCreator = new JTextField();
    protected JButton createProjectButton = new JButton("Create New Project");
    protected JButton removeProjectButton = new JButton("Remove Project");
    protected JTextField fieldRemoveProject = new JTextField();

    protected JButton loadProjectsButton = new JButton("Load Projects");
    protected JButton saveActivityButton = new JButton("Save Activity");
    protected JButton refreshAllProjectsButton = new JButton("Refresh");

    protected JLabel projectName = new JLabel("Name");
    protected JLabel projectCreator = new JLabel("Creator");
    protected JLabel removeProject = new JLabel("Select Project: ");

    public AllProjectsFrame(AllProjects allProjects) {
        super("Bug Tracker Application", "Project", "Create New Project","Remove Project",
                "View All Projects");


        this.allProjects = allProjects;
    }

    @Override
    public JComponent createTab1() {
        createProjectPanel = super.createTab1();

        createProjectPanel.setLayout(null);
        createProjectButton.setBounds(0, 250, 475, 25);
        createProjectButton.addActionListener(this);
        createProjectPanel.add(createProjectButton, BorderLayout.CENTER);



        fieldProjectName.setBounds(150, 100, 200, 25);
        fieldProjectCreator.setBounds(150, 150, 200, 25);

        createProjectPanel.add(fieldProjectName,BorderLayout.CENTER);
        createProjectPanel.add(fieldProjectCreator,BorderLayout.CENTER);

        projectName.setBounds(100, 100, 75, 25);
        projectCreator.setBounds(100, 150, 75, 25);
        projectName.setForeground(Color.white);
        projectCreator.setForeground(Color.white);
        createProjectPanel.add(projectName);
        createProjectPanel.add(projectCreator);

        return createProjectPanel;

    }


    @Override
    protected JComponent createTab2() {
        removeProjectPanel = super.createTab2();
        removeProject.setBounds(15, 475, 125, 25);
        removeProject.setForeground(Color.white);

        removeProjectPanel.add(removeProject);

        return removeProjectPanel;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createProjectButton) {
            Project project = new Project(fieldProjectName.getText(), fieldProjectCreator.getText());
            allProjects.addProject(project);

        } else if (e.getSource() == removeProjectButton) {
            System.out.println(allProjects.getNumberAllProjects());
            //showUpdatedProjects();
            Project removeProject = allProjects.getProjectArrayList().get(Integer.parseInt(fieldRemoveProject.getText())
                    - 1);

            allProjects.removeProject(removeProject);
            saveAllProjects();
            //deleteProjectFromJson(removeProject);
            //showUpdatedProjects();


        } /*else if (e.getSource() == saveActivityButton) {
            saveAllProjects();

        } else if (e.getSource() == loadProjectsButton) {
            loadAllProjects();

        } else if (e.getSource() == refreshAllProjectsButton) {
            showUpdated();

        }*/



    }

    /*// EFFECTS: saves the workroom to file
    protected void saveAllProjects() {
        try {
            jsonWriter.open();
            jsonWriter.writeAllProjects(allProjects);
            jsonWriter.close();
            System.out.println("Saved all projects to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    protected void loadAllProjects() {
        try {
            allProjects = jsonReader.read();
            System.out.println("Loaded all projects from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        //viewAllProjects();
    }*/




    @Override
    public void showUpdated() {
        resetViewAllProjects();
        resetRemoveProjects();
        showUpdatedAllProjectsPanel();
        showUpdatedRemoveProjectPanel();
        frame.revalidate();
    }

    // MODIFIES: this
    // EFFECTS: resets view projects
    public void resetViewAllProjects() {
        for (JButton button : wasteButtonsAllProjects) {
            viewAllProjectsPanel.remove(button);
        }
    }


    // MODIFIES: this
    // EFFECTS: resets remove project
    public void resetRemoveProjects() {
        for (JButton button : wasteButtonsRemove) {
            removeProjectPanel.remove(button);
        }
    }

    public void showUpdatedRemoveProjectPanel() {

        if (allProjects.getNumberAllProjects() > 0) {


            ArrayList<Project> projectList = allProjects.getProjectArrayList();
            for (int i = 0; i < projectList.size(); i++) {
                Project project = projectList.get(i);
                JButton projectButton = new JButton((i + 1) + ": " + project.getName()
                        + " - " + project.getCreator());

                projectButton.setBounds(0, i * 25 + 25, 475, 25);
                projectButton.setHorizontalAlignment(SwingConstants.LEFT);
                projectButton.addActionListener(this);

                this.revalidate();

                removeProjectPanel.add(projectButton, BorderLayout.CENTER);
                wasteButtonsRemove.add(projectButton);


                this.revalidate();
            }

        }


    }

    public void showUpdatedAllProjectsPanel() {
        //System.out.println(allProjects.getNumberAllProjects());

        if (allProjects.getNumberAllProjects() > 0) {


            ArrayList<Project> projectList = allProjects.getProjectArrayList();
            for (int i = 0; i < projectList.size(); i++) {
                Project project = projectList.get(i);
                JButton projectButton = new JButton((i + 1) + ": " + project.getName()
                        + " - " + project.getCreator());

                projectButton.setBounds(0, i * 25 + 25, 475, 25);
                projectButton.setHorizontalAlignment(SwingConstants.LEFT);
                projectButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == projectButton) {
                            if (project != null) {
                                newProjectFrame = new ProjectFrame(allProjects,project);
                            }

                        }
                    }
                });
                this.revalidate();

                viewAllProjectsPanel.add(projectButton, BorderLayout.CENTER);
                wasteButtonsAllProjects.add(projectButton);

                this.revalidate();
            }

        }
    }
}
