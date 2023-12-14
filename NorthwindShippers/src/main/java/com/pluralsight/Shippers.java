package com.pluralsight;

public class Shippers {
    private int shipperId;
    private String companyName, phoneNum;

    public Shippers(int shipperId, String companyName, String phoneNum) {
        this.shipperId = shipperId;
        this.companyName = companyName;
        this.phoneNum = phoneNum;
    }

    public int getShipperId() {
        return shipperId;
    }

    public void setShipperId(int shipperId) {
        this.shipperId = shipperId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

}
