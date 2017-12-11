package hgwxr.zs.com.d_project.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hgwxr on 2017/12/11.
 */

public class DPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment>  mLists;
    String[] titles=new String[]{
            "系统登陆模块",
            "收入/支出模块",
            "查询模块"
    };
    public DPagerAdapter(FragmentManager fm) {
        super(fm);
        mLists=new ArrayList<>();
        mLists.add(UserMoudleFragment.instance());
        mLists.add(TransFragment.instance());
        mLists.add(SearchFragment.instance());
    }

    @Override
    public Fragment getItem(int position) {
        return mLists.get(position);
    }

    @Override
    public int getCount() {
        return mLists.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
