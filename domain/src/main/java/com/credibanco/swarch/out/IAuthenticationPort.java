package com.credibanco.swarch.out;


import com.credibanco.swarch.model.PartyDataReference;
import com.credibanco.swarch.model.response.PartyDataReferenceResponseModel;

public interface IAuthenticationPort {

    PartyDataReferenceResponseModel getTokenAuthenticationEntrust(String userName, String transactionId);

    void createUserEntrust(PartyDataReference[] partyDataReference, String transactionId);

}
