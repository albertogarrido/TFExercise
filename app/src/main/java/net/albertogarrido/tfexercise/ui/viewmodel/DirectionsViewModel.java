package net.albertogarrido.tfexercise.ui.viewmodel;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

import net.albertogarrido.tfexercise.data.responsemodels.DirectionsResponse;
import net.albertogarrido.tfexercise.data.responsemodels.Leg;
import net.albertogarrido.tfexercise.data.responsemodels.Route;
import net.albertogarrido.tfexercise.data.responsemodels.RouteStep;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@AutoValue
public abstract class DirectionsViewModel {

    private static DirectionsViewModel create(String totalDistance, String totalDuration, List<String> stepsViewModel) {
        return new AutoValue_DirectionsViewModel(totalDistance, totalDuration, stepsViewModel, false);
    }

    public static DirectionsViewModel createFrom(DirectionsResponse directionsResponse) {
        DirectionsViewModel returnValue;
        if (directionsResponse.routes().size() > 0) {
            // we get only the first route in this case, it is the fastest
            Route route = directionsResponse.routes().get(0);
            // there should be only 1 since no waypoints are specified
            if (route.legs().size() > 0) {
                Leg leg = route.legs().get(0);
                String totalDistance = BigDecimal.valueOf(leg.distance().value() / 1000, 2).setScale(2, RoundingMode.CEILING) + " km";
                String totalDuration = leg.duration().text();
                List<String> stepsViewModel = new ArrayList<>(leg.steps().size());
                for (RouteStep step : leg.steps()) {
                    stepsViewModel.add(
                            step.instruction() + " (" +
                                    BigDecimal.valueOf(step.distance().value() / 1000, 2).setScale(2, RoundingMode.CEILING) + " km, " +
                                    step.duration().text() + ")"
                    );
                }
                returnValue = DirectionsViewModel.create(totalDistance, totalDuration, stepsViewModel);
            } else {
                returnValue = DirectionsViewModel.createEmpty(true);
            }
        } else {
            returnValue = DirectionsViewModel.createEmpty(true);
        }
        return returnValue;
    }

    private static DirectionsViewModel createEmpty(boolean isEmpty) {
        return new AutoValue_DirectionsViewModel(null, null, null, isEmpty);
    }


    @Nullable
    public abstract String totalDistance();

    @Nullable
    public abstract String totalDuration();

    @Nullable
    public abstract List<String> steps();

    public abstract boolean empty();
}
