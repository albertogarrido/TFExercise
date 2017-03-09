package net.albertogarrido.tfexercise.ui;

import net.albertogarrido.tfexercise.domain.interactor.SearchInteractor;
import net.albertogarrido.tfexercise.ui.viewmodel.SearchViewModel;

public class DirectionsPresenter {
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
        searchInteractor.getDirections(searchViewModel.from(), searchViewModel.to());
    }

    interface DirectionsView {

    }
}
