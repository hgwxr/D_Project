package hgwxr.zs.com.d_project.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import hgwxr.zs.com.d_project.R;
import hgwxr.zs.com.d_project.db.DSqlHelper;
import hgwxr.zs.com.d_project.db.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserMoudleFragment extends Fragment {


    private DSqlHelper dSqlHelper;
    private UserAdapter userAdapter;

    public static UserMoudleFragment  instance(){
        return new UserMoudleFragment();
    }
    public UserMoudleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_moudle, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView userList = view.findViewById(R.id.user_list);
        view.findViewById(R.id.tv_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserDialog userDialog = new UserDialog(getContext());
                userDialog.setFragment(UserMoudleFragment.this);
                userDialog.show();
            }
        });
        userAdapter = new UserAdapter(getContext());
        userList.setAdapter(userAdapter);
        dSqlHelper = DSqlHelper.instance(getContext());
        final ArrayList<User> users = dSqlHelper.queryUsers();
        userAdapter.addAll(users);
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                UserDialog userDialog = new UserDialog(getContext());
                userDialog.setFragment(UserMoudleFragment.this);
                userDialog.setUser(users.get(position));
                userDialog.show();
            }
        });
    }

    public void refreshData() {
        userAdapter.clear();
        ArrayList<User> users = dSqlHelper.queryUsers();
        userAdapter.addAll(users);
    }
}
