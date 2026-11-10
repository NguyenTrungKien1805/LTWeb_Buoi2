package murach.business;

import java.io.Serializable;

public class Survey implements Serializable {
    private String hearAbout;
    private String wantUpdate;
    private String wantAnnouncement;
    private String contact;

    public Survey() {
        this.hearAbout = "";
        this.wantUpdate = "";
        this.wantAnnouncement = "";
        this.contact = "";
    }

    public Survey(String hearAbout, String wantUpdate, String wantAnnouncement, String contact) {
        this.hearAbout = hearAbout;
        this.wantUpdate = wantUpdate;
        this.wantAnnouncement = wantAnnouncement;
        this.contact = contact;
    }

    public String getHearAbout() { return hearAbout; }
    public void setHearAbout(String hearAbout) { this.hearAbout = hearAbout; }

    public String getWantUpdate() { return wantUpdate; }
    public void setWantUpdate(String wantUpdate) { this.wantUpdate = wantUpdate; }

    public String getWantAnnouncement() { return wantAnnouncement; }
    public void setWantAnnouncement(String wantAnnouncement) { this.wantAnnouncement = wantAnnouncement; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
}