package fr.garrycity.pol.gitprojectb3.models;

import java.io.Serializable;

/**
 * Created by Pol on 29/04/2017.
 */

public class Gist implements Serializable {
    private String filename;
    private String type;
    private String language;
    private String rawUrl;

    public Gist(String filename, String type, String language, String rawUrl) {
        this.filename = filename;
        this.type = type;
        this.language = language;
        this.rawUrl = rawUrl;
    }

    public Gist() {

    }

    public String getFilename() {
        return filename;
    }

    public String getType() {
        return type;
    }

    public String getLanguage() {
        return language;
    }

    public String getRawUrl() {
        return rawUrl;
    }


    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setRawUrl(String rawUrl) {
        this.rawUrl = rawUrl;
    }
}