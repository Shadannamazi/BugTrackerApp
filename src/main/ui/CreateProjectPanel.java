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

public class CreateProjectPanel extends JFrame implements ActionListener {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    public static final Color COLOR = new Color(40, 40, 40);


    private static final String JSON_STORE = "./data/allProjects.json";
    private AllProjects allProjects = new AllProjects();
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;



    //////////
    ArrayList<JButton> wasteButtons = new ArrayList<>();
    protected JComponent viewAllProjectsPanel = new JPanel();



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
    //protected JLabel selectProject = new JLabel("Select Project: ");


    public CreateProjectPanel() {

        //frame.setLayout(null);
        frame.setTitle("Bug Tracker Application");
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setResizable(false);

        frame.getContentPane().setBackground(COLOR);
        //mainPanel.setLayout(new GridLayout(0,1));
        //mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        createButtonsForAllProjects();


        allProjects = new AllProjects();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);


        //createSaveAndLoad();

        //test();


        viewAllProjectsPanel.setLayout(null);


        frame.setVisible(true);
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
        //selectProject.setForeground(Color.white);
        selectProject.setVerticalAlignment(JLabel.TOP);
        selectProject.setIcon(bugLogo);
        selectProject.setBounds(0,0,500,50);

        saveActivityButton.setBounds(0, 50, 100, 50);
        saveActivityButton.addActionListener(this);
        frame.add(saveActivityButton, BorderLayout.CENTER);

        loadProjectsButton.setBounds(0, 100, 100, 50);
        loadProjectsButton.addActionListener(this);
        frame.add(loadProjectsButton, BorderLayout.CENTER);


        refreshAllProjectsButton.setBounds(0, 150, 100, 50);
        refreshAllProjectsButton.addActionListener(this);
        frame.add(refreshAllProjectsButton, BorderLayout.CENTER);


        frame.add(selectProject);

        createTabs();


    }


    public void createTabs() {

        JTabbedPane tabbedPane = new JTabbedPane();
        //tabbedPane.setLayout(null);
        tabbedPane.setBackground(Color.DARK_GRAY);

        JComponent createProjectPanel = createNewProjectTab();
        createProjectPanel.setBackground(new Color(164, 37, 37));
        //createProjectPanel.setBounds(50,0,75,25);
        tabbedPane.addTab("Create New Project",createProjectPanel);



        JComponent removeProjectPanel = removeProjectPanel();
        removeProjectPanel.setBackground(new Color(18, 83, 225));
        //removeProjectPanel.setBounds(150,0,75,25);
        tabbedPane.addTab("Remove Project",removeProjectPanel);



        this.revalidate();
        JComponent viewAllProjectsPanel = viewAllProjects();
        //viewAllProjectsPanel.setBounds(250,0,75,25);
        viewAllProjectsPanel.setBackground(new Color(77, 159, 74));
        tabbedPane.addTab("View All Projects",viewAllProjectsPanel);




        /*tabbedPane.setComponentAt(0,createProjectPanel);
        tabbedPane.setComponentAt(1,removeProjectPanel);
        tabbedPane.setComponentAt(2,viewAllProjectsPanel);*/
        mainPanel.add(tabbedPane);


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

        return createProjectPanel;
    }

    public JComponent removeProjectPanel() {

        JComponent removeProjectPanel = new JPanel();
        removeProjectPanel.setLayout(null);
        removeProjectButton.setBounds(0, 250, 475, 25);
        removeProjectButton.addActionListener(this);
        removeProjectPanel.add(removeProjectButton, BorderLayout.CENTER);


        fieldRemoveProject.setBounds(150, 100, 200, 25);


        removeProjectPanel.add(fieldRemoveProject,BorderLayout.CENTER);


        removeProject.setBounds(50, 100, 125, 25);

        removeProject.setForeground(Color.white);

        removeProjectPanel.add(removeProject);


        return removeProjectPanel;

    }

    public JComponent viewAllProjects() {

        showUpdatedProjects();


        return viewAllProjectsPanel;
    }

    /*public void test() {
        System.out.println(allProjects.getProjectArrayList().size());

        this.revalidate();
        ArrayList<Project> projectList = allProjects.getProjectArrayList();
        for (int i = 0; i < projectList.size(); i++) {
            Project project = projectList.get(i);
            System.out.println("proj" + project.getName() + project.getCreator());


        }
    }*/


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
        //viewAllProjects();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createProjectButton) {
            Project project = new Project(fieldProjectName.getText(), fieldProjectCreator.getText());
            allProjects.addProject(project);

        } else if (e.getSource() == removeProjectButton) {
            Project removeProject = allProjects.getProjectArrayList().get(Integer.valueOf(fieldRemoveProject.getName())
                    - 1);
            allProjects.removeProject(removeProject);

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
        showUpdatedAllProjectsPanel();
        showUpdatedRemoveProjectPanel();
        frame.revalidate();

    }

    // MODIFIES: this
    // EFFECTS: resets view projects
    public void resetViewAllProjects() {
        for (JButton button : wasteButtons) {
            viewAllProjectsPanel.remove(button);
        }
    }

    public void showUpdatedRemoveProjectPanel() {
    }

    public void showUpdatedAllProjectsPanel() {


        System.out.println(allProjects.getNumberAllProjects());

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

                viewAllProjectsPanel.add(projectButton, BorderLayout.CENTER);
                wasteButtons.add(projectButton);

                this.revalidate();
            }

        }
        //viewAllProjectsPanel.revalidate();
        //return viewAllProjectsPanel;
    }




}
