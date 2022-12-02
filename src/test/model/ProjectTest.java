package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ProjectTest {

    private Project testProject;
    private Bug bug1;
    private Bug bug2;
    private Bug bug3;

    @BeforeEach
    public void runBefore() {
        testProject = new Project("testProject", "testCreator");
        bug1 = new Bug("bug1", "assignee1", "pub1", BugSeverityLevel.LOW);
        bug2 = new Bug("bug2", "assignee2", "pub2", BugSeverityLevel.MEDIUM);
        bug3 = new Bug("bug3", "assignee3", "pub3", BugSeverityLevel.HIGH);
        EventLog.getInstance().clear();
    }

    @Test
    public void testConstructor() {
        assertEquals("testProject", testProject.getName());
        assertEquals("testCreator", testProject.getCreator());
        assertEquals(0, testProject.getSizeBugList());
    }

    @Test
    public void testAddBug() {
        testProject.addBug(bug1);
        assertEquals(bug1,testProject.getBugArrayList().get(0));
        assertEquals(1,testProject.getSizeBugList());
    }

    @Test
    public void testAddBugMultiple() {
        testProject.addBug(bug1);
        assertEquals(bug1,testProject.getBugArrayList().get(0));
        assertEquals(1,testProject.getSizeBugList());

        testProject.addBug(bug2);
        assertEquals(bug2,testProject.getBugArrayList().get(1));
        assertEquals(2,testProject.getSizeBugList());

        testProject.addBug(bug3);
        assertEquals(bug3,testProject.getBugArrayList().get(2));
        assertEquals(3,testProject.getSizeBugList());

    }

    @Test
    public void testRemoveBug() {
        testProject.addBug(bug1);
        testProject.addBug(bug2);
        testProject.addBug(bug3);

        testProject.removeBug(bug1);
        assertEquals(bug2,testProject.getBugArrayList().get(0));
        assertFalse(testProject.getBugArrayList().contains(bug1));
        assertEquals(2,testProject.getSizeBugList());
    }

    @Test
    public void testRemoveBugMultiple() {
        testProject.addBug(bug1);
        testProject.addBug(bug2);
        testProject.addBug(bug3);

        testProject.removeBug(bug1);
        assertEquals(2,testProject.getSizeBugList());
        assertFalse(testProject.getBugArrayList().contains(bug1));

        testProject.removeBug(bug2);
        assertEquals(1,testProject.getSizeBugList());
        assertFalse(testProject.getBugArrayList().contains(bug2));

        testProject.removeBug(bug3);
        assertEquals(0,testProject.getSizeBugList());
        assertFalse(testProject.getBugArrayList().contains(bug3));

    }

    @Test
    public void testEventLogForProject() {

        testProject.addBug(bug1);
        testProject.addBug(bug2);
        testProject.removeBug(bug1);
        testProject.removeBug(bug2);

        List<String> eventList = new ArrayList<>();
        for (Event e : EventLog.getInstance()) {
            eventList.add(e.getDescription());

        }

        assertEquals("Event log cleared.", eventList.get(0));

        assertEquals(("Added bug: " + bug1.getTitle()
                + " to all bugs"), eventList.get(1));
        assertEquals(("Added bug: " + bug2.getTitle()
                + " to all bugs"), eventList.get(2));
        assertEquals(("Removed bug: " + bug1.getTitle()
                + " from all bugs"), eventList.get(3));
        assertEquals(("Removed bug: " + bug2.getTitle()
                + " from all bugs"), eventList.get(4));


        assertEquals(5,eventList.size());

    }
}
