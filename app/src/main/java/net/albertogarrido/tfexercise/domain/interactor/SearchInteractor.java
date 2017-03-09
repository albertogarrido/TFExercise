package net.albertogarrido.tfexercise.domain.interactor;

import android.util.Log;

import net.albertogarrido.tfexercise.data.DirectionsService;
import net.albertogarrido.tfexercise.data.valueclasses.DirectionsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchInteractor {
    public void getDirections(String from, String to) {
        DirectionsService directionsService = DirectionsService.retrofit.create(DirectionsService.class);
        Call<DirectionsResponse> directionsCall = directionsService.getDirections(from, to);
        directionsCall.enqueue(new Callback<DirectionsResponse>() {
            @Override
            public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {
                Log.d("TAG", response.body().status());
            }

            @Override
            public void onFailure(Call<DirectionsResponse> call, Throwable t) {

            }
        });
    }
}
