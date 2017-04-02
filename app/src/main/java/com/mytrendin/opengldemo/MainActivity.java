package com.mytrendin.opengldemo;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // https://www.youtube.com/watch?v=OtQBTUZeVv8&t=28s
        //Justin's Tutorials
        ActivityManager am= (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo info = am.getDeviceConfigurationInfo();
        boolean supportES2 = (info.reqGlEsVersion >= 0x20000);
        if(supportES2){
            Renderer renderer = new Renderer();
            SurfaceView surfaceView = new SurfaceView(this);
            surfaceView.setEGLContextClientVersion(2);
            surfaceView.setRenderer(renderer);
            this.setContentView(surfaceView);
        }
        else
            Log.e("OpelGLES 2","Your device doesn't support ES2, ("+info.reqGlEsVersion+")");
    }
}
