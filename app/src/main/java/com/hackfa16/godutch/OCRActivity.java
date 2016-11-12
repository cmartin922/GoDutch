package com.hackfa16.godutch;

import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Bitmap;
import android.content.res.AssetManager;
import android.widget.TextView;
import android.R.id;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;

import com.googlecode.tesseract.android.TessBaseAPI;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class OCRActivity extends AppCompatActivity {
    Bitmap image;
    private TessBaseAPI mTess;
    String datapath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocr);

        //init image
        image = BitmapFactory.decodeResource(getResources(), R.drawable.rolls);

        datapath = getFilesDir()+ "/tesseract/";

        //make sure training data has been copied
        checkFile(new File(datapath + "tessdata/"));

        //init Tesseract API
        String language = "eng";

        mTess = new TessBaseAPI();
        mTess.init(datapath, language);

        String  contents = processImage();
        parseText(contents);
    }

    private void copyFile() {
        try {
            //location we want the file to be at
            String filepath = datapath + "/tessdata/eng.traineddata";

            //get access to AssetManager
            AssetManager assetManager = getAssets();

            //open byte streams for reading/writing
            InputStream instream = assetManager.open("tessdata/eng.traineddata");
            OutputStream outstream = new FileOutputStream(filepath);

            //copy the file to the location specified by filepath
            byte[] buffer = new byte[1024];
            int read;
            while ((read = instream.read(buffer)) != -1) {
                outstream.write(buffer, 0, read);
            }
            outstream.flush();
            outstream.close();
            instream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkFile(File dir) {
        //directory does not exist, but we can successfully create it
        if (!dir.exists()&& dir.mkdirs()){
            copyFile();
        }
        //The directory exists, but there is no data file in it
        if(dir.exists()) {
            String datafilepath = datapath+ "/tessdata/eng.traineddata";
            File datafile = new File(datafilepath);
            if (!datafile.exists()) {
                copyFile();
            }
        }
    }

    public String processImage(){
        mTess.setImage(image);
        String OCRresult = mTess.getUTF8Text();
        return OCRresult;
    }

    public class REntry {
        String name;
        String price;
        public REntry(String n, String p) {
            name = n;
            price = p;
        }

        public String getName() {
            return name;
        }

        public String getPrice() {
            return price;
        }
    }

    public class recContents {
        REntry total;
        REntry tax;

        REntry[] items;

        public recContents(){

        }

        public REntry getTotal(){
            return total;
        }
        public REntry getTax(){
            return tax;
        }
        public REntry[] getItems(){
            return items;
        }
    }


    private void parseText(String s) {
        String[] lines = s.split("\\r?\\n");

        for(int i = 0; i < lines.length;i++){
            if ((lines[i].contains("."))) {
                lines[i] = "bad";
            }
        }


    }
}
