package com.hackfa16.godutch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class TipActivity extends AppCompatActivity {

    public double totalTip;
    public RContents items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButton0:
                if (checked)
                    totalTip = 0;
                    break;
            case R.id.radioButton15:
                if (checked)
                    totalTip = .15*items.getTotal().getPrice();
                    break;
            case R.id.radioButton18:
                if (checked)
                    totalTip = .18*items.getTotal().getPrice();
                    break;
            case R.id.radioButton20:
                if (checked)
                    totalTip = .2*items.getTotal().getPrice();
                    break;
        }
    }
}
