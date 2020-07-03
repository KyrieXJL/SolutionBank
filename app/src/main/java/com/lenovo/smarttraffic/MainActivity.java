package com.lenovo.smarttraffic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.lenovo.smarttraffic.ui.activity.BaseActivity;
import com.lenovo.smarttraffic.ui.activity.LoginActivity;
import com.lenovo.smarttraffic.ui.fragment.DesignFragment;
import com.lenovo.smarttraffic.ui.fragment.MainContentFragment;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author Amoly
 * @date 2019/4/11.
 * description：
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener{

    private Toolbar mToolbar;
    private DrawerLayout mDrawer;
    private MainContentFragment mMainContent;
    private DesignFragment mDesignFragment;
    private static final String POSITION = "position";
    private static final String SELECT_ITEM = "bottomItem";
    private static final int FRAGMENT_MAIN = 0;
    private static final int FRAGMENT_DESIGN = 1;
    private BottomNavigationView bottom_navigation;
    private long exitTime = 0;
    //    private int position;
    private FragmentManager fragmentManager;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        if (savedInstanceState != null) {
            loadMultipleRootFragment(R.id.container,0,mMainContent, mDesignFragment);   //使用fragmentation加载根组件
            // 恢复 recreate 前的位置
            showFragment(savedInstanceState.getInt(POSITION));
            bottom_navigation.setSelectedItemId(savedInstanceState.getInt(SELECT_ITEM));
        } else {
            showFragment(FRAGMENT_MAIN);
        }
    }

    private void showFragment(int index) {
//        position = index;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        switch (index) {
            case FRAGMENT_MAIN:
                mToolbar.setTitle(R.string.title_main);
                if (mMainContent == null){
                    mMainContent = MainContentFragment.getInstance();
                    ft.add(R.id.container,mMainContent,MainContentFragment.class.getName());
                }else {
                    ft.show(mMainContent);
                }
                break;
            case FRAGMENT_DESIGN:
                mToolbar.setTitle(R.string.creative_design);
                if (mDesignFragment == null){
                    mDesignFragment = DesignFragment.getInstance();
                    ft.add(R.id.container,mDesignFragment,DesignFragment.class.getName());
                }else {
                    ft.show(mDesignFragment);
                }
                break;
        }
        ft.commit();

    }

    private void hideFragment(FragmentTransaction ft) {
        // 如果不为空，就先隐藏起来
        if (mMainContent != null) {
            ft.hide(mMainContent);
        }
        if (mDesignFragment != null) {
            ft.hide(mDesignFragment);
        }
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        mDrawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        bottom_navigation = findViewById(R.id.bottom_navigation);
        CircleImageView imageView = navigationView.getHeaderView(0).findViewById(R.id.ivAvatar);
        setSupportActionBar(mToolbar);
        imageView.setOnClickListener(this);
        /*设置选择item监听*/
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initData() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        mDrawer.addDrawerListener(toggle);
        bottom_navigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.action_main:
                    showFragment(FRAGMENT_MAIN);
                    break;
                case R.id.action_creative:
                    showFragment(FRAGMENT_DESIGN);
                    break;
            }
            return true;
        });

    }

    @Override
    public void onBackPressedSupport() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {    /*打开或关闭左边的菜单*/
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressedSupport();
            showExitDialog();
        }
    }

    /*是否退出项目*/
    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定退出吗");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", (dialogInterface, i) -> InitApp.getInstance().exitApp());
        builder.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        fragmentManager=getSupportFragmentManager();

        /*设置选中item事件*/
        int id = item.getItemId();
        String string = null;
        switch (id){
            case R.id.nav_account:
                string = "个人";
                break;
            case R.id.item_1:
                string = "item1";
                Fragment01 fragment01=new Fragment01();
                fragmentManager.beginTransaction().replace(R.id.container,fragment01).commit();
               // startActivity(new Intent(this, Item1Activity.class));
                break;
            case R.id.item_2:
                string = "item2";
                Fragment02 fragment02=new Fragment02();
                fragmentManager.beginTransaction().replace(R.id.container,fragment02).commit();
                break;
            case R.id.item_3:
                string = "item3";
                Fragment03 fragment03=new Fragment03();
                fragmentManager.beginTransaction().replace(R.id.container,fragment03).commit();
                break;
            case R.id.item_4:
                string = "item4";
                Fragment04 fragment04=new Fragment04();
                fragmentManager.beginTransaction().replace(R.id.container,fragment04).commit();
                break;
            case R.id.item_5:
                string = "item5";
                Fragment05 fragment05=new Fragment05();
                fragmentManager.beginTransaction().replace(R.id.container,fragment05).commit();
                break;
            case R.id.item_6:
                string = "item6";
                Fragment06 fragment06=new Fragment06();
                fragmentManager.beginTransaction().replace(R.id.container,fragment06).commit();
                break;
            case R.id.item_7:
                string = "item7";
                Fragment07 fragment07=new Fragment07();
                fragmentManager.beginTransaction().replace(R.id.container,fragment07).commit();
                break;
            case R.id.item_8:
                string = "item8";
                Fragment08 fragment08=new Fragment08();
                fragmentManager.beginTransaction().replace(R.id.container,fragment08).commit();
                break;
            case R.id.item_9:
                string = "item9";
                Fragment09 fragment09=new Fragment09();
                fragmentManager.beginTransaction().replace(R.id.container,fragment09).commit();
                break;
            case R.id.item_10:
                string = "item10";
                Fragment10 fragment10=new Fragment10();
                fragmentManager.beginTransaction().replace(R.id.container,fragment10).commit();
                break;
            case R.id.item_11:
                string = "item11";
                Fragment11 fragment11=new Fragment11();
                fragmentManager.beginTransaction().replace(R.id.container,fragment11).commit();
                break;
            case R.id.item_12:
                string = "item12";
                Fragment12 fragment12=new Fragment12();
                fragmentManager.beginTransaction().replace(R.id.container,fragment12).commit();
                break;
            case R.id.item_12_1:
                string = "item12_1";
                Fragment50 fragment50=new Fragment50();
                fragmentManager.beginTransaction().replace(R.id.container,fragment50).commit();
                break;
            case R.id.item_13:
                string = "item13";
                Fragment13 fragment13=new Fragment13();
                fragmentManager.beginTransaction().replace(R.id.container,fragment13).commit();
                break;
            case R.id.item_15:
                string = "item15";
                Fragment15 fragment15=new Fragment15();
                fragmentManager.beginTransaction().replace(R.id.container,fragment15).commit();
                break;
            case R.id.item_16:
                string = "item16";
                Fragment16 fragment16=new Fragment16();
                fragmentManager.beginTransaction().replace(R.id.container,fragment16).commit();
                break;
            case R.id.item_20:
                string = "item20";
                Fragment20 fragment20=new Fragment20();
                fragmentManager.beginTransaction().replace(R.id.container,fragment20).commit();
                break;
            case R.id.item_21:
                string = "item21";
                Fragment21 fragment21=new Fragment21();
                fragmentManager.beginTransaction().replace(R.id.container,fragment21).commit();
                break;
            case R.id.item_22:
                string = "item22";
                Fragment22 fragment22=new Fragment22();
                fragmentManager.beginTransaction().replace(R.id.container,fragment22).commit();
                break;
            case R.id.item_23:
                string = "item23";
                Fragment23 fragment23=new Fragment23();
                fragmentManager.beginTransaction().replace(R.id.container,fragment23).commit();
                break;
            case R.id.item_25:
                string = "item25";
                Fragment25 fragment25=new Fragment25();
                fragmentManager.beginTransaction().replace(R.id.container,fragment25).commit();
                break;
            case R.id.item_27:
                string = "item27";
                Fragment27 fragment27=new Fragment27();
                fragmentManager.beginTransaction().replace(R.id.container,fragment27).commit();
                break;
            case R.id.item_28:
                string = "item28";
                Fragment28 fragment28=new Fragment28();
                fragmentManager.beginTransaction().replace(R.id.container,fragment28).commit();
                break;
            case R.id.item_30:
                string = "item30";
                Fragment30 fragment30=new Fragment30();
                fragmentManager.beginTransaction().replace(R.id.container,fragment30).commit();
                break;
            case R.id.item_31:
                string = "item31";
                Fragment31 fragment31=new Fragment31();
                fragmentManager.beginTransaction().replace(R.id.container,fragment31).commit();
                break;
            case R.id.item_34:
                string = "item34";
                Fragment34 fragment34=new Fragment34();
                fragmentManager.beginTransaction().replace(R.id.container,fragment34).commit();
                break;
            case R.id.item_35:
                string = "item35";
                Fragment35 fragment35=new Fragment35();
                fragmentManager.beginTransaction().replace(R.id.container,fragment35).commit();
                break;
            case R.id.item_36:
                string = "item36";
                Fragment36 fragment36=new Fragment36();
                fragmentManager.beginTransaction().replace(R.id.container,fragment36).commit();
                break;
            case R.id.item_37:
                string = "item37";
                Fragment37 fragment37=new Fragment37();
                fragmentManager.beginTransaction().replace(R.id.container,fragment37).commit();
                break;
            case R.id.item_46:
                string = "item46";
                Fragment46 fragment46=new Fragment46();
                fragmentManager.beginTransaction().replace(R.id.container,fragment46).commit();
                break;
            case R.id.nav_setting:
                string = "设置";
                break;
            case R.id.nav_about:
                string = "关于";
                break;
        }
        if (!TextUtils.isEmpty(string))
        Toast.makeText(InitApp.getContext(), "你点击了"+"\""+string+"\"", Toast.LENGTH_SHORT).show();
//        mDrawer.closeDrawer(GravityCompat.START);
        mDrawer.closeDrawers();
        return true;
    }


    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.ivAvatar){    /*点击头像跳转登录界面*/
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        mDrawer.closeDrawer(GravityCompat.START);
    }
}
