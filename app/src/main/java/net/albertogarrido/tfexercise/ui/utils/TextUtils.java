package net.albertogarrido.tfexercise.ui.utils;

import android.text.Html;
import android.text.Spanned;

public class TextUtils {
    private TextUtils() {
        throw new AssertionError("No instances allowed for " + TextUtils.class.getSimpleName());
    }

    public static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }
}
