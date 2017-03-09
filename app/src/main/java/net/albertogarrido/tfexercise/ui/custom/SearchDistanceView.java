package net.albertogarrido.tfexercise.ui.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.albertogarrido.tfexercise.R;
import net.albertogarrido.tfexercise.ui.viewmodel.SearchViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchDistanceView extends LinearLayout {

    @BindView(R.id.isv_from)
    InputSuggestionsView inputSuggestionsViewFrom;
    @BindView(R.id.isv_to)
    InputSuggestionsView inputSuggestionsViewTo;
    @BindView(R.id.btn_perform_search)
    Button btnPerformSearch;
    @BindView(R.id.tv_loading_indicator)
    TextView loadingIndicator;

    public SearchDistanceView(Context context) {
        super(context);
        init(context);
    }

    public SearchDistanceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SearchDistanceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SearchDistanceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.cv_search_distance, this, true);
        setOrientation(VERTICAL);
        ButterKnife.bind(this, view);

        inputSuggestionsViewFrom.setHint(context.getResources().getString(R.string.hint_et_from));
        inputSuggestionsViewTo.setHint(context.getResources().getString(R.string.hint_et_to));
    }

    public void addOnSearchButtonListener(OnClickListener listener) {
        btnPerformSearch.setOnClickListener(listener);
    }

    public SearchViewModel getSearchTerms() {

        if (inputSuggestionsViewFrom.validate() & inputSuggestionsViewTo.validate()) {
            return SearchViewModel.create(
                    inputSuggestionsViewFrom.getSearchTerm(),
                    inputSuggestionsViewTo.getSearchTerm()
            );
        } else {
            return SearchViewModel.createEmpty();
        }
    }

    public void showHideLoadingIndicator(boolean shouldShow) {
        if (shouldShow) {
            loadingIndicator.setVisibility(VISIBLE);
        } else {
            loadingIndicator.setVisibility(GONE);
        }
    }

    public void addOnSearchInputTextChangedListener(SearchInputTextChangedListener searchInputTextChangedListener) {
        inputSuggestionsViewFrom.addOnSearchInputTextChangedListener(searchInputTextChangedListener);
        inputSuggestionsViewTo.addOnSearchInputTextChangedListener(searchInputTextChangedListener);
    }

    public void showSuggestions(int viewId, List<String> suggestions) {
        if (viewId == inputSuggestionsViewFrom.getId()) {
            inputSuggestionsViewFrom.populateSuggestions(suggestions);
        } else if (viewId == inputSuggestionsViewTo.getId()) {
            inputSuggestionsViewTo.populateSuggestions(suggestions);

        }
    }
}
