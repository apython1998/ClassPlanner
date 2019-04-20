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

    public Transcript() {
        courseList = new ArrayList<>();
    }

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

    public String toString() {
        String result = "";
        result += courseList.get(0).toString();
        for (int i = 1; i < courseList.size(); i++) {
            result += "\n" + courseList.get(i).toString();
        }
        return result;
    }

    public boolean addEntry(Course course, String grade, boolean inProgress, boolean courseComplete){
        TranscriptEntry newEntry = new TranscriptEntry(course, grade, inProgress, courseComplete);
        return courseList.add(newEntry);
    }

    public List<TranscriptEntry> getCourseList() {
        return courseList;
    }
}
