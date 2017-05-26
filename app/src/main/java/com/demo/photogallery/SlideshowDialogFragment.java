package com.demo.photogallery;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.github.angads25.filepicker.controller.DialogSelectionListener;
import com.github.angads25.filepicker.model.DialogConfigs;
import com.github.angads25.filepicker.model.DialogProperties;
import com.github.angads25.filepicker.view.FilePickerDialog;

import java.io.File;
import java.util.ArrayList;

public class SlideshowDialogFragment extends DialogFragment {


    private Button selectFile;
    private Spinner transitionsList;
    private Spinner durationList;

    int transitionIn;
    int transitionOut;
    int duration;
    ArrayList<String> contents;
    String[] fileCollection;

    SlideshowDialogFragment getData;

    public SlideshowDialogFragment() {

    }

    public static SlideshowDialogFragment newInstance(String title) {
        SlideshowDialogFragment fragment = new SlideshowDialogFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.slideshow_dialog, container, false);

        Button selectFile = (Button) view.findViewById(R.id.b_slideshow_file_select);
        selectFile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //initialize file chooser
                DialogProperties properties = new DialogProperties();
                // Config for multiple file selection
                properties.selection_mode = DialogConfigs.MULTI_MODE;
                properties.selection_type = DialogConfigs.FILE_AND_DIR_SELECT;
                properties.root = new File(DialogConfigs.DEFAULT_DIR);
                properties.error_dir = new File(DialogConfigs.DEFAULT_DIR);
                properties.offset = new File(DialogConfigs.DEFAULT_DIR);
                properties.extensions = null;
                // set the dialog box
                final FilePickerDialog dialog = new FilePickerDialog(SlideshowDialogFragment.this.getActivity(), properties);
                dialog.setTitle("Select File(s)");

                dialog.show();

                dialog.setDialogSelectionListener(new DialogSelectionListener() {
                    @Override
                    public void onSelectedFilePaths(String[] files) {
                        fileCollection = files;
                    }
                });
            }
        });

        // spinner listener
        Spinner transitionInSelect = (Spinner) view.findViewById(R.id.spinner_transitions_in);
        transitionInSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                transitionIn = parent.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // spinner listener
        Spinner transitionOutSelect = (Spinner) view.findViewById(R.id.spinner_transitions_out);
        transitionOutSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                transitionOut = parent.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // spinner listener
        Spinner durationSelect = (Spinner) view.findViewById(R.id.spinner_timer_list);
        durationSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                duration = parent.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button confirm = (Button) view.findViewById(R.id.b_view_slideshow);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent slideshowIntent = new Intent(getActivity(), SlideshowActivity.class);
                slideshowIntent.putExtra("duration", duration);
                slideshowIntent.putExtra("transitionIn", transitionIn);
                slideshowIntent.putExtra("transitionOut", transitionOut);
                slideshowIntent.putExtra("files", fileCollection);
                startActivity(slideshowIntent);
            }
        });

        // cancel button
        Button cancel = (Button) view.findViewById(R.id.b_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SlideshowDialogFragment.this.dismiss();
            }
        });

        return view;
    }


}
