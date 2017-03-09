package net.albertogarrido.tfexercise.ui.viewmodel;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class SearchViewModel {

    public static SearchViewModel create(String from, String to) {
        return new AutoValue_SearchViewModel(from, to, true);
    }

    public static SearchViewModel createEmpty() {
        return new AutoValue_SearchViewModel(null, null, false);
    }

    @Nullable
    public abstract String from();

    @Nullable
    public abstract String to();

    public abstract boolean valid();

}
