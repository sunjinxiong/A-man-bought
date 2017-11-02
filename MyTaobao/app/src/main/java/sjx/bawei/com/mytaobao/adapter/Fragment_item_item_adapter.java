package sjx.bawei.com.mytaobao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sjx.bawei.com.mytaobao.R;

/**
 * dell 孙劲雄
 * 2017/9/12
 * 10:01
 */

public class Fragment_item_item_adapter extends RecyclerView.Adapter<Fragment_item_item_adapter.Holder>{

    private Context context;

    private JSONArray jsonArray;

    public Fragment_item_item_adapter(Context context, JSONArray jsonArray) {
        this.context = context;
        this.jsonArray = jsonArray;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {


        View inflate = LayoutInflater.from(context).inflate(R.layout.fragment_item_item, parent, false);

        Holder holder=new Holder(inflate);


        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {


        JSONObject jsonObject = null;
        try {
            jsonObject = jsonArray.getJSONObject(position);

            String class_list = jsonObject.optString("gc_name");

            holder.button.setText(class_list);


        } catch (JSONException e) {
            e.printStackTrace();
        }




    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }

    class Holder extends RecyclerView.ViewHolder {

        Button button;

        public Holder(View itemView) {
            super(itemView);

            button= (Button) itemView.findViewById(R.id.button_item);

        }
    }


}
