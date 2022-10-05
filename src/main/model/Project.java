package model;

import java.util.ArrayList;

public class Project {
    // delete or rename this class!
    private String name;
    private String creator;
    private ArrayList<Bug> bugArrayList;

    public Project(String name, String creator) {
        this.name = name;
        this.creator = creator;
        this.bugArrayList = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public String getCreator() {
        return this.creator;
    }

    public void addBug(Bug bug) {
        bugArrayList.add(bug);
    }

    public void removeBug(Bug bug) {
        bugArrayList.remove(bug);

    }

    public ArrayList<Bug> getBugArrayList() {
        return bugArrayList;
    }


}

