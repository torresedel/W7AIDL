package com.example.admin.w7aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    ISubtractionService mService;
    private static final String TAG = "MainActivityTag";

    @BindView(R.id.etNumber1)
    EditText etNumber1;
    @BindView(R.id.etNumber2)
    EditText etNumber2;
    @BindView(R.id.result)
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Intent intentService = new Intent(this, SubtractionService.class);
        bindService(intentService, mConnection, Context.BIND_AUTO_CREATE);
    }

    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mService = ISubtractionService.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };


    public void subtract(View view) {

        int a = Integer.parseInt(etNumber1.getText().toString());
        int b = Integer.parseInt(etNumber2.getText().toString());

        int result = 0;

        Log.d(TAG, "subtract: ");
        try {
            result = mService.subtract(a, b);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        tvResult.setText("Result: "+ result);
    }
}
