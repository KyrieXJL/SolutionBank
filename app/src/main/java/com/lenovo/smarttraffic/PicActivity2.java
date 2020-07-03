package com.lenovo.smarttraffic;

import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

public class PicActivity2 extends AppCompatActivity {

    private ImageView imagepic;
    private ScaleGestureDetector scaleGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic2);
        initView();
    }

    private void initView() {
        imagepic = (ImageView) findViewById(R.id.imagepic);
        imagepic.setImageResource(R.mipmap.car);
        imagepic.setScaleType(ImageView.ScaleType.MATRIX);
        scaleGestureDetector=new ScaleGestureDetector(this, new ScaleGestureDetector.OnScaleGestureListener() {
            Matrix matrix=new Matrix();

            @Override
            public boolean onScale(ScaleGestureDetector scaleGestureDetector) {

                float f=scaleGestureDetector.getScaleFactor()/3;
                matrix.setScale(f,f);
                imagepic.setImageMatrix(matrix);
                return false;
            }

            @Override
            public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
                return true;
            }

            @Override
            public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {

            }
        });
    }
}
