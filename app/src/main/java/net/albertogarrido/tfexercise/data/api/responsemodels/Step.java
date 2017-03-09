package net.albertogarrido.tfexercise.data.api.responsemodels;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class Step {

    public static Step create(Distance distance, Duration duration, String instruction) {
        return new AutoValue_Step(distance, duration, instruction);
    }

    public static TypeAdapter<Step> typeAdapter(Gson gson) {
        return new AutoValue_Step.GsonTypeAdapter(gson);
    }

    public abstract Distance distance();

    public abstract Duration duration();

    @SerializedName("html_instructions")
    public abstract String instruction();
}
