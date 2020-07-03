package com.lenovo.smarttraffic.Adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lenovo.smarttraffic.Entity.Car09;
import com.lenovo.smarttraffic.Helper.SQLHelper;
import com.lenovo.smarttraffic.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Adapter09 extends ArrayAdapter<Car09> {
    public Adapter09(@NonNull Context context, int resource, @NonNull List<Car09> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        Car09 car09=getItem(position);
        if (convertView==null){
            view=LayoutInflater.from(getContext()).inflate(R.layout.fragment09_list,null,false);
            viewHolder=new ViewHolder();
            viewHolder.text1=view.findViewById(R.id.text09_1);
            viewHolder.text2=view.findViewById(R.id.text09_2);
            viewHolder.text3=view.findViewById(R.id.text09_3);
            viewHolder.text4=view.findViewById(R.id.text09_4);
            viewHolder.imageView=view.findViewById(R.id.image09);
            viewHolder.button=view.findViewById(R.id.btn09);
            viewHolder.checkBox=view.findViewById(R.id.checkbox09);
            viewHolder.background=view.findViewById(R.id.background09);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }

        viewHolder.text1.setText(String.valueOf(car09.getCarId()));
        viewHolder.text2.setText(String.valueOf(car09.getCard()));
        viewHolder.text3.setText("车主："+car09.getUser());
        viewHolder.text4.setText(String.valueOf(car09.getBalance()));
        Glide.with(getContext()).load(car09.getImage()).into(viewHolder.imageView);
        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                car09.setSelected(b);
            }
        });
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog=new ProgressDialog(getContext());
                progressDialog.setMessage("请稍等。。。");
                progressDialog.show();
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                View in=LayoutInflater.from(getContext()).inflate(R.layout.fragment09_item,null,false);
                TextView addchehoa=in.findViewById(R.id.addchehao09);
                EditText addmoney=in.findViewById(R.id.addmoney09);
                addchehoa.setText(car09.getCard()+"");
                builder.setPositiveButton("取消",null);
                builder.setNegativeButton("充值", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (TextUtils.isEmpty(addmoney.getText().toString())){
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "请输入金额", Toast.LENGTH_SHORT).show();
                        }else if (Integer.valueOf(addmoney.getText().toString())<0||Integer.valueOf(addmoney.getText().toString())>999){
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "请输入正确金额", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(), "充值成功", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            car09.setBalance(Integer.valueOf(viewHolder.text4.getText().toString())+Integer.valueOf(addmoney.getText().toString()));
                            notifyDataSetChanged();
                            SQLHelper sqlHelper=new SQLHelper(getContext(),"Car0921.db",null,1);
                            SQLiteDatabase db=sqlHelper.getWritableDatabase();
                            ContentValues values=new ContentValues();
                            values.put("card",car09.getCard());
                            values.put("money",Integer.valueOf(addmoney.getText().toString()));
                            values.put("balance",Integer.valueOf(viewHolder.text4.getText().toString())+Integer.valueOf(addmoney.getText().toString()));
                            values.put("user",car09.getUser());
                            Date date=new Date();
                            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String dt=simpleDateFormat.format(date);
                            values.put("date",dt);
                            db.insert("car092",null,values);
                            sqlHelper.close();
                        }
                    }
                });

                builder.setView(in);
                builder.create().show();

            }
        });
        if (Integer.valueOf(viewHolder.text4.getText().toString())>50){
            viewHolder.background.setBackgroundColor(Color.WHITE);
        }else {
            viewHolder.background.setBackgroundColor(Color.YELLOW);
        }

        return view;
    }

    class ViewHolder{
        TextView text1,text2,text3,text4;
        Button button;
        CheckBox checkBox;
        ImageView imageView;
        LinearLayout background;
    }
}
