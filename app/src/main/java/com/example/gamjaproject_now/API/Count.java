package com.example.gamjaproject_now.API;

import com.google.gson.annotations.SerializedName;

public class Count {
    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    @SerializedName("cnt")
    private int cnt;


    @Override
    public String toString() {
        return "Count{" +
                "cnt=" + cnt +
                '}';
    }
}
