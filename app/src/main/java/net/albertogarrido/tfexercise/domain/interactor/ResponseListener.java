package net.albertogarrido.tfexercise.domain.interactor;

import net.albertogarrido.tfexercise.data.responsemodels.DirectionsResponse;

public interface ResponseListener {

    void onResponseSuccess(DirectionsResponse directionsResponse);
    void onResponseError(String error);

}
