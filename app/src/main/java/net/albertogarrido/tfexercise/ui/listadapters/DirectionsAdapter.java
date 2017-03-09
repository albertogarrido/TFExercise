package net.albertogarrido.tfexercise.ui.listadapters;

import android.os.Build;
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

public class DirectionsAdapter extends RecyclerView.Adapter<DirectionsAdapter.DirectionsViewHolder> {

    private List<String> directionsList;

    public DirectionsAdapter(List<String> directionsList) {
        this.directionsList = directionsList;
    }

    @Override
    public int getItemCount() {
        return directionsList.size();
    }

    @Override
    public void onBindViewHolder(DirectionsViewHolder holder, int position) {
        String direction = directionsList.get(position);
        holder.directionInstruction.setText(TextUtils.fromHtml(direction));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((position + 1) % 2 == 0) {
                holder.directionInstruction.setBackgroundColor(holder.directionInstruction.getContext().getColor(R.color.colorPrimaryLight));
            } else {
                holder.directionInstruction.setBackgroundColor(holder.directionInstruction.getContext().getColor(R.color.white));
            }
        }
    }

    @Override
    public DirectionsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final View itemView;
        itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_directions, viewGroup, false);
        return new DirectionsViewHolder(itemView);
    }

    public static class DirectionsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.direction_instruction)
        protected TextView directionInstruction;


        public DirectionsViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

