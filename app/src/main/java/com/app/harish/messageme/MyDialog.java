package com.app.harish.messageme;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyDialog extends DialogFragment {

    public interface Retry{
        void checkOnline();
    }
    TextView mActionCancel;
    TextView mActionRetry;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_custom,container,false);

        mActionCancel = view.findViewById(R.id.action_cancel);
        mActionRetry = view.findViewById(R.id.action_ok);

        mActionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        mActionRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).checkOnline();
                getDialog().dismiss();
            }
        });
        return view;
    }
}
