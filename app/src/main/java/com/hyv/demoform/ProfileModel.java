package com.hyv.demoform;

public class ProfileModel {
    private String user_id, user_name,email_id,contact_number,date_of_birth,address,city,state,
            country,Pincode,gstin_no,pan_no,aadhaar_no,driving_licence,voter_id,upi_id;

    public ProfileModel(){}
    public ProfileModel(String user_id, String user_name, String email_id, String contact_number, String date_of_birth,
                        String address, String city, String state, String country, String Pincode, String gstin_no, String pan_no,
                        String aadhaar_no, String driving_licence,String voter_id, String upi_id ){

        this.user_id = user_id;
        this.user_name = user_name;
        this.email_id = email_id;
        this.contact_number = contact_number;
        this.date_of_birth = date_of_birth;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.Pincode = Pincode;
        this.gstin_no = gstin_no;
        this.pan_no = pan_no;
        this.aadhaar_no = aadhaar_no;
        this.driving_licence = driving_licence;
        this.voter_id = voter_id;
        this.upi_id = upi_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPincode() {
        return Pincode;
    }

    public void setPincode(String pincode) {
        Pincode = pincode;
    }

    public String getGstin_no() {
        return gstin_no;
    }

    public void setGstin_no(String gstin_no) {
        this.gstin_no = gstin_no;
    }

    public String getPan_no() {
        return pan_no;
    }

    public void setPan_no(String pan_no) {
        this.pan_no = pan_no;
    }

    public String getAadhaar_no() {
        return aadhaar_no;
    }

    public void setAadhaar_no(String aadhaar_no) {
        this.aadhaar_no = aadhaar_no;
    }

    public String getDriving_licence() {
        return driving_licence;
    }

    public void setDriving_licence(String driving_licence) {
        this.driving_licence = driving_licence;
    }

    public String getVoter_id() {
        return voter_id;
    }

    public void setVoter_id(String voter_id) {
        this.voter_id = voter_id;
    }

    public String getUpi_id() {
        return upi_id;
    }

    public void setUpi_id(String upi_id) {
        this.upi_id = upi_id;
    }
}
