package com.example.sagar.sicklab;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
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
    private static final String PREF_IS_LOGGED_IN = "IS_LOGGED";
    private static final String PREF_NAME = "LOGIN_PREF";
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
                        new PrimaryDrawerItem().withName(R.string.profile).withIcon(R.drawable.home).withIdentifier(1),
                        new PrimaryDrawerItem().withName(R.string.logout).withIcon(R.drawable.home),

                        //add some more items to get a scrolling list
                        new SectionDrawerItem().withName(R.string.more_options),
                        new SecondaryDrawerItem().withName(R.string.dev_profile).withIcon(R.drawable.home),
                        new SecondaryDrawerItem().withName(R.string.rate_us).withIcon(R.drawable.home),
                        new SecondaryDrawerItem().withName(R.string.suggestions).withIcon(R.drawable.home)


                )
                .withSavedInstance(savedInstanceState)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        if (position == 0) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                            startActivity(intent);
                        }
                        if (position == 1) {


                        }
                        if (position == 2) {





                                            SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, 0);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putBoolean("FirstLogin", true);
                                            editor.apply();

                                            Intent intent = new Intent(getApplicationContext(), LoginPage.class);
                                            startActivity(intent);
                                            finish();





                        }
                        if (position == 4) {
                            Intent intent = new Intent(getApplicationContext(), IntroActivity.class);

                            startActivity(intent);
                        }
                        if (position == 5) {
                            final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                            try {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                            } catch (android.content.ActivityNotFoundException anfe) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                            }

                        }
                        if (position == 6) {
                            Intent intent_email = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "sagarach65@gmail.com"));
                            intent_email.putExtra(Intent.EXTRA_SUBJECT, "Regarding SickLab App");
                            startActivity(intent_email);

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
