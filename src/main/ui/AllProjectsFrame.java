package ui;

import model.AllProjects;
import model.Project;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class AllProjectsFrame extends JFrame implements ActionListener {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    public static final Color COLOR = new Color(40, 40, 40);


    private static final String JSON_STORE = "./data/allProjects.json";
    protected AllProjects allProjects = new AllProjects();
    protected JsonWriter jsonWriter;
    protected JsonReader jsonReader;

    protected ProjectFrame newProjectFrame;

    ArrayList<JButton> wasteButtonsAllProjects = new ArrayList<>();
    ArrayList<JButton> wasteButtonsRemove = new ArrayList<>();
    protected JComponent viewAllProjectsPanel = new JPanel();
    protected JComponent removeProjectPanel = new JPanel();



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



    public AllProjectsFrame() {

        frame.setTitle("Bug Tracker Application");
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(COLOR);

        createButtonsForAllProjects();

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        viewAllProjectsPanel.setLayout(null);
        removeProjectPanel.setLayout(null);

        frame.setVisible(true);
        this.revalidate();
        this.repaint();
    }


    public void createButtonsForAllProjects() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBounds(100,0,500,575);
        mainPanel.setBackground(COLOR);
        frame.add(mainPanel);

        JLabel selectProject = new JLabel();

        ImageIcon bugLogo = new ImageIcon(new ImageIcon("data/bugLogo.png").getImage().getScaledInstance(
                100, 50, Image.SCALE_DEFAULT));

        selectProject.setVerticalAlignment(JLabel.TOP);
        selectProject.setIcon(bugLogo);
        selectProject.setBounds(0,0,500,50);

        setSaveActivityButton();
        setLoadProjectsButton();
        setRefreshAllProjectsButton();

        frame.add(selectProject);

        createTabs();
        this.revalidate();
        this.repaint();


    }

    public void setSaveActivityButton() {
        saveActivityButton.setBounds(0, 50, 100, 50);
        saveActivityButton.addActionListener(this);
        frame.add(saveActivityButton, BorderLayout.CENTER);
    }

    public void setLoadProjectsButton() {
        loadProjectsButton.setBounds(0, 100, 100, 50);
        loadProjectsButton.addActionListener(this);
        frame.add(loadProjectsButton, BorderLayout.CENTER);
    }

    public void setRefreshAllProjectsButton() {
        refreshAllProjectsButton.setBounds(0, 150, 100, 50);
        refreshAllProjectsButton.addActionListener(this);
        frame.add(refreshAllProjectsButton, BorderLayout.CENTER);
    }


    public void createTabs() {

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(Color.DARK_GRAY);

        JComponent createProjectPanel = createNewProjectTab();
        createProjectPanel.setBackground(new Color(91, 89, 89));
        tabbedPane.addTab("Create New Project",createProjectPanel);


        JComponent removeProjectPanel = removeAllProjects();
        removeProjectPanel.setBackground(new Color(91, 89, 89));
        tabbedPane.addTab("Remove Project",removeProjectPanel);


        this.revalidate();
        JComponent viewAllProjectsPanel = viewAllProjects();
        viewAllProjectsPanel.setBackground(new Color(91, 89, 89));
        tabbedPane.addTab("View All Projects",viewAllProjectsPanel);

        tabbedPane.setComponentAt(0,createProjectPanel);
        tabbedPane.setComponentAt(1,removeProjectPanel);
        tabbedPane.setComponentAt(2,viewAllProjectsPanel);

        mainPanel.add(tabbedPane);
        this.revalidate();
        this.repaint();

    }

    public JComponent createNewProjectTab() {
        JComponent createProjectPanel = new JPanel();
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
        this.revalidate();
        this.repaint();

        return createProjectPanel;
    }

    public JComponent viewAllProjects() {

        showUpdatedProjects();
        this.revalidate();
        this.repaint();

        return viewAllProjectsPanel;
    }

    public JComponent removeAllProjects() {

        removeProjectButton.setBounds(0, 500, 475, 25);
        removeProjectButton.addActionListener(this);
        removeProjectPanel.add(removeProjectButton, BorderLayout.CENTER);

        fieldRemoveProject.setBounds(115, 475, 355, 25);

        removeProjectPanel.add(fieldRemoveProject,BorderLayout.CENTER);
        removeProject.setBounds(15, 475, 125, 25);
        removeProject.setForeground(Color.white);
        removeProjectPanel.add(removeProject);

        showUpdatedProjects();
        this.revalidate();
        this.repaint();

        return removeProjectPanel;
    }


    // EFFECTS: saves the workroom to file
    private void saveAllProjects() {
        try {
            jsonWriter.open();
            jsonWriter.writeAllProjects(allProjects);
            jsonWriter.close();
            System.out.println("Saved all projects to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
        showUpdatedProjects();
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadAllProjects() {
        try {
            allProjects = jsonReader.read();
            System.out.println("Loaded all projects from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        showUpdatedProjects();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createProjectButton) {
            Project project = new Project(fieldProjectName.getText(), fieldProjectCreator.getText());
            allProjects.addProject(project);
            showUpdatedProjects();

        } else if (e.getSource() == removeProjectButton) {
            System.out.println(allProjects.getNumberAllProjects());
            //showUpdatedProjects();
            Project removeProject = allProjects.getProjectArrayList().get(Integer.parseInt(fieldRemoveProject.getText())
                    - 1);

            allProjects.removeProject(removeProject);
            saveAllProjects();
            showUpdatedProjects();

        } else if (e.getSource() == saveActivityButton) {
            saveAllProjects();

        } else if (e.getSource() == loadProjectsButton) {
            loadAllProjects();

        } else if (e.getSource() == refreshAllProjectsButton) {
            showUpdatedProjects();

        }

    }

    public void showUpdatedProjects() {
        resetViewAllProjects();
        resetRemoveProjects();
        showUpdatedAllProjectsPanel();
        showUpdatedRemoveProjectPanel();
        frame.revalidate();
        frame.repaint();

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

                refresh();
            }
        }
    }


    public void showUpdatedAllProjectsPanel() {

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

                viewAllProjectsPanel.add(projectButton, BorderLayout.CENTER);
                wasteButtonsAllProjects.add(projectButton);
                refresh();
            }

        }

    }

    public void refresh() {
        this.revalidate();
        this.repaint();
    }

}
