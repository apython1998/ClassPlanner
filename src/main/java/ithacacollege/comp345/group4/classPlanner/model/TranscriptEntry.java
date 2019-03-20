package ithacacollege.comp345.group4.classPlanner.model;

public class TranscriptEntry {
    private Course course;
    private String grade;
    private boolean inProgress;
    private boolean courseComplete;

    public TranscriptEntry() {
        course = null;
        grade = "";
        inProgress = false;
        courseComplete = false;
    }

    public TranscriptEntry(Course courseIn, String gradeIn, boolean inProgressIn, boolean courseCompleteIn) {
        course = courseIn;
        grade = gradeIn;
        inProgress = inProgressIn;
        courseComplete = courseCompleteIn;
    }

    public TranscriptEntry(String filename) {

    }

    public String toString() {
        if (course == null) {
            return "";
        } else {
            String inProgressString = "";
            String courseCompleteString = "";
            if (inProgress)
                inProgressString = "In Progress";
            if (courseComplete)
                courseCompleteString = "Completed";
            return inProgressString + //
                    courseCompleteString + //
                    "\t" + //
                    course.getCourseDiscAndNum() + //
                    "\t" + //
                    course.getName() + //
                    "\t" + //
                    grade + //
                    "\t" + //
                    course.getCredits() + //
                    "\t" + //
                    course.getSemester();
        }
    }
}
