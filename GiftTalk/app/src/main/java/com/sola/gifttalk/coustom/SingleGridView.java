package com.sola.gifttalk.coustom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by dllo on 17/2/18.
 */
//自定义的GridView
public class SingleGridView extends GridView {

    public SingleGridView(Context context) {
        super(context);
    }

    public SingleGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 在该方法中使GridView的高为wrap_content的大小,否则GridView中
     * 的内容只能显示很小一部分
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
