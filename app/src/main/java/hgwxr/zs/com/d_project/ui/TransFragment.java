package hgwxr.zs.com.d_project.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import hgwxr.zs.com.d_project.R;
import hgwxr.zs.com.d_project.db.DSqlHelper;
import hgwxr.zs.com.d_project.db.TransEntity;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransFragment extends Fragment {


    private TransAdapter adapter;
    private ListView listView;

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
        View view = inflater.inflate(R.layout.fragment_trans, container, false);
        listView = view.findViewById(R.id.trans_list);
        view.findViewById(R.id.trans_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransDialog transDialog = new TransDialog(getContext());
                transDialog.setFragment(TransFragment.this);
                transDialog.show();
            }
        });
        adapter = new TransAdapter(getContext());
        adapter.setTransFragment(this);
        listView.setAdapter(adapter);
        loadData();
        return view;
    }

    private void loadData() {
        ArrayList<TransEntity> transEntities = DSqlHelper.instance(getContext()).queryTransDatas();
        adapter.addAll(transEntities);
    }

    public void refreshData() {
        adapter.clear();
        loadData();
    }
}
