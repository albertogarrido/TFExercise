package net.albertogarrido.tfexercise.data.responsemodels;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

@AutoValue
public abstract class Route {

    public static Route create(List<Leg> legs) {
        return new AutoValue_Route(legs);
    }

    public static TypeAdapter<Route> typeAdapter(Gson gson) {
        return new AutoValue_Route.GsonTypeAdapter(gson);
    }

    public abstract List<Leg> legs();

}
