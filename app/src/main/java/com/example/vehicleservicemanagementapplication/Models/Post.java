package com.example.vehicleservicemanagementapplication.Models;

// https://www.youtube.com/watch?v=cmekm6hM4ew

import com.google.firebase.database.ServerValue;

// Post class
public class Post
{
    // Initialising variables which will be stored in database
    private String title;
    private String make;
    private String model;
    private String description;
    private String picture;
    private String userId;
    private String userPhoto;
    private Object timeStamp;
    private String vehicleKey;

    // Constructor for all initialised variables
    public Post(String title, String make, String model, String description, String picture, String userId, String userPhoto)
    {
        this.title = title;
        this.make = make;
        this.model = model;
        this.description = description;
        this.picture = picture;
        this.userId = userId;
        this.userPhoto = userPhoto;
        this.timeStamp = ServerValue.TIMESTAMP;

    // end of constructor
    }


    // Base constructor
    public Post()
    {

    }


    // GETTERS
    // Title getter method
    public String getTitle()
    {
        return title;
    }

    // Make getter method
    public String getMake()
    {
        return make;
    }

    // Model getter method
    public String getModel()
    {
        return model;
    }

    // Description getter method
    public String getDescription()
    {
        return description;
    }

    // Picture getter method
    public String getPicture()
    {
        return picture;
    }

    // User ID getter method
    public String getUserId()
    {
        return userId;
    }

    // User photo getter method
    public String getUserPhoto()
    {
        return userPhoto;
    }

    // Timestamp getter method
    public Object getTimeStamp()
    {
        return timeStamp;
    }

    // Vehicle key getter method
    public String getVehicleKey()
    {
        return vehicleKey;
    }


    // SETTERS
    // Title setter method
    public void setTitle(String title)
    {
        this.title = title;
    }

    // Make setter method
    public void setMake(String make)
    {
        this.make = make;
    }

    // Model setter method
    public void setModel(String model)
    {
        this.model = model;
    }

    // Description setter method
    public void setDescription(String description)
    {
        this.description = description;
    }

    // Picture setter method
    public void setPicture(String picture)
    {
        this.picture = picture;
    }

    // User ID setter method
    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    // User photo setter method
    public void setUserPhoto(String userPhoto)
    {
        this.userPhoto = userPhoto;
    }

    // Timestamp setter method
    public void setTimeStamp(Object timeStamp)
    {
        this.timeStamp = timeStamp;
    }

    // Vehicle Key setter method
    public void setVehicleKey(String vehicleKey)
    {
        this.vehicleKey = vehicleKey;
    }




// end of class
}
