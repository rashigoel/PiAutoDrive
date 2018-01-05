package com.example.hp.rg;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.support.annotation.IdRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.example.hp.Adapter.CardAdapter;
import com.example.hp.Adapter.Data;
import com.example.hp.FuelList;
import com.example.hp.FuelStatus;
import com.example.hp.lastparked.LastParked;
import com.example.hp.layout.Analytics;
import com.example.hp.layout.History;
import com.example.hp.layout.TripLog;
import com.github.brnunes.swipeablerecyclerview.SwipeableRecyclerViewTouchListener;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.hookedonplay.decoviewlib.events.DecoEvent;

import com.wooplr.spotlight.SpotlightView;
import com.wooplr.spotlight.prefs.PreferencesManager;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;

import java.util.ArrayList;
import java.util.List;

import at.grabner.circleprogress.CircleProgressView;

import static android.R.attr.id;

public class MainPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    LocationManager locationManager;
    LocationListener locationListener;
    PagerAdapter pagerAdapter;
    List<Data> cardList = new ArrayList<>();
    RecyclerView recyclerView;
    CardAdapter mAdapter;
    ViewPager viewPager;
    TextView miles;
    ImageView imageView;
    private boolean isRevealEnabled = true;
    FragmentManager fragmentManager;
    Analytics analytics = new Analytics();


    ContextMenuDialogFragment mMenuDialogFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
       // imageView = (ImageView)findViewById(R.id.img2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        //Swipe Cards For Notificattion starts

      /*  cardList = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            Data data = new Data("No Problem Detected", "Last Updated");
            cardList.add(data);


        }

        mAdapter = new CardAdapter(cardList);*/


       /* new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                showIntro(recyclerView,"INTRO CARD");
            }
        }, 5000);*/

       /* recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);

        SwipeableRecyclerViewTouchListener swipeTouchListener =
                new SwipeableRecyclerViewTouchListener(recyclerView,
                        new SwipeableRecyclerViewTouchListener.SwipeListener() {

                            @Override
                            public boolean canSwipeLeft(int position) {
                                return false;
                            }

                            @Override
                            public boolean canSwipeRight(int position) {
                                return true;
                            }

                            @Override
                            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    cardList.remove(position);
                                    mAdapter.notifyItemRemoved(position);
                                }
                                mAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    cardList.remove(position);
                                    mAdapter.notifyItemRemoved(position);
                                }
                                mAdapter.notifyDataSetChanged();
                            }
                        });
        recyclerView.addOnItemTouchListener(swipeTouchListener);*/

        //Swipe Cards For Notificattion ends

        //............................................................................................................................


        //Location Permissions starts
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);

            }
        };

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("abc","abc");
                Snackbar.make(view, "Know Your Last Parked Location", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                if (statusCheck()) {
                    Intent i = new Intent(MainPage.this, Map.class);
                    startActivity(i);
                } else {
                    //statusCheck();
                    Toast.makeText(getApplicationContext(),"Enable Location Services ",Toast.LENGTH_LONG).show();


                }
            }
        });


/*
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (statusCheck()) {
                    Intent i = new Intent(MainPage.this, LastParked.class);
                    startActivity(i);
                } else {
                    statusCheck();
                }
            }
        });*/

        //Location Permission Ends


        //.............................................................................................................................


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);





        DecoView decoView = (DecoView) findViewById(R.id.dynamicArcView);

        final SeriesItem seriesItem1 = new SeriesItem.Builder(Color.parseColor("#3c3c46"))
                .setRange(0, 100, 0)
                .build();

        int backIndex = decoView.addSeries(seriesItem1);
        decoView.addEvent(new DecoEvent.Builder(100)
                .setIndex(backIndex)
                .build());
        final SeriesItem seriesItem = new SeriesItem.Builder(Color.parseColor("#fd6708"))
                .setRange(0, 100, 0)
                .build();

        int series1Index = decoView.addSeries(seriesItem);

        decoView.addEvent(new DecoEvent.Builder(50)
                .setIndex(series1Index)
                .setDelay(1000)
                .build());

        /*final TextView textPercentage = (TextView) findViewById(R.id.textView4);
        seriesItem.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                float percentFilled = ((currentPosition - seriesItem.getMinValue()) / (seriesItem.getMaxValue() - seriesItem.getMinValue()));
                textPercentage.setText(String.format("%.0f%%", percentFilled * 100f));
            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {

            }
        });*/


       /* MenuObject analytics = new MenuObject("Analytics");
        analytics.setResource(R.drawable.ic_cast_disabled_light);
        analytics.setBgColor(R.color.colorPrimary);
        analytics.setMenuTextAppearanceStyle(R.style.TextViewStyle);
        analytics.setColor(R.color.colorAccent);


        MenuObject history = new MenuObject("History");
        history.setResource(R.drawable.ic_history_white_36dp);
        history.setBgColor(R.color.m);
        history.setMenuTextAppearanceStyle(R.style.TextViewStyle);

        MenuObject nearby = new MenuObject("Nearby Help");
        nearby.setResource(R.drawable.ic_cast_disabled_light);
        nearby.setBgColor(R.color.m);
        nearby.setMenuTextAppearanceStyle(R.style.TextViewStyle);

        MenuObject social = new MenuObject("Social");
        social.setResource(R.drawable.ic_group_white_36dp);
        social.setBgColor(R.color.m);
        social.setMenuTextAppearanceStyle(R.style.TextViewStyle);


        List<MenuObject> menuObjects = new ArrayList<>();
        menuObjects.add(analytics);
        menuObjects.add(history);
        menuObjects.add(nearby);
        menuObjects.add(social);


        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.action_bar_height));
        menuParams.setMenuObjects(menuObjects);
        menuParams.setClosableOutside(true);
        // set other settings to meet your needs
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);*/


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        /*if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_page, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.action_settings:Intent i = new Intent(this, TripLog.class);
                startActivity(i);

               // mMenuDialogFragment.show(getSupportFragmentManager(), "ContextMenuDialogFragment");
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.navigation) {
            if (statusCheck()) {
                Intent i = new Intent(MainPage.this, Map.class);
                startActivity(i);
            } else {
            }
        } else if (id == R.id.triplog) {
            /*FragmentManager fragmentManager = getSupportFragmentManager();
            History history = new History();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container,history);
            fragmentTransaction.addToBackStack(null);
            Log.d("a","a");
            fragmentTransaction.commit();
*/
            Intent i = new Intent(this,TripLog.class);
            startActivity(i);
        }
        else if (id == R.id.analytics) {
            fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container,analytics);
            fragmentTransaction.addToBackStack(null);
            Log.d("a","a");
            fragmentTransaction.commit();
        }
         else if (id == R.id.fuel) {
            Intent i = new Intent(MainPage.this, FuelList.class);
            startActivity(i);
        }

//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);*/

        return true;
    }


    public boolean statusCheck() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
            return false;
        }
        return true;


    }


    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.dismiss();
                        dialog.cancel();
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();

    }


    @Override
    public void onClick(View v) {
        PreferencesManager mPreferencesManager = new PreferencesManager(MainPage.this);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int screenWidth = displaymetrics.widthPixels;
        int screenHeight = displaymetrics.heightPixels;

       /* switch (v.getId()) {

            case R.id.recyclerview:
                if (isRevealEnabled) {
                    recyclerView.setBackgroundColor(Color.RED);
                    isRevealEnabled = false;
                } else {
                    recyclerView.setBackgroundColor(Color.BLUE);

                    isRevealEnabled = true;
                }
                mPreferencesManager.resetAll();
                break;

            case R.id.fab:
                mPreferencesManager.resetAll();
                break;
            case R.id.miles:
                mPreferencesManager.resetAll();
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showIntro(recyclerView, "intro card");
                    }
                }, 400);
                break;*/
       // }
    }
    private void showIntro(View view, String usageId) {
        new SpotlightView.Builder(this)
                .introAnimationDuration(400)
                .enableRevalAnimation(isRevealEnabled)
                .performClick(true)
                .fadeinTextDuration(400)
                //.setTypeface(FontUtil.get(this, "RemachineScript_Personal_Use"))
                .headingTvColor(Color.parseColor("#eb273f"))
                .headingTvSize(32)
                .headingTvText("Love")
                .subHeadingTvColor(Color.parseColor("#ffffff"))
                .subHeadingTvSize(16)
                .subHeadingTvText("Like the picture?\nLet others know.")
                .maskColor(Color.parseColor("#dc000000"))
                .target(view)
                .lineAnimDuration(400)
                .lineAndArcColor(Color.parseColor("#eb273f"))
                .dismissOnTouch(true)
                .enableDismissAfterShown(true)
                .usageId(usageId) //UNIQUE ID
                .show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }
}
