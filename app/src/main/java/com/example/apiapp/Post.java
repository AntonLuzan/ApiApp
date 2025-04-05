package com.example.apiapp;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Post")
public class Post implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String body;

    // Constructor for Room
    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    // Constructor for Parcelable
    protected Post(Parcel in) {
        id = in.readInt();
        title = in.readString();
        body = in.readString();
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    // Parcelable implementation
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id); // writing id
        dest.writeString(title); // writing title
        dest.writeString(body); // writing body
    }

    // Creator for Parcelable
    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in); // creating new Post object from Parcel
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size]; // returning an array of Post objects
        }
    };

    // Optional toString method for easy logging and debugging
    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}






