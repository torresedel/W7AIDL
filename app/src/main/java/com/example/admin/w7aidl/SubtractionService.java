package com.example.admin.w7aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class SubtractionService extends Service {
    public SubtractionService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.

        return mBinder;
    }

    ISubtractionService.Stub mBinder = new ISubtractionService.Stub() {
        @Override
        public int subtract(int a, int b) throws RemoteException {
            return a - b;
        }
    };
}
