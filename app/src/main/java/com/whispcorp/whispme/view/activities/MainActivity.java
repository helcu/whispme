package com.whispcorp.whispme.view.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;
import com.whispcorp.whispme.R;
import com.whispcorp.whispme.view.adapters.ViewPagerAdapter;
import com.whispcorp.whispme.view.fragments.*;
import com.whispcorp.whispme.util.ViewPagerCustom;

public class MainActivity extends AppCompatActivity {

    ViewPagerCustom viewPager;
    MapMenuFragment map_menu_rag;
    NotificationFragment notificationFragmentAdapter;
    UserFragment userFragmentAdapter;
    WorldFragment worldFragmentAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPagerCustom) findViewById(R.id.viewpager);
        viewPager.setPagingEnabled(false);


//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                if (prevMenuItem != null) {
//                    prevMenuItem.setChecked(false);
//                }
//                else
//                {
//                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
//                }
//                Log.d("page", "onPageSelected: "+position);
//                bottomNavigationView.getMenu().getItem(position).setChecked(true);
//                prevMenuItem = bottomNavigationView.getMenu().getItem(position);
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });


        setupViewPager(viewPager);

        SpaceNavigationView spaceNavigationView = (SpaceNavigationView) findViewById(R.id.space);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem("HOME", R.drawable.ic_home));
        spaceNavigationView.addSpaceItem(new SpaceItem("NOTIFICATION", R.drawable.ic_flash));
        spaceNavigationView.addSpaceItem(new SpaceItem("USER", R.drawable.ic_bell));
        spaceNavigationView.addSpaceItem(new SpaceItem("WORLD", R.drawable.ic_person_black_24dp));
        spaceNavigationView.setCentreButtonIcon(R.drawable.ic_record_voice_over);
        spaceNavigationView.showIconOnly();



        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Toast.makeText(MainActivity.this,"onCentreButtonClick", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {

                switch (itemIndex) {
                    case 0:
                        viewPager.setCurrentItem(0);
                        break;
                    case 1:
                        viewPager.setCurrentItem(1);
                        break;
                    case 2:
                        viewPager.setCurrentItem(2);
                        break;
                    case 3:
                        viewPager.setCurrentItem(3);
                        break;
                }


                Toast.makeText(MainActivity.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                Toast.makeText(MainActivity.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }
        });



          //Disable ViewPager Swipe



    }





    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        map_menu_rag =new MapMenuFragment();
        notificationFragmentAdapter =new NotificationFragment();
        userFragmentAdapter =new UserFragment();
        worldFragmentAdapter = new WorldFragment();
        adapter.addFragment(map_menu_rag);
        adapter.addFragment(notificationFragmentAdapter);
        adapter.addFragment(userFragmentAdapter);
        adapter.addFragment(worldFragmentAdapter);
        viewPager.setAdapter(adapter);
    }


}
