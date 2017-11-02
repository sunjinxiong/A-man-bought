package sjx.bawei.com.mytaobao.adapter;

import android.content.Context;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.x;

import sjx.bawei.com.mytaobao.R;

/**
 * dell 孙劲雄
 * 2017/9/12
 * 9:44
 */

public class Fragmentb_recycleview_adapter extends RecyclerView.Adapter<Fragmentb_recycleview_adapter.FragmentHolder> {

    private Context context;
    private JSONArray data;

    public Fragmentb_recycleview_adapter(Context context, JSONArray data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public FragmentHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View inflate = LayoutInflater.from(context).inflate(R.layout.fragment_recycle_item, parent, false);


          FragmentHolder fragmentHolder=new FragmentHolder(inflate);

          return fragmentHolder;
    }

    @Override
    public void onBindViewHolder(final FragmentHolder holder, int position) {

        try {
            JSONObject jsonObject = data.getJSONObject(position);

         String class_list = jsonObject.optString("gc_name");
          String  gc_id = jsonObject.optString("gc_id");
            String path="http://169.254.254.142/mobile/index.php?act=goods_class&gc_id="+(gc_id)+"";
            holder.textView.setText(class_list);
            RequestParams params=new RequestParams(path);

            x.http().get(params, new org.xutils.common.Callback.CommonCallback<String>() {

                @Override
                public void onSuccess(String result) {

                    JSONObject jsonObject= null;
                    try {

                        jsonObject = new JSONObject(result);

                        JSONObject jsonObject1 = jsonObject.optJSONObject("datas");

                        JSONArray jsonArray = jsonObject1.optJSONArray("class_list");

                        holder.recyclerView.setLayoutManager(new GridLayoutManager(context,4));

                        Fragment_item_item_adapter item_item_adapter=new Fragment_item_item_adapter(context,jsonArray);

                        holder.recyclerView.setAdapter(item_item_adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

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

    @Override
    public int getItemCount() {

        return data.length();
    }

    class FragmentHolder extends RecyclerView.ViewHolder {

        TextView textView;

        RecyclerView recyclerView;


        public FragmentHolder(View itemView) {
            super(itemView);


            textView= (TextView) itemView.findViewById(R.id.fragmentb_recycle_item_text);

            recyclerView= (RecyclerView) itemView.findViewById(R.id.framentb_recycleview_item);

        }
    }


}
