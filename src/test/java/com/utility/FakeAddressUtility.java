package com.utility;

import com.github.javafaker.Faker;
import com.ui.pojo.AddressPOJO;

public class FakeAddressUtility {

    public static AddressPOJO getFakeAddress() {
        Faker faker = new Faker();
        AddressPOJO addressPOJO = new AddressPOJO(faker.company().name(),faker.address().buildingNumber(),
                faker.address().fullAddress(),faker.address().city(),"43115"
                ,faker.phoneNumber().cellPhone(), faker.phoneNumber().cellPhone(),
                "Arizona", "other information",
                "Altias");
        return addressPOJO;
    }
}