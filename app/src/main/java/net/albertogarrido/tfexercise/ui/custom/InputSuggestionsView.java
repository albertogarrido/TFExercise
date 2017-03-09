package net.albertogarrido.tfexercise.ui.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import net.albertogarrido.tfexercise.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InputSuggestionsView extends LinearLayout implements TextWatcher, ListObserver {

    @BindView(R.id.isv_input_layout) TextInputLayout inputLayout;
    @BindView(R.id.isv_input) EditText input;
    @BindView(R.id.isv_list) RecyclerView suggestionsList;
    private SearchInputTextChangedListener searchInputTextChangedListener;
    private SuggestionsAdapter suggestionsAdapter;


    public InputSuggestionsView(Context context) {
        super(context);
        init(context);
    }

    public InputSuggestionsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public InputSuggestionsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public InputSuggestionsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.cv_input_suggestions, this, true);
        setOrientation(VERTICAL);
        ButterKnife.bind(this, view);

        input.addTextChangedListener(this);

        suggestionsList.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        suggestionsList.setLayoutManager(layoutManager);
    }

    public void setHint(String hint) {
        inputLayout.setHint(hint);
    }

    public boolean validate() {
        if (input.getText() != null && !"".equals(input.getText().toString())) {
            inputLayout.setErrorEnabled(false);
            inputLayout.setError(null);
            return true;
        } else {
            inputLayout.setErrorEnabled(true);
            inputLayout.setError("Cannot be empty");
            return false;
        }
    }

    public String getSearchTerm() {
        return input.getText().toString();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        // Do nothing
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        // Do nothing
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (searchInputTextChangedListener == null) {
            throw new AssertionError("Listener must be initialized");
        }
        if (input.getText() != null && input.getText().length() > 2) {
            searchInputTextChangedListener.onTextChanged(this.getId(), input.getText().toString());
        }
    }

    public void addOnSearchInputTextChangedListener(SearchInputTextChangedListener searchInputTextChangedListener) {
        this.searchInputTextChangedListener = searchInputTextChangedListener;
    }

    public void populateSuggestions(List<String> suggestions) {
        if (suggestions.size() > 0) {
            suggestionsList.setVisibility(VISIBLE);
            suggestionsAdapter = new SuggestionsAdapter(suggestions, this);
            suggestionsList.setAdapter(suggestionsAdapter);
            suggestionsAdapter.notifyDataSetChanged();
        } else {
            suggestionsList.setVisibility(GONE);
        }
    }


    @Override
    public void onItemSelected(String address) {
        input.setText(address);
        suggestionsList.setVisibility(GONE);
    }
}
