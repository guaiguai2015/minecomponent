package com.guaiguai.wrl.minecomponent.fragment.home;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.guaiguai.wrl.minecomponent.R;
import com.guaiguai.wrl.minecomponent.fragment.BaseFragment;
import com.guaiguai.wrl.minecomponent.moudle.recommand.BaseRecommandModel;
import com.guaiguai.wrl.minecomponent.network.http.RequestCenter;
import com.guaiguai.wrl.mylibrary.okhttp.CommonOkHttpClient;
import com.guaiguai.wrl.mylibrary.okhttp.listener.DisposeDataHandle;
import com.guaiguai.wrl.mylibrary.okhttp.listener.DisposeDataListener;
import com.guaiguai.wrl.mylibrary.okhttp.request.CommonRequest;
import com.guaiguai.wrl.mylibrary.okhttp.response.CommonJsonCallBack;

/**
 * Created by WRL on 2017/5/3.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private static final int REQUEST_QRCODE = 0x01;
    /**
     * UI
     */
    private View mContentView;
    private ListView mListView;
    private TextView mQRCodeView;
    private TextView mCategoryView;
    private TextView mSearchView;
    private ImageView mLoadingView;
    /**
     * data
     */
//    private CourseAdapter mAdapter;
    private BaseRecommandModel mRecommandData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestRecommandData();
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mContext = getActivity();
        mContentView = inflater.inflate(R.layout.fragment_home_layout,null);
        initView();
        return mContentView;
    }

    private void initView() {
        mQRCodeView = (TextView) mContentView.findViewById(R.id.qrcode_view);
        mQRCodeView.setOnClickListener(this);
        mCategoryView = (TextView) mContentView.findViewById(R.id.category_view);
        mCategoryView.setOnClickListener(this);
        mSearchView = (TextView) mContentView.findViewById(R.id.search_view);
        mSearchView.setOnClickListener(this);
        mListView = (ListView) mContentView.findViewById(R.id.list_view);
        mListView.setOnItemClickListener(this);
        mLoadingView = (ImageView) mContentView.findViewById(R.id.loading_view);
        //加载数据的动画的开启
        AnimationDrawable anim = (AnimationDrawable) mLoadingView.getDrawable();
        anim.start();
    }


    private void requestRecommandData() {
        RequestCenter.requestCommandData(new DisposeDataListener() {

            @Override
            public void onSuccess(Object responseObject) {
                mRecommandData = (BaseRecommandModel) responseObject;
                //更新UI
                showSuccessView();
            }

            @Override
            public void onFailure(Object reasonObject) {
                //显示请求失败View
                showErrorView();
            }

        });
    }

    private void showErrorView() {

    }

    private void showSuccessView() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
