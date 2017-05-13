package fr.garrycity.pol.gitprojectb3.models;

import java.io.Serializable;

/**
 * Created by Pol on 29/04/2017.
 */

public class Organization implements Serializable {
    private String login;
    private String avatarUrl;
    private String description;

    public Organization(String loginJSON, String avatarUrlJSON, String descriptionJSON) {
        this.login = loginJSON;
        this.avatarUrl = avatarUrlJSON;
        this.description = descriptionJSON;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getDescription() {
        return description;
    }


}