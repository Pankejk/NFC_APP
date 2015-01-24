package com.example.mike.nfc_app;

import android.annotation.TargetApi;
import android.content.Intent;
import android.nfc.cardemulation.HostApduService;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

/**
 * Created by mike on 1/11/15.
 */

public class ApduClass extends HostApduService {
    /*private int counter =0;

    @Override
    public void onStart(Intent intent, int startId){
        new Thread(new Runnable(){
            @Override
            public void run(){
                while(true){
                    Log.d("HCE", String.valueOf(counter));
                    counter++;
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).run();
    }*/
    @Override
    public byte[] processCommandApdu(byte[] apdu, Bundle extras) {
        //String s = "3B8F8001804F0CA000000306030001000000006A";
        //BigInteger b = new BigInteger(s, 16);
        //byte [] array = b.toByteArray();
        //byte [] array = {(byte)0x3B,(byte)0x8F,(byte)0x80,0x01,(byte)0x80,0x4F,0x0C,(byte)0xA0,0x00,0x00,0x03,0x06,0x03,0x00,0x01,0x00,0x00,0x00,0x00,0x6A};
        //byte [] array = {1,1,1,0,1,1,1,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,1,0,0,1,1,1,1,0,0,0,0,1,1,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,1,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,0,1,0};
        //byte [] array = {(byte)0x00}//,0x00, 0x00, 0x03,0x06};
        byte[] response = null;
        //String log = new String(apdu);
        //Log.d(log, String.valueOf(0));
        //if(apdu[2] == 0xA4) {
            response = new byte[]{(byte) 0x90, 0x00};
        //}
        return response;
    }

    @Override
    public void onDeactivated(int reason) {
        Log.d("HCE", "onDeactivated");
    }
}
