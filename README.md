# Bug Tracker Application 


### What is a ***Bug Tracker***?
This is a bug tracking desktop application that lets users add bugs and errors 
they have encountered in order to keep track of all bugs and information related 
to them all in one place. This application enables developers to log the issues 
they have found along with the severity level, details about the issue, 
the assignee for the issue, and progress made to resolve the issue.
This application also enables developers to keep track of repetitive 
issues and view a history of all previous bugs.

### Who will use a **Bug Tracker**?
This application is directed towards developers who are looking to improve 
their project by keeping track of all the bugs and issues along with 
descriptions of them. Developers are able to organize, prioritize and track 
these issues until they are resolved. 

### Why is this project of interest to me?
As a developer I appreciate software solutions that aim to assist my fellow 
software developers for the purpose of efficiency and productivity in daily 
projects. Streamlining the debugging process of alpha and beta releases of 
software. I intend to create a space for collecting all bugs and issues to 
make it easier for developers to fix the issues. Additionally, this project 
can contribute to my future idea of creating a larger scale
social network for users of this profession.  


Some *features* of this application can include:
- Log new bugs and issues
- View a history of previous bugs 
- Prioritize the tasks by level of severity 
- Assign a specific person to a task 
- Track progress made and issues resolved 
- Log a description of the issue 
- Delete a bug from list of issues 
- Editing the reports of bugs and presenting the updates on bug fixes
- Mark resolved issues 

## User Stories 
- As a user, I want to be able to log bugs I found
- As a user, I want to be able to store a description of the issue 
- As a user, I want to be able to set a level of severity to the bugs
- As a user, I want to be able to view a list of previous logged bugs 
- As a user, I want to be able to have an assignee for each logged bug 
- As a user, I want to be able to delete a bug from my list of bugs 
- As a user, I want to be able to mark an issue that was resolved
- As a user, I want to be able to save all my projects along with its bugs in a file
- As a user, when I start the application, I want to be given the option to load 
my projects along with its bugs from file

# Instructions for Grader
- You can generate the first required event related to adding projects to the all projects panel
    by filling out the information fields and pressing the "Create New Project" button in the "Create New Project" tab
- You can generate the second required event of removing a project from all projects by entering the number of the 
project you want to remove and pressing the "Remove Project" button
- You can select a project by clicking on the project button in the view all projects tab to open a new window 
containing your selected project to view and add bugs to the project
- You can generate the first required event related to adding bugs to the project
    by filling out the information fields and pressing the "Create New Bug" button in the "Create New Bug" tab
- You can generate the second required event of removing a bug from a project by entering the number of the
    bug you want to remove and pressing the "Remove Bug" button
- You can view all projects added by clicking the view all projects tab 
- You can view all bugs added by clicking the view all bugs tab
- You can locate my visual at the top left of the frames
- You can save the state of my application by clicking on the "Save Activity" button
- You can reload the state of my application by clicking on the "Load Activity" button
- You can refresh the state of the application by clicking on the "Refresh" button

### Phase 4: Task 2
Mon Nov 28 14:52:06 PST 2022
Loaded all projects

Mon Nov 28 14:52:06 PST 2022
Added bug: bug 1 to all bugs

Mon Nov 28 14:52:06 PST 2022
Added project: new loaded project to all projects

Mon Nov 28 14:54:55 PST 2022
Added project: new proj to all projects

Mon Nov 28 14:54:57 PST 2022
Added project: new proj to all projects

Mon Nov 28 14:55:02 PST 2022
Added project: remove this to all projects

Mon Nov 28 14:55:11 PST 2022
Removed project: remove this from all projects

Mon Nov 28 14:55:19 PST 2022
Added project: remove this project with bugs to all projects

Mon Nov 28 14:55:28 PST 2022
Added bug: bug 2 to all bugs

Mon Nov 28 14:55:42 PST 2022
Added bug: remove this bug to all bugs

Mon Nov 28 14:55:52 PST 2022
Added bug: fix this bug to all bugs

Mon Nov 28 14:55:56 PST 2022
Fixed bug: fix this bug

Mon Nov 28 14:55:59 PST 2022
Removed bug: remove this bug from all bugs

Mon Nov 28 14:56:19 PST 2022
Added bug: shadis bug to all bugs

Mon Nov 28 14:56:34 PST 2022
Added bug: remove this bug to all bugs

Mon Nov 28 14:56:40 PST 2022
Added bug: fix this to all bugs

Mon Nov 28 14:56:42 PST 2022
Added bug: fix this to all bugs

Mon Nov 28 14:56:46 PST 2022
Fixed bug: fix this

Mon Nov 28 14:56:49 PST 2022
Removed bug: fix this from all bugs

Mon Nov 28 14:56:52 PST 2022
Removed bug: remove this bug from all bugs

Mon Nov 28 14:57:11 PST 2022
Saved all projects


### Phase 4: Task 3
If I had more time to work on this project i would make the following changes to my project to increase cohesion 
and decrease code duplication:
- Make AllProjectsFrame which represents all the projects extend AllFrames as there is a lot of duplication
between the AllFrames class and AllProjects class. This way I would refactor all the duplicate
code between the AllProjectsFrame and Project frame into the abstract class ALlFrames.


- Use the single responsibility principle on the classes AllFrames, AllProjectsFrame and ProjectFrame 
so that every class is centered around one cohesive concept by splitting the classes into two separate classes
I would make a Tabs class in my ui package so that the AllFrames, AllProjectsFrame and ProjectFrame 
classes can only be focused on creating the frames and the tabs would be created in a separate class so that 
each class only has one responsibility.

 
- Make a method that would take a JPanel, and a JButton as arguments, and would create the settings of the button 
and add it to the given panel. This would be done in order to avoid cohesion, and to make the method reusable for other 
panels within the class.




