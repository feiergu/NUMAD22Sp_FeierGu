package edu.neu.madcourse.numad22sp_feiergu;

public class Link {
    private String linkName;
    private String url;

    public Link(String linkName, String url) {
        this.linkName = linkName;
        this.url = url;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
