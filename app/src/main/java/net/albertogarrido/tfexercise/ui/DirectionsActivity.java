package net.albertogarrido.tfexercise.ui;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import net.albertogarrido.tfexercise.R;
import net.albertogarrido.tfexercise.ui.custom.InputSuggestionsView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static net.albertogarrido.tfexercise.ui.utils.AnimationUtils.collapseUp;
import static net.albertogarrido.tfexercise.ui.utils.AnimationUtils.expandDown;

public class DirectionsActivity extends AppCompatActivity {

    @BindView(R.id.search_layout)
    LinearLayout searchLayout;
    @BindView(R.id.isv_from)
    InputSuggestionsView inputSuggestionsViewFrom;
    @BindView(R.id.isv_to)
    InputSuggestionsView inputSuggestionsViewTo;

    private boolean isSearchExpanded = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions);
        ButterKnife.bind(this);
        Resources res = getResources();
        inputSuggestionsViewFrom.setHint(res.getString(R.string.hint_et_from));
        inputSuggestionsViewTo.setHint(res.getString(R.string.hint_et_to));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_distance_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_item_search) {
            toggleSearchLayout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void toggleSearchLayout() {
        if (isSearchExpanded) {
            collapseUp(searchLayout);
        } else {
            expandDown(searchLayout);
        }
        isSearchExpanded = !isSearchExpanded;
    }
}
