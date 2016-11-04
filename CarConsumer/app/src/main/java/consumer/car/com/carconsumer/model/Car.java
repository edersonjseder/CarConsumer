package consumer.car.com.carconsumer.model;

import android.graphics.Bitmap;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * Created by ederson.js on 31/10/2016.
 */

public class Car implements Serializable{

    private Integer id;
    private String name;
    private String description;
    private String type;
    private String urlPhoto;
    private String urlVideo;
    private String latitud;
    private String longitud;

    @JsonIgnore
    private Bitmap carImageByte;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    @JsonIgnore
    public Bitmap getCarImageByte() {
        return carImageByte;
    }

    public void setCarImageByte(Bitmap carImageByte) {
        this.carImageByte = carImageByte;
    }
}
