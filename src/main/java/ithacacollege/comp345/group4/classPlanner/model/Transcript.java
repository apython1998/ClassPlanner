package ithacacollege.comp345.group4.classPlanner.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Transcript {
    private List<TranscriptEntry> courseList;

    /**
     * default constructor. just creates empty transcript entry list
     */
    public Transcript() {
        courseList = new ArrayList<>();
    }


    /**
     * takes a json filename with a list of transcript entries and creates a new transcript entry.
     * @throws FileNotFoundException if path is invalid
     */
    public Transcript(String filename) throws FileNotFoundException {
        courseList = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(filename)) {
            Object obj = jsonParser.parse(reader);
            JSONArray transcript = (JSONArray) obj;
            for (Object o : transcript) {
                courseList.add(TranscriptEntry.parseEntry((JSONObject) o));
            }
        } catch (Exception e) {
            throw new FileNotFoundException("Could not find file specified");
        }
    }

    /**
     * @return strings for each course with completion status, grades, and course name
     */
    public String toString() {
        String result = "";
        result += courseList.get(0).toString();
        for (int i = 1; i < courseList.size(); i++) {
            result += "\n" + courseList.get(i).toString();
        }
        return result;
    }

    /**
     * @param course course objects
     * @param grade single letter
     * @param inProgress true if currently in progress
     * @param courseComplete true if course has been completed
     * @return true if successfully added to transcript
     */
    public boolean addEntry(Course course, String grade, boolean inProgress, boolean courseComplete){
        TranscriptEntry newEntry = new TranscriptEntry(course, grade, inProgress, courseComplete);
        return courseList.add(newEntry);
    }

    /**
     * @return list of all transcript entries
     */
    public List<TranscriptEntry> getCourseList() {
        return courseList;
    }
}
