package fr.garrycity.pol.gitprojectb3.models;

import java.io.Serializable;

/**
 * Created by Pol on 11/04/2017.
 */

public class Repository implements Serializable {
    private String name;
    private String fullName;
    private String description;

    private String created;
    private String updated;
    private String pushed;

    private String login;
    private String avatar;

    private String forks;
    private String watchers;
    private String language;

    private String htmlUrl;

    public Repository(String nameJSON, String fullNameJSON, String descriptionJSON, String createdJSON, String updatedJSON, String pushedJSON, String loginJSON, String avatarJSON, String forksJSON, String watchersJSON, String languageJSON, String htmlUrlJSON) {
        name = nameJSON;
        fullName = fullNameJSON;
        if (descriptionJSON != "null") {
            description = descriptionJSON;
        }
        else {
            description = "Description not available";
        }

        created = createdJSON;
        updated = updatedJSON;
        pushed = pushedJSON;

        login = loginJSON;
        avatar = avatarJSON;

        forks = forksJSON;
        watchers = watchersJSON;
        language = languageJSON;

        if (languageJSON != "null") {
            language = languageJSON;
        }
        else {
            language = "Language not available";
        }

        htmlUrl = htmlUrlJSON;
    }


    public String getDescription() {
        return description;
    }

    public String getFullName() {
        return fullName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }

    public String getPushed() {
        return pushed;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatar() {
        return avatar;
    }


    public String getForks() {
        return forks;
    }

    public String getWatchers() {
        return watchers;
    }

    public String getLanguage() {
        return language;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }
}
