package com.example.mike.nfc_app;

//import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.math.BigInteger;


public class MainActivity extends Activity {
    private TextView myText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //LinearLayout lView = (LinearLayout)findViewById(R.id.textView);

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("text you want to display\n HEHE\nHey guys, I know this is very basic but I need to know how I can display the contents of a variable on the screen.\n" +
                "\n" +
                "Do I use a textview in my layout?\n" +
                "\n" +
                "I have a textview box and I can set it to say something in the editor but I need to write the contents of a variable so I can do some error checking.\n" +
                "\n" +
                "Anyone help?Hey guys, I know this is very basic but I need to know how I can display the contents of a variable on the screen.\n" +
                "\n" +
                "Do I use a textview in my layout?\n" +
                "\n" +
                "I have a textview box and I can set it to say something in the editor but I need to write the contents of a variable so I can do some error checking.\n" +
                "\n" +
                "Anyone help?Hey guys, I know this is very basic but I need to know how I can display the contents of a variable on the screen.\n" +
                "\n" +
                "Do I use a textview in my layout?\n" +
                "\n" +
                "I have a textview box and I can set it to say something in the editor but I need to write the contents of a variable so I can do some error checking.\n" +
                "\n" +
                "Anyone help?Hey guys, I know this is very basic but I need to know how I can display the contents of a variable on the screen.\n" +
                "\n" +
                "Do I use a textview in my layout?\n" +
                "\n" +
                "I have a textview box and I can set it to say something in the editor but I need to write the contents of a variable so I can do some error checking.\n" +
                "\n" +
                "Anyone help?Hey guys, I know this is very basic but I need to know how I can display the contents of a variable on the screen.\n" +
                "\n" +
                "Do I use a textview in my layout?\n" +
                "\n" +
                "I have a textview box and I can set it to say something in the editor but I need to write the contents of a variable so I can do some error checking.\n" +
                "\n" +
                "Anyone help?Hey guys, I know this is very basic but I need to know how I can display the contents of a variable on the screen.\n" +
                "\n" +
                "Do I use a textview in my layout?\n" +
                "\n" +
                "I have a textview box and I can set it to say something in the editor but I need to write the contents of a variable so I can do some error checking.\n" +
                "\n" +
                "Anyone help?Hey guys, I know this is very basic but I need to know how I can display the contents of a variable on the screen.\n" +
                "\n" +
                "Do I use a textview in my layout?\n" +
                "\n" +
                "I have a textview box and I can set it to say something in the editor but I need to write the contents of a variable so I can do some error checking.\n" +
                "\n" +
                "Anyone help?");
        ApduClass tmp = new ApduClass();
        byte [] array =null;
        Bundle bu = null;
        tmp.processCommandApdu(array, bu);
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
