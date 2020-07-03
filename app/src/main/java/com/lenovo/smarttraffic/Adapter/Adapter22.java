package com.lenovo.smarttraffic.Adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.smarttraffic.Entity.Car22;
import com.lenovo.smarttraffic.Helper.SQLHelper;
import com.lenovo.smarttraffic.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Adapter22 extends ArrayAdapter<Car22> {
    public Adapter22(@NonNull Context context, int resource, @NonNull List<Car22> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        Car22 car22=getItem(position);
        if (convertView==null){
            view=LayoutInflater.from(getContext()).inflate(R.layout.fragment22_list,null,false);
            viewHolder=new ViewHolder();
            viewHolder.textView1=view.findViewById(R.id.text22_list1);
            viewHolder.textView2=view.findViewById(R.id.text22_list2);
            viewHolder.button=view.findViewById(R.id.btn22);
            viewHolder.checkBox=view.findViewById(R.id.checkbox22);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.textView1.setText(String .valueOf(car22.getCarId()));
        viewHolder.textView2.setText(String.valueOf(car22.getBalance()));
        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                car22.setSelected(b);
            }
        });
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog=new ProgressDialog(getContext());
                progressDialog.setMessage("请稍等。。。");
                progressDialog.show();
                progressDialog.dismiss();
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                View in=LayoutInflater.from(getContext()).inflate(R.layout.fragment22_item,null,false);
                TextView addchehao=in.findViewById(R.id.addchehao22);
                EditText addjine=in.findViewById(R.id.addjine22);
                addchehao.setText(car22.getCarId()+"");
                builder.setPositiveButton("取消",null);
                builder.setNegativeButton("充值", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (TextUtils.isEmpty(addjine.getText().toString())){
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "请输入金额", Toast.LENGTH_SHORT).show();
                        }else if (Integer.valueOf(addjine.getText().toString())<0||Integer.valueOf(addjine.getText().toString())>999){
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "请输入正确金额", Toast.LENGTH_SHORT).show();
                        }else {
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "充值成功", Toast.LENGTH_SHORT).show();
                            car22.setBalance(Integer.valueOf(car22.getBalance())+Integer.valueOf(addjine.getText().toString()));
                            notifyDataSetChanged();
                            SQLHelper sqlHelper=new SQLHelper(getContext(),"Car221.db",null,1);
                            SQLiteDatabase db=sqlHelper.getWritableDatabase();
                            ContentValues values=new ContentValues();
                            values.put("carid",car22.getCarId());
                            values.put("money",Integer.valueOf(addjine.getText().toString()));
                            values.put("balance",Integer.valueOf(viewHolder.textView2.getText().toString())+Integer.valueOf(addjine.getText().toString()));
                            values.put("user",car22.getUser());
                            Date date=new Date();
                            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String dt=simpleDateFormat.format(date);
                            values.put("date",dt);
                            db.insert("car22",null,values);
                            sqlHelper.close();
                        }
                    }
                });
                builder.setView(in);
                builder.create().show();
            }
        });
        return view;
    }

    class ViewHolder{
        TextView textView1,textView2;
        CheckBox checkBox;
        Button button;
    }
}
