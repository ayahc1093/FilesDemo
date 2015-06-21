package com.example.mcberliner.filesdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainActivity extends Activity {

    private EditText etInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput = (EditText) findViewById(R.id.etInput);
    }

    public void writeToFile(View v) {
        try {
            String inputStr = etInput.getText().toString();
            FileOutputStream fos = openFileOutput("myfile.txt", MODE_PRIVATE);
            fos.write(inputStr.getBytes());
            fos.flush();
            fos.close();

            Toast.makeText(this, "File saved successfuly!", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromFile(View v) {
        try {
            FileInputStream fis = openFileInput("myfile.txt");
            StringBuffer stringBuffer = new StringBuffer();
            BufferedReader bReader = new BufferedReader(new InputStreamReader(fis));
            String strLine = null;

            while((strLine = bReader.readLine()) != null) {
                stringBuffer.append(strLine + "\n");
            }

            bReader.close();
            fis.close();

            Toast.makeText(this, "File Content: \n" + stringBuffer.toString(), Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
