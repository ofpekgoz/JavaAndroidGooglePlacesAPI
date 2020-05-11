package com.omerfpekgoz.uygulama_googleplacesapi.model;

public class Places {

    private String placeName;
    private Double placeLatitude;  //Enlem
    private Double placeLongitude;  //Boylam;
    private String placeVicinity;   //Adres
    private String placeIcon;


    public Places() {
    }

    public Places(String placeName, Double placeLatitude, Double placeLongitude, String placeVicinity, String placeIcon) {
        this.placeName = placeName;
        this.placeLatitude = placeLatitude;
        this.placeLongitude = placeLongitude;
        this.placeVicinity = placeVicinity;
        this.placeIcon = placeIcon;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public Double getPlaceLatitude() {
        return placeLatitude;
    }

    public void setPlaceLatitude(Double placeLatitude) {
        this.placeLatitude = placeLatitude;
    }

    public Double getPlaceLongitude() {
        return placeLongitude;
    }

    public void setPlaceLongitude(Double placeLongitude) {
        this.placeLongitude = placeLongitude;
    }

    public String getPlaceVicinity() {
        return placeVicinity;
    }

    public void setPlaceVicinity(String placeVicinity) {
        this.placeVicinity = placeVicinity;
    }

    public String getPlaceIcon() {
        return placeIcon;
    }

    public void setPlaceIcon(String placeIcon) {
        this.placeIcon = placeIcon;
    }
}
