package com.hackfa16.godutch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class TipActivity extends AppCompatActivity {

    public double tipRate;
    public RContents items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);

        Intent intent = getIntent();
        items = (RContents)intent.getSerializableExtra("receipt");
        System.out.println("tip act");
        System.out.println(items==null);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButton0:
                if (checked)
                    tipRate = 0;
                    break;
            case R.id.radioButton15:
                if (checked)
                    tipRate = .15;
                    break;
            case R.id.radioButton18:
                if (checked)
                    tipRate = .18;
                    break;
            case R.id.radioButton20:
                if (checked)
                    tipRate = .2;
                    break;
        }
        Intent intent = new Intent(this, SelectorActivity.class);
        Bundle extras = new Bundle();
        extras.putDouble("tipRate", tipRate);
        extras.putSerializable("receipts", items);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
