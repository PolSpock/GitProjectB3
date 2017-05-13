package fr.garrycity.pol.gitprojectb3.models;

import java.io.Serializable;

/**
 * Created by Pol on 29/04/2017.
 */

public class Profile implements Serializable {
    private String login;
    private String url;
    private String urlAvatar;

    private String repos_url;
    private String organizations_url;
    private String name;
    private String company;
    private String location;
    private String email;
    private String bio;
    private String public_repos;
    private String public_gists;
    private String followers;
    private String following;
    private String created;
    private String updated;


    public Profile(String loginJSON, String urlJSON, String urlAvatarJSON) {
        login = loginJSON;
        url = urlJSON;
        urlAvatar = urlAvatarJSON;
    }


    public String getLogin() {
        return login;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }


    public String getRepos_url() {
        return repos_url;
    }

    public String getOrganizations_url() {
        return organizations_url;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public String getBio() {
        return bio;
    }

    public String getPublic_repos() {
        return public_repos;
    }

    public String getPublic_gists() {
        return public_gists;
    }

    public String getFollowers() {
        return followers;
    }

    public String getFollowing() {
        return following;
    }

    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }

    public void setRepos_url(String repos_url) {
        this.repos_url = repos_url;
    }

    public void setOrganizations_url(String organizations_url) {
        this.organizations_url = organizations_url;
    }

    public void setName(String name) {
        if (name != "null") {
            this.name = name;
        }
        else {
            this.name = "Name not available";
        }
    }

    public void setCompany(String company) {
        if (company != "null") {
            this.company = company;
        }
        else {
            this.company = "Company not available";
        }
    }

    public void setLocation(String location) {
        if (location != "null") {
            this.location = location;
        }
        else {
            this.location = "Location not available";
        }
    }

    public void setEmail(String email) {
        if (email != "null") {
            this.email = email;
        }
        else {
            this.email = "Email not available";
        }
    }

    public void setBio(String bio) {

        if (bio != "null") {
            this.bio = bio;
        }
        else {
            this.bio = "Bio not available";
        }
    }

    public void setPublic_repos(String public_repos) {
        this.public_repos = public_repos;
    }

    public void setPublic_gists(String public_gists) {
        this.public_gists = public_gists;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }


}