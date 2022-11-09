package ui;

import javax.swing.*;
import java.awt.*;

public class AllProjectsView extends JFrame {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 600;

    protected JFrame frame = new JFrame();
    protected JPanel mainPanel;
    protected JTextField fieldProjectName = new JTextField();
    protected JTextField fieldProjectCreator = new JTextField();
    protected JButton createProjectButton = new JButton("Create New Project");
    protected JLabel projectName = new JLabel("Name");
    protected JLabel projectCreator = new JLabel("Creator");
    //protected JLabel selectProject = new JLabel("Select Project: ");


    public AllProjectsView() {

        frame.setLayout(null);
        frame.setTitle("Bug Tracker Application");
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setResizable(false);

        frame.getContentPane().setBackground(new Color(40, 40, 40));
        //mainPanel.setLayout(new GridLayout(0,1));
        //mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        createButtonsForAllProjects();

        frame.setVisible(true);
    }

    public void createButtonsForAllProjects() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        frame.add(mainPanel);

        JLabel selectProject = new JLabel("Create New Project: ");

        ImageIcon bugLogo = new ImageIcon(new ImageIcon("data/bugLogo.png").getImage().getScaledInstance(
                50, 50, Image.SCALE_DEFAULT));
        selectProject.setForeground(Color.white);
        selectProject.setVerticalAlignment(JLabel.TOP);
        selectProject.setIcon(bugLogo);
        selectProject.setBounds(0,0,500,50);
        frame.add(selectProject);

        addButtonsForAllProjects();

    }

    public void addButtonsForAllProjects() {
        createProjectButton.setBounds(0, 250, 500, 25);
        frame.add(createProjectButton, BorderLayout.CENTER);

        fieldProjectName.setBounds(150, 100, 200, 25);
        fieldProjectCreator.setBounds(150, 150, 200, 25);

        frame.add(fieldProjectName,BorderLayout.CENTER);
        frame.add(fieldProjectCreator,BorderLayout.CENTER);

        projectName.setBounds(75, 100, 75, 25);
        projectCreator.setBounds(75, 150, 75, 25);
        projectName.setForeground(Color.white);
        projectCreator.setForeground(Color.white);
        frame.add(projectName);
        frame.add(projectCreator);



    }


}
