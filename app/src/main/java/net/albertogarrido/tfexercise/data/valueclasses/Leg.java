package net.albertogarrido.tfexercise.data.valueclasses;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

@AutoValue
public abstract class Leg {

    public static Leg create(Distance distance, Duration duration, List<RouteStep> steps) {
        return new AutoValue_Leg(distance, duration, steps);
    }

    public static TypeAdapter<Leg> typeAdapter(Gson gson) {
        return new AutoValue_Leg.GsonTypeAdapter(gson);
    }

    public abstract Distance distance();

    public abstract Duration duration();

    public abstract List<RouteStep> steps();
}
