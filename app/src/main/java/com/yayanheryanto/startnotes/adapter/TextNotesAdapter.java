package com.yayanheryanto.startnotes.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yayanheryanto.startnotes.CreateNotes;
import com.yayanheryanto.startnotes.R;
import com.yayanheryanto.startnotes.model.TextNotes;

import java.util.List;

/**
 * Created by Yayan Heryanto on 9/13/2017.
 */

public class TextNotesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_TEXT_1 = 1;
    public static final int TYPE_TEXT_2 = 2;
    public static final int TYPE_TEXT_3 = 3;
    public static final int TYPE_TEXT_4 = 4;
    public static final int TYPE_TEXT_5 = 5;
    public static final int TYPE_TEXT_6 = 6;
    public static final int TYPE_TEXT_7 = 7;
    public static final int TYPE_TEXT_8 = 8;
    public static final int TYPE_TEXT_9 = 9;
    public static final int TYPE_TEXT_10 = 10;
    public static final int TYPE_TEXT_11 = 11;
    public static final int TYPE_TEXT_12 = 12;
    public static final int TYPE_TEXT_13 = 13;
    public static final int TYPE_TEXT_14 = 14;
    public static final int TYPE_TEXT_15 = 15;

    public static final String NOTES_DATA = "data";

    private Context context;
    private List<TextNotes> textNotes;

    public TextNotesAdapter(Context context, List<TextNotes> textNotes) {
        this.context = context;
        this.textNotes = textNotes;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        switch (viewType){
            case TYPE_TEXT_1:
                view = inflater.inflate(R.layout.text_notes_cardview1, parent, false);
                viewHolder = new TextNotes1ViewHolder(view);
                break;

            case TYPE_TEXT_2:
                view = inflater.inflate(R.layout.text_notes_cardview2, parent, false);
                viewHolder = new TextNotes2ViewHolder(view);
                break;

            case TYPE_TEXT_3:
                view = inflater.inflate(R.layout.text_notes_cardview3, parent, false);
                viewHolder = new TextNotes3ViewHolder(view);
                break;

            case TYPE_TEXT_4:
                view = inflater.inflate(R.layout.text_notes_cardview4, parent, false);
                viewHolder = new TextNotes4ViewHolder(view);
                break;

            case TYPE_TEXT_5:
                view = inflater.inflate(R.layout.text_notes_cardview5, parent, false);
                viewHolder = new TextNotes5ViewHolder(view);
                break;

            case TYPE_TEXT_6:
                view = inflater.inflate(R.layout.text_notes_cardview6, parent, false);
                viewHolder = new TextNotes6ViewHolder(view);
                break;

            case TYPE_TEXT_7:
                view = inflater.inflate(R.layout.text_notes_cardview7, parent, false);
                viewHolder = new TextNotes7ViewHolder(view);
                break;

            case TYPE_TEXT_14:
                view = inflater.inflate(R.layout.text_notes_cardview1, parent, false);
                viewHolder = new TextNotes14ViewHolder(view);
                break;

            case TYPE_TEXT_8:
                view = inflater.inflate(R.layout.text_notes_cardview2, parent, false);
                viewHolder = new TextNotes8ViewHolder(view);
                break;

            case TYPE_TEXT_9:
                view = inflater.inflate(R.layout.text_notes_cardview3, parent, false);
                viewHolder = new TextNotes9ViewHolder(view);
                break;

            case TYPE_TEXT_10:
                view = inflater.inflate(R.layout.text_notes_cardview4, parent, false);
                viewHolder = new TextNotes10ViewHolder(view);
                break;

            case TYPE_TEXT_11:
                view = inflater.inflate(R.layout.text_notes_cardview5, parent, false);
                viewHolder = new TextNotes11ViewHolder(view);
                break;

            case TYPE_TEXT_12:
                view = inflater.inflate(R.layout.text_notes_cardview6, parent, false);
                viewHolder = new TextNotes12ViewHolder(view);
                break;

            case TYPE_TEXT_13:
                view = inflater.inflate(R.layout.text_notes_cardview7, parent, false);
                viewHolder = new TextNotes13ViewHolder(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final TextNotes notes = textNotes.get(position);
        switch (getItemViewType(position)){
            case TYPE_TEXT_1 :
                TextNotes1ViewHolder notesHolder1 = (TextNotes1ViewHolder) holder;
                notesHolder1.judul.setText(notes.getTitle());
                notesHolder1.deskripsi.setText(notes.getDesccription());
                notesHolder1.lokasi.setText(notes.getLocation());
                notesHolder1.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, CreateNotes.class);
                        intent.putExtra(NOTES_DATA, notes);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                break;

            case TYPE_TEXT_2 :
                TextNotes2ViewHolder notesHolder2 = (TextNotes2ViewHolder) holder;
                notesHolder2.judul.setText(notes.getTitle());
                notesHolder2.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, CreateNotes.class);
                        intent.putExtra(NOTES_DATA, notes);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                break;

            case TYPE_TEXT_3 :
                TextNotes3ViewHolder notesHolder3 = (TextNotes3ViewHolder) holder;
                notesHolder3.deskripsi.setText(notes.getDesccription());
                notesHolder3.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, CreateNotes.class);
                        intent.putExtra(NOTES_DATA, notes);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                break;

            case TYPE_TEXT_4 :
                TextNotes4ViewHolder notesHolder4 = (TextNotes4ViewHolder) holder;
                notesHolder4.lokasi.setText(notes.getLocation());
                notesHolder4.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, CreateNotes.class);
                        intent.putExtra(NOTES_DATA, notes);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                break;

            case TYPE_TEXT_5 :
                TextNotes5ViewHolder notesHolder5 = (TextNotes5ViewHolder) holder;
                notesHolder5.judul.setText(notes.getTitle());
                notesHolder5.deskripsi.setText(notes.getDesccription());
                notesHolder5.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, CreateNotes.class);
                        intent.putExtra(NOTES_DATA, notes);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                break;

            case TYPE_TEXT_6 :
                TextNotes6ViewHolder notesHolder6 = (TextNotes6ViewHolder) holder;
                notesHolder6.judul.setText(notes.getTitle());
                notesHolder6.lokasi.setText(notes.getLocation());
                notesHolder6.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, CreateNotes.class);
                        intent.putExtra(NOTES_DATA, notes);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                break;

            case TYPE_TEXT_7 :
                TextNotes7ViewHolder notesHolder7 = (TextNotes7ViewHolder) holder;
                notesHolder7.deskripsi.setText(notes.getDesccription());
                notesHolder7.lokasi.setText(notes.getLocation());
                notesHolder7.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, CreateNotes.class);
                        intent.putExtra(NOTES_DATA, notes);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                break;

            case TYPE_TEXT_14 :
                TextNotes14ViewHolder notesHolder14 = (TextNotes14ViewHolder) holder;
                notesHolder14.judul.setText(notes.getTitle());
                notesHolder14.deskripsi.setText(notes.getDesccription());
                notesHolder14.lokasi.setText(notes.getLocation());
                notesHolder14.layout.setBackgroundColor(notes.getColor());
                notesHolder14.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, CreateNotes.class);
                        intent.putExtra(NOTES_DATA, notes);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                break;

            case TYPE_TEXT_8 :
                TextNotes8ViewHolder notesHolder8 = (TextNotes8ViewHolder) holder;
                notesHolder8.judul.setText(notes.getTitle());
                notesHolder8.layout.setBackgroundColor(notes.getColor());
                notesHolder8.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, CreateNotes.class);
                        intent.putExtra(NOTES_DATA, notes);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                break;

            case TYPE_TEXT_9 :
                TextNotes9ViewHolder notesHolder9 = (TextNotes9ViewHolder) holder;
                notesHolder9.deskripsi.setText(notes.getDesccription());
                notesHolder9.layout.setBackgroundColor(notes.getColor());
                notesHolder9.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, CreateNotes.class);
                        intent.putExtra(NOTES_DATA, notes);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                break;

            case TYPE_TEXT_10 :
                TextNotes10ViewHolder notesHolder10 = (TextNotes10ViewHolder) holder;
                notesHolder10.lokasi.setText(notes.getLocation());
                notesHolder10.layout.setBackgroundColor(notes.getColor());
                notesHolder10.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, CreateNotes.class);
                        intent.putExtra(NOTES_DATA, notes);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                break;

            case TYPE_TEXT_11 :
                TextNotes11ViewHolder notesHolder11 = (TextNotes11ViewHolder) holder;
                notesHolder11.judul.setText(notes.getTitle());
                notesHolder11.deskripsi.setText(notes.getDesccription());
                notesHolder11.layout.setBackgroundColor(notes.getColor());
                notesHolder11.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, CreateNotes.class);
                        intent.putExtra(NOTES_DATA, notes);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                break;

            case TYPE_TEXT_12 :
                TextNotes12ViewHolder notesHolder12 = (TextNotes12ViewHolder) holder;
                notesHolder12.judul.setText(notes.getTitle());
                notesHolder12.lokasi.setText(notes.getLocation());
                notesHolder12.layout.setBackgroundColor(notes.getColor());
                notesHolder12.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, CreateNotes.class);
                        intent.putExtra(NOTES_DATA, notes);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                break;

            case TYPE_TEXT_13 :
                TextNotes13ViewHolder notesHolder13 = (TextNotes13ViewHolder) holder;
                notesHolder13.deskripsi.setText(notes.getDesccription());
                notesHolder13.lokasi.setText(notes.getLocation());
                notesHolder13.layout.setBackgroundColor(notes.getColor());
                notesHolder13.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, CreateNotes.class);
                        intent.putExtra(NOTES_DATA, notes);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        TextNotes notes = textNotes.get(position);
        int viewType = 0;
        if (!notes.getTitle().equals("") && notes.getDesccription().equals("") && notes.getLocation().equals("") && notes.getColor()==0){
            viewType = TYPE_TEXT_2;
        }else if (!notes.getDesccription().equals("") && notes.getTitle().equals("") && notes.getLocation().equals("") && notes.getColor()==0){
            viewType = TYPE_TEXT_3;
        }else if (!notes.getLocation().equals("") && notes.getTitle().equals("") && notes.getDesccription().equals("") && notes.getColor()==0){
            viewType = TYPE_TEXT_4;
        }else if (!notes.getTitle().equals("") && !notes.getDesccription().equals("") && notes.getLocation().equals("") && notes.getColor()==0){
            viewType = TYPE_TEXT_5;
        }else if (!notes.getTitle().equals("") && !notes.getLocation().equals("") && notes.getDesccription().equals("") && notes.getColor()==0){
            viewType = TYPE_TEXT_6;
        }else if (!notes.getLocation().equals("") && !notes.getDesccription().equals("") && notes.getTitle().equals("") && notes.getColor()==0){
            viewType = TYPE_TEXT_7;
        }else if (!notes.getTitle().equals("") && !notes.getDesccription().equals("") && !notes.getLocation().equals("") && notes.getColor()==0){
            viewType = TYPE_TEXT_1;
        } else if (!notes.getTitle().equals("") && notes.getDesccription().equals("") && notes.getLocation().equals("") && notes.getColor()!=0){
            viewType = TYPE_TEXT_8;
        }else if (!notes.getDesccription().equals("") && notes.getTitle().equals("") && notes.getLocation().equals("") && notes.getColor()!=0){
            viewType = TYPE_TEXT_9;
        }else if (!notes.getLocation().equals("") && notes.getTitle().equals("") && notes.getDesccription().equals("") && notes.getColor()!=0){
            viewType = TYPE_TEXT_10;
        }else if (!notes.getTitle().equals("") && !notes.getDesccription().equals("") && notes.getLocation().equals("") && notes.getColor()!=0){
            viewType = TYPE_TEXT_11;
        }else if (!notes.getTitle().equals("") && !notes.getLocation().equals("") && notes.getDesccription().equals("") && notes.getColor()!=0){
            viewType = TYPE_TEXT_12;
        }else if (!notes.getLocation().equals("") && !notes.getDesccription().equals("") && notes.getTitle().equals("") && notes.getColor()!=0){
            viewType = TYPE_TEXT_13;
        }else if (!notes.getTitle().equals("") && !notes.getDesccription().equals("") && !notes.getLocation().equals("") && notes.getColor()!=0){
            viewType = TYPE_TEXT_14;
        }
        return viewType;
    }

    @Override
    public int getItemCount() {
        return textNotes.size();
    }

    /*ViewHolder 1
    *
    * */
    public class TextNotes1ViewHolder extends RecyclerView.ViewHolder{

        private TextView judul, deskripsi, lokasi;
        private View mView;

        public TextNotes1ViewHolder(View itemView) {
            super(itemView);
            this.mView = itemView;
            judul = (TextView) itemView.findViewById(R.id.judul1);
            deskripsi = (TextView) itemView.findViewById(R.id.description1);
            lokasi = (TextView) itemView.findViewById(R.id.txtPlace1);
        }
    }

    /*ViewHolder 2
    *
    * */
    public class TextNotes2ViewHolder extends RecyclerView.ViewHolder{

        private TextView judul;
        private View mView;

        public TextNotes2ViewHolder(View itemView) {
            super(itemView);
            this.mView = itemView;
            judul = (TextView) itemView.findViewById(R.id.judul2);
        }
    }

    /*ViewHolder 3
    *
    * */
    public class TextNotes3ViewHolder extends RecyclerView.ViewHolder{

        private TextView deskripsi;
        private View mView;

        public TextNotes3ViewHolder(View itemView) {
            super(itemView);
            this.mView = itemView;
            deskripsi = (TextView) itemView.findViewById(R.id.description3);
        }
    }

    /*ViewHolder 4
    *
    * */
    public class TextNotes4ViewHolder extends RecyclerView.ViewHolder{

        private TextView lokasi;
        private View mView;

        public TextNotes4ViewHolder(View itemView) {
            super(itemView);
            this.mView = itemView;
            lokasi = (TextView) itemView.findViewById(R.id.txtPlace4);
        }
    }

    /*ViewHolder 5
    *
    * */
    public class TextNotes5ViewHolder extends RecyclerView.ViewHolder{

        private TextView judul, deskripsi;
        private View mView;

        public TextNotes5ViewHolder(View itemView) {
            super(itemView);
            this.mView = itemView;
            judul = (TextView) itemView.findViewById(R.id.judul5);
            deskripsi = (TextView) itemView.findViewById(R.id.description5);
        }
    }

    /*ViewHolder 6
    *
    * */
    public class TextNotes6ViewHolder extends RecyclerView.ViewHolder{

        private TextView judul, lokasi;
        private View mView;

        public TextNotes6ViewHolder(View itemView) {
            super(itemView);
            this.mView = itemView;
            judul = (TextView) itemView.findViewById(R.id.judul6);
            lokasi = (TextView) itemView.findViewById(R.id.txtPlace6);
        }
    }

    /*ViewHolder 7
     *
    * */
    public class TextNotes7ViewHolder extends RecyclerView.ViewHolder{

        private TextView deskripsi, lokasi;
        private View mView;

        public TextNotes7ViewHolder(View itemView) {
            super(itemView);
            this.mView = itemView;
            deskripsi = (TextView) itemView.findViewById(R.id.description7);
            lokasi = (TextView) itemView.findViewById(R.id.txtPlace7);
        }
    }

    /*ViewHolder 14
    *
    * */
    public class TextNotes14ViewHolder extends RecyclerView.ViewHolder{

        private TextView judul, deskripsi, lokasi;
        private LinearLayout layout;
        private View mView;

        public TextNotes14ViewHolder(View itemView) {
            super(itemView);
            this.mView = itemView;
            judul = (TextView) itemView.findViewById(R.id.judul1);
            deskripsi = (TextView) itemView.findViewById(R.id.description1);
            lokasi = (TextView) itemView.findViewById(R.id.txtPlace1);
            layout = (LinearLayout) itemView.findViewById(R.id.notesLayout1);
        }
    }

    /*ViewHolder 8
    *
    * */
    public class TextNotes8ViewHolder extends RecyclerView.ViewHolder{

        private TextView judul;
        private LinearLayout layout;
        private View mView;

        public TextNotes8ViewHolder(View itemView) {
            super(itemView);
            this.mView = itemView;
            judul = (TextView) itemView.findViewById(R.id.judul2);
            layout = (LinearLayout) itemView.findViewById(R.id.notesLayout2);
        }
    }

    /*ViewHolder 9
    *
    * */
    public class TextNotes9ViewHolder extends RecyclerView.ViewHolder{

        private TextView deskripsi;
        private LinearLayout layout;
        private View mView;

        public TextNotes9ViewHolder(View itemView) {
            super(itemView);
            this.mView = itemView;
            deskripsi = (TextView) itemView.findViewById(R.id.description3);
            layout = (LinearLayout) itemView.findViewById(R.id.notesLayout3);
        }
    }

    /*ViewHolder 10
    *
    * */
    public class TextNotes10ViewHolder extends RecyclerView.ViewHolder{

        private TextView lokasi;
        private LinearLayout layout;
        private View mView;

        public TextNotes10ViewHolder(View itemView) {
            super(itemView);
            this.mView = itemView;
            lokasi = (TextView) itemView.findViewById(R.id.txtPlace4);
            layout = (LinearLayout) itemView.findViewById(R.id.notesLayout4);
        }
    }

    /*ViewHolder 11
    *
    * */
    public class TextNotes11ViewHolder extends RecyclerView.ViewHolder{

        private TextView judul, deskripsi;
        private LinearLayout layout;
        private View mView;

        public TextNotes11ViewHolder(View itemView) {
            super(itemView);
            this.mView = itemView;
            judul = (TextView) itemView.findViewById(R.id.judul5);
            deskripsi = (TextView) itemView.findViewById(R.id.description5);
            layout = (LinearLayout) itemView.findViewById(R.id.notesLayout5);
        }
    }

    /*ViewHolder 12
    *
    * */
    public class TextNotes12ViewHolder extends RecyclerView.ViewHolder{

        private TextView judul, lokasi;
        private LinearLayout layout;
        private View mView;

        public TextNotes12ViewHolder(View itemView) {
            super(itemView);
            this.mView = itemView;
            judul = (TextView) itemView.findViewById(R.id.judul6);
            lokasi = (TextView) itemView.findViewById(R.id.txtPlace6);
            layout = (LinearLayout) itemView.findViewById(R.id.notesLayout6);
        }
    }

    /*ViewHolder 13
     *
    * */
    public class TextNotes13ViewHolder extends RecyclerView.ViewHolder{

        private TextView deskripsi, lokasi;
        private LinearLayout layout;
        private View mView;

        public TextNotes13ViewHolder(View itemView) {
            super(itemView);
            this.mView = itemView;
            deskripsi = (TextView) itemView.findViewById(R.id.description7);
            lokasi = (TextView) itemView.findViewById(R.id.txtPlace7);
            layout = (LinearLayout) itemView.findViewById(R.id.notesLayout7);

        }
    }

}
