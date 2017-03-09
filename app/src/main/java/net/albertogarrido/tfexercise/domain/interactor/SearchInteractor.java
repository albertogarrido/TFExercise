package net.albertogarrido.tfexercise.domain.interactor;

import android.util.Log;

import net.albertogarrido.tfexercise.data.DirectionsService;
import net.albertogarrido.tfexercise.data.responsemodels.DirectionsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchInteractor {
    public void getDirections(String from, String to, final ResponseListener listener) {
        DirectionsService directionsService = DirectionsService.retrofit.create(DirectionsService.class);
        Call<DirectionsResponse> directionsCall = directionsService.getDirections(from, to);
        directionsCall.enqueue(new Callback<DirectionsResponse>() {
            @Override
            public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {

                if (response.isSuccessful() && "OK".equals(response.body().status())) {
                    listener.onResponseSuccess(response.body());
                } else {
                    //TODO find proper Directions API errors: https://developers.google.com/maps/documentation/directions/intro#StatusCodes
                    listener.onResponseError("Error: " + response.code());
                }

                Log.d("TAG", response.body().status());
            }

            @Override
            public void onFailure(Call<DirectionsResponse> call, Throwable t) {
                listener.onResponseError(t.getMessage());
            }
        });
    }
}
