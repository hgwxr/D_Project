package hgwxr.zs.com.d_project.ui;

import android.content.Context;
import android.widget.TextView;

import hgwxr.zs.com.d_project.R;
import hgwxr.zs.com.d_project.db.User;

/**
 * Created by hgwxr on 2017/12/11.
 */

public class UserAdapter extends ListViewAdapter<User> {
    public UserAdapter(Context context) {
        super(context,new int[]{R.layout.item_user});
    }

    @Override
    public void onBindView(ViewHolder holder, User data, int position) {
        TextView id = holder.getItemView().findViewById(R.id.u_id);
        TextView name = holder.getItemView().findViewById(R.id.u_name);
        TextView psd = holder.getItemView().findViewById(R.id.u_psd);
        id.setText(data.getId()+"");
        name.setText(data.getName());
        psd.setText(data.getPsd());
    }
}
