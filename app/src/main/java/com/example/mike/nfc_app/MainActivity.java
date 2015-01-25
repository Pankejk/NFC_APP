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
    static final byte[] [] KEY_A = {{(byte) 0xa0,(byte) 0xa1, (byte) 0xa2, (byte) 0xa3, (byte) 0xa4, (byte) 0xa5},
                                    {(byte) 0x00, (byte) 0xf3, (byte) 0x81, (byte) 0xb0, (byte) 0x95, (byte) 0x55},
                                    {(byte) 0x7d, (byte) 0x12, (byte) 0x46, (byte) 0x67, (byte) 0xf0, (byte) 0x44},
                                    {(byte) 0x30, (byte) 0x91, (byte) 0x7f, (byte) 0x3f, (byte) 0xec, (byte) 0xe2},
                                    {(byte) 0xcc, (byte) 0xd0, (byte) 0x46, (byte) 0xdb, (byte) 0x10, (byte) 0xb5},
                                    {(byte) 0xc1, (byte) 0x45, (byte) 0x31, (byte) 0xd3, (byte) 0xd9, (byte) 0x8d},
                                    {(byte) 0x54, (byte) 0x1a, (byte) 0xcb, (byte) 0x61, (byte) 0xff, (byte) 0x24},
                                    {(byte) 0x86, (byte) 0x06, (byte) 0xa3, (byte) 0x3a, (byte) 0xdc, (byte) 0x47},
                                    {(byte) 0x9a, (byte) 0x19, (byte) 0x0d, (byte) 0xb4, (byte) 0xa7, (byte) 0xee},
                                    {(byte) 0x61, (byte) 0x99, (byte) 0xee, (byte) 0xc5, (byte) 0xd2, (byte) 0xe5},
                                    {(byte) 0xe1, (byte) 0xf4, (byte) 0xf7, (byte) 0x46, (byte) 0x5b, (byte) 0x7b},
                                    {(byte) 0xbb, (byte) 0xca, (byte) 0x10, (byte) 0xdf, (byte) 0xf2, (byte) 0x0b},
                                    {(byte) 0x52, (byte) 0x72, (byte) 0x36, (byte) 0xa1, (byte) 0xd1, (byte) 0x77},
                                    {(byte) 0xd3, (byte) 0xb2, (byte) 0xe7, (byte) 0x9b, (byte) 0x16, (byte) 0xf0},
                                    {(byte) 0xa0, (byte) 0xa1, (byte) 0xa2, (byte) 0xa3, (byte) 0xa4, (byte) 0xa5},
                                    {(byte) 0xa0, (byte) 0xa1, (byte) 0xa2, (byte) 0xa3, (byte) 0xa4, (byte) 0xa5}};
    private static NfcAdapter mAdapter = null;
    private static PendingIntent mPendingIntent = null;
    private static IntentFilter ndef = null;
    private static IntentFilter [] mFilters = null;
    private static String [][] mTechLists = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //LinearLayout lView = (LinearLayout)findViewById(R.id.textView);

        //TextView textView = (TextView) findViewById(R.id.textView);
        //textView.setText("");
        mAdapter = NfcAdapter.getDefaultAdapter(this);
        mPendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        ndef = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        mFilters = new IntentFilter[] {ndef,};
        mTechLists = new String[][] { new String[] { MifareClassic.class.getName() } };

        try {
            ndef.addDataType("*/*");
        } catch (IntentFilter.MalformedMimeTypeException e) {
            throw new RuntimeException("fail", e);
        }

        Log.i("TAG","COS dZiala");
        Intent intent = getIntent();
        resolveIntent(intent);
        ApduClass tmp = new ApduClass();
        byte [] array =null;
        Bundle bu = null;
        tmp.processCommandApdu(array, bu);
    }

    public void resolveIntent(Intent intent){
        String action = intent.getAction();
        TextView textView = (TextView) findViewById(R.id.textView);
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
                    auth = mfc.authenticateSectorWithKeyA(j,KEY_A[0]);
                    if(auth){
                       /*bCount = mfc.getBlockCount();
                        bIndex = 0;
                        for(int i = 0; i < bCount; i++){
                            bIndex = mfc.sectorToBlock(j);
                            // 6.3) Read the block
                            data = mfc.readBlock(bIndex);
                            // 7) Convert the data into a string from Hex format.
                            Log.i("TAG", getHexString(data, data.length));
                            bIndex++;*/
                        int counter = 0;
                        while(true) {
                            if ( counter != 0 && counter % 4 == 0)
                                break;
                            data = mfc.readBlock(bIndex);
                            //String tmp = getHexString(data, data.length);
                            Log.i("TAG", getHexString(data, data.length));
                            //textView.setText(tmp);
                            bIndex++;
                            counter ++;
                        }
                        //}
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


    @Override
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
    }


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
