package net.albertogarrido.tfexercise.ui;

import android.util.Log;

import net.albertogarrido.tfexercise.data.responsemodels.DirectionsResponse;
import net.albertogarrido.tfexercise.domain.interactor.ResponseListener;
import net.albertogarrido.tfexercise.domain.interactor.SearchInteractor;
import net.albertogarrido.tfexercise.ui.viewmodel.DirectionsViewModel;
import net.albertogarrido.tfexercise.ui.viewmodel.SearchViewModel;

public class DirectionsPresenter implements ResponseListener {
    private DirectionsView directionsView;
    private SearchInteractor searchInteractor;

    public DirectionsPresenter(DirectionsView directionsView) {
        this.directionsView = directionsView;
        this.searchInteractor = new SearchInteractor();
    }

    public void onDestroy() {
        directionsView = null;
    }

    public void performSearch(SearchViewModel searchViewModel) {
        searchInteractor.getDirections(searchViewModel.from(), searchViewModel.to(), this);
    }

    @Override
    public void onResponseSuccess(DirectionsResponse directionsResponse) {
        DirectionsViewModel.createFrom(directionsResponse);
        Log.d("TAG", "");
    }

    @Override
    public void onResponseError(String error) {

    }

    interface DirectionsView {

    }
}
