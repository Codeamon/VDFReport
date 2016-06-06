package com.ayoka.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ayoka.vdfreport.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmetyildirim on 4.6.2016.
 */
public class FilterAlertDialogFragment  extends DialogFragment {
    private List<String> mCheckedItems = new ArrayList<String>();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String[] list = new String[]{"Material theme", "Holo theme", "Custom theme"};
        LinearLayout layout       = new LinearLayout(getActivity());
        TextView tvMessage        = new TextView(getActivity());
        final EditText etInput    = new EditText(getActivity());
        tvMessage.setText("Enter name:");
        etInput.setSingleLine();
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(tvMessage);
        layout.addView(etInput);
        return new AlertDialog.Builder(getActivity())
                // set dialog icon

                .setTitle(R.string.app_name)
                .setMultiChoiceItems(list,
                        new boolean[]{false, false, false},
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked) {
                                    mCheckedItems.add(list[which]);
                                } else {
                                    mCheckedItems.remove(list[which]);
                                }
                                showToast(
                                        list[which] + " is "
                                                + (isChecked ? "checked" : "unchecked" + ".")
                                );
                            }
                        })
                .setNeutralButton("More info", new ButtonClickedListener("More info"))
                .setNegativeButton("Cancel", new ButtonClickedListener("Cancel"))
                .setPositiveButton(
                        "Choose",
                        new ButtonClickedListener("Chose " + mCheckedItems.toString())
                )
                .setView(layout)
                .show();
    }

    private class ButtonClickedListener implements DialogInterface.OnClickListener {
        private CharSequence mShowWhenClicked;

        public ButtonClickedListener(CharSequence showWhenClicked) {
            mShowWhenClicked = showWhenClicked;
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            showToast("\"" + mShowWhenClicked + "\"" + " button clicked.");
        }
    }

    private Toast mToast = null;

    private void showToast(CharSequence toastText) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(getActivity(), toastText, Toast.LENGTH_SHORT);
        mToast.show();
    }
}