package hgwxr.zs.com.d_project.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import hgwxr.zs.com.d_project.R;
import hgwxr.zs.com.d_project.db.DSqlHelper;
import hgwxr.zs.com.d_project.db.TransEntity;
import hgwxr.zs.com.d_project.db.User;

/**
 * Created by hgwxr on 2017/12/11.
 */

public class TransDialog extends Dialog {
    private TransFragment tFragment;

    private TransEntity transEntity;

    public void setTransEntity(TransEntity transEntity) {
        this.transEntity = transEntity;
    }

    public TransDialog(@NonNull Context context) {
        super(context, R.style.style_dialog);
    }

    public void setFragment(TransFragment tFragment) {
        this.tFragment = tFragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trans_input);

        Window window = getWindow();
        window.setGravity(Gravity.CENTER); //可设置dialog的位置
        window.getDecorView().setPadding(0, 0, 0, 0); //消除边距

        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //设置宽度充满屏幕
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        final EditText et_category= findViewById(R.id.tv_category);
        final EditText et_money= findViewById(R.id.tv_money);
        final TextView idTv = findViewById(R.id.u_id);
        if (transEntity != null) {
            idTv.setText(transEntity.getId() + "");
            et_category.setText(transEntity.getCategoryName());
            et_money.setText(transEntity.getMoney()+"");
        }
        final DSqlHelper dSqlHelper = new DSqlHelper(getContext());
        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = et_category.getText().toString();
                String money = et_money.getText().toString();
                if (!TextUtils.isEmpty(category) && !TextUtils.isEmpty(money.trim())) {
                    if (transEntity!=null) {
                        transEntity.setCategoryName(category);
                        transEntity.setMoney(Long.parseLong(TextUtils.isEmpty(money)?"0":money));
                        dSqlHelper.updateTrans(transEntity);
                    } else {
                        dSqlHelper.insertTransData(Long.parseLong(TextUtils.isEmpty(money)?"0":money), category);
                    }
                    tFragment.refreshData();
                    dismiss();
                } else {
                    Toast.makeText(getContext(), "不能为空", Toast.LENGTH_LONG).show();
                }
            }
        });
        findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

}
