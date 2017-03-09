package net.albertogarrido.tfexercise.ui.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class JsonUtils {

    private JsonUtils() {
        throw new AssertionError("No instances allowed for " + JsonUtils.class.getSimpleName());
    }

    public static String stringListToJson(List<String> steps) {
        return new Gson().toJson(steps);
    }

    public static List<String> jsonToStringList(String routeJson) {
        return new Gson().fromJson(routeJson, new TypeToken<List<String>>() {
        }.getType());
    }
}