package net.albertogarrido.tfexercise.ui.custom;

import android.content.Context;
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

    public InputSuggestionsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.cv_input_suggestions, this, true);
        ButterKnife.bind(view);
    }

    public void setHint(String hint) {
        inputLayout.setHint(hint);
    }
}
