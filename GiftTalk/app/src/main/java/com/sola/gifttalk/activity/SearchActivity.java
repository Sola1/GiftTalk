package com.sola.gifttalk.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.sola.gifttalk.R;
import com.sola.gifttalk.adapter.SearchListViewAdapter;
import com.sola.gifttalk.adapter.SearchRecyclerAdapter;
import com.sola.gifttalk.bean.HotSearchBean;
import com.sola.gifttalk.db.DBTool;
import com.sola.gifttalk.db.Search;
import com.sola.gifttalk.inter.RecyclerItemInter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by dllo on 17/3/1.
 */

public class SearchActivity extends BaseActivity {

    private HotSearchBean.DataBean dataBean;
    private SearchRecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;
    private EditText searchEt;
    private Search search,search1;
    private ListView listView;
    private SearchListViewAdapter listViewAdapter;
    private List<Search> searchList;

    @Override
    public int bindLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        recyclerView = bindView(R.id.recycler_view_search);
        searchEt = bindView(R.id.et_search);
        listView = bindView(R.id.list_view_search);

    }

    @Override
    protected void initData() {
        searchList = new ArrayList<>();
        listViewAdapter = new SearchListViewAdapter(this);
        searchList =DBTool.getInstance().querySearch();
        listViewAdapter.setSearchList(DBTool.getInstance().querySearch());

        listViewAdapter.notifyDataSetChanged();

        dataBean = getIntent().getParcelableExtra("dataBean");
        recyclerAdapter = new SearchRecyclerAdapter(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 5, LinearLayoutManager.VERTICAL, false));
        recyclerAdapter.setDataBean(dataBean);
        recyclerView.setAdapter(recyclerAdapter);



        recyclerAdapter.setInter(new RecyclerItemInter() {
            @Override
            public void onItemJump(int pos) {
                search = new Search();
 //               search1 = new Search();
                search.setWords(dataBean.getHot_words().get(pos));
//                search1.setThings(searchEt.getText().toString());

                if (DBTool.getInstance().isSaveSearch(search) == false) {
                    DBTool.getInstance().insertSearchBean(search);
                    searchList.add(search);
                    Log.d("SearchActivity", "searchList.size():  ture" + searchList.size());
                    listViewAdapter.setSearchList(searchList);
                    listViewAdapter.notifyDataSetChanged();
                }
                Log.d("SearchActivity", "searchList.size():  false" + searchList.size());
//                if (DBTool.getInstance().isSaveSearch(search1) == false){
//                    DBTool.getInstance().insertSearchBean(search1);
//                    searchList.add(search1);
//                }









            }
        });
        listView.setAdapter(listViewAdapter);




    }

    @Override
    protected void bindEvent() {
        bindView(R.id.tv_home_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
