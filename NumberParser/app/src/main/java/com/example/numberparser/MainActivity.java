package com.example.numberparser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public String dxnumber ="01234";
    public String uNumber = "+447413734189";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText number = (EditText)findViewById(R.id.dNumber);
        final TextView userNumber = (TextView)findViewById(R.id.textView01);
        final TextView dialledNumber = (TextView)findViewById(R.id.textView02);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dxnumber = number.getText().toString();

                Map<String, Integer> countryCodes = new HashMap<>();
                Map<String, String> prefixes = new HashMap<>();
                countryCodes.put("GB", 44); prefixes.put("GB", "0");
                countryCodes.put("US", 1); prefixes.put("US", "1");
                countryCodes.put("SL", 94); prefixes.put("SL", "0");

                NumberParser nParser = new NumberParser(countryCodes,prefixes);
                dxnumber = nParser.parse(dxnumber,uNumber);

                userNumber.setText("User Number: "+uNumber);
                dialledNumber.setText("Dialled Number: "+dxnumber);
            }
        });
    }
}
