package de.ibapp.ghostnet.moduls;

public class GhostNetModul {

    private int id;
    private String lat;
    private String lon;
    private int size;
    private String editor;
    private String reporter;
    private String number;
    private int status;

    public GhostNetModul() {
    }

    public GhostNetModul(String lat, String lon, int size, String editor, String reporter, int status) {
        this.lat = lat;
        this.lon = lon;
        this.size = size;
        this.editor = editor;
        this.reporter = reporter;
        this.status = status;
    }

    public GhostNetModul(int id, String lat, String lon, int size, String editor, String reporter, int status) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.size = size;
        this.editor = editor;
        this.reporter = reporter;
        this.status = status;
    }

    public GhostNetModul(GhostNetModul gnm) {
        this.id = gnm.getId();
        this.lat = gnm.getLat();
        this.lon = gnm.getLon();
        this.size = gnm.getSize();
        this.editor = gnm.getEditor();
        this.reporter = gnm.getReporter();


    }

    public int getId() {
        return id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
