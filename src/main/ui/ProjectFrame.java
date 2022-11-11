package ui;

import model.AllProjects;
import model.Bug;
import model.BugSeverityLevel;
import model.Project;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ProjectFrame extends AllFrames implements ActionListener {


    private static final String JSON_STORE = "./data/allProjects.json";
    //private AllProjects allProjects = new AllProjects();
    //private JsonWriter jsonWriter;
    //private JsonReader jsonReader;

    protected JLabel bugName = new JLabel("Name");
    protected JLabel bugPublisher = new JLabel("Publisher");
    protected JLabel bugAssignee = new JLabel("Assignee");
    protected JLabel bugIsFixed = new JLabel("Fixed");
    protected JLabel bugSeverityLevel = new JLabel("Severity Level");

    protected JLabel removeBug = new JLabel("Select Bug: ");
    protected JButton createBugButton;
    protected JButton removeBugButton;

    protected JTextField fieldBugName;
    protected JTextField fieldBugPublisher;
    protected JTextField fieldBugAssignee;
    protected JCheckBox checkBoxBugIsFixed;
    protected JComboBox bugSeverityLevelList;
    private String projectName;
    private String projectCreator;

    private Project project;
    private AllProjects allProjects;

    public ProjectFrame(AllProjects allProjects,Project project) {


        super(project.getName(),"Bug", "Create New Bug", "Remove Bug", "View All Bugs");

        this.allProjects = allProjects;
        int indexProj;
        for (Project p : allProjects.getProjectArrayList()) {
            if (p.getName().equals(project.getName()) && p.getCreator().equals(project.getCreator())) {
                indexProj = allProjects.getProjectArrayList().indexOf(p);
            }
        }
        this.project = project;


        //project = allProjects.getProjectArrayList().get(indexProj);
        //this.projectName = project.getName();
        //this.projectCreator = project.getCreator();

        bugSeverityLevelList.addActionListener(this);

        //project = new Project(projectName,projectCreator);

    }



    @Override
    public void showUpdated() {
        resetViewAllBugs();
        resetRemoveBugs();
        showUpdatedAllBugsPanel();
        showUpdatedRemoveBugPanel();
        frame.revalidate();

    }



    @Override
    public JComponent createTab1() {
        createPanelTab1 = super.createTab1();

        //JButton createBugButton = new JButton("Create New Bug");
        //JButton removeBugButton = new JButton("Remove Bug");

        //createBugButton.setBounds(0, 250, 475, 25);
        //createBugButton.addActionListener(this);
        //createPanelTab1.add(createBugButton, BorderLayout.CENTER);

        fieldBugName = new JTextField();
        fieldBugPublisher = new JTextField();
        fieldBugAssignee = new JTextField();
        checkBoxBugIsFixed = new JCheckBox();
        bugSeverityLevelList = new JComboBox(BugSeverityLevel.values());

        bugName = new JLabel("Name");
        bugPublisher = new JLabel("Publisher");
        bugAssignee = new JLabel("Assignee");
        bugIsFixed = new JLabel("Fixed");
        bugSeverityLevel = new JLabel("Severity Level");

        fieldBugName.setBounds(100,0,200,25);
        fieldBugPublisher.setBounds(100,50,200,25);
        fieldBugAssignee.setBounds(100,100,200,25);
        checkBoxBugIsFixed.setBounds(100,150,200,25);
        bugSeverityLevelList.setBounds(100,200,200,25);

        createPanelTab1.add(fieldBugName);
        createPanelTab1.add(fieldBugPublisher);
        createPanelTab1.add(fieldBugAssignee);
        createPanelTab1.add(checkBoxBugIsFixed);
        createPanelTab1.add(bugSeverityLevelList);


        bugName.setBounds(15,0,100,25);
        bugPublisher.setBounds(15,50,100,25);
        bugAssignee.setBounds(15,100,100,25);
        bugIsFixed.setBounds(15,150,100,25);
        bugSeverityLevel.setBounds(15,200,100,25);

        createPanelTab1.add(bugName);
        createPanelTab1.add(bugPublisher);
        createPanelTab1.add(bugAssignee);
        createPanelTab1.add(bugIsFixed);
        createPanelTab1.add(bugSeverityLevel);



        return createPanelTab1;

    }

    @Override
    public JComponent createTab2() {
        removePanel = super.createTab2();
        removeBug = new JLabel("Select Bug: ");
        removeBug.setBounds(15, 475, 125, 25);
        removeBug.setForeground(Color.white);
        removePanel.add(removeBug);
        showUpdated();

        return removePanel;
    }






    // MODIFIES: this
    // EFFECTS: resets view projects
    public void resetViewAllBugs() {
        for (JButton button : wasteButtonsCreate) {
            viewAllPanel.remove(button);
        }
    }


    // MODIFIES: this
    // EFFECTS: resets remove project
    public void resetRemoveBugs() {
        for (JButton button : wasteButtonsRemove) {
            removePanel.remove(button);
        }
    }

    public void showUpdatedRemoveBugPanel() {

        if (project != null) {
            if (project.getSizeBugList() > 0) {


                ArrayList<Bug> bugList = project.getBugArrayList();
                for (int i = 0; i < bugList.size(); i++) {
                    Bug bug = bugList.get(i);
                    JButton projectButton = new JButton((i + 1) + ": Title: " + bug.getTitle()
                            + "   Publisher: " + bug.getPublisher()
                            + "   Assignee: " + bug.getAssignee()
                            + "   Severity Level: " + bug.getSeverityLevel()
                            + "   Fixed: " + bug.isFixed());


                    projectButton.setBounds(0, i * 25 + 25, 475, 25);
                    projectButton.setHorizontalAlignment(SwingConstants.LEFT);
                    projectButton.addActionListener(this);

                    this.revalidate();

                    removePanel.add(projectButton, BorderLayout.CENTER);
                    wasteButtonsRemove.add(projectButton);


                    this.revalidate();
                }

            }

        }


    }


    /*//https://stackoverflow.com/questions/45263672/how-to-populate-jtable-with-arraylist-values
    private static Object[][] getTableData(ArrayList<Bug> data, String[] columns) throws IllegalAccessException, NoSuchFieldException {
        Object[][] dataForTable = new Object[data.size()][columns.length];

        for (int i = 0; i < data.size(); i++) {

            String value = data.get(i).getClass().getDeclaredField(columns[column]).get(data.get(i)).toString();
            dataForTable[i][column] = value;

        }
        return dataForTable;
    }*/

    public void showUpdatedAllBugsPanel() {
        if (project != null) {
            if (project.getSizeBugList() > 0) {



                /*try {
                    Object[][] data = getTableData(project.getBugArrayList(),columnNames);
                    JTable table = new JTable(data, columnNames);
                    JScrollPane scrollPane = new JScrollPane(table);
                    table.setFillsViewportHeight(true);
                    scrollPane.setBounds(0, 25, 475, 25);
                    viewAllPanel.add(scrollPane);

                } catch (IllegalAccessException e) {
                    System.out.println("Illegal access");
                } catch (NoSuchFieldException e) {
                    System.out.println("No such file");
                }*/

                ArrayList<Bug> bugList = project.getBugArrayList();
                //https://stackoverflow.com/questions/20526917/load-arraylist-data-into-jtable
                String[] columnNames = {"Title", "Publisher", "Assignee", "Severity Level", "Fixed"};
                DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
                JTable table = new JTable(tableModel);
                table.setBounds(0, 0, 480,bugList.size() * 21);
                table.setShowHorizontalLines(true);
                table.setGridColor(new Color(40,40,40));

                tableModel.addRow(columnNames);
                viewAllPanel.add(table);

                for (int i = 0; i < bugList.size(); i++) {

                    Bug bug = bugList.get(i);


                    Object[] bugRow = {bug.getTitle(),bug.getPublisher(),bug.getAssignee(),bug.getSeverityLevel(),
                            bug.isFixed()};

                    tableModel.addRow(bugRow);

                    /*JButton bugButton = new JButton((i + 1) + ": Title: " + bug.getTitle()
                            + "   Publisher: " + bug.getPublisher()
                            + "   Assignee: " + bug.getAssignee()
                            + "   Severity Level: " + bug.getSeverityLevel()
                            + "   Fixed: " + bug.isFixed());


                    bugButton.setBounds(0, i * 25 + 25, 475, 25);
                    bugButton.setHorizontalAlignment(SwingConstants.LEFT);
                    bugButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (e.getSource() == bugButton) {
                                BugFrame newBugFrame = new BugFrame(bug.getTitle());
                            }
                        }
                    });*/

                    this.revalidate();

                    //viewAllPanel.add(bugButton, BorderLayout.CENTER);
                    //wasteButtonsCreate.add(bugButton);

                    this.revalidate();
                }

            }
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //project = new Project(projectName,projectCreator);
        if (e.getSource() == createButtonTab1) {
            BugSeverityLevel severityLevel = BugSeverityLevel.LOW;

            if (e.getSource() == bugSeverityLevelList) {
                severityLevel = (BugSeverityLevel) bugSeverityLevelList.getSelectedItem();
            }

            Bug bug = new Bug(fieldBugName.getText(), fieldBugAssignee.getText(), fieldBugPublisher.getText(),
                    severityLevel);
            project.addBug(bug);

        } else if (e.getSource() == removeButtonTab2) {
            //System.out.println(allProjects.getNumberAllProjects());
            //showUpdatedProjects();
            Bug removeBug = project.getBugArrayList().get(Integer.parseInt(fieldRemoveTab2.getText()) - 1);

            project.removeBug(removeBug);
            //showUpdated();
            //saveAllProjects();

            //deleteProjectFromJson(removeProject);
            //showUpdatedProjects();


        } else if (e.getSource() == refreshButton) {
            showUpdated();

        }  else if (e.getSource() == loadButton) {
            loadAllProjects();

        } else if (e.getSource() == saveActivityButton) {
            saveAllProjects();

        }


    }

    // EFFECTS: saves the workroom to file
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

    /*
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




}
