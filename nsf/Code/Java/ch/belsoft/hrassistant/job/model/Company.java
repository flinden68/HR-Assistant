package ch.belsoft.hrassistant.job.model;

import java.io.Serializable;

import ch.belsoft.hrassistant.model.DataItem;

public class Company extends DataItem implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private String name = "";
    private Address address = null;
    private final DataType dataType = DataType.COMPANY;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Address getAddress() {
        return address;
    }
    
    public void setAddress(Address address) {
        this.address = address;
    }
    
    public DataType getDataType() {
        return dataType;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Name: " + getName() + ",");
        sb.append("Address: " + getAddress());
        return sb.toString();
    }
    
}
