package fr.garrycity.pol.gitprojectb3.models;

/**
 * Created by Pol on 29/04/2017.
 */

public class Item {
    public final String name;
    public final int drawableId;
    public final String usage;

    public Item(String name, int drawableId, String usage) {
        this.name = name;
        this.drawableId = drawableId;
        this.usage = usage;
    }

    public String getName() {
        return name;
    }

    public String getUsage() {
        return usage;
    }
}