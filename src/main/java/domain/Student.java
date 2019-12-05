package domain;

import json.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {

    private List<Tuple<String, Integer>> exams;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.exams = Arrays.asList(exams);
    }

    public JsonObject toJsonObject() {
        JsonObject jsonObject = super.toJsonObject();
        Json[] examsJsons = new Json[exams.size()];
        for (int i = 0; i < exams.size(); i++) {
            Integer mark = exams.get(i).value;
            Json exam = new JsonObject(
                    new JsonPair("course", new JsonString(exams.get(i).key)),
                    new JsonPair("mark", new JsonNumber(mark)),
                    new JsonPair("passed", new JsonBoolean(mark > 2))
            );
            examsJsons[i] = exam;
        }
        jsonObject.add(new JsonPair("exams", new JsonArray(examsJsons)));
        // ToDo
        return jsonObject;
    }
}
