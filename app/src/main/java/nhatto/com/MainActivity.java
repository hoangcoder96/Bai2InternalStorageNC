package nhatto.com;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvData;
    private Button btnSaveData, btnReadData;
    private final String FILE_NAME = "nhatto.com";
    private final String CONTENT = "Blog chia se kien thuc lap trinh!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ////// TODO: 26/05/2017  
        tvData = (TextView) findViewById(R.id.tv_data);
        btnSaveData = (Button) findViewById(R.id.btn_save_data);
        btnReadData = (Button) findViewById(R.id.btn_read_data);
        ////// TODO: 26/05/2017  
        btnSaveData.setOnClickListener(this);
        btnReadData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_save_data:
                ////// TODO: 26/05/2017
                //saveData();
                saveDataByCache();
                break;
            case R.id.btn_read_data:
                ////// TODO: 26/05/2017
                //readData();
                readData2();
                break;


            default:
                break;
        }
    }
    ////// TODO: 26/05/2017 cach1
    public void saveData(){
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fileOutputStream.write(CONTENT.getBytes());
            fileOutputStream.close();
            Toast.makeText(this, "Save Data Successfully", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /////// TODO: 26/05/2017 cach2
    public void saveDataByCache(){
        FileOutputStream fileOutputStream = null;
        File file = null;

        try {
            file = new File(getCacheDir(),FILE_NAME);
            Log.d("MainActivity", getCacheDir().getAbsolutePath());
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(CONTENT.getBytes());
            fileOutputStream.close();
            Toast.makeText(this, "Save Data Successfully", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ////// TODO: 26/05/2017 cach1
    public void readData(){
        try {
            FileInputStream inputStream = openFileInput(FILE_NAME);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer buffer = new StringBuffer();
            String line = null;
            while ((line = br.readLine())!= null){
                buffer.append(line).append("\n");
            }
            Log.d("MainActivity", buffer.toString());
            tvData.setText(buffer.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ////// TODO: 26/05/2017 cach2
    public void readData2(){
        try {
            File file = new File(getFilesDir(), FILE_NAME);
            FileInputStream inputStream = openFileInput(FILE_NAME);
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            StringBuffer buffer = new StringBuffer();
            String line = null;
            while ((line = br.readLine())!= null){
                buffer.append(line).append("\n");
            }
            Log.d("MainActivity", buffer.toString());
            tvData.setText(buffer.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
