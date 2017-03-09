package net.albertogarrido.tfexercise.ui.custom;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.albertogarrido.tfexercise.R;
import net.albertogarrido.tfexercise.ui.utils.TextUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SuggestionsAdapter extends RecyclerView.Adapter<SuggestionsAdapter.SuggestionsViewHolder> {

    private final ListObserver listObserver;
    private List<String> suggestionsList;

    public SuggestionsAdapter(List<String> suggestionsList, ListObserver listObserver) {
        this.suggestionsList = suggestionsList;
        this.listObserver = listObserver;
    }


    @Override
    public int getItemCount() {
        return suggestionsList.size();
    }

    @Override
    public void onBindViewHolder(SuggestionsViewHolder holder, int position) {
        String direction = suggestionsList.get(position);
        holder.suggestionsView.setText(TextUtils.fromHtml(direction));
    }

    @Override
    public SuggestionsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final View itemView;
        itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_suggestions, viewGroup, false);
        return new SuggestionsViewHolder(itemView, new AddressTapListener() {
            @Override
            public void onItemTap(View view, int position) {
                listObserver.onItemSelected(getItemsList().get(position));
            }
        });
    }

    public List<String> getItemsList() {
        return suggestionsList;
    }

    public static class SuggestionsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        AddressTapListener listener;

        @BindView(R.id.direction_suggestions)
        protected TextView suggestionsView;


        public SuggestionsViewHolder(@NonNull View view, AddressTapListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            this.listener = listener;
            suggestionsView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemTap(view, getAdapterPosition());
        }
    }
}

