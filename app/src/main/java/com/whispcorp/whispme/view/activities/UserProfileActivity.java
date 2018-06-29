package com.whispcorp.whispme.view.activities;

import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.whispcorp.whispme.R;
import com.whispcorp.whispme.database.entities.User;
import com.whispcorp.whispme.database.entities.Whisp;
import com.whispcorp.whispme.util.Constants;
import com.whispcorp.whispme.util.SharedPreferencesUtil;
import com.whispcorp.whispme.view.adapters.ViewPagerAdapter;
import com.whispcorp.whispme.view.fragments.FollowersFragment;
import com.whispcorp.whispme.view.fragments.FollowingFragment;
import com.whispcorp.whispme.viewmodels.UserViewModel;

public class UserProfileActivity extends AppCompatActivity {

    Context mContext = this;
    CollapsingToolbarLayout profileCollapsingToolbarLayout;
    ViewPagerAdapter profileViewPagerAdapter;
    ViewPager profileViewPager;
    TabLayout profileTabLayout;
    TextView profileNameTextView, profileUsernameTextView;
    Whisp whisp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        whisp = (Whisp) getIntent().getSerializableExtra(Constants.Extra.MAIN_USERPROFILE);
        User owner = whisp.getOwner();

        String username = owner.getUsername();
        String email = owner.getEmail();

        profileNameTextView = findViewById(R.id.profileNameTextView);
        profileUsernameTextView = findViewById(R.id.profileUsernameTextView);
        profileNameTextView.setText(username);
        profileUsernameTextView.setText(email);

        profileTabLayout = findViewById(R.id.profileTabLayout);
        profileViewPager = findViewById(R.id.profileViewPager);
        profileCollapsingToolbarLayout = findViewById(R.id.profileCollapsingToolbarLayout);
        profileViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        profileViewPagerAdapter.addFragment(new FollowersFragment(), "Followers");
        profileViewPagerAdapter.addFragment(new FollowingFragment(), "Folowing");

        profileCollapsingToolbarLayout.setTitle(username);
        profileCollapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.space_transparent));
        profileCollapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimary));
        profileCollapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.colorPrimaryDark));

        profileViewPager.setAdapter(profileViewPagerAdapter);
        profileTabLayout.setupWithViewPager(profileViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_user_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_follow:
                UserViewModel userViewModel = new UserViewModel();
                userViewModel.followUser(whisp.getOwner().getServerId(), new UserViewModel.UserViewModelCallback() {
                    @Override
                    public void onSucess() {
                        Toast.makeText(mContext, "¡Siguiendo!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailed(String message) {
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    }
                });
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }
}
