package com.example.aplikasiservice;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    private Button mSetBtn;
    private Button mUnSetBtn;
    private RadioButton mMenitRadio;
    private RadioButton mLimaRadio;
    private RadioButton mTigaPuluhRadio;
    private RadioButton mJamRadio;
    private RadioGroup mTimeRadioGroup;
    public int mChargeTime = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSetBtn = (Button) findViewById(R.id.btnSet);
        mUnSetBtn = (Button) findViewById(R.id.btnUnset);
        mMenitRadio = (RadioButton) findViewById(R.id.radio0);
        mLimaRadio = (RadioButton) findViewById(R.id.radio1);
        mTigaPuluhRadio = (RadioButton) findViewById(R.id.radio2);
        mJamRadio = (RadioButton) findViewById(R.id.radio3);
        mTimeRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        mUnSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent mDisable = new Intent(MainActivity.this,WallpaperChangeService.class);
                stopService(mDisable);
                finish();
            }
        });

        mSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                int mRadioID = mTimeRadioGroup.getCheckedRadioButtonId();
                if(mMenitRadio.getId()==mRadioID) {mChargeTime=60;}
                else if(mLimaRadio.getId()==mRadioID) {mChargeTime=5*60;}
                else if(mTigaPuluhRadio.getId()==mRadioID) {mChargeTime=30*60;}
                else if(mJamRadio.getId()==mRadioID) {mChargeTime=60*60;}

                Intent mService = new Intent(MainActivity.this,WallpaperChangeService.class);
                Bundle mBundleTime = new Bundle();
                mBundleTime.putInt("durasi", mChargeTime);
                mService.putExtras(mBundleTime);
                startService(mService);
                finish();
            }
        });
    }
}
