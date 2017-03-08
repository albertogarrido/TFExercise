package net.albertogarrido.tfexercise.ui.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import net.albertogarrido.tfexercise.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchDistanceView extends LinearLayout {

    @BindView(R.id.isv_from)
    InputSuggestionsView inputSuggestionsViewFrom;
    @BindView(R.id.isv_to)
    InputSuggestionsView inputSuggestionsViewTo;

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

    public SearchDistanceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.cv_search_distance, this, true);
        ButterKnife.bind(view);
        inputSuggestionsViewFrom.setHint(context.getResources().getString(R.string.hint_et_from));
        inputSuggestionsViewTo.setHint(context.getResources().getString(R.string.hint_et_to));
    }
}
