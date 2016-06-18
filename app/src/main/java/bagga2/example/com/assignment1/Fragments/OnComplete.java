package bagga2.example.com.assignment1.Fragments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import bagga2.example.com.assignment1.Database.DatabaseHandler;
import bagga2.example.com.assignment1.R;


public class OnComplete extends Fragment {
    View v;
    DatabaseHandler handler;
    SQLiteDatabase database;


    public OnComplete() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_on_complete, container, false);
        final String task = getArguments().getString("task");
        Button onComplete = (Button)v.findViewById(R.id.OnComplete);


        onComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Delete that task and go back
                System.out.println(task);
            }
        });

        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
