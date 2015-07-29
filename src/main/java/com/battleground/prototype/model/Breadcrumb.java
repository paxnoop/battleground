package com.battleground.prototype.model;

/**
 * Created by sugin on 15. 7. 3..
 */
public class Breadcrumb {
    private String link;
    private String name;
    private boolean isActive;

    public Breadcrumb() {

    }

    public Breadcrumb(String link, String name, boolean isActive) {
        this.link = link;
        this.name = name;
        this.isActive = isActive;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
