package hgwxr.zs.com.d_project.ui;

import android.content.Context;

import hgwxr.zs.com.d_project.R;
import hgwxr.zs.com.d_project.db.TransEntity;


public class SearchAdapter  extends ListViewAdapter<TransEntity> {
    public SearchAdapter(Context context) {
        super(context,new int[]{R.layout.item_trans});
    }

    @Override
    public void onBindView(ViewHolder holder, TransEntity data, int position) {

    }
}
