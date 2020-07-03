package com.lenovo.smarttraffic;

import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class


PicActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView image04_2;
    private ScaleGestureDetector gestureDetector;
    private Button btn04;
    private ImageView image04_2_1;
    private ImageView image04_2_2;
    private ImageView image04_2_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);
        initView();
    }

    private void initView() {
        image04_2 = (ImageView) findViewById(R.id.image04_2);
        image04_2_1 = (ImageView) findViewById(R.id.image04_2_1);
        image04_2_2 = (ImageView) findViewById(R.id.image04_2_2);
        image04_2_3 = (ImageView) findViewById(R.id.image04_2_3);
        image04_2.setImageResource(R.drawable.weizhang01);
        image04_2_1.setImageResource(R.drawable.weizhang02);
        image04_2_2.setImageResource(R.drawable.weizhang03);
        image04_2_3.setImageResource(R.drawable.weizhang04);


        btn04 = (Button) findViewById(R.id.btn04);
        btn04.setOnClickListener(this);
        image04_2.setScaleType(ImageView.ScaleType.MATRIX);//设置缩放模式
        image04_2_1.setScaleType(ImageView.ScaleType.MATRIX);
        image04_2_2.setScaleType(ImageView.ScaleType.MATRIX);
        image04_2_3.setScaleType(ImageView.ScaleType.MATRIX);
        gestureDetector = new ScaleGestureDetector(this, new ScaleGestureDetector.OnScaleGestureListener() {
            Matrix matrix = new Matrix();

            @Override
            public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                float f = scaleGestureDetector.getScaleFactor() / 3;
                matrix.setScale(f, f);
                image04_2.setImageMatrix(matrix);
                image04_2_1.setImageMatrix(matrix);
                image04_2_2.setImageMatrix(matrix);
                image04_2_3.setImageMatrix(matrix);
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
        image04_2_1 = (ImageView) findViewById(R.id.image04_2_1);
        image04_2_2 = (ImageView) findViewById(R.id.image04_2_2);
        image04_2_3 = (ImageView) findViewById(R.id.image04_2_3);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn04:
                finish();

                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
}
