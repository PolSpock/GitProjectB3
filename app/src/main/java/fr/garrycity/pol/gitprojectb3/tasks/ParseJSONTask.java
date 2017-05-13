package fr.garrycity.pol.gitprojectb3.tasks;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;

import fr.garrycity.pol.gitprojectb3.models.Gist;
import fr.garrycity.pol.gitprojectb3.models.Organization;
import fr.garrycity.pol.gitprojectb3.models.Profile;
import fr.garrycity.pol.gitprojectb3.models.Repository;

/**
 * Created by Pol on 11/04/2017.
 */

public class ParseJSONTask {

    public static void RepositorySearchParse(String stringJSON, List<Repository> listRepository) {
        listRepository.clear();
        try {
            JSONObject mainObject = new JSONObject(stringJSON);
            JSONArray uniObject = mainObject.getJSONArray("items");

            for (int i=0; i < uniObject.length(); i++)
            {
                try {
                    JSONObject oneObject = uniObject.getJSONObject(i);

                    // Pulling items from the array
                    String oneObjectsItem = oneObject.getString("name");
                    String oneObjectsItem2 = oneObject.getString("full_name");
                    String oneObjectsItem3 = oneObject.getString("description");

                    String oneObjectsItem4 = oneObject.getString("created_at");
                    String oneObjectsItem5 = oneObject.getString("updated_at");
                    String oneObjectsItem6 = oneObject.getString("pushed_at");

                    JSONObject ownerObject = oneObject.getJSONObject("owner");
                    String oneObjectsItem7 = ownerObject.getString("login");
                    String oneObjectsItem8 = ownerObject.getString("avatar_url");

                    String oneObjectsItem9 = oneObject.getString("watchers");
                    String oneObjectsItem10 = oneObject.getString("forks");
                    String oneObjectsItem11 = oneObject.getString("language");

                    String oneObjectsItem12 = oneObject.getString("html_url");

                    listRepository.add(new Repository(oneObjectsItem, oneObjectsItem2, oneObjectsItem3, oneObjectsItem4, oneObjectsItem5, oneObjectsItem6, oneObjectsItem7, oneObjectsItem8, oneObjectsItem9, oneObjectsItem10, oneObjectsItem11, oneObjectsItem12));

                } catch (JSONException e) {
                    // Oops
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void RepositoryProfileParse(String stringJSON, List<Repository> listRepository) {
        listRepository.clear();
        try {
            JSONArray mainObject = new JSONArray(stringJSON);

            for (int i=0; i < mainObject.length(); i++)
            {
                try {
                    JSONObject oneObject = mainObject.getJSONObject(i);

                    // Pulling items from the array
                    String oneObjectsItem = oneObject.getString("name");
                    String oneObjectsItem2 = oneObject.getString("full_name");
                    String oneObjectsItem3 = oneObject.getString("description");

                    String oneObjectsItem4 = oneObject.getString("created_at");
                    String oneObjectsItem5 = oneObject.getString("updated_at");
                    String oneObjectsItem6 = oneObject.getString("pushed_at");

                    JSONObject ownerObject = oneObject.getJSONObject("owner");
                    String oneObjectsItem7 = ownerObject.getString("login");
                    String oneObjectsItem8 = ownerObject.getString("avatar_url");

                    String oneObjectsItem9 = oneObject.getString("watchers");
                    String oneObjectsItem10 = oneObject.getString("forks");
                    String oneObjectsItem11 = oneObject.getString("language");

                    String oneObjectsItem12 = oneObject.getString("html_url");

                    listRepository.add(new Repository(oneObjectsItem, oneObjectsItem2, oneObjectsItem3, oneObjectsItem4, oneObjectsItem5, oneObjectsItem6, oneObjectsItem7, oneObjectsItem8, oneObjectsItem9, oneObjectsItem10, oneObjectsItem11, oneObjectsItem12));

                } catch (JSONException e) {
                    // Oops
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void ProfileSearchParse(String stringJSON, List<Profile> listProfile) {
        listProfile.clear();
        try {
            JSONObject mainObject = new JSONObject(stringJSON);
            JSONArray uniObject = mainObject.getJSONArray("items");

            for (int i=0; i < uniObject.length(); i++)
            {
                try {
                    JSONObject oneObject = uniObject.getJSONObject(i);


                    // Pulling items from the array
                    String oneObjectsItem = oneObject.getString("login");
                    String oneObjectsItem2 = oneObject.getString("url");
                    String oneObjectsItem3 = oneObject.getString("avatar_url");

                    listProfile.add(new Profile(oneObjectsItem, oneObjectsItem2, oneObjectsItem3));

                    System.out.println(oneObjectsItem);
                    System.out.println(oneObjectsItem2);
                } catch (JSONException e) {
                    // Oops
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void ProfileParse(String stringJSON, Profile profile) {
        try {
            JSONObject mainObject = new JSONObject(stringJSON);

            System.out.println(mainObject);

            profile.setRepos_url(mainObject.getString("repos_url"));
            profile.setOrganizations_url(mainObject.getString("organizations_url"));
            profile.setName(mainObject.getString("name"));
            profile.setCompany(mainObject.getString("company"));
            profile.setLocation(mainObject.getString("location"));
            profile.setEmail(mainObject.getString("email"));
            profile.setBio(mainObject.getString("bio"));
            profile.setPublic_repos(mainObject.getString("public_repos"));
            profile.setPublic_gists(mainObject.getString("public_gists"));
            profile.setFollowers(mainObject.getString("followers"));
            profile.setFollowing(mainObject.getString("following"));
            profile.setCreated(mainObject.getString("created_at"));
            profile.setUpdated(mainObject.getString("updated_at"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void GistSearchParse(String stringJSON, List<Gist> listGist) {
        listGist.clear();
        try {
            JSONArray mainObject = new JSONArray(stringJSON);

            for (int i = 0; i < mainObject.length(); i++) {
                try {
                    JSONObject oneObject = mainObject.getJSONObject(i);

                    JSONObject secondObject = oneObject.getJSONObject("files");
                    Iterator<String> iter = secondObject.keys();

                    String oneObjectsItem = null;
                    String oneObjectsItem2 = null;
                    String oneObjectsItem3 = null;
                    String oneObjectsItem4 = null;

                    while (iter.hasNext()) {
                        String key = iter.next();
                        try {
                            JSONObject value = (JSONObject) secondObject.get(key);

                            oneObjectsItem = value.getString("filename");
                            oneObjectsItem2 = value.getString("type");
                            oneObjectsItem3 = value.getString("language");
                            oneObjectsItem4 = value.getString("raw_url");

                        } catch (JSONException e) {
                            // Something went wrong!
                        }
                    }

                    listGist.add(new Gist(oneObjectsItem, oneObjectsItem2, oneObjectsItem3, oneObjectsItem4));

                } catch (JSONException e) {
                    // Oops
                }
            }
        }
        catch (JSONException e) {
            // Oops
        }
    }

    public JSONObject GistCreate(String filename, String description, String content, boolean isPublic) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("description", description);
            jsonObject.put("public", isPublic);

            JSONObject jsonObjectContent = new JSONObject();
            jsonObjectContent.put("content", content);

            JSONObject jsonObjectFilename = new JSONObject();
            jsonObjectFilename.put(filename, jsonObjectContent);

            jsonObject.put("files", jsonObjectFilename);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static void GistOneParse(String stringJSON, Gist activeGist) {
        try {
            JSONObject oneObject = new JSONObject(stringJSON);

            JSONObject secondObject = oneObject.getJSONObject("files");
            Iterator<String> iter = secondObject.keys();

            String oneObjectsItem = null;
            String oneObjectsItem2 = null;
            String oneObjectsItem3 = null;
            String oneObjectsItem4 = null;

            while (iter.hasNext()) {
                String key = iter.next();
                try {
                    JSONObject value = (JSONObject) secondObject.get(key);

                    oneObjectsItem = value.getString("filename");
                    oneObjectsItem2 = value.getString("type");
                    oneObjectsItem3 = value.getString("language");
                    oneObjectsItem4 = value.getString("raw_url");

                } catch (JSONException e) {
                    // Something went wrong!
                }
            }

            activeGist.setFilename(oneObjectsItem);
            activeGist.setType(oneObjectsItem2);
            activeGist.setLanguage(oneObjectsItem3);
            activeGist.setRawUrl(oneObjectsItem4);

        } catch (JSONException e) {
            // Oops
        }
    }

    public static void OrganizationProfileParse(String stringJSON, List<Organization> listOrganization) {
        listOrganization.clear();
        try {
            JSONArray mainObject = new JSONArray(stringJSON);

            for (int i=0; i < mainObject.length(); i++)
            {
                try {
                    JSONObject oneObject = mainObject.getJSONObject(i);

                    // Pulling items from the array
                    String oneObjectsItem = oneObject.getString("login");
                    String oneObjectsItem2 = oneObject.getString("avatar_url");
                    String oneObjectsItem3 = oneObject.getString("description");

                    listOrganization.add(new Organization(oneObjectsItem, oneObjectsItem2, oneObjectsItem3));

                } catch (JSONException e) {
                    // Oops
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
