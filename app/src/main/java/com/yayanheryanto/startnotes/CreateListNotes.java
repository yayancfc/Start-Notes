package com.yayanheryanto.startnotes;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.thebluealliance.spectrum.SpectrumDialog;

public class CreateListNotes extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout listNotesLayout, layoutListItem;
    private ImageView addItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list_notes);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bindingData();
        addItemList.setOnClickListener(this);
    }

    private void bindingData() {
        listNotesLayout = (LinearLayout) findViewById(R.id.list_note_layout);
        layoutListItem = (LinearLayout) findViewById(R.id.layoutList);
        addItemList = (ImageView) findViewById(R.id.addItemList);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.action_color :
                showColorDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_note, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void showColorDialog(){
        final SpectrumDialog.Builder builder = new SpectrumDialog.Builder(this);
        builder.setTitle("Select Color");
        builder.setColors(R.array.colorPicker);
        builder.setDismissOnColorSelected(true);
        builder.setOutlineWidth(2);
        builder.setOnColorSelectedListener(new SpectrumDialog.OnColorSelectedListener() {
            @Override
            public void onColorSelected(boolean positiveResult, @ColorInt int color) {
                if (positiveResult) {
                    listNotesLayout.setBackgroundColor(color);
                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(color));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Window window = getWindow();
                        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.setStatusBarColor(color);
                    }

                    Log.d("Color ", String.valueOf(color) + " " + Integer.toHexString(color).toUpperCase());
                    //Toast.makeText(getApplicationContext(), "Color selected: #" + Integer.toHexString(color).toUpperCase(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Color Not Selected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        SpectrumDialog dialog = builder.build();
        dialog.show(getSupportFragmentManager(),"Color Dialog");
    }

    @Override
    public void onClick(View view) {
        if (view==addItemList){
            Toast.makeText(this, "Add Item", Toast.LENGTH_SHORT).show();
        }
    }
}
