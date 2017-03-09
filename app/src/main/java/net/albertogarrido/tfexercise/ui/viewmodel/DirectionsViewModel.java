package net.albertogarrido.tfexercise.ui.viewmodel;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

import net.albertogarrido.tfexercise.data.api.responsemodels.DirectionsResponse;
import net.albertogarrido.tfexercise.data.api.responsemodels.Leg;
import net.albertogarrido.tfexercise.data.api.responsemodels.Route;
import net.albertogarrido.tfexercise.data.api.responsemodels.Step;
import net.albertogarrido.tfexercise.data.db.SearchHistory;
import net.albertogarrido.tfexercise.ui.utils.JsonUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@AutoValue
public abstract class DirectionsViewModel {

    private static DirectionsViewModel create(String totalDistance, String totalDuration, List<String> stepsViewModel) {
        return new AutoValue_DirectionsViewModel(totalDistance, totalDuration, stepsViewModel, false);
    }

    @Nullable
    public abstract String totalDistance();

    @Nullable
    public abstract String totalDuration();

    @Nullable
    public abstract List<String> steps();

    public abstract boolean empty();

    public static DirectionsViewModel createFrom(DirectionsResponse directionsResponse) {
        DirectionsViewModel returnValue;
        if (directionsResponse.routes().size() > 0) {
            // we get only the first route in this case, it is the fastest
            Route route = directionsResponse.routes().get(0);
            // there should be only 1 since no waypoints are specified
            if (route.legs().size() > 0) {
                Leg leg = route.legs().get(0);
                String totalDistance = BigDecimal.valueOf((double) leg.distance().value() / 1000).setScale(2, RoundingMode.HALF_UP) + " km";
                String totalDuration = leg.duration().text();
                List<String> stepsViewModel = new ArrayList<>(leg.steps().size());
                for (Step step : leg.steps()) {
                    stepsViewModel.add(
                            step.instruction() + " (" +
                                    BigDecimal.valueOf((double) step.distance().value() / 1000).setScale(2, RoundingMode.HALF_UP) + " km, " +
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


    public static DirectionsViewModel createFrom(SearchHistory searchHistory) {
        return new AutoValue_DirectionsViewModel(searchHistory.getDistance(), searchHistory.getDuration(), JsonUtils.jsonToStringList(searchHistory.getRouteJson()), false);
    }
}
