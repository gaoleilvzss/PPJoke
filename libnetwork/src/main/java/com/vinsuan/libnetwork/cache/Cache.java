package com.vinsuan.libnetwork.cache;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


import java.io.Serializable;

@Entity
public class Cache implements Serializable {
    @PrimaryKey
    @NonNull
    public String key;
    public byte[] data;
}
