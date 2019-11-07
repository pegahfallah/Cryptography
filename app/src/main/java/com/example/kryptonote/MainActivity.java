package com.example.kryptonote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kryptonote_layout);
    }

    public void onEncrypt(View v){
    try {
        //cipher instance then pass key
        EditText encryptView = (EditText) findViewById(R.id.key);
        String myKey = encryptView.getText().toString();

        EditText data = (EditText) findViewById(R.id.data);
        String userData = data.getText().toString();

        Cipher cipher = new Cipher(myKey);
        String result = cipher.encrypt(userData);

        ((TextView) findViewById(R.id.data)).setText(result);
    }
    catch (Exception e){
        Toast label = makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
        label.show();
        }
    }
    public void onDecrypt(View v) {
        try {
            EditText decryptView = (EditText) findViewById(R.id.key);
            String myKey = decryptView.getText().toString();

            EditText data = (EditText) findViewById(R.id.data);
            String userData = data.getText().toString();

            Cipher cipher = new Cipher(myKey);
            String result = cipher.decrypt(userData);

            ((TextView) findViewById(R.id.data)).setText(result);
        }

        catch(Exception e){
            Toast label = makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
            label.show();
        }

    }
    public void onSave(View v){
       try {
           String name = ((EditText) findViewById(R.id.file)).getText().toString();
           File dir = this.getFilesDir();
           File file = new File(dir, name);
           FileWriter fw = new FileWriter(file);
           fw.write(((EditText) findViewById(R.id.data)).getText().toString());
           fw.close();
           Toast label = Toast.makeText(this, "Note Saved. ", Toast.LENGTH_LONG);
           label.show();
       }
       catch (Exception e){
           Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
       }
    }
    public void onLoad(View v){
        try {
            String name = ((EditText) findViewById(R.id.file)).getText().toString();
            File dir = this.getFilesDir();
            File file = new File(dir, name);
            FileReader fr = new FileReader(file);

            String show = "";
            for (int c = fr.read(); c != -1; c = fr.read())
            {
                show += (char) c;
            }
            fr.close();
            ((EditText) findViewById(R.id.data)).setText(show);
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
        }
    }
}
