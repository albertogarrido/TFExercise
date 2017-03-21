package net.albertogarrido.tfexercise.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.albertogarrido.tfexercise.R;
import net.albertogarrido.tfexercise.ui.custom.SearchDistanceView;
import net.albertogarrido.tfexercise.ui.custom.SearchInputTextChangedListener;
import net.albertogarrido.tfexercise.ui.listadapters.DirectionsAdapter;
import net.albertogarrido.tfexercise.ui.utils.SystemUtils;
import net.albertogarrido.tfexercise.ui.viewmodel.SearchViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static net.albertogarrido.tfexercise.ui.utils.AnimationUtils.collapseUp;
import static net.albertogarrido.tfexercise.ui.utils.AnimationUtils.expandDown;

public class DirectionsActivity extends AppCompatActivity implements DirectionsPresenter.DirectionsView, SearchInputTextChangedListener {

    @BindView(R.id.search_distance_view)
    SearchDistanceView searchDistanceView;

    @BindView(R.id.instructions_list)
    RecyclerView instructionsListRecycler;

    @BindView(R.id.tv_distance)
    TextView summaryDistance;

    @BindView(R.id.tv_duration)
    TextView summaryDuration;

    @BindView(R.id.error_msg)
    TextView errorView;

    @BindView(R.id.layout_results)
    ViewGroup layoutResults;

    private boolean isSearchExpanded = true;
    private DirectionsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions);
        ButterKnife.bind(this);

        presenter = new DirectionsPresenter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        searchDistanceView.addOnSearchButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initRecycler();
                SearchViewModel searchViewModel = searchDistanceView.getSearchTerms();
                if (searchViewModel.valid()) {
                    presenter.performSearch(searchViewModel);
                }
            }
        });
        searchDistanceView.addOnSearchInputTextChangedListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_distance_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void populateInstructions(List<String> instructions) {
        DirectionsAdapter directionsAdapter = new DirectionsAdapter(instructions);
        instructionsListRecycler.setAdapter(directionsAdapter);
        directionsAdapter.notifyDataSetChanged();
    }

    @Override
    public void populateSummary(String distance, String duration) {
        SystemUtils.closeKeyboard(this);
        Resources res = getResources();

        summaryDistance.setText(res.getString(R.string.prompt_distance, distance));
        summaryDuration.setText(res.getString(R.string.prompt_duration, duration));
    }

    @Override
    public void displayError(@Nullable String error, boolean shouldShow) {
        if (shouldShow) {
            errorView.setVisibility(View.VISIBLE);
            if (error != null) {
                errorView.setText(error);
            }
        } else {
            errorView.setVisibility(View.GONE);
        }
    }

    @Override
    public void displayResultsLayout(boolean shouldShow) {
        layoutResults.setVisibility(shouldShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void shouldShowLoadingIndicator(boolean shouldShow) {
        searchDistanceView.showHideLoadingIndicator(shouldShow);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_item_search) {
            toggleSearchLayout();
        }
        if(item.getItemId() == R.id.menu_react) {
            startActivity(new Intent(this, MyReactActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void toggleSearchLayout() {
        if (isSearchExpanded) {
            collapseUp(searchDistanceView);
        } else {
            expandDown(searchDistanceView);
        }
        isSearchExpanded = !isSearchExpanded;
    }

    private void initRecycler() {
        instructionsListRecycler.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        instructionsListRecycler.setLayoutManager(layoutManager);

    }

    @Override
    public void onTextChanged(int viewId, String text) {
        presenter.addressLiveSearch(viewId, text);
    }

    @Override
    public void displaySuggestions(int viewId, List<String> suggestedAddresses) {
        searchDistanceView.showSuggestions(viewId, suggestedAddresses);
    }
}
