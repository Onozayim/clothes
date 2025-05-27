package com.clothes.clothes.services;

import java.util.List;

import com.clothes.clothes.entities.Oc;
import com.clothes.clothes.entities.User;
import com.clothes.clothes.responses.OcWithDetailsResponse;

public interface OcService {
    public Oc saveOc(User user);
    public List<OcWithDetailsResponse> getUserOcs(User user); 
}
