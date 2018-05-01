package com.example.user.recognito.Utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Created by emmanuel on 12/28/2017.
 */


public class Base64Converter{

    public static String toBase64(String client_id, String client_secret){
        String base64String = null;
        String credential = client_id +":"+client_secret;
        try {
            byte[] credentialBytes = credential.getBytes("UTF-8");
            base64String = Base64.encodeToString(credentialBytes, Base64.NO_WRAP);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return base64String;
    }

}
