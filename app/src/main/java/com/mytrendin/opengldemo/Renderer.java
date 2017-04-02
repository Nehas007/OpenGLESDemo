package com.mytrendin.opengldemo;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Shree on 4/2/2017.
 */

public class Renderer implements GLSurfaceView.Renderer {
    Triangle triangle;
    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {

        triangle =new Triangle();
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i1) {

    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        GLES20.glClearColor(0.0f,0.8f,0.0f,1.0f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        triangle.draw();
    }
}
