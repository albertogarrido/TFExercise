package net.albertogarrido.tfexercise.data.db;

import com.orm.SugarRecord;

public class SearchHistory extends SugarRecord {

    private long timestamp;
    private String origin; // cant use from as it is reserved word
    private String destination; // cant use to as it is reserved word
    private String duration;
    private String distance;
    private String routeJson;

    public SearchHistory() {
        // do not delete, needed for sugarRecord
    }

    public SearchHistory(long timestamp, String origin, String destination, String duration, String distance, String routeJson) {
        this.timestamp = timestamp;
        this.origin = origin;
        this.destination = destination;
        this.duration = duration;
        this.distance = distance;
        this.routeJson = routeJson;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getRouteJson() {
        return routeJson;
    }

    public void setRouteJson(String routeJson) {
        this.routeJson = routeJson;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
