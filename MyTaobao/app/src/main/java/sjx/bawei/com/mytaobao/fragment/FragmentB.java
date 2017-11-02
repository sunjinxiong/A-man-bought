package sjx.bawei.com.mytaobao.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.x;
import sjx.bawei.com.mytaobao.R;
import sjx.bawei.com.mytaobao.adapter.FragmentB_listview_adapter;
import sjx.bawei.com.mytaobao.adapter.Fragmentb_recycleview_adapter;

/**
 * dell 孙劲雄
 * 2017/9/1
 * 13:45
 */
public class FragmentB extends Fragment {

    private ListView listView;

    private RecyclerView recyclerView;
    private JSONArray class_list;
    private Handler handler=new Handler(){


        private Fragmentb_recycleview_adapter fragmentb_recycleview_adapter;

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){

                case 0:
                    String obj = (String) msg.obj;

                    try {
                        JSONObject object=new JSONObject(obj);

                        JSONObject jsonObject = object.optJSONObject("datas");

                        class_list = jsonObject.optJSONArray("class_list");

                        listView.setAdapter(new FragmentB_listview_adapter(getActivity(), class_list));


                        Log.i("++++", class_list +"");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;

                case 1:

                    String obj1 = (String) msg.obj;

                    try {
                        JSONObject jsonObject=new JSONObject(obj1);

                        JSONObject jsonObject1 = jsonObject.optJSONObject("datas");

                        JSONArray jsonArray = jsonObject1.optJSONArray("class_list");

                        fragmentb_recycleview_adapter = new Fragmentb_recycleview_adapter(getActivity(),jsonArray);

                        recyclerView.setAdapter(fragmentb_recycleview_adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=View.inflate(getActivity(), R.layout.fragmentb,null);

        listView= (ListView) view.findViewById(R.id.class_);

        recyclerView= (RecyclerView) view.findViewById(R.id.class_er);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        getdata();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                try {
                    JSONObject jsonObject = class_list.optJSONObject(position);

                    String gc_id = jsonObject.getString("gc_id");

                    String path="http://169.254.254.142/mobile/index.php?act=goods_class&gc_id="+(gc_id)+"";

                    RequestParams params=new RequestParams(path);

                    x.http().get(params, new org.xutils.common.Callback.CommonCallback<String>() {

                        @Override
                        public void onSuccess(String result) {

                            Message me=new Message();
                            me.what=1;
                            me.obj=result;

                            handler.sendMessage(me);
                        }

                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {

                            Log.i("Xx","错误");
                        }

                        @Override
                        public void onCancelled(CancelledException cex) {

                        }

                        @Override
                        public void onFinished() {

                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        });



    }


    public void getdata() {

        String path = "http://169.254.254.142/mobile/index.php?act=goods_class";

        RequestParams params=new RequestParams(path);

        x.http().get(params, new org.xutils.common.Callback.CommonCallback<String>() {

       @Override
       public void onSuccess(String result) {

           Message me=new Message();


           me.what=0;
           me.obj=result;

           handler.sendMessage(me);
       }

       @Override
       public void onError(Throwable ex, boolean isOnCallback) {

           Log.i("Xx","错误");
       }

       @Override
       public void onCancelled(CancelledException cex) {

       }

       @Override
       public void onFinished() {

       }
   });









    }
}
