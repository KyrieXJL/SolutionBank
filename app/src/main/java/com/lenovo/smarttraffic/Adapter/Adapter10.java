package com.lenovo.smarttraffic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.lenovo.smarttraffic.Entity.Car10;
import com.lenovo.smarttraffic.R;

import java.util.List;

public class Adapter10 extends BaseExpandableListAdapter {

    private List<Car10> car10s;

    public Adapter10( List<Car10> car10s) {
        this.car10s = car10s;
    }

    @Override
    public int getGroupCount() {
        return car10s.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return car10s.get(i).getCars().size();
    }

    @Override
    public Object getGroup(int i) {
        return car10s.get(i).getStation();
    }

    @Override
    public Object getChild(int i, int i1) {
        return car10s.get(i).getCars().get(i1);
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
        Car10 car10= car10s.get(i);
        if (conterview==null){
            view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fargment10_zhu_list,null,false);
            groupViewHolder=new GroupViewHolder();
            groupViewHolder.textzhu=view.findViewById(R.id.text10_zhu_list);
            view.setTag(groupViewHolder);
        }else {
            view=conterview;
            groupViewHolder= (GroupViewHolder) view.getTag();
        }

        groupViewHolder.textzhu.setText(String.valueOf(car10.getStation()));
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View conterview, ViewGroup viewGroup) {
        View view;
        ChildViewHolder childViewHolder;
        Car10.CarsBean bean= (Car10.CarsBean) getChild(i,i1);
        if (conterview==null){
            view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment10_ci_list,null,false);
            childViewHolder=new ChildViewHolder();
            childViewHolder.text1=view.findViewById(R.id.text10_ci_list1);
            childViewHolder.text2=view.findViewById(R.id.text10_ci_list2);
            childViewHolder.text3=view.findViewById(R.id.text10_ci_list3);
            view.setTag(childViewHolder);
        }else {
            view=conterview;
            childViewHolder= (ChildViewHolder) view.getTag();
        }
        childViewHolder.text1.setText(bean.getCarid()+"号（"+bean.getPeople()+"人）");
        childViewHolder.text2.setText(1+bean.getDistance()/300+"分钟到达");
        childViewHolder.text3.setText("距离站台"+bean.getDistance()+"米");
        return view;

    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    class GroupViewHolder{
        TextView textzhu;

    }
    class ChildViewHolder{
        TextView text1,text2,text3;
    }

}
