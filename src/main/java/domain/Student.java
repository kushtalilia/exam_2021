package domain;

import json.*;

import java.util.HashMap;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {

    private final HashMap<String, Json> studentInfo = new HashMap<String, Json>();

    @SafeVarargs
    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        studentInfo.put("name", new JsonString(name));
        studentInfo.put("surname", new JsonString(surname));
        studentInfo.put("year", new JsonNumber(year));

        JsonObject[] examsInfo = new JsonObject[exams.length];
        int pos = 0;

        for (Tuple<String, Integer> exam: exams) {
            JsonObject examInfo = new JsonObject();
            examInfo.add(new JsonPair("course", new JsonString(exam.key)));
            examInfo.add(new JsonPair("mark", new JsonNumber(exam.value)));

            if (exam.value < 3) {
                examInfo.add(new JsonPair("passed", new JsonBoolean(false)));
            } else {
                examInfo.add(new JsonPair("passed", new JsonBoolean(true)));
            }

            examsInfo[pos] = examInfo;
            pos++;
        }

        studentInfo.put("exams", new JsonArray(examsInfo));
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObject jsonObject = new JsonObject();

        for (String key: studentInfo.keySet()) {
            jsonObject.add(new JsonPair(key, studentInfo.get(key)));
        }
        return jsonObject;
    }
}