package com.guaiguai.wrl.minecomponent.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.guaiguai.wrl.minecomponent.R;
import com.guaiguai.wrl.minecomponent.fragment.BaseFragment;
import com.guaiguai.wrl.mylibrary.okhttp.CommonOkHttpClient;
import com.guaiguai.wrl.mylibrary.okhttp.listener.DisposeDataHandle;
import com.guaiguai.wrl.mylibrary.okhttp.listener.DisposeDataListener;
import com.guaiguai.wrl.mylibrary.okhttp.request.CommonRequest;
import com.guaiguai.wrl.mylibrary.okhttp.response.CommonJsonCallBack;

/**
 * Created by WRL on 2017/5/3.
 */
public class HomeFragment extends BaseFragment {



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mContext = getActivity();
        View view = inflater.inflate(R.layout.fragment_home_layout,null);

        return view;
    }

    public void start (View view) {
         //开始请求网络
        CommonOkHttpClient.sendRequest(CommonRequest.createGetRequest("www.baidu.com",null),
                new CommonJsonCallBack(new DisposeDataHandle(new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObject) {
                        Toast.makeText(getActivity(),"请求网络成功",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Object reasonObject) {
                        Toast.makeText(getActivity(),"请求网络失败",Toast.LENGTH_LONG).show();
                    }
                })));
    }
}
