package net.albertogarrido.tfexercise.data.api.responsemodels;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class Duration {

    public static Duration create(String text, Long value) {
        return new AutoValue_Duration(text, value);
    }

    public static TypeAdapter<Duration> typeAdapter(Gson gson) {
        return new AutoValue_Duration.GsonTypeAdapter(gson);
    }

    // Human readable, but not very precise. For example 4 seconds became 1 min. 
    // Ideally we would use only our own transformation
    public abstract String text();

    // real value in seconds
    public abstract Long value();
}
