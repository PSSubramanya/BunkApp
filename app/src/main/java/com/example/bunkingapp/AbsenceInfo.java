package com.example.bunkingapp;

public class AbsenceInfo
{

    public String name;
    public String usn;
    public String date;
    public String day;
    public String slot;
    public String reason;

    public AbsenceInfo()
    {

    }

    public AbsenceInfo(String name, String usn, String date, String day, String slot, String reason)
    {
        this.name = name;
        this.usn = usn;
        this.date = date;
        this.day = day;
        this.slot = slot;
        this.reason = reason;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


}
