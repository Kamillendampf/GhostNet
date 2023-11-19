package de.ibapp.ghostnet.bean;

import jdk.jfr.Name;

import java.io.Serializable;

public class GhostNetBean implements Serializable {


    private int id;
    private String koordinaten;
    private String editor;
    private String reporter;
    private int size;
    private int status;

    public GhostNetBean() {}



    public String getKoordinaten() {
        return koordinaten;
    }

    public void setKoordinaten(String lat, String lon) {
        this.koordinaten =  lat + ", " + lon;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
