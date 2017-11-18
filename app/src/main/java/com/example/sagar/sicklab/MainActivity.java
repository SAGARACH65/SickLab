package com.example.sagar.sicklab;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

import java.util.ArrayList;
import java.util.List;

import eu.long1.spacetablayout.SpaceTabLayout;

public class MainActivity extends AppCompatActivity {


    private Drawer result = null;

    SpaceTabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.background_login)
                .addProfiles(
                        new ProfileDrawerItem().withName("Mike Penz").withEmail("mikepenz@gmail.com").withIcon(getResources().getDrawable(R.drawable.background_login))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();


        new DrawerBuilder()
                .withAccountHeader(headerResult)
                .withActivity(this)
                .withFullscreen(true)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.app_name).withIcon(R.drawable.home).withIdentifier(1),
                        new PrimaryDrawerItem().withName(R.string.app_name).withIcon(R.drawable.home),
                        new PrimaryDrawerItem().withName(R.string.app_name).withIcon(R.drawable.home),
                        //add some more items to get a scrolling list
                        new SectionDrawerItem().withName(R.string.app_name),
                        new SecondaryDrawerItem().withName(R.string.app_name).withIcon(R.drawable.home),
                        new SecondaryDrawerItem().withName(R.string.app_name).withIcon(R.drawable.home).withEnabled(false),
                        new SecondaryDrawerItem().withName(R.string.app_name).withIcon(R.drawable.home),
                        new SecondaryDrawerItem().withName(R.string.app_name).withIcon(R.drawable.home),
                        new SectionDrawerItem().withName(R.string.app_name),
                        new PrimaryDrawerItem().withName(R.string.app_name).withIcon(R.drawable.home),
                        new PrimaryDrawerItem().withName(R.string.app_name).withIcon(R.drawable.home),
                        new PrimaryDrawerItem().withName(R.string.app_name).withIcon(R.drawable.home),
                        new PrimaryDrawerItem().withName(R.string.app_name).withIcon(R.drawable.home),
                        new PrimaryDrawerItem().withName(R.string.app_name).withIcon(R.drawable.home),
                        new PrimaryDrawerItem().withName(R.string.app_name).withIcon(R.drawable.home),
                        new PrimaryDrawerItem().withName(R.string.app_name).withIcon(R.drawable.home),
                        new PrimaryDrawerItem().withName(R.string.app_name).withIcon(R.drawable.home)
                )
                .withSavedInstance(savedInstanceState)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        if(position==0){
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                            startActivity(intent);
                        }
                        if (position==1){
                            Intent intent = new Intent(getApplicationContext(), IntroActivity.class);

                            startActivity(intent);
                        }
                        return true;
                    }
                })
                .build();

        Toolbar toolbar;
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        TextView title_bar = (TextView) findViewById(R.id.mainToolBar);
        title_bar.setText(R.string.app_name);


        //add the fragments you want to display in a List
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new Fragment1());
        fragmentList.add(new Fragment2());
        fragmentList.add(new Fragment3());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (SpaceTabLayout) findViewById(R.id.spaceTabLayout);

        //we need the savedInstanceState to get the position
        tabLayout.initialize(viewPager, getSupportFragmentManager(),
                fragmentList, savedInstanceState);
    }



    //we need the outState to save the position
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        tabLayout.saveState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
}
