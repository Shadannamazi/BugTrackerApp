package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class AllProjectsTest {

    private AllProjects allProjectsTest;
    private Project project1;
    private Project project2;
    private Project project3;
    private Project project4;
    //private EventLog eventLog;


    @BeforeEach
    public void runBefore() {
        allProjectsTest = new AllProjects();
        project1 = new Project("pro1", "creator1");
        project2 = new Project("pro2", "creator2");
        project3 = new Project("pro3", "creator3");
        project4 = new Project("pro4", "creator4");
        EventLog.getInstance().clear();


    }

    @Test
    public void testConstructor() {
        assertEquals(0,allProjectsTest.getNumberAllProjects());
    }

    @Test
    public void testAddProject() {
        allProjectsTest.addProject(project1);
        assertEquals(1,allProjectsTest.getNumberAllProjects());
        assertEquals(project1,allProjectsTest.getProjectArrayList().get(0));



    }

    @Test
    public void testAddMultipleProjects() {
        allProjectsTest.addProject(project1);
        allProjectsTest.addProject(project2);
        assertEquals(2,allProjectsTest.getNumberAllProjects());
        allProjectsTest.addProject(project3);
        allProjectsTest.addProject(project4);
        assertEquals(4,allProjectsTest.getNumberAllProjects());
        assertEquals(project1,allProjectsTest.getProjectArrayList().get(0));
        assertEquals(project2,allProjectsTest.getProjectArrayList().get(1));
        assertEquals(project3,allProjectsTest.getProjectArrayList().get(2));
        assertEquals(project4,allProjectsTest.getProjectArrayList().get(3));



    }

    @Test
    public void testAddSameProject() {
        allProjectsTest.addProject(project1);
        assertEquals(1,allProjectsTest.getNumberAllProjects());
        assertEquals(project1,allProjectsTest.getProjectArrayList().get(0));

        allProjectsTest.addProject(project1);
        assertEquals(2,allProjectsTest.getNumberAllProjects());
        assertEquals(project1,allProjectsTest.getProjectArrayList().get(1));

        allProjectsTest.addProject(project1);
        assertEquals(3,allProjectsTest.getNumberAllProjects());
        assertEquals(project1,allProjectsTest.getProjectArrayList().get(2));

    }

    @Test
    public void testRemoveProject() {
        allProjectsTest.addProject(project1);
        allProjectsTest.addProject(project2);
        allProjectsTest.addProject(project3);
        allProjectsTest.addProject(project4);

        allProjectsTest.removeProject(project1);
        assertEquals(3,allProjectsTest.getNumberAllProjects());
        assertFalse(allProjectsTest.getProjectArrayList().contains(project1));

    }

    @Test
    public void testRemoveMultipleProjects() {
        allProjectsTest.addProject(project1);
        allProjectsTest.addProject(project2);
        allProjectsTest.addProject(project3);
        allProjectsTest.addProject(project4);

        allProjectsTest.removeProject(project1);
        assertEquals(3,allProjectsTest.getNumberAllProjects());
        assertFalse(allProjectsTest.getProjectArrayList().contains(project1));

        allProjectsTest.removeProject(project2);
        assertEquals(2,allProjectsTest.getNumberAllProjects());
        assertFalse(allProjectsTest.getProjectArrayList().contains(project2));

        allProjectsTest.removeProject(project3);
        assertEquals(1,allProjectsTest.getNumberAllProjects());
        assertFalse(allProjectsTest.getProjectArrayList().contains(project3));

        allProjectsTest.removeProject(project4);
        assertEquals(0,allProjectsTest.getNumberAllProjects());
        assertFalse(allProjectsTest.getProjectArrayList().contains(project4));
    }

    @Test
    public void testRemoveNonExistent() {
        allProjectsTest.addProject(project1);
        allProjectsTest.addProject(project2);
        allProjectsTest.removeProject(project3);
        allProjectsTest.removeProject(project4);
        assertEquals(2,allProjectsTest.getProjectArrayList().size());

    }

    @Test
    public void testEventLogForAllProjects() {
        allProjectsTest.addProject(project1);
        allProjectsTest.addProject(project2);
        allProjectsTest.addProject(project3);
        allProjectsTest.addProject(project4);

        allProjectsTest.removeProject(project1);
        allProjectsTest.removeProject(project2);

        List<String> eventList = new ArrayList<>();
        for (Event e : EventLog.getInstance()) {
            eventList.add(e.getDescription());

        }

        assertEquals("Event log cleared.", eventList.get(0));

        assertEquals(("Added project: " + project1.getName()
                + " to all projects"), eventList.get(1));
        assertEquals(("Added project: " + project2.getName()
                + " to all projects"), eventList.get(2));
        assertEquals(("Added project: " + project3.getName()
                + " to all projects"), eventList.get(3));
        assertEquals(("Added project: " + project4.getName()
                + " to all projects"), eventList.get(4));

        assertEquals(("Removed project: " + project1.getName()
                + " from all projects"), eventList.get(5));
        assertEquals(("Removed project: " + project2.getName()
                + " from all projects"), eventList.get(6));

        assertEquals(7,eventList.size());

    }


}