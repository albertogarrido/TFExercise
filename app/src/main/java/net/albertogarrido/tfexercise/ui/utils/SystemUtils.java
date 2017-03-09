package net.albertogarrido.tfexercise.ui.utils;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

public final class SystemUtils {
    private SystemUtils() {
        throw new AssertionError("No instances allowed for " + SystemUtils.class.getSimpleName());
    }

    public static void closeKeyboard(Activity activity) {
        InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
