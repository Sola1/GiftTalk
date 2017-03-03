package com.sola.gifttalk.db;

import com.sola.gifttalk.tool.MyApp;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by dllo on 17/3/2.
 */
public class DBTool {
    private SearchDao searchDao;
    private CollectDao collectDao;
    private static DBTool ourInstance;

    public static DBTool getInstance() {
        if (ourInstance == null) {
            synchronized (DBTool.class) {
                if (ourInstance == null) {
                    ourInstance = new DBTool();
                }
            }
        }
        return ourInstance;
    }

    private DBTool() {
        //在构造方法中对操作数据库的对象进行初始化
        searchDao = MyApp.getDaoSession().getSearchDao();
        collectDao = MyApp.getDaoSession().getCollectDao();
    }

    public void insertCollectBean(Collect collect){
        collectDao.insert(collect);
    }

    public void insertSearchBean(Search search) {
        searchDao.insert(search);
    }

    public void insertString(String words) {
        Search search = new Search();
        search.setWords(words);
        searchDao.insert(search);
    }

    public void insertSearchList(List<Search> list) {
        searchDao.insertInTx(list);
    }

    public void deleteSearch(Search search) {
        searchDao.delete(search);
    }

    public void deleteAllSearch() {
        searchDao.deleteAll();
    }

    public void deleteByWords(String words) {
        DeleteQuery<Search> delete = searchDao.
                queryBuilder().where(SearchDao.Properties.Words.eq(words)).buildDelete();
        delete.executeDeleteWithoutDetachingEntities();


    }

    public List<Search> querySearch() {
        List<Search> list = searchDao.loadAll();
        return list;
    }
    public List<Collect> queryCollect() {
        List<Collect> list = collectDao.loadAll();
        return list;
    }



    //查重
    public boolean isSaveSearch(Search search) {
        QueryBuilder<Search> bulider = searchDao.queryBuilder().where(SearchDao.Properties.Words.eq(search.getWords()));

        Long count = bulider.buildCount().count();

        return count > 0 ? true : false;

    }
    public boolean isSaveCollect(Collect collect) {
        QueryBuilder<Collect> bulider = collectDao.queryBuilder().where(CollectDao.Properties.Url.eq(collect.getUrl()));

        Long count = bulider.buildCount().count();

        return count > 0 ? true : false;

    }


}
