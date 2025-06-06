package com.credibanco.swarch.out;


import com.credibanco.swarch.model.EntrustCredentials;

public interface IEntrustSecret {

    EntrustCredentials retrieveAndParseSecret(String secretName);

}
