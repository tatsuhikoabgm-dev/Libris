package com.tsd.libris.domain.dto.user.mypage;


import lombok.Data;

@Data
public class ZipAddressDto {

    private String postalCode;
    private String prefecture;
    private String city;
    private String town;
}
