package com.yayanheryanto.startnotes;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.yayanheryanto.startnotes.adapter.TextNotesAdapter;
import com.yayanheryanto.startnotes.database.DBHelper;
import com.yayanheryanto.startnotes.model.TextNotes;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private FloatingActionMenu fabMenu;
    private FloatingActionButton fabText, fabImage, fabCall;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    public static final int PICK_CONTACT = 1;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private TextNotesAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;

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
    }

    private void initDb() {
        DBHelper db = new DBHelper(this);
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
        fabMenu = (FloatingActionMenu) findViewById(R.id.fabMenu);
        fabText = (FloatingActionButton) findViewById(R.id.fabText);
        fabImage = (FloatingActionButton) findViewById(R.id.fabImage);
        fabCall = (FloatingActionButton) findViewById(R.id.fabCall);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerNote);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);

    }

    private void setRecyclerView(){
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
    }

    private void setFab(){
        fabText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabMenu.close(true);
                Intent intent = new Intent(MainActivity.this, CreateNotes.class);
                startActivity(intent);
            }
        });

        fabImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabMenu.close(true);
                Intent intent = new Intent(MainActivity.this, CreateListNotes.class);
                startActivity(intent);
            }
        });

        fabCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabMenu.close(true);
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT);
            }
        });
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        switch (reqCode) {
            case (PICK_CONTACT) :
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor c =  getContentResolver().query(contactData, null, null, null, null);
                    if (c.moveToFirst()) {
                        String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        Log.d("Contact", name);
                        // TODO Whatever you want to do with the selected contact name.
                    }
                }else if (resultCode==Activity.RESULT_CANCELED){
                    Toast.makeText(this, "Contact Not Selected", Toast.LENGTH_SHORT).show();
                }
                break;
        }
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
            if (item.getIcon().getConstantState()==getResources().getDrawable(R.drawable.ic_action_database).getConstantState()){
                item.setIcon(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_action_tiles_small,null));
                layoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(layoutManager);
            }else {
                item.setIcon(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_action_database,null));
                layoutManager = new GridLayoutManager(this,2);
                recyclerView.setLayoutManager(layoutManager);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_notes) {
            // Handle the camera action
        } else if (id == R.id.nav_reminder) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
