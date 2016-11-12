package com.hackfa16.godutch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Selector extends Activity {

    public RContents items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);
        Intent getRContents = getIntent();
    }

}
