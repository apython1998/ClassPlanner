package ithacacollege.comp345.group4.classPlanner;


import ithacacollege.comp345.group4.classPlanner.controller.StudentAPI;
import ithacacollege.comp345.group4.classPlanner.model.Directory;
import ithacacollege.comp345.group4.classPlanner.ui.StudentUI;

public class ClassPlannerMain {

    /**
     * Main to run ClassPlanner
     * @param args
     */
    public static void main(String[] args) {
        Directory directory = new Directory();
        StudentAPI studentAPI = new StudentAPI(directory);
        StudentUI studentUI = new StudentUI(studentAPI);
        studentUI.run();
    }

}
