package hgwxr.zs.com.d_project.ui;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import hgwxr.zs.com.d_project.R;
import hgwxr.zs.com.d_project.TimeUtils;
import hgwxr.zs.com.d_project.db.DSqlHelper;
import hgwxr.zs.com.d_project.db.TransEntity;


public class SearchAdapter  extends ListViewAdapter<TransEntity> {
    public SearchAdapter(Context context) {
        super(context,new int[]{R.layout.item_trans});
    }

    private SearchFragment searchFragment;

    public void setSearchFragment(SearchFragment searchFragment) {
        this.searchFragment = searchFragment;
    }

    @Override
    public void onBindView(ViewHolder holder, final TransEntity data, int position) {
        final View itemView = holder.getItemView();
        TextView transId = itemView.findViewById(R.id.trans_id);
        TextView transMoney = itemView.findViewById(R.id.trans_money);
        TextView transTime = itemView.findViewById(R.id.trans_time);
        final TextView transCategory = itemView.findViewById(R.id.trans_category);
        transId.setText(data.getId()+"");
        transMoney.setText(data.getMoney()+"");
        transTime.setText(TimeUtils.formatLongToString(data.getTime()));
        transCategory.setText(data.getCategoryName());
        itemView.findViewById(R.id.trans_delete).setVisibility(View.INVISIBLE);
        itemView.findViewById(R.id.trans_update).setVisibility(View.INVISIBLE);
    }
}
