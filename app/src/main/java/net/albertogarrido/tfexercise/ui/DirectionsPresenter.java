package net.albertogarrido.tfexercise.ui;

import android.util.Log;

import net.albertogarrido.tfexercise.data.api.responsemodels.DirectionsResponse;
import net.albertogarrido.tfexercise.data.db.SearchHistory;
import net.albertogarrido.tfexercise.domain.interactor.ResponseListener;
import net.albertogarrido.tfexercise.domain.interactor.SearchInteractor;
import net.albertogarrido.tfexercise.ui.utils.JsonUtils;
import net.albertogarrido.tfexercise.ui.viewmodel.DirectionsViewModel;
import net.albertogarrido.tfexercise.ui.viewmodel.SearchViewModel;

import java.util.List;

class DirectionsPresenter implements ResponseListener {

    interface DirectionsView {

        void shouldShowLoadingIndicator(boolean shouldShow);

        void populateInstructions(List<String> instructions);

        void populateSummary(String distance, String duration);

        void displayError(String error, boolean shouldShow);

        void displayResultsLayout(boolean shouldShow);

        void toggleSearchLayout();

        void displaySuggestions(int viewId, List<String> suggestedAddresses);
    }

    private DirectionsView directionsView;

    private SearchInteractor searchInteractor;

    private SearchViewModel searchViewModel;

    DirectionsPresenter(DirectionsView directionsView) {
        this.directionsView = directionsView;
        this.searchInteractor = new SearchInteractor();
    }

    void onDestroy() {
        directionsView = null;
    }

    void performSearch(SearchViewModel searchViewModel) {
        this.searchViewModel = searchViewModel;
        directionsView.shouldShowLoadingIndicator(true);
        SearchHistory searchHistory = searchInteractor.findSearchHistory(searchViewModel.from(), searchViewModel.to());
        if (searchHistory != null && !searchInteractor.isSearchHistoryOutDated(searchHistory.getTimestamp())) {
            Log.d(DirectionsPresenter.class.getSimpleName(), "fetch from database");
            onStoredResponse(searchHistory);
        } else {
            Log.d(DirectionsPresenter.class.getSimpleName(), "fetch from api");
            searchInteractor.getDirectionsFromApi(searchViewModel.from(), searchViewModel.to(), this);

        }
    }

    private void onStoredResponse(SearchHistory searchHistory) {
        prepareView();
        DirectionsViewModel directionsViewModel = DirectionsViewModel.createFrom(searchHistory);
        postDataInView(directionsViewModel);
    }


    @Override
    public void onResponseSuccess(DirectionsResponse directionsResponse) {
        prepareView();
        DirectionsViewModel directionsViewModel = DirectionsViewModel.createFrom(directionsResponse);
        postDataInView(directionsViewModel);
    }

    private String stepsToJson(List<String> steps) {
        return JsonUtils.stringListToJson(steps);
    }

    public void addressLiveSearch(int viewId, String text) {
        List<String> suggestedAddresses = searchInteractor.findAddress(text);
        directionsView.displaySuggestions(viewId, suggestedAddresses);
    }

    @Override
    public void onResponseError(String error) {
        directionsView.shouldShowLoadingIndicator(false);
        directionsView.displayError(error, true);
        directionsView.displayResultsLayout(false);
    }

    private void prepareView() {
        directionsView.shouldShowLoadingIndicator(false);
        directionsView.displayError(null, false);
        directionsView.displayResultsLayout(true);
    }

    private void postDataInView(DirectionsViewModel directionsViewModel) {
        searchInteractor.storeOrUpdateSearch(searchViewModel.from(), searchViewModel.to(), directionsViewModel.totalDuration(), directionsViewModel.totalDistance(), stepsToJson(directionsViewModel.steps()));
        directionsView.populateSummary(directionsViewModel.totalDistance(), directionsViewModel.totalDuration());
        directionsView.populateInstructions(directionsViewModel.steps());
        directionsView.toggleSearchLayout();
    }
}
