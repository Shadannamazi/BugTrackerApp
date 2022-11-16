package ui;

import model.AllProjects;
import model.Bug;
import model.BugSeverityLevel;
import model.Project;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ProjectFrame extends AllFrames implements ActionListener {


    private static final String JSON_STORE = "./data/allProjects.json";

    protected JLabel bugName = new JLabel("Name");
    protected JLabel bugPublisher = new JLabel("Publisher");
    protected JLabel bugAssignee = new JLabel("Assignee");
    protected JLabel bugIsFixed = new JLabel("Fixed");
    protected JLabel bugSeverityLevel = new JLabel("Severity Level");

    protected JLabel removeBug = new JLabel("Select Bug: ");
    protected JButton createBugButton;
    protected JButton removeBugButton;

    protected JButton fixBugButton;
    protected JLabel fixBug;
    protected JTextField fieldFixBug;

    protected JTextField fieldBugName;
    protected JTextField fieldBugPublisher;
    protected JTextField fieldBugAssignee;
    protected JCheckBox checkBoxBugIsFixed;
    protected JComboBox bugSeverityLevelList;
    private String projectName;
    private String projectCreator;

    private Project project;
    private AllProjects allProjects;

    protected DefaultTableModel tableModel;
    protected JTable table;

    public ProjectFrame(AllProjects allProjects,Project project) {

        super(project.getName(),"Bug", "Create New Bug", "Remove Bug", "View All Bugs");

        this.allProjects = allProjects;

        this.project = project;
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        bugSeverityLevelList.addActionListener(this);

    }


    @Override
    public void showUpdated() {
        resetViewAllBugs();
        resetRemoveBugs();
        showUpdatedAllBugsPanel();
        showUpdatedRemoveBugPanel();
        frame.revalidate();
        frame.repaint();

    }


    @Override
    public JComponent createTab1() {
        createPanelTab1 = super.createTab1();

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

        setBoundsButtonsFields();

        createPanelTab1.add(fieldBugName);
        createPanelTab1.add(fieldBugPublisher);
        createPanelTab1.add(fieldBugAssignee);
        createPanelTab1.add(checkBoxBugIsFixed);
        createPanelTab1.add(bugSeverityLevelList);

        createPanelTab1.add(bugName);
        createPanelTab1.add(bugPublisher);
        createPanelTab1.add(bugAssignee);
        createPanelTab1.add(bugIsFixed);
        createPanelTab1.add(bugSeverityLevel);

        return createPanelTab1;

    }

    public void setBoundsButtonsFields() {
        fieldBugName.setBounds(100,0,200,25);
        fieldBugPublisher.setBounds(100,50,200,25);
        fieldBugAssignee.setBounds(100,100,200,25);
        checkBoxBugIsFixed.setBounds(100,150,200,25);
        bugSeverityLevelList.setBounds(100,200,200,25);
        bugName.setBounds(15,0,100,25);
        bugPublisher.setBounds(15,50,100,25);
        bugAssignee.setBounds(15,100,100,25);
        bugIsFixed.setBounds(15,150,100,25);
        bugSeverityLevel.setBounds(15,200,100,25);
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

    @Override
    public JComponent createTab3() {
        viewAllPanel = super.createTab3();
        fixBugButton = new JButton("Fix Bug");
        fixBug = new JLabel("Select Bug: ");
        fieldFixBug = new JTextField();

        fixBugButton.setBounds(0, 500, 475, 25);
        fixBugButton.addActionListener(this);
        viewAllPanel.add(fixBugButton, BorderLayout.CENTER);

        fieldFixBug.setBounds(115, 475, 355, 25);

        viewAllPanel.add(fieldFixBug,BorderLayout.CENTER);
        fixBug.setBounds(15, 475, 125, 25);
        fixBug.setForeground(Color.white);

        viewAllPanel.add(fixBug);
        return viewAllPanel;
    }






    // MODIFIES: this
    // EFFECTS: resets view projects
    public void resetViewAllBugs() {
        if (table != null) {
            viewAllPanel.remove(table);
        }

    }


    // MODIFIES: this
    // EFFECTS: resets remove project
    public void resetRemoveBugs() {
        for (JButton button : wasteButtonsRemove) {
            removePanel.remove(button);
        }
    }

    //https://stackoverflow.com/questions/20526917/load-arraylist-data-into-jtable
    // EFFECTS: draws a table to store the bugs of the project
    public void drawBugTable(JComponent panel) {
        ArrayList<Bug> bugList = project.getBugArrayList();

        String[] columnNames = {"#", "Title", "Publisher", "Assignee", "Severity Level", "Fixed"};
        tableModel = new DefaultTableModel(columnNames, 0) {

            boolean[] canEdit = new boolean[]{
                    false, false, false, false, false,true
            };
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {

                return columnIndex == 5;
            }
        };
        table = new JTable(tableModel);
        table.setBounds(0, 0, 480,(bugList.size() + 1) * 16);
        table.setShowHorizontalLines(true);
        table.setGridColor(new Color(40,40,40));
        table.getColumnModel().getColumn(0).setPreferredWidth(5);
        table.getColumnModel().getColumn(5).setPreferredWidth(5);


        tableModel.addRow(columnNames);

        panel.add(table);



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




    public void showUpdatedAllBugsPanel() {


        if (project != null) {
            if (project.getSizeBugList() > 0) {

                ArrayList<Bug> bugList = project.getBugArrayList();

                drawBugTable(viewAllPanel);

                for (int i = 0; i < bugList.size(); i++) {

                    Bug bug = bugList.get(i);

                    Object[] bugRow = {i + 1, bug.getTitle(),bug.getPublisher(),bug.getAssignee(),
                            bug.getSeverityLevel(), bug.isFixed()};

                    tableModel.addRow(bugRow);

                    this.revalidate();
                    this.repaint();

                    wasteButtonsCreate.add(bugRow);
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


            severityLevel = (BugSeverityLevel) bugSeverityLevelList.getSelectedItem();


            Bug bug = new Bug(fieldBugName.getText(), fieldBugAssignee.getText(), fieldBugPublisher.getText(),
                    severityLevel);
            project.addBug(bug);
            showUpdated();

        } else if (e.getSource() == removeButtonTab2) {

            Bug removeBug = project.getBugArrayList().get(Integer.parseInt(fieldRemoveTab2.getText()) - 1);

            project.removeBug(removeBug);
            showUpdated();

        } else if (e.getSource() == fixBugButton) {


            project.getBugArrayList().get(Integer.parseInt(fieldFixBug.getText()) - 1).fixBug();
            showUpdated();

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
        showUpdated();
    }






}
