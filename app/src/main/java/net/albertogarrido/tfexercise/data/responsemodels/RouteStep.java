package net.albertogarrido.tfexercise.data.responsemodels;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class RouteStep {

    public static RouteStep create(Distance distance, Duration duration, String instruction) {
        return new AutoValue_RouteStep(distance, duration, instruction);
    }

    public static TypeAdapter<RouteStep> typeAdapter(Gson gson) {
        return new AutoValue_RouteStep.GsonTypeAdapter(gson);
    }

    public abstract Distance distance();

    public abstract Duration duration();

    @SerializedName("html_instructions")
    public abstract String instruction();
}
