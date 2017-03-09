package net.albertogarrido.tfexercise.data.valueclasses;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class Distance {

    public static Duration create(String text, Long value) {
        return new AutoValue_Duration(text, value);
    }

    public static TypeAdapter<Distance> typeAdapter(Gson gson) {
        return new AutoValue_Distance.GsonTypeAdapter(gson);
    }

    // Human readable, but not very precise. For example 4 seconds became 1 min.
    // Ideally we would use only our own transformation
    public abstract String text();

    // real value in meters (always meters)
    public abstract Long value();
}
