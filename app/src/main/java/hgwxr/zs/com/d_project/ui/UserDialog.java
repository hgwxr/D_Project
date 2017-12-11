package hgwxr.zs.com.d_project.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import hgwxr.zs.com.d_project.R;
import hgwxr.zs.com.d_project.db.DSqlHelper;
import hgwxr.zs.com.d_project.db.User;

/**
 * Created by hgwxr on 2017/12/11.
 */

public class UserDialog extends Dialog {
    private  UserMoudleFragment uFragment;

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public UserDialog(@NonNull Context context) {
        super(context,R.style.style_dialog);
    }

    public void setFragment(UserMoudleFragment uFragment) {
        this.uFragment = uFragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_input);

        Window window = getWindow();
        window.setGravity(Gravity.CENTER); //可设置dialog的位置
        window.getDecorView().setPadding(0, 0, 0, 0); //消除边距

        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //设置宽度充满屏幕
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        final EditText et_name = findViewById(R.id.tv_name);
        final EditText et_psd = findViewById(R.id.tv_psd);
        final TextView idTv = (TextView) findViewById(R.id.u_id);
        idTv.setText(user.getId()+"");
        et_name.setText(user.getName());
        et_psd.setText(user.getPsd());
        final DSqlHelper dSqlHelper = new DSqlHelper(getContext());
        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_name.getText().toString();
                String psd = et_psd.getText().toString();

                if (!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(psd)) {
                    String id = idTv.getText().toString();
                    if (!TextUtils.isEmpty(id)){
                        dSqlHelper.updateUserData(Integer.parseInt(id),name,psd);
                    }else {
                        dSqlHelper.insertUserData(name, psd);
                    }
                    uFragment.refreshData();
                    dismiss();
                }else{
                    Toast.makeText(getContext(),"不能为空",Toast.LENGTH_LONG).show();
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
