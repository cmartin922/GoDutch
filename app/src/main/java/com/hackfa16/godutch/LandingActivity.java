package com.hackfa16.godutch;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class LandingActivity extends AppCompatActivity {
    private final static int REQUEST_IMAGE_CAPTURE = 1;
    private final static int REQUEST_SELECT_IMAGE = 2;
    private Uri mLocationForPhotos;
    private String filePath;
    private File photoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        filePath = Environment.getExternalStorageDirectory().getPath();
        filePath = filePath + "/" + "temp.png";
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void openCamera(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                System.out.println("Error creating image file");
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                mLocationForPhotos = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mLocationForPhotos);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    public void openImagePicker(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, REQUEST_SELECT_IMAGE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            System.out.println(mLocationForPhotos);
            Intent intent = new Intent(this, OCRActivity.class);
            intent.putExtra("imageUri", mLocationForPhotos.toString());
            startActivity(intent);
        }
        if (requestCode == REQUEST_SELECT_IMAGE && resultCode == RESULT_OK) {
            mLocationForPhotos = data.getData();
            System.out.println(mLocationForPhotos);
            Intent intent = new Intent(this, OCRActivity.class);
            intent.putExtra("imageUri", mLocationForPhotos.toString());
            startActivity(intent);
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        filePath = "file:" + image.getAbsolutePath();
        return image;
    }

}
