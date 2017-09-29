package com.yayanheryanto.startnotes;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.yayanheryanto.startnotes.adapter.TextNotesAdapter;
import com.yayanheryanto.startnotes.database.DBHelper;
import com.yayanheryanto.startnotes.model.TextNotes;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    public static final int PICK_CONTACT = 1;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private TextNotesAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindingData();
        setRecyclerView();
        setToolbar();
        setFab();
        setDrawer();
        setNavigationView();
        initDb();
        setPostRefresh();
        setSwipeRefresh();

//        Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
//
//        cal.set(Calendar.DATE,17);  //1-31
//        cal.set(Calendar.MONTH,Calendar.SEPTEMBER);  //first month is 0!!! January is zero!!!
//        cal.set(Calendar.YEAR,2017);//year...
//
//        cal.set(Calendar.HOUR_OF_DAY, 10);  //HOUR
//        cal.set(Calendar.MINUTE, 33);       //MIN
//        //cal.set(Calendar.SECOND, 10);       //SEC
//
//        Intent intent = new Intent(MainActivity.this, CreateNotes.class);
//        PendingIntent pendingIntent = PendingIntent.getService(MainActivity.this, 0,intent, 0);
//
//        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
    }

    private void initDb() {
        db = new DBHelper(this);
        List<TextNotes> notes = db.getAllNotes();
        adapter = new TextNotesAdapter(this, notes);
        recyclerView.setAdapter(adapter);
        Log.d("Notes", String.valueOf(notes.size()));
    }


    private void setPostRefresh() {
        swipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                swipeRefresh.setRefreshing(true);
                initDb();
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    private void setSwipeRefresh() {
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.setRefreshing(true);
                initDb();
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    private void bindingData() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerNote);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);

    }

    private void setRecyclerView(){
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy){
                if (dy > 0 ||dy<0 && fab.isShown())
                    fab.hide();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    fab.show();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
    }

    private void setFab(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateNotes.class);
                startActivity(intent);
            }
        });

    }

    private void setDrawer(){
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    private void setNavigationView(){
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }else if (id==R.id.action_change){
            swipeRefresh.setRefreshing(true);
            if (item.getIcon().getConstantState()==getResources().getDrawable(R.drawable.ic_action_database).getConstantState()){
                item.setIcon(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_action_tiles_small,null));
                layoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(layoutManager);
            }else {
                item.setIcon(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_action_database,null));
                layoutManager = new GridLayoutManager(this,2);
                recyclerView.setLayoutManager(layoutManager);
            }
            swipeRefresh.setRefreshing(false);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        item.setCheckable(false);
        int id = item.getItemId();

        if (id == R.id.nav_notes) {
            swipeRefresh.setRefreshing(true);
            initDb();
            swipeRefresh.setRefreshing(false);
        } else if (id == R.id.nav_reminder) {

        }else if (id == R.id.nav_about){
            Intent intent = new Intent(MainActivity.this, About.class);
            startActivity(intent);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
