package com.example.ecommerce.model;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class ImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long imageID;
    private String imageName;
    private String type;
    @Column(length = 90000000)
    private byte[] pictureByte;

    public ImageModel() {

    }

    public ImageModel(String imageName, String type, byte[] pictureByte) {
        this.imageName = imageName;
        this.type = type;
        this.pictureByte = pictureByte;
    }

    public long getImageID() {
        return imageID;
    }

    public void setImageID(long imageID) {
        this.imageID = imageID;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getPictureByte() {
        return pictureByte;
    }

    public void setPictureByte(byte[] pictureByte) {
        this.pictureByte = pictureByte;
    }
}
