package ch.belsoft.hrassistant.job.model;

import java.io.Serializable;

public class Address implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String street = "";
    private String streetNumber = "";
    private String zip = "";
    private String city = "";
    private String country = "";
    
    public String getStreet() {
        return street;
    }
    
    public void setStreet(String street) {
        this.street = street;
    }
    
    public String getStreetNumber() {
        return streetNumber;
    }
    
    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }
    
    public String getZip() {
        return zip;
    }
    
    public void setZip(String zip) {
        this.zip = zip;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getStreetAndNumber(){
        return getStreet() + " " + getStreetNumber();
    }
    
    public String getAddressDisplay(){
        return getStreet() + " " + getStreetNumber() + "<br />" + getZip() + " " + getCity() + "<br />" + getCountry();
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Street: " + getStreet() + ",");
        sb.append("Streetnumber: " + getStreetNumber() + ",");
        sb.append("ZIP: " + getZip() + ",");
        sb.append("City: " + getCity() + ",");
        sb.append("Country: " + getCountry());
        return sb.toString();
    }
    
}
