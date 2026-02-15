package com.tsd.libris.service.User;

import org.springframework.stereotype.Service;

@Service
public class ZipCloudService {

    public ZipAddressDto search(String zipCode){

        String url = "https://zipcloud.ibsnet.co.jp/api/search?zipcode=" + zipCode;


    }


}
