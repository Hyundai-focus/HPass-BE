package com.hyundai.hpass.dto;

import lombok.Getter;
import lombok.Setter;


public class SubscriptionDTO {
    private int rowNo;
    private String email;
    private String memberName;
    private String subsStartDt;
    private String payment;
    private String subsStatus;

    public int getRowNo() {
        return rowNo;
    }

    public String getEmail() {
        String[] parts = email.split("@");
        return makeMasking(parts[0]) + "@" + makeMasking(parts[1]);
    }

    public String getMemberName() {
        String firstChar = memberName.substring(0, 1);
        String lastChar = memberName.substring(memberName.length() - 1);
        StringBuilder converted = new StringBuilder(firstChar);
        for (int i = 1; i < memberName.length() - 1; i++) {
            converted.append("*");
        }
        converted.append(lastChar);
        return converted.toString();
    }

    public String getSubsStartDt() {
        return subsStartDt;
    }

    public String getPayment() {
        return payment;
    }

    public String getSubsStatus() {
        return subsStatus;
    }

    private String makeMasking(String str){
        StringBuilder maskingStr = new StringBuilder(str.substring(0, 3));
        for (int i=3; i< str.length(); i++) {
            maskingStr.append("*");
        }
        return maskingStr.toString();
    }
}
