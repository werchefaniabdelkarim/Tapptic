package com.tapptic.abwe.datamodels;

import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("name")
    public String name;
    @SerializedName("text")
    public String text;
    @SerializedName("image")
    public String image;
}
