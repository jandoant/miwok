package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jan Doant on 15.12.2016.
 */

class WordAdapter extends ArrayAdapter<Word> {

    private int categoryColorResourceId;

    WordAdapter(Context context, ArrayList<Word> wordsArr, int categoryColorResourceId) {
        super(context, R.layout.list_row, wordsArr);
        this.categoryColorResourceId = categoryColorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        //reuse recycled View
        View listItemView = convertView;

        //Data to be displayed in this row
        Word word = getItem(position);

        //inflate Custom Row Layout into the RowView -- the Custom Layout gets inflated into the parent View Group
        if (listItemView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            listItemView = inflater.inflate(R.layout.list_row, parent, false);
        }

        //Style the background of the listItemView according to the Category
        int color;
        color = getContext().getResources().getColor(categoryColorResourceId);
        listItemView.setBackgroundColor(color);

        //Identify the Child Views of the rowView
        ImageView img_word = (ImageView) listItemView.findViewById(R.id.img_word);
        TextView txt_miwok_word = (TextView) listItemView.findViewById(R.id.txt_miwok_word);
        TextView txt_default_word = (TextView) listItemView.findViewById(R.id.txt_default_word);

        //Populate the Data into these Views
        if (word != null) {

            if (word.hasImage()) {
                img_word.setImageResource(word.getImageResourceId());
                img_word.setVisibility(View.VISIBLE);
            } else {
                img_word.setVisibility(View.GONE);
            }
            txt_miwok_word.setText(word.getMiwokTranslation());
            txt_default_word.setText(word.getDefaultTranslation());
        }
        //return the newly created View
        return listItemView;
    }
}
