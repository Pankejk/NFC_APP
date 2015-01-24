package com.example.mike.nfc_app;

//import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.math.BigInteger;


public class MainActivity extends Activity {
    private TextView myText = null;
    private static final byte[] HEX_CHAR_TABLE = { (byte) '0', (byte) '1',
            (byte) '2', (byte) '3', (byte) '4', (byte) '5', (byte) '6',
            (byte) '7', (byte) '8', (byte) '9', (byte) 'A', (byte) 'B',
            (byte) 'C', (byte) 'D', (byte) 'E', (byte) 'F' };
    //private NfcAdapter mAdapter = null;
    //private PendingIntent mPendingIntent = null;
    //private IntentFilter ndef = null;
    //private IntentFilter [] mFilters = null;
    //private String [][] mTechLists = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //LinearLayout lView = (LinearLayout)findViewById(R.id.textView);

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("asda");
        NfcAdapter mAdapter =NfcAdapter.getDefaultAdapter(this);
        PendingIntent mPendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        IntentFilter [] mFilters = new IntentFilter[] {ndef,};
        String [] [] mTechLists = new String[][] { new String[] { MifareClassic.class.getName() } };

        try {
            ndef.addDataType("*/*");
        } catch (IntentFilter.MalformedMimeTypeException e) {
            throw new RuntimeException("fail", e);
        }

        Log.i("TAG","COS dZiala");
        Intent intent = getIntent();
        resolveIntent(intent);
        //ApduClass tmp = new ApduClass();
        //byte [] array =null;
        //Bundle bu = null;
        //tmp.processCommandApdu(array, bu);
    }

    public void resolveIntent(Intent intent){
        String action = intent.getAction();
        Log.i("TAG","COS DZIALA");
        Log.i("TAG",action);
        if(NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)){
            Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            MifareClassic mfc = MifareClassic.get(tagFromIntent);
            byte [] data;

            try{
                Log.i("TAG","A TO DZIALA?fffffffffff");
                mfc.connect();
                Log.i("TAG","A TO DZIALA?lllll");
                boolean auth = false;
                String cardData = null;
                int secCount = mfc.getSectorCount();
                int bCount =0;
                int bIndex = 0;
                for(int j=0; j<secCount;j++){
                    auth = mfc.authenticateSectorWithKeyA(j,MifareClassic.KEY_DEFAULT);
                    if(auth){
                       bCount = mfc.getBlockCount();
                        bIndex = 0;
                        for(int i=0; i<bCount; i++){
                            bIndex = mfc.sectorToBlock(j);
                            data = mfc.readBlock(bIndex);
                            Log.i("TAG",getHexString(data,data.length));
                            bIndex++;
                        }
                    }else{
                        Log.e("tag","Autoryzacja nie powiodla sie!\n");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.i("TAG","A TO DZIALASDASFDAFASF");
    }

    public static String getHexString(byte[] raw, int len){
        byte [] hex = new byte[2*len];
        int index =0;
        int pos =0;
        for(byte b : raw){
            if(pos>=len)
                break;
            pos++;
            int v = b & 0xFF;
            hex[index++] = HEX_CHAR_TABLE[v >>> 4];
            hex[index++] = HEX_CHAR_TABLE[v & 0xF];
        }
        return new String(hex);
    }


    /*@Override
    public void onResume() {
        super.onResume();
        mAdapter.enableForegroundDispatch(this, mPendingIntent, mFilters, mTechLists);
    }

    @Override
    public void onNewIntent(Intent intent) {
        Log.i("Foreground dispatch", "Discovered tag with intent: " + intent);
        resolveIntent(intent);
    }

    @Override
    public void onPause() {
        super.onPause();
        mAdapter.disableForegroundDispatch(this);
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
