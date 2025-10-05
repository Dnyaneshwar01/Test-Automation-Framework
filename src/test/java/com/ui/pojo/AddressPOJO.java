package com.ui.pojo;

public class AddressPOJO {

    private String company;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String postCode;
    private String homePhone;
    private String mobileNumber;
    private String state;
    private String otherInformation;
    private String addressAltias;

    public AddressPOJO(String company, String addressLine1, String addressLine2, String city, String postCode, String homePhone, String mobileNumber, String state, String otherInformation, String addressAltias) {
        this.company = company;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.postCode = postCode;
        this.homePhone = homePhone;
        this.mobileNumber = mobileNumber;
        this.state = state;
        this.otherInformation = otherInformation;
        this.addressAltias = addressAltias;
    }

    public String getCompany() {
        return company;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getCity() {
        return city;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getState() {
        return state;
    }

    public String getOtherInformation() {
        return otherInformation;
    }

    public String getAddressAltias() {
        return addressAltias;
    }

    @Override
    public String toString() {
        return "AddressPOJO{" +
                "company='" + company + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", postCode='" + postCode + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", state='" + state + '\'' +
                ", otherInformation='" + otherInformation + '\'' +
                ", addressAltias='" + addressAltias + '\'' +
                '}';
    }
}
