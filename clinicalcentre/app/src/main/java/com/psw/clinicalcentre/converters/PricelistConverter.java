package com.psw.clinicalcentre.converters;

import com.psw.clinicalcentre.clinics.Pricelist;
import com.psw.clinicalcentre.clinics.PricelistDTO;

public class PricelistConverter {

    public static PricelistDTO pricelistToPricelistDto(Pricelist pricelist) {
        return PricelistDTO.builder().id(pricelist.getId()).price(pricelist.getPrice())
                .discount(pricelist.getDiscount()).build();
    }

}
