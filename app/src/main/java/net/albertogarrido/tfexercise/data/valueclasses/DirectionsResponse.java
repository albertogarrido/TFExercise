package net.albertogarrido.tfexercise.data.valueclasses;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

@AutoValue
public abstract class DirectionsResponse {

    public static DirectionsResponse create(String status, List<Route> routes) {
        return new AutoValue_DirectionsResponse(status, routes);
    }
    
    public static TypeAdapter<DirectionsResponse> typeAdapter(Gson gson) {
        return new AutoValue_DirectionsResponse.GsonTypeAdapter(gson);
    }    

    public abstract String status();

    public abstract List<Route> routes();
}
