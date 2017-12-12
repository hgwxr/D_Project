package hgwxr.zs.com.d_project.ui;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import hgwxr.zs.com.d_project.R;
import hgwxr.zs.com.d_project.db.DSqlHelper;
import hgwxr.zs.com.d_project.db.TransEntity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


    private SearchAdapter searchAdapter;

    public static SearchFragment instance() {
        return new SearchFragment();
    }

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_search, container, false);
        final EditText categoryEt = inflate.findViewById(R.id.search_et_category);
        inflate.findViewById(R.id.search_category).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = categoryEt.getText().toString();
                if (!TextUtils.isEmpty(category.trim())) {
                    ArrayList<TransEntity> transEntities = DSqlHelper.instance(getContext()).queryTransData(category);
                    searchAdapter.clear();
                    searchAdapter.addAll(transEntities);
                } else {
                    Toast.makeText(getContext(), "请输入类别", Toast.LENGTH_SHORT).show();
                }
            }
        });
        inflate.findViewById(R.id.search_time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar d = Calendar.getInstance(Locale.CHINA);
                // 创建一个日历引用d，通过静态方法getInstance() 从指定时区 Locale.CHINA 获得一个日期实例
                Date myDate = new Date();
                // 创建一个Date实例
                d.setTime(myDate);
                // 设置日历的时间，把一个新建Date实例myDate传入
                int year = d.get(Calendar.YEAR);
                int month = d.get(Calendar.MONTH);
                final int day = d.get(Calendar.DAY_OF_MONTH);
                //初始化默认日期year, month, day
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    /**
                     * 点击确定后，在这个方法中获取年月日
                     */
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {

                        Calendar instance = Calendar.getInstance();
                        instance.set(year, monthOfYear, day,0,0,0);
                        long timeInMillis = instance.getTimeInMillis();
                        ArrayList<TransEntity> transEntities = DSqlHelper.instance(getContext()).queryTransData(timeInMillis);
                        searchAdapter.clear();
                        searchAdapter.addAll(transEntities);
                    }
                }, year, month, day);
                datePickerDialog.setMessage("请选择日期");
                datePickerDialog.show();
            }
        });
        ListView searchList = inflate.findViewById(R.id.search_list);
        searchAdapter = new SearchAdapter(getContext());
        searchList.setAdapter(searchAdapter);
        ArrayList<TransEntity> transEntities = DSqlHelper.instance(getContext()).queryTransDatas();
        searchAdapter.addAll(transEntities);
        return inflate;
    }

}
