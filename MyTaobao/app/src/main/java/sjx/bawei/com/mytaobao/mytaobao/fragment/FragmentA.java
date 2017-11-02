package sjx.bawei.com.mytaobao.mytaobao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yalantis.phoenix.PullToRefreshView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import sjx.bawei.com.mytaobao.R;
import sjx.bawei.com.mytaobao.mytaobao.OkHttp3Utils;
import sjx.bawei.com.mytaobao.mytaobao.activty.SousuActivity;
import sjx.bawei.com.mytaobao.mytaobao.adapter.RecycleVIewAdapter;
import sjx.bawei.com.mytaobao.mytaobao.bean.YBean;

/**
 * dell 孙劲雄
 * 2017/8/31
 * 20:13
 */

public class FragmentA extends Fragment {

    private  String path="http://m.yunifang.com/yunifang/mobile/home";
    private PullToRefreshView mPullToRefreshView;
    private LinearLayout layout;
    private TextView fragment_sousu;
    private RecyclerView recycleview;
   private Handler handler=new Handler(){
      @Override
      public void handleMessage(Message msg) {
          super.handleMessage(msg);

                   switch (msg.what)
                   {
          case 1:
              YBean obj = (YBean) msg.obj;
              RecycleVIewAdapter recycleVIewAdapter= new RecycleVIewAdapter(getActivity(),obj);
              recycleview.setAdapter(recycleVIewAdapter);
              break;
               }

      }
  };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = View.inflate(getActivity(), R.layout.fragmenta, null);

        fragment_sousu= (TextView) view.findViewById(R.id.fragmenta_soufu);

          layout= (LinearLayout) view.findViewById(R.id.ha);

        mPullToRefreshView = (PullToRefreshView) view.findViewById(R.id.pull_to_refresh);

        recycleview= (RecyclerView) view.findViewById(R.id.list_view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        recycleview.setLayoutManager(new LinearLayoutManager(getActivity()));


        OkHttp3Utils.doGet(path, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String string = response.body().string();

                 final YBean gson = new Gson().fromJson(string,YBean.class);

                        Message message=new Message();
                        message.what=1;
                        message.obj=gson;
                        handler.sendMessage(message);
            }
        });




        JianTin();





    }

    private void JianTin() {

        //刷新监听
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {

                layout.setVisibility(View.GONE);

                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);

                        layout.setVisibility(View.VISIBLE);
                    }
                }, 2000);
            }
        });

        //搜索跳转
        fragment_sousu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(), SousuActivity.class);

                startActivity(intent);

            }
        });
    }
}
