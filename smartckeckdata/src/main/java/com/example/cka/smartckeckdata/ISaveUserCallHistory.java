package com.example.cka.smartckeckdata;

public interface ISaveUserCallHistory {
    public void SaveUserCallHistory(int type);

    String getNumber();

    void setNumber(String number);

    int getType();

    void setType(int type);

    long getDate();

    void setDate(long date);

    int getDuration();

    void setDuration(int duration);

    String getCoolDuration();

    String getContactName();

    String getUserNumber();
}
