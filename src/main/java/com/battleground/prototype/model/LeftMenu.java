package com.battleground.prototype.model;

/**
 * Created by sugin on 15. 7. 29..
 */
public class LeftMenu {
    private String location1;
    private String location2;
    private int ord;
    private String manager;
    private Long incoming;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;


    public LeftMenu(){

    }

    public String getLocation1() {
        return location1;
    }

    public void setLocation1(String location1) {
        this.location1 = location1;
    }

    public String getLocation2() {
        return location2;
    }

    public void setLocation2(String location2) {
        this.location2 = location2;
    }

    public int getOrd() {
        return ord;
    }

    public void setOrd(int ord) {
        this.ord = ord;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public Long getIncoming() {
        return incoming;
    }

    public void setIncoming(Long incoming) {
        this.incoming = incoming;
    }
}
