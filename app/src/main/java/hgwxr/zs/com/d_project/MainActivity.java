package hgwxr.zs.com.d_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import hgwxr.zs.com.d_project.db.DSqlHelper;
import hgwxr.zs.com.d_project.db.User;

public class MainActivity extends AppCompatActivity {

    private DSqlHelper mDSqlHelper;
    private EditText etUser;
    private EditText etPsd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDSqlHelper = new DSqlHelper(this);
        mDSqlHelper.insertTransData(100,"c1");
//        dSqlHelper.insertUserData("admin1","admin1");
//        dSqlHelper.insertUserData("admin","admin1");
//        ArrayList<User> users = dSqlHelper.queryUsers();
//        dSqlHelper.updateUserData(1,"admin","123");
        //User admin = dSqlHelper.queryUser("admin");
//        Log.e("TAG", "onCreate: " );
        etUser = ((EditText) findViewById(R.id.tv_name));
        etPsd = ((EditText) findViewById(R.id.tv_psd));
    }

    public void login(View view) {
        String name = etUser.getText().toString();
        User user = mDSqlHelper.queryUser(name);
        if (user!=null&& TextUtils.equals(user.getPsd(),etPsd.getText().toString())){
            Intent intent = new Intent(this, ManagerActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this,"登陆失败",Toast.LENGTH_LONG).show();
        }
    }
}
