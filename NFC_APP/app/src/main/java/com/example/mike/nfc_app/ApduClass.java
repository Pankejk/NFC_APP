package com.example.mike.nfc_app;

import android.annotation.TargetApi;
import android.nfc.cardemulation.HostApduService;
import android.os.Build;
import android.os.Bundle;

import java.math.BigInteger;

/**
 * Created by mike on 1/11/15.
 */

public class ApduClass extends HostApduService {
    @Override
    public byte[] processCommandApdu(byte[] apdu, Bundle extras) {
        //String s = "3B8F8001804F0CA000000306030001000000006A";
        //BigInteger b = new BigInteger(s, 16);
        //byte [] array = b.toByteArray();
        byte [] array = {(byte)0x3B,(byte)0x8F,(byte)0x80,0x01,(byte)0x80,0x4F,0x0C,(byte)0xA0,0x00,0x00,0x03,0x06,0x03,0x00,0x01,0x00,0x00,0x00,0x00,0x6A};
        //byte [] array = {1,1,1,0,1,1,1,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,1,0,0,1,1,1,1,0,0,0,0,1,1,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,1,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,0,1,0};
        //byte [] array = {(byte)0x00}//,0x00, 0x00, 0x03,0x06};
        return array;
    }

    @Override
    public void onDeactivated(int reason) {
    }
}
