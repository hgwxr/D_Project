package hgwxr.zs.com.d_project.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hgwxr.zs.com.d_project.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransFragment extends Fragment {


    public static  TransFragment instance(){
        return new TransFragment();
    }
    public TransFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trans, container, false);
    }

}
