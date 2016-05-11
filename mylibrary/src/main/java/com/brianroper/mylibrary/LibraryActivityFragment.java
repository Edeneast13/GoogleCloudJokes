package com.brianroper.mylibrary;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class LibraryActivityFragment extends Fragment {

    String mJoke = null;
    TextView mJokeTextView;


    public LibraryActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_library, container, false);
        mJokeTextView = (TextView)root.findViewById(R.id.joke_textview);

        getArgs();

        mJokeTextView.setText(mJoke);

        return root;
    }

    public void getArgs(){

        Intent intent = getActivity().getIntent();
        mJoke = intent.getStringExtra("joke");
        Log.i("JOKE: ", mJoke);
    }
}
