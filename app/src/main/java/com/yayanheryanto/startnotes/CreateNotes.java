package com.yayanheryanto.startnotes;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.thebluealliance.spectrum.SpectrumDialog;
import com.yayanheryanto.startnotes.MainActivity;
import com.yayanheryanto.startnotes.R;
import com.yayanheryanto.startnotes.database.DBHelper;
import com.yayanheryanto.startnotes.model.TextNotes;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateNotes extends AppCompatActivity implements View.OnClickListener {

    private CoordinatorLayout notesLayout;
    private GoogleApiClient mGoogleApiClient;
    public static final String TAG = "PLACE";
    private static final int REQUEST_CODE_AUTOCOMPLETE = 1;
    private ImageView placeSearch;
    private TextView txtPlace;
    private EditText txtDate, txtTime, txtTitle, txtDescription;
    private Calendar myCalendar;
    private String jam, tanggal;
    private int warna;
    private TextNotes notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notes);


        bindingData();
        getFromIntent();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        showDateDialog();
        showTimeDialog();
        placeSearch.setOnClickListener(this);
    }

    private void getFromIntent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle!=null){
            notes = (TextNotes) bundle.getParcelable("data");
            txtTitle.setText(notes.getTitle());
            txtDescription.setText(notes.getDesccription());
            txtPlace.setText(notes.getLocation());
            txtDate.setText(notes.getDate());
            txtTime.setText(notes.getTime());
            notesLayout.setBackgroundColor(notes.getColor());
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(notes.getColor()));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(notes.getColor());
            }
            Log.d("MAIN DATA", notes.getTitle() + " " + notes.getId());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_AUTOCOMPLETE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                txtPlace.setText(place.getName() + ", " + place.getAddress());
                Log.i(TAG, "Place: " + place.getName());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.i(TAG, status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    private void bindingData() {
        notesLayout = (CoordinatorLayout) findViewById(R.id.notesLayout);
        placeSearch = (ImageView) findViewById(R.id.place);
        txtPlace = (TextView) findViewById(R.id.txtPlace);
        txtDate = (EditText) findViewById(R.id.txtDate);
        txtTime = (EditText) findViewById(R.id.txtTime);
        txtTitle = (EditText) findViewById(R.id.txtTitle);
        txtDescription = (EditText) findViewById(R.id.txtDescription);
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

            case R.id.action_save :
                saveTextNotes();
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
                    warna = color;
                    notesLayout.setBackgroundColor(color);
                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(color));
                    builder.setSelectedColor(color);
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
        if (view==placeSearch){
            try {
                // The autocomplete activity requires Google Play Services to be available. The intent
                // builder checks this and throws an exception if it is not the case.
                Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                        .build(this);
                startActivityForResult(intent, REQUEST_CODE_AUTOCOMPLETE);
            } catch (GooglePlayServicesRepairableException e) {
                // Indicates that Google Play Services is either not installed or not up to date. Prompt
                // the user to correct the issue.
                GoogleApiAvailability.getInstance().getErrorDialog(this, e.getConnectionStatusCode(),
                        0 /* requestCode */).show();
            } catch (GooglePlayServicesNotAvailableException e) {
                // Indicates that Google Play Services is not available and the problem is not easily
                // resolvable.
                String message = "Google Play Services is not available: " +
                        GoogleApiAvailability.getInstance().getErrorString(e.errorCode);

                Log.e(TAG, message);
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showTimeDialog() {
        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                final int second = mcurrentTime.get(Calendar.SECOND);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(CreateNotes.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        txtTime.setText( selectedHour + ":" + selectedMinute + " ");
                        jam = selectedHour + ":" + selectedMinute;
                        Log.d("Jam", jam);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
    }

    private void showDateDialog() {
        myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String format = "EEEE, dd MMM yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                tanggal = sdf.format(myCalendar.getTime());
                txtDate.setText(tanggal);
                Log.d("Tanggal", tanggal);
            }
        };

        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CreateNotes.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }


    private void saveTextNotes() {
        String judul = txtTitle.getText().toString();
        String deskripsi = txtDescription.getText().toString();
        String lokasi = txtPlace.getText().toString();
        
        if (judul.equals("") && deskripsi.equals("") && lokasi.equals("")){
            Toast.makeText(this, "Kosong", Toast.LENGTH_SHORT).show();
        }else {
            DBHelper db = new DBHelper(this);
            TextNotes note = new TextNotes();
            note.setTitle(judul);
            note.setDesccription(deskripsi);
            note.setLocation(lokasi);
            note.setDate(tanggal);
            note.setTime(jam);
            note.setColor(warna);

            if (notes==null) {
                db.addNotes(note);
            }else {
                db.updateNotes(note,notes.getId());
            }
            Intent intent = new Intent(CreateNotes.this, MainActivity.class);
            startActivity(intent);
            finish();

        }
    }
}
