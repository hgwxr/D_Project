package hgwxr.zs.com.d_project;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import hgwxr.zs.com.d_project.ui.DPagerAdapter;

public class ManagerActivity extends AppCompatActivity {

    private TabLayout mTablayout;
    private ViewPager mViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        mTablayout = ((TabLayout) findViewById(R.id.tab_layout));
        mViewpager = ((ViewPager) findViewById(R.id.view_pager));

        DPagerAdapter dPagerAdapter = new DPagerAdapter(getSupportFragmentManager());
        mViewpager.setAdapter(dPagerAdapter);
        mTablayout.setupWithViewPager(mViewpager);

    }
}
