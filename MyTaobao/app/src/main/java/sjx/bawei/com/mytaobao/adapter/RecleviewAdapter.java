package sjx.bawei.com.mytaobao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import sjx.bawei.com.mytaobao.R;
import sjx.bawei.com.mytaobao.bean.YBean;

/**
 * dell 孙劲雄
 * 2017/9/8
 * 20:04
 */

public class RecleviewAdapter extends RecyclerView.Adapter<RecleviewAdapter.Myholder> {

    private Context context;

    private YBean yBean;

    public RecleviewAdapter(Context context, YBean yBean) {
        this.context = context;
        this.yBean = yBean;
    }

    @Override
    public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.item, parent,false);

        View view=View.inflate(context,R.layout.ietm,null);

        Myholder myholder=new Myholder(view);

        Log.i("+++",yBean+"");
        return myholder;
    }

    @Override
    public void onBindViewHolder(Myholder holder, int position) {


    Picasso.with(context).load(yBean.getData().getDefaultGoodsList().get(position).getGoods_img()).into(holder.imageView);

    holder.textView.setText(yBean.getData().getDefaultGoodsList().get(position).getGoods_name());


    }

    @Override
    public int getItemCount() {
        return yBean.getData().getDefaultGoodsList().size();
    }

    public class Myholder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public Myholder(View itemView) {

            super(itemView);

            textView= (TextView) itemView.findViewById(R.id.item_text);

            imageView=(ImageView)itemView.findViewById(R.id.item_image);
        }
    }
}
