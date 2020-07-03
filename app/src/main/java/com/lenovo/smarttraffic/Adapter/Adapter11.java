package com.lenovo.smarttraffic.Adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import com.lenovo.smarttraffic.Entity.Car11;
import com.lenovo.smarttraffic.R;

import java.util.List;

public class Adapter11 extends ArrayAdapter<Car11> {
    public Adapter11(@NonNull Context context, int resource, @NonNull List<Car11> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        Car11 car11=getItem(position);
        if (convertView==null){
            view=LayoutInflater.from(getContext()).inflate(R.layout.fragment11_list,null,false);
            viewHolder=new ViewHolder();
            viewHolder.way=view.findViewById(R.id.way11);
            viewHolder.red=view.findViewById(R.id.red11);
            viewHolder.green=view.findViewById(R.id.green11);
            viewHolder.yellow=view.findViewById(R.id.yellow11);
            viewHolder.checkBox=view.findViewById(R.id.checkbox11);
            viewHolder.button=view.findViewById(R.id.btn11);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }


        viewHolder.way.setText(String.valueOf(car11.getWay()));
        viewHolder.red.setText(String.valueOf(car11.getRed()));
        viewHolder.yellow.setText(String.valueOf(car11.getYellow()));
        viewHolder.green.setText(String .valueOf(car11.getGreen()));
        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                car11.setSelected(b);
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
                View in=LayoutInflater.from(getContext()).inflate(R.layout.fragment11_item,null,false);
                EditText addred=in.findViewById(R.id.addred11);
                EditText addyellow=in.findViewById(R.id.addyellow11);
                EditText addgreen=in.findViewById(R.id.addgreen11);
                builder.setPositiveButton("取消",null);
                builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (TextUtils.isEmpty(addred.getText().toString())||TextUtils.isEmpty(addgreen.getText().toString())||TextUtils.isEmpty(addyellow.getText().toString())){
                            Toast.makeText(getContext(), "请填写完整信息", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(), "设置成功", Toast.LENGTH_SHORT).show();
                            car11.setRed(Integer.parseInt(addred.getText().toString()));
                            car11.setYellow(Integer.valueOf(addyellow.getText().toString()));
                            car11.setGreen(Integer.valueOf(addgreen.getText().toString()));
                            notifyDataSetChanged();
                        }
                    }
                });
                builder.setView(in);
                builder.create().show();;
            }
        });
        return view;
    }

    class ViewHolder{
        TextView way,red,yellow,green;
        Button button;
        CheckBox checkBox;
    }
}
