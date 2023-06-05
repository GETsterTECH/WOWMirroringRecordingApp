package com.wowmirroringrecordingapp.bluetoothservice;

import android.content.Intent;
import android.os.Build;
import android.service.quicksettings.TileService;

import androidx.annotation.RequiresApi;

import com.wowmirroringrecordingapp.activity.StartScreenMirroringRecording;

@RequiresApi(api = Build.VERSION_CODES.N)
public class BluetoothTileService extends TileService {

    @Override
    public void onClick() {
        try {
            Intent intent = new Intent(this, StartScreenMirroringRecording.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
