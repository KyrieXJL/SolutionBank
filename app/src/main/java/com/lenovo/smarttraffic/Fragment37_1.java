package com.lenovo.smarttraffic;


import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment37_1 extends Fragment {


    private ImageView image37_1,ivZoom;
    private HashMap hints;
    private Bitmap bitmap,bitmap1;
    private TextView text37;
    //private LinearLayout llZoom;
    private String str;

    public Fragment37_1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment37_1, container, false);
        initView(view);
        //llZoom.setVisibility(View.GONE);


      /*  //正则表达式
        Pattern pattern = Pattern.compile("^[a-zA-Z]{4}\\w*$");
        Matcher matcher =pattern.matcher("123B");
        boolean rs = matcher.matches();*/

        //telphone==>******

        return view;
    }

    private void initView(View view) {
        image37_1 = (ImageView) view.findViewById(R.id.image37_1);
        text37 = (TextView) view.findViewById(R.id.text37);
        //llZoom=view.findViewById(R.id.ll_zoom);
        ivZoom=view.findViewById(R.id.iv_zoom);
        //  hints=new HashMap();
        // hints.put(EncodeHintType.CHARACTER_SET,"utf-8");  //定义内容字符集的编码
        // hints.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.L);
        //  hints.put(EncodeHintType.MARGIN,2);
        Bundle bundle = getArguments();
        str = bundle.getString("values1");
        if (str.equals("")) {
            Toast.makeText(getContext(), "没数据", Toast.LENGTH_SHORT).show();
        } else {
            bitmap=createBitmap(str,500,500);
            image37_1.setImageBitmap(bitmap);
            image37_1.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    show();
                    return false;
                }
            });

        }




      image37_1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              AlertDialog.Builder builder=new AlertDialog.Builder(getActivity(),R.style.DialogTheme);
              View llZoom=View.inflate(getActivity(),R.layout.full_screen,null);
              ivZoom=llZoom.findViewById(R.id.iv_zoom);
//              llZoom.post(new Runnable() {
//                  @Override
//                  public void run() {
//                     int width=llZoom.getMeasuredWidth();
//                     int height=llZoom.getMeasuredHeight();
//                    int s=width>height?height:width;
//                     Bitmap bitmap1=scaleBitmap(bitmap,s,s);
//                     ivZoom.setImageBitmap(bitmap1);
//                  }
//              });
              Bitmap bitmap1=scaleBitmap(bitmap,1200,1200);
              ivZoom.setImageBitmap(bitmap1);
              builder.setView(llZoom);
              AlertDialog alertDialog=builder.create();


              llZoom.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      alertDialog.cancel();
                  }
              });
              alertDialog.show();

          }
      });

//        llZoom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                llZoom.setVisibility(View.GONE);
//            }
//        });

    }

    private Bitmap scaleBitmap(Bitmap origin, int newWidth, int newHeight) {
        if (origin == null) {
            return null;
        }
        int height = origin.getHeight();
        int width = origin.getWidth();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);// 使用后乘
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);

        return newBM;
    }




    private  Bitmap createBitmap(String data,int width,int height)
     {
         BitMatrix bitMatrix = null;
         try {
             String contents = new String(data.getBytes("UTF-8"), "ISO-8859-1");
             try {
                 bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height);
             } catch (WriterException e) {
                 e.printStackTrace();
             }

         } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
         }
         ;


         int[] pixels = new int[width * height];
         for (int y = 0; y < height; y++) {
             for (int x = 0; x < width; x++) {
                 if (bitMatrix.get(x, y)) {
                     pixels[y * width + x] = Color.BLACK;
                 } else {
                     pixels[y * width + x] = Color.WHITE;
                 }
             }
         }
            /*bitmap=Bitmap.createBitmap(width,height, Bitmap.Config.RGB_565);
            bitmap.setPixels(pixels,0,width,0,0,width,height);
            image37_1.setImageBitmap(bitmap);*/
         bitmap = Bitmap.createBitmap(bitMatrix.getWidth(), bitMatrix.getHeight(), Bitmap.Config.RGB_565);
         bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
         return bitmap;
     }
    private void show() {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] piexs = new int[width * height];
        bitmap.getPixels(piexs, 0, width, 0, 0, width, height);
        Result result = null;
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new RGBLuminanceSource(width, height, piexs)));

        try {
            result = new MultiFormatReader().decode(binaryBitmap);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        text37.setText(result+"");


    }
}
