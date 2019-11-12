package com.example.vi5h.staffseventplanner;

public class EventModel
{
    private String Event;
    private String Location;
    private String Date;
    private String Image;
    private String Desc;
    private String Ticket;
    private String MoreInfo;
    private String Time;

    public EventModel()
    {

    }

    public EventModel(String event, String location, String date, String image, String desc,
                      String ticket, String moreInfo, String time)
    {
        Event = event;
        Location = location;
        Date = date;
        Image = image;
        Desc = desc;
        Ticket = ticket;
        MoreInfo = moreInfo;
        Time = time;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getTicket() {
        return Ticket;
    }

    public void setTicket(String ticket) {
        Ticket = ticket;
    }

    public String getMoreInfo() {
        return MoreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        MoreInfo = moreInfo;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
