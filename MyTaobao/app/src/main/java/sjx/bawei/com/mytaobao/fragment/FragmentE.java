package sjx.bawei.com.mytaobao.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import sjx.bawei.com.mytaobao.R;
import sjx.bawei.com.mytaobao.activty.DluActivity;
import sjx.bawei.com.mytaobao.activty.Main2Activity;
import sjx.bawei.com.mytaobao.activty.ShezhiActivity;
import sjx.bawei.com.mytaobao.adapter.ListviewAdapter;
import view.XListView;

import java.util.ArrayList;
import java.util.List;

/**
 * dell 孙劲雄
 * 2017/9/1
 * 9:08
 */

public class FragmentE extends Fragment {


    private List<View> listview;
    private XListView xListView;
    private TextView textView;
    private ImageView touxiang;
    private SharedPreferences sp;
    private TextView shezhi;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=View.inflate(getActivity(), R.layout.fragmente,null);

        xListView= (XListView) view.findViewById(R.id.xlistview);

        shezhi= (TextView) view.findViewById(R.id.fragment_shezhi);

        return view;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listview=new ArrayList<>();

        xListView.setPullRefreshEnable(true);

        xListView.setPullLoadEnable(true);


        View view2=View.inflate(getActivity(),R.layout.listview,null);

        touxiang= (ImageView) view2.findViewById(R.id.touxiang);

        textView= (TextView) view2.findViewById(R.id.nicheng);

        listview.add(view2);

        xListView.setPullLoadEnable(true);

        xListView.setPullRefreshEnable(true);

        xListView.setAdapter(new ListviewAdapter(getActivity(),listview));

        sp = getActivity().getSharedPreferences("name", Context.MODE_PRIVATE);



        getinit();

    }

   public void getinit(){

       boolean user = sp.getBoolean("user", false);
       String username = sp.getString("username","sjx");

       Log.i("++++",user+"");
       if(user) {

           textView.setText(username);

           Toast.makeText(getActivity(),"你已经登录",Toast.LENGTH_LONG).show();

       }else {

           textView.setText("未登录");

           touxiang.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   Intent it = new Intent(getActivity(), DluActivity.class);

                   startActivity(it);

               }
           });

       }
       xListView.setXListViewListener(new XListView.IXListViewListener() {
           @Override
           public void onRefresh() {

               handler.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       xListView.stopRefresh();
                   }
               },2000);

           }

           @Override
           public void onLoadMore() {

           }
       });

       shezhi.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Intent intent=new Intent(getActivity(), ShezhiActivity.class);

               startActivity(intent);


           }
       });


   }


}
