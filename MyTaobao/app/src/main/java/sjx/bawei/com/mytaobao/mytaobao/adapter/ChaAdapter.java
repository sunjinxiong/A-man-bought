package sjx.bawei.com.mytaobao.mytaobao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import sjx.bawei.com.mytaobao.MyUtils;
import sjx.bawei.com.mytaobao.R;
import sjx.bawei.com.mytaobao.mytaobao.bean.Laols;

/**
 * dell 孙劲雄
 * 2017/9/11
 * 19:10
 */

public class ChaAdapter extends RecyclerView.Adapter<ChaAdapter.MHolder> {

    private Laols data;
    private Context context;

    public ChaAdapter(Laols data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public MHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View inflate = LayoutInflater.from(context).inflate(R.layout.cha_item, parent, false);

        MHolder mHolder=new MHolder(inflate);

        return mHolder;
    }

    @Override
    public void onBindViewHolder(MHolder holder, int position) {

        Picasso.with(context).load(MyUtils.Unicode2GBK(data.getDatas().getGoods_list().get(position).getGoods_image_url()));

        holder.textView.setText(MyUtils.Unicode2GBK(data.getDatas().getGoods_list().get(position).getGoods_name()));

        holder.textView.setText(MyUtils.Unicode2GBK(data.getDatas().getGoods_list().get(position).getGoods_price()));

    }

    @Override
    public int getItemCount() {
        return data.getDatas().getGoods_list().size();
    }

  class MHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        TextView textView,textView2;

        public MHolder(View itemView) {
            super(itemView);

            imageView= (ImageView) itemView.findViewById(R.id.cha_item_ima);

            textView= (TextView) itemView.findViewById(R.id.cha_item_text);

            textView2= (TextView) itemView.findViewById(R.id.cha_item_text2);


        }

    }


}
