package com.guaiguai.wrl.minecomponent.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.guaiguai.wrl.minecomponent.R;
import com.guaiguai.wrl.minecomponent.adapter.PhotoPagerAdapter;
import com.guaiguai.wrl.minecomponent.moudle.recommand.RecommandHeadValue;
import com.guaiguai.wrl.minecomponent.util.ImageLoaderManager;


/**
 * @author: vision
 * @function:
 * @date: 16/9/2
 */
public class HomeHeaderLayout extends RelativeLayout {

    private static final int MSG_UPDATE_IMAGE = 1; //开始进行轮播
    private static final int MSG_STOP_UPDATE = 2;   //暂停轮播
    private static final int MSG_DELAY = 5000; //轮播间隔时间

    private int currentItem = 0;

    private ImageLoaderManager mImageLoaderManager;
    private Context mContext;
    private RecommandHeadValue mHeadValue;
    private LayoutInflater inflater;
    private ViewPager mViewPager;

    private PhotoPagerAdapter mAdapter;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what) {
                case MSG_UPDATE_IMAGE:
                    currentItem++;
                    mViewPager.setCurrentItem(currentItem,true);
                    handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE,MSG_DELAY);
                    break;

                case MSG_STOP_UPDATE:
                    if (handler.hasMessages(MSG_UPDATE_IMAGE)) {
                          handler.removeMessages(MSG_UPDATE_IMAGE);
                    }
                    break;
            }
        }
    };

    public HomeHeaderLayout(Context context, RecommandHeadValue headValue) {
        this(context,null,headValue);
    }

    public HomeHeaderLayout(Context context, AttributeSet attrs,RecommandHeadValue headValue) {

        super(context, attrs);
        this.mContext = context;
        this.mHeadValue = headValue;

        mImageLoaderManager = ImageLoaderManager.getInstance(mContext);
        inflater = LayoutInflater.from(mContext);
        initView();

    }

    private void initView() {
        View view = inflater.inflate(R.layout.listview_home_head_layout,this);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);

        mAdapter = new PhotoPagerAdapter(mContext,mHeadValue.ads);
        mViewPager.setAdapter(mAdapter);


        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentItem = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:   //在拖动的过程中
//                        handler.sendEmptyMessage(MSG_STOP_UPDATE);
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:      //在空闲的时候
//                        handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE,MSG_DELAY);
                        break;
                    default:
                        break;
                }
            }
        });
        handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE,MSG_DELAY);

    }

}
