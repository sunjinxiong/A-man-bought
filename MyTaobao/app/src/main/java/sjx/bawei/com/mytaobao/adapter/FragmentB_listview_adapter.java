package sjx.bawei.com.mytaobao.adapter;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sjx.bawei.com.mytaobao.MyUtils;
import sjx.bawei.com.mytaobao.R;

/**
 * dell 孙劲雄
 * 2017/9/11
 * 21:11
 */

public class FragmentB_listview_adapter extends BaseAdapter {

    private Context context;

    private JSONArray data;


    public FragmentB_listview_adapter(Context context, JSONArray data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.length();
    }

    @Override
    public Object getItem(int position) {


        try {
            return  data.get(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if(convertView==null){


            viewHolder=new ViewHolder();

            convertView=View.inflate(context, R.layout.fragment_listview_item,null);

            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.fragment_listview_item_ima);

            viewHolder.textView= (TextView) convertView.findViewById(R.id.fragment_listview_item_text);

            convertView.setTag(viewHolder);
        }else {


            viewHolder= (ViewHolder) convertView.getTag();

        }


        try {


            JSONObject jsonObject = data.getJSONObject(position);

            String gc_name = MyUtils.Unicode2GBK(jsonObject.optString("gc_name"));

            Log.i("name",gc_name);

            String image = MyUtils.Unicode2GBK(jsonObject.optString("image"));


            if(image.length()>0){

                Picasso.with(context).load(image).into(viewHolder.imageView);
            }


            viewHolder.textView.setText(gc_name);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return convertView;
    }

    class ViewHolder{

        TextView textView;

        ImageView imageView;



    }
}
