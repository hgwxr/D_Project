package hgwxr.zs.com.d_project.ui;


import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import hgwxr.zs.com.d_project.R;
import hgwxr.zs.com.d_project.TimeUtils;
import hgwxr.zs.com.d_project.db.DSqlHelper;
import hgwxr.zs.com.d_project.db.TransEntity;

public class TransAdapter extends ListViewAdapter<TransEntity>{
    public TransAdapter(Context context) {
        super(context,new int[]{R.layout.item_trans});
    }

    private TransFragment mTransFragment;

    public void setTransFragment(TransFragment mTransFragment) {
        this.mTransFragment = mTransFragment;
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
        itemView.findViewById(R.id.trans_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int trans = DSqlHelper.instance(itemView.getContext()).deleteTrans(data.getId());
                if (trans==1){
                    Toast.makeText(itemView.getContext(),"删除成功",Toast.LENGTH_LONG).show();
                    mTransFragment.refreshData();
                }else{
                    Toast.makeText(itemView.getContext(),"删除失败",Toast.LENGTH_LONG).show();
                }
            }
        });
        itemView.findViewById(R.id.trans_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransDialog transDialog = new TransDialog(mTransFragment.getContext());
                transDialog.setFragment(mTransFragment);
                transDialog.setTransEntity(data);
                transDialog.show();
            }
        });
    }
}
