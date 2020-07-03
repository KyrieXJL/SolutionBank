package com.lenovo.smarttraffic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.lenovo.smarttraffic.Entity.Car28;
import com.lenovo.smarttraffic.R;

import java.util.List;

public class Adapter28 extends BaseExpandableListAdapter {
    private Context context;
    private List<Car28> car28s;

    public Adapter28(Context context, List<Car28> car28s) {
        this.context = context;
        this.car28s = car28s;
    }

    @Override
    public int getGroupCount() {
        return car28s.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return car28s.get(i).getCars().size();
    }

    @Override
    public Object getGroup(int i) {
        return car28s.get(i).getStation();
    }

    @Override
    public Object getChild(int i, int i1) {
        return car28s.get(i).getCars().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View conterview, ViewGroup viewGroup) {
        View view;
        GroupViewHolder groupViewHolder;
        Car28 car28=car28s.get(i);
        if (conterview==null){
            view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment28_zhu_list,null,false);
            groupViewHolder=new GroupViewHolder();
            groupViewHolder.textView=view.findViewById(R.id.text28_zhu);
            view.setTag(groupViewHolder);
        }else {
            view=conterview;
            groupViewHolder= (GroupViewHolder) view.getTag();
        }
        groupViewHolder.textView.setText(car28.getStation());
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View conterview, ViewGroup viewGroup) {
        View view;
        ChildViewHolder childViewHolder;
        Car28.CarsBean carsBean= (Car28.CarsBean) getChild(i,i1);
        if (conterview==null){
            view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment28_ci_list,null,false);
            childViewHolder=new ChildViewHolder();
            childViewHolder.textView2=view.findViewById(R.id.text28_ci_2);
            childViewHolder.textView1=view.findViewById(R.id.text28_ci_1);
            view.setTag(childViewHolder);
        }else {
            view=conterview;
            childViewHolder= (ChildViewHolder) view.getTag();
        }
        childViewHolder.textView2.setText(carsBean.getDistance()+"米");
        childViewHolder.textView1.setText(carsBean.getCarid()+"号公交车");
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
    class GroupViewHolder{
        TextView textView;
    }
    class ChildViewHolder{
        TextView textView1,textView2;
    }
}
