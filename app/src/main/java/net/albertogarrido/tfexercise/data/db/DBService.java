package net.albertogarrido.tfexercise.data.db;

import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.Date;
import java.util.List;

public class DBService {
    public static SearchHistory findSearchHistory(String from, String to) {
        return Select
                .from(SearchHistory.class)
                .where(Condition.prop("origin").eq(from), Condition.prop("destination").eq(to))
                .first(); //Should be only one
    }

    public static void saveHistory(String from, String to, String duration, String distance, String routeJson) {
        SearchHistory searchHistory = new SearchHistory(new Date().getTime(), from, to, duration, distance, routeJson);
        searchHistory.save();
    }

    public static List<SearchHistory> findAddress(String partialAddress) {
        return Select
                .from(SearchHistory.class)
                .or(Condition.prop("destination").like(partialAddress + "%"), Condition.prop("origin").like(partialAddress + "%"))
                .list();
    }

    public static void updateHistory(SearchHistory history, String duration, String distance, String routeJson) {
        history.setTimestamp(new Date().getTime());
        history.setRouteJson(routeJson);
        history.setDistance(distance);
        history.setDuration(duration);
        history.update();
    }
}
