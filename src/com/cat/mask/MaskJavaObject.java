package com.cat.mask;

import com.cat.model.AccountDetail;
import com.cat.model.AddressDetail;
import com.cat.model.CreditCardDetail;

public class MaskJavaObject {

    public static void main(String[] args) {
        AddressDetail addressDetail = new AddressDetail("1205 Moher Blvd", "Franklin", "TN", "37069", "USA");
        CreditCardDetail creditCardDetail = new CreditCardDetail("1234567890123456", "123", "12/90");
        AccountDetail accountDetail = new AccountDetail("Sourabh", "Verma", addressDetail, creditCardDetail);
        System.out.println(accountDetail);
    }

}