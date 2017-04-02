package com.mytrendin.opengldemo;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Shree on 4/2/2017.
 */

public class Triangle {
    private FloatBuffer vertexBuffer;
    private float vertices[]=new float[]{
        0.0f, 0.5f,0.0f,
        -0.8f,-0.5f,0.0f,
        0.8f,-0.5f,0.0f
    };
    private float color[]=new float[]{0.0f,0.0f,0.0f,1.0f};
    private final String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = vPosition;" +
                    "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";
    private int sharderProgram;
    public  static int loadShader(int type, String shaderCode){
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader,shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }
    public Triangle(){
        ByteBuffer bfr = ByteBuffer.allocateDirect(vertices.length *4);
        bfr.order(ByteOrder.nativeOrder());
        vertexBuffer = bfr.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);
        int vertexShader= loadShader(GLES20.GL_VERTEX_SHADER,vertexShaderCode);
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER,fragmentShaderCode);

        sharderProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(sharderProgram,vertexShader);
        GLES20.glAttachShader(sharderProgram,fragmentShader);
        GLES20.glLinkProgram(sharderProgram);
    }
    public void draw(){
        GLES20.glUseProgram(sharderProgram);
        int positionAttrib = GLES20.glGetAttribLocation(sharderProgram,"vPosition");
        GLES20.glEnableVertexAttribArray(positionAttrib);
        GLES20.glVertexAttribPointer(positionAttrib,3,GLES20.GL_FLOAT,false,0,vertexBuffer);
   int colorUniform = GLES20.glGetUniformLocation(sharderProgram,"vColor");
        GLES20.glUniform4fv(colorUniform,1,color,0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES,0,vertices.length / 3);
        GLES20.glDisableVertexAttribArray(positionAttrib);

    }
}
