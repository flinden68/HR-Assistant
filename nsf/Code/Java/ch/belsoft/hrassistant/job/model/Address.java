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
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((street == null) ? 0 : street.hashCode());
        result = prime * result
        + ((streetNumber == null) ? 0 : streetNumber.hashCode());
        result = prime * result + ((zip == null) ? 0 : zip.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Address other = (Address) obj;
        if (city == null) {
            if (other.city != null) {
                return false;
            }
        } else if (!city.equals(other.city)) {
            return false;
        }
        if (country == null) {
            if (other.country != null) {
                return false;
            }
        } else if (!country.equals(other.country)) {
            return false;
        }
        if (street == null) {
            if (other.street != null) {
                return false;
            }
        } else if (!street.equals(other.street)) {
            return false;
        }
        if (streetNumber == null) {
            if (other.streetNumber != null) {
                return false;
            }
        } else if (!streetNumber.equals(other.streetNumber)) {
            return false;
        }
        if (zip == null) {
            if (other.zip != null) {
                return false;
            }
        } else if (!zip.equals(other.zip)) {
            return false;
        }
        return true;
    }
    
    
    
}
