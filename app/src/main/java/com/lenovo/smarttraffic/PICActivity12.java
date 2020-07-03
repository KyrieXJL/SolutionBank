package com.lenovo.smarttraffic;

import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class PICActivity12 extends AppCompatActivity implements View.OnClickListener {

    private ImageView image12_pic1;
    private ImageView image12_pic2;
    private ImageView image12_pic3;
    private ImageView image12_pic4;
    private Button btn12_pic;
    private ScaleGestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic12);
        initView();
    }

    private void initView() {
        image12_pic1 = (ImageView) findViewById(R.id.image12_pic1);
        image12_pic2 = (ImageView) findViewById(R.id.image12_pic2);
        image12_pic3 = (ImageView) findViewById(R.id.image12_pic3);
        image12_pic4 = (ImageView) findViewById(R.id.image12_pic4);
        btn12_pic = (Button) findViewById(R.id.btn12_pic);

        btn12_pic.setOnClickListener(this);
        image12_pic1.setScaleType(ImageView.ScaleType.MATRIX);
        image12_pic2.setScaleType(ImageView.ScaleType.MATRIX);
        image12_pic3.setScaleType(ImageView.ScaleType.MATRIX);
        image12_pic4.setScaleType(ImageView.ScaleType.MATRIX);
        gestureDetector=new ScaleGestureDetector(this, new ScaleGestureDetector.OnScaleGestureListener() {
            Matrix matrix=new Matrix();
            @Override
            public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                float f=gestureDetector.getScaleFactor()/3;
                matrix.setScale(f,f);
                image12_pic1.setImageMatrix(matrix);
                image12_pic2.setImageMatrix(matrix);
                image12_pic3.setImageMatrix(matrix);
                image12_pic4.setImageMatrix(matrix);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn12_pic:
                finish();

                break;
        }
    }
}
