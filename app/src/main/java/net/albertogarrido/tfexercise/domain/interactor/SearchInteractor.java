package net.albertogarrido.tfexercise.domain.interactor;

import android.support.annotation.Nullable;

import net.albertogarrido.tfexercise.data.api.DirectionsApiService;
import net.albertogarrido.tfexercise.data.api.responsemodels.DirectionsResponse;
import net.albertogarrido.tfexercise.data.db.DBService;
import net.albertogarrido.tfexercise.data.db.SearchHistory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchInteractor {

    public static final long MILLISECONDS_TO_BE_OUTDATED = 5 * 24 * 60 * 60 * 1000; // 5days to be outdated

    public void getDirectionsFromApi(String from, String to, final ResponseListener listener) {
        DirectionsApiService directionsApiService = DirectionsApiService.retrofit.create(DirectionsApiService.class);
        Call<DirectionsResponse> directionsCall = directionsApiService.getDirections(from, to);
        directionsCall.enqueue(new Callback<DirectionsResponse>() {
            @Override
            public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {
                if (response.isSuccessful() && "OK".equals(response.body().status())) {
                    listener.onResponseSuccess(response.body());
                } else {
                    //TODO find proper Directions API status: https://developers.google.com/maps/documentation/directions/intro#StatusCodes
                    listener.onResponseError(response.body().status());
                }
            }

            @Override
            public void onFailure(Call<DirectionsResponse> call, Throwable t) {
                listener.onResponseError(t.getMessage());
                t.printStackTrace();
            }
        });
    }

    public void storeOrUpdateSearch(String from, String to, String duration, String distance, String routeJson) {
        SearchHistory history = DBService.findSearchHistory(from, to);
        if (history == null) {
            DBService.saveHistory(from, to, duration, distance, routeJson);
        } else {
            DBService.updateHistory(history, duration, distance, routeJson);
        }
    }

    @Nullable
    public SearchHistory findSearchHistory(String from, String to) {
        return DBService.findSearchHistory(from, to);
    }

    @Nullable
    public List<String> findAddress(String partialAddress) {
        List<SearchHistory> searchHistories = DBService.findAddress(partialAddress);
        List<String> addresses = new ArrayList<>(searchHistories.size());
        for (SearchHistory history : searchHistories) {
            if (history.getOrigin().toLowerCase().contains(partialAddress.toLowerCase())) {
                if (!addresses.contains(history.getOrigin())) {
                    addresses.add(history.getOrigin());
                }
            } else if (history.getDestination().toLowerCase().contains(partialAddress.toLowerCase())) {
                if (!addresses.contains(history.getDestination())) {
                    addresses.add(history.getDestination());
                }
            }
        }
        return addresses;
    }

    public boolean isSearchHistoryOutDated(long storedTime) {
        long current = new Date().getTime();
        return (current - storedTime) > MILLISECONDS_TO_BE_OUTDATED;
    }
}
