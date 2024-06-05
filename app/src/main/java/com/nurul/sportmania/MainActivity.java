package com.nurul.sportmania;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.nurul.sportmania.Activities.SettingsActivity;
import com.nurul.sportmania.Fragments.AboutFragment;
import com.nurul.sportmania.Fragments.CategoryFragment;
import com.nurul.sportmania.Fragments.FavoriteFragment;
import com.nurul.sportmania.Fragments.HomeFragment;
import com.nurul.sportmania.Fragments.SearchFragment;
import com.nurul.sportmania.Helpers.BottomNavigationBehavior;
import com.nurul.sportmania.Helpers.Constants;
import com.nurul.sportmania.Helpers.InternetConnection;
import com.nurul.sportmania.Helpers.SharedPref;

public class MainActivity extends AppCompatActivity {

    private SearchView searchView;
    private ActionBar toolbar;
    SharedPref pref;
    static BottomNavigationView navigation;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pref = new SharedPref(this);
        if(pref.loadNightModeState() == true){
            setTheme(R.style.AppThemeNight);
        }else{
            setTheme(R.style.AppThemeLight);
        }
        Log.d("home",pref.loadNightModeState()+"");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main_activity);

        toolbar = getSupportActionBar();

        if (InternetConnection.checkConnection(this)) {

        }else{
            Toast.makeText(this, R.string.internet_error, Toast.LENGTH_LONG).show();
        }

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        Menu menu = navigation.getMenu();
        menu.findItem(R.id.navigation_home).setTitle(pref.loadUser("TitleHome"));
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        // attaching bottom sheet behaviour - hide / show on scroll
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

        toolbar.setTitle(pref.loadUser("TitleHome"));
        loadFragment(new HomeFragment());


    }

    // Fetches reg id from shared preferences
    // and displays on the screen
    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Constants.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);

        Log.e(TAG, "Firebase reg id: " + regId);

        if (!TextUtils.isEmpty(regId))
            Log.e(TAG, "Firebase reg id: " + regId);
        else
            Log.e(TAG, "Firebase Reg Id is not received yet!");
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolbar.setTitle(pref.loadUser("TitleHome"));
                    item.setTitle(pref.loadUser("TitleHome"));
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_about:
                    toolbar.setTitle(pref.loadUser("TitleAbout"));
                    item.setTitle(pref.loadUser("TitleAbout"));
                    fragment = new AboutFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_category:
                    toolbar.setTitle(pref.loadUser("TitleCategory"));
                    item.setTitle(pref.loadUser("TitleCategory"));
                    fragment = new CategoryFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_favorite:
                    toolbar.setTitle(pref.loadUser("TitleFavorite"));
                    item.setTitle(pref.loadUser("TitleFavorite"));
                    fragment = new FavoriteFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        final MenuItem item_set = menu.findItem(R.id.action_settings);

        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                item_set.setVisible(false);
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // go to home :)
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                return true;
            }
        });

        if (searchItem != null) {
            searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
            searchView.setQueryHint(pref.loadUser("Search"));

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Fragment fragment;
                    fragment = new SearchFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("search",query);

                    fragment.setArguments(bundle);

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_container, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    /*Fragment fragment;
                    fragment = new SearchFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("search",newText);

                    fragment.setArguments(bundle);

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_container, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();*/

                    return false;
                }
            });

            searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    navigation.setSelectedItemId(R.id.navigation_home);
                    Fragment fragment;
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return false;
                }
            });
        }
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.search) {
            return true;
        }

        if (id == R.id.action_settings) {
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onBackPressed() {
        //navigation = findViewById(R.id.navigation);
        if (navigation.getSelectedItemId() == R.id.navigation_home)
        {
            finish();
        }
        else
        {
            navigation.setSelectedItemId(R.id.navigation_home);
        }
    }

    protected void onResume() {
        super.onResume();

    }
}
