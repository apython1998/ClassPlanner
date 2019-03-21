package ithacacollege.comp345.group4.classPlanner.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Transcript {
    List<TranscriptEntry> courseList;

    public Transcript() {
        courseList = new ArrayList<>();
    }

    public Transcript(String filename) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(filename)) {
            Object obj = jsonParser.parse(filename);
            JSONArray transcript = (JSONArray) obj;
            for (Object o : transcript) {
                courseList.add(TranscriptEntry.parseEntry((JSONObject) o));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        String result = "";
        for (TranscriptEntry c: courseList) {
            result += c.toString() + "\n";
        }
        return result;
    }
}
