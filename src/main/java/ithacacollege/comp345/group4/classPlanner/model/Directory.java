package ithacacollege.comp345.group4.classPlanner.model;

import ithacacollege.comp345.group4.classPlanner.InvalidArgumentException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Directory {

    private Map<String, Major> majorDirectory;
    private Map<String, Student> students;
    private Map<String, Faculty> faculty;
    private Map<String, Course> courseCatalog;
    private Map<String, List<Section>> sectionCatalog;

    public Directory() {
        this.majorDirectory = new HashMap<>();
        this.students = new HashMap<>();
        this.faculty = new HashMap<>();
        this.courseCatalog = new HashMap<>();
        this.sectionCatalog = new HashMap<>();
    }

    public Directory(Map<String, Student> users) {
        this.students = users;
    }

    /**
     * Registers a new user to the directory
     * @param username
     * @param password
     * @return true if registration successful, false if username already exists
     */
    public boolean registerStudent(String username, String password) throws InvalidArgumentException {
        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidArgumentException("Invalid String Used");
        } else if (students.containsKey(username)) {
            return false;
        } else {
            Student newUser = new Student(username, password, null, null);
            students.put(newUser.getUsername(), newUser);
            return true;
        }
    }

    /**
     * Authenticates a student's attempt to login if username and password exist in Student directory
     * @param username String of student's username
     * @param password String of student's attempted password entry
     * @return Student that is logged in if authenticated, else return null
     */
    public Student loginStudent(String username, String password) throws InvalidArgumentException {
        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidArgumentException("Invalid String Used for Username or Password");
        } else {
            Student userAttempt = students.get(username);
            if (userAttempt != null) {
                if (userAttempt.authenticate(password)) {
                    return userAttempt;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
    }

    /**
     * Registers a new faculty member to the directory
     * @param username
     * @param password
     * @return
     * @throws InvalidArgumentException
     */
    public boolean registerFaculty(String username, String password) throws InvalidArgumentException {
        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidArgumentException("Invalid String Used");
        } else if (faculty.containsKey(username)) {
            return false;
        } else {
            Faculty newFaculty = new Faculty(username, password);
            faculty.put(newFaculty.getUsername(), newFaculty);
            return true;
        }
    }

    /**
     * Logs in new faculty member
     * @param username
     * @param password
     * @return
     */
    public Faculty loginFaculty(String username, String password) {
        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidArgumentException("Invalid String Used for Username or Password");
        } else {
            Faculty userAttempt = faculty.get(username);
            if (userAttempt != null) {
                if (userAttempt.authenticate(password)) {
                    return userAttempt;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
    }

    public void uploadMajor(String file){
        JSONParser parser = new JSONParser();
        Major newMajor = new Major();
        try {
            JSONObject majorObject = (JSONObject)parser.parse(new FileReader(file));

            String title = (String) majorObject.get("title");
            newMajor.setTitle(title);

            JSONArray courses = (JSONArray) majorObject.get("courses");
            Iterator<String> courseitr = courses.iterator();
            while (courseitr.hasNext()) {
                String courseTitle = courseitr.next();
                Course newCourse = new Course();
                newCourse.setCourseNum(courseTitle);
                newMajor.addCourse(newCourse);
            }

            JSONArray grouping = (JSONArray) majorObject.get("grouping");
            Iterator<JSONArray> groupingItr = grouping.iterator();
            while(groupingItr.hasNext()){
                JSONArray chooseOne = (JSONArray) groupingItr.next();
                Iterator<String> chooseCourseItr = chooseOne.iterator();
                List<Course> chooseCourseList = new ArrayList<>();
                while(chooseCourseItr.hasNext()) {
                    Course c = new Course();
                    c.setCourseNum(chooseCourseItr.next());
                    chooseCourseList.add(c);
                }
                newMajor.addChooseOne(chooseCourseList);
            }
        }
        catch(IOException e){e.printStackTrace();}
        catch(ParseException e){e.printStackTrace();}
        majorDirectory.put(newMajor.getTitle(), newMajor);
    }

    public List<Course> viewCurrentCourses(String name){
        if (!students.containsKey(name)){
            throw new InvalidArgumentException("There is no account associated with that name");
        }
        User student = students.get(name);
        return ((Student) student).getCurrentCourses();
    }

    public List<Course> viewTakenCourses(String name){
        if (!students.containsKey(name)){
            throw new InvalidArgumentException("There is no account associated with that name");
        }
        User student = students.get(name);
        return ((Student) student).getTakenCourses();
    }

    public List<Course> viewPlannedCourses(String name){
        if (!students.containsKey(name)){
            throw new InvalidArgumentException("There is no account associated with that name");
        }
        User student = students.get(name);
        return ((Student) student).getPlannedCourses();
    }

    public boolean addCurrentCourse(String name, Course course){
        if (!students.containsKey(name)){
            throw new InvalidArgumentException("There is no account associated with that name");
        }
        User student = students.get(name);
        return ((Student) student).addCurrentCourses(course);
    }

    public boolean addPastCourse(String name, Course course){
        if (!students.containsKey(name)){
            throw new InvalidArgumentException("There is no account associated with that name");
        }
        User student = students.get(name);
        return ((Student) student).addTakenCourses(course);
    }

    public boolean addFutureCourse(String name, Course course){
        if (!students.containsKey(name)){
            throw new InvalidArgumentException("There is no account associated with that name");
        }
        User student = students.get(name);
        return ((Student) student).addPlannedCourses(course);
    }

    public void addToSectionCatalog(String name, String courseNum, int number, String classTimes, String crn, String year) {
        if (!sectionCatalog.containsKey(name)) {
            Course course = courseCatalog.get(courseNum);
            Section sectionToAdd = new Section(course, number, crn, year, classTimes);
        }
    }

    /**
     * Algorithm for generating a course plan for College based on a major:
     * Get the student, and then their major. Add the course requirements from that major to a list
     * of Courses, courseReqs. Add all of the prerequisites for the courses in courseReqs. Once accumulated
     * all courses required for a major, remove any courses the student has taken or is current enrolled in.
     * Disperse the remaining courses for each semester until there are no more courses to be planned.
     * @param studentID - student id of the student requesting a course plan
     * @param semester - the current semester of the student
     * @param year - the current year
     * @param creditsPerSemester - the amount of credits a student would like to take per semester
     * @return - hashmap of Semester & Year to a list of courses.
     *           For example: key- Fall2019 would return a list of courses the student should take Fall 2019
     */
    public HashMap<String, List<Course>> genCoursePlan(String studentID, Semester semester, int year, int creditsPerSemester){
        Student student = students.get(studentID);
        Major major = student.getMajor();
        List<Course> courseReqs = new ArrayList<>(major.getRequirements());
        List<List<Course>> chooseOnes = major.getChooseOnes();
        addPreReqs(courseReqs); //gets all prerequisites for all course requirements
        addCourses(courseReqs, student.getPlannedCourses());
        student.clearPlannedCourses();
        List<Course> chooseOnesToAdd = addChooseOnes(chooseOnes);
        addCourses(courseReqs, chooseOnesToAdd);
        removeCourseReqs(courseReqs, student.getTakenCourses()); // remove requirements already completed
        removeCourseReqs(courseReqs, student.getCurrentCourses()); // remove requirements currently being completed
        Collections.sort(courseReqs); // sort courses lower lever -> higher level
        HashMap<String, List<Course>> plan = new HashMap<>();
        Semester nextSemester = getNextSemester(semester);
        int currentYear = year;
        while(!courseReqs.isEmpty()){
            planSemester(nextSemester, currentYear, plan, courseReqs, creditsPerSemester, student); // plan for next semester
            nextSemester = getNextSemester(nextSemester); // forward the semester
            if (nextSemester == Semester.Spring){
                currentYear++; // increment year if changed from fall to spring
            }
        }
        return plan;
    }

    public List<Course> addChooseOnes(List<List<Course>> chooseOnes){
        List<Course> returnCourses = new ArrayList<>();
        System.out.println("There are " + chooseOnes.size() + " sets of courses that are your choice.");
        Scanner in = new Scanner(System.in);
        for (List<Course> chooseOne : chooseOnes) {
            System.out.println("\tSelect one of the Following:");
            for (int i = 0; i < chooseOne.size(); i++){
                System.out.println("\t\t" + (i + 1) + ". " +  chooseOne.get(i).toString());
            }
            System.out.print("Selection: ");
            int choiceIdx = in.nextInt() - 1;
            returnCourses.add(chooseOne.get(choiceIdx));
            System.out.println();
        }
        return returnCourses;
    }

    private void addCourses(List<Course> courses, List<Course> plannedCourses){
        for (Course course: plannedCourses){
            if (!courses.contains(course)){
                courses.add(course);
            }
        }
    }

    public Schedule genSchedule(String studentID) {
        HashMap<String, List<Course>> plan = genCoursePlan(studentID, Semester.Spring, 2019, 12);
        return new Schedule(sectionCatalog, plan.get("Fall2019"));
    }

    public static String scheduleToStr(HashMap<String, List<Course>> plan){
        String toReturn = "";
        Set<String> semesters = plan.keySet();
        double totalCredits = 0;
        for (String semester : semesters){
            toReturn += semester + ": ";
            List<Course> courses = plan.get(semester);
            double credits = 0;
            for (int i = 0; i < courses.size() - 1; i++){
                toReturn += courses.get(i).getCourseNum() + ", ";
                credits += courses.get(i).getCredits();
            }
            if (courses.size() > 0) {
                credits += courses.get(courses.size() - 1).getCredits();
                toReturn += courses.get(courses.size() - 1).getCourseNum() +
                        ". Credits: " + credits + "\n";
            } else {
                toReturn += "\n";
            }
            totalCredits += credits;
        }
        return toReturn + "Total Credits: " + totalCredits + "\n";
    }


    /**
     * Returns the semester following the passed semester
     * @param semester - current semester
     * @return - next semester
     */
    private Semester getNextSemester(Semester semester){
        if (semester == Semester.Fall){
            return Semester.Spring;
        } else {
            return Semester.Fall;
        }
    }

    /**
     * Algorithm for planning a semesters schedule // Assumes courseReq is sorted
     * take courses from courseReqs and check if the course is offered during the given
     * semester, and if adding that course will pass the specified restriction
     * If the course is offered, and won't pass the restriction, add it to an arraylist of
     * courses and remove it from the list of requirements. continue until you have added all of
     * the requirements or reached maximum credits.
     * @param semester - semester to be planned
     * @param year - year of semester being planned
     * @param plan - hashmap that will store the list of courses for the associated semester/year
     * @param courseReq - list of course requirements
     * @param creditsPer - maximum credits the student expects to have per semester
     * @return - number of credits for that semester, double
     */
    private double planSemester(Semester semester, int year, HashMap<String, List<Course>> plan,List<Course> courseReq, int creditsPer, Student student){
        double credits = 0;
        String semesterYear = semester + "" + year;
        List<Course> coursePlan = new ArrayList<>();
        List<Course> requirements = new ArrayList<>(courseReq);
        Iterator<Course> requirementItr = requirements.iterator();
        while (requirementItr.hasNext() && credits < creditsPer){
            Course course = requirementItr.next();
            if (offeredThisSemester(semester, course) && satisfyPreReq(course, student)) {
                if (credits + course.getCredits() <= creditsPer) {
                    coursePlan.add(course);
                    courseReq.remove(course);
                    credits += course.getCredits();
                }
            }
        }
        for (Course course : coursePlan){
            student.addPlannedCourses(course);
        }
        plan.put(semesterYear, coursePlan);
        return credits;
    }

    /**
     * Checks if a student has fulfilled all of the prerequisites for a course. Checks against
     * all past, current, or planned courses because in order to plan a upper level 300 course
     * 3 semesters away, you have to check if they have planned on completing a prerequisite earlier.
     * @param course - course to be checked
     * @param student - student checking for course allowance
     * @return - boolean true if they have satisfied the prerequisites
     */
    public boolean satisfyPreReq(Course course, Student student){
        List<String> preReqs = course.getprereqs();
        for (String preReqStr : preReqs){
            Course preReq = courseCatalog.get(preReqStr);
            if (!student.getTakenCourses().contains(preReq)){
                if (!student.getCurrentCourses().contains(preReq)){
                    if (!student.getPlannedCourses().contains(preReq)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Checks if a course is offered during a given semester
     * @param semester - semester to check
     * @param course - course to check
     * @return - boolean true if course is offered that semester, false otherwise
     */
    private boolean offeredThisSemester(Semester semester, Course course){
        List<SemestersOffered> offered = course.getSemestersOffered();
        if(offered.isEmpty())
            throw new IllegalStateException("Course object is missing SemestersOffered data.");
        for (int i = 0; i < offered.size(); i++) {
            SemestersOffered sem = offered.get(i);
            if (semester.equals(convertSemesterOfferedtoSemester(sem))){
                return true;
            }
        }
        return false;
    }

    /**
     * converts a SemesterOffered enumerator into a Semester enumerator
     * @param so - SemesterOffered value
     * @return Semester equivalent to the SemesterOffered value
     */
    private Semester convertSemesterOfferedtoSemester(SemestersOffered so){
        if (so == SemestersOffered.S){
            return Semester.Spring;
        } else if (so == SemestersOffered.F){
            return Semester.Fall;
        } else if (so == SemestersOffered.SUM){
            return Semester.Summer;
        } else {
            return Semester.Winter;
        }
    }

    /**
     * Goes through a list of courses, and adds all of each courses prerequisites to the list
     * This results in a list of all courses required for a major
     * @param courseReq
     */
    private void addPreReqs(List<Course> courseReq){
        List<Course> courses = new ArrayList<>(courseReq);
        for (Course course: courses){
            addPreReqCourses(course, courseReq);
        }
    }

    /**
     * Adds all of a courses prerequisites - and the prerequisites prerequisites - to a list of courses
     * @param course - course to add prereqs of
     * @param courseReq - list to store the prereqs
     */
    private void addPreReqCourses(Course course, List<Course> courseReq){
        List<String> prereqs = course.getprereqs();
        if (prereqs.size() == 0){
            if (courseReq.contains(course)){
                return;
            }
            courseReq.add(course);
        }
        for (int i = 0; i < prereqs.size(); i++) {
            Course preReq = courseCatalog.get(prereqs.get(i));
            addPreReqCourses(preReq, courseReq);
            if (!courseReq.contains(preReq)){
                courseReq.add(preReq);
            }
        }
    }

    /**
     * removes courses from reqs that are in the list courses
     * @param reqs - arraylist of Courses required for a major
     * @param courses - arraylist of Courses to be removed
     */
    private void removeCourseReqs(List<Course> reqs, List<Course> courses) {
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            if (reqs.contains(course)) {
                reqs.remove(course);
            }
        }
    }

    /**************************** GETTERS AND SETTERS     ****************************/
    public Map<String, Student> getStudents() {
        return students;
    }
    public Map<String, Major> getMajorDirectory() { return majorDirectory; }

    public Map<String, Course> getCourseCatalog() {
        return courseCatalog;
    }

    public void setCourseCatalog(Map<String, Course> courseCatalog) {
        this.courseCatalog = courseCatalog;
    }

    public void setStudents(Map<String, Student> users) {
        this.students = users;
    }

    public Map<String, List<Section>> getSectionCatalog() {
        return sectionCatalog;
    }

    public void setSectionCatalog(Map<String, List<Section>> sectionCatalog) {
        this.sectionCatalog = sectionCatalog;
    }

    public void setMajorDirectory(Map<String, Major> majorDirectory) {
        this.majorDirectory = majorDirectory;
    }

    public Map<String, Faculty> getFaculty() {
        return faculty;
    }

    public void setFaculty(Map<String, Faculty> faculty) {
        this.faculty = faculty;
    }

    public void addSection(String courseNum, int id, String classTimes) {

    }
}
