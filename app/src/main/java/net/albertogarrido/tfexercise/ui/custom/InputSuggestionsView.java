package net.albertogarrido.tfexercise.ui.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import net.albertogarrido.tfexercise.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InputSuggestionsView extends LinearLayout {

    @BindView(R.id.isv_input_layout) TextInputLayout inputLayout;
    @BindView(R.id.isv_input) EditText input;
    @BindView(R.id.isv_list) RecyclerView suggestionsList;

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
    }

    public void setHint(String hint) {
        inputLayout.setHint(hint);
    }

    public boolean validate() {
        if(input.getText() != null && !"".equals(input.getText().toString())){
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
}
