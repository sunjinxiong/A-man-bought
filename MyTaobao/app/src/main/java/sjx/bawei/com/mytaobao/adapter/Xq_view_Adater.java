package sjx.bawei.com.mytaobao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import sjx.bawei.com.mytaobao.MyUtils;
import sjx.bawei.com.mytaobao.R;
import sjx.bawei.com.mytaobao.bean.Shop;

/**
 * dell 孙劲雄
 * 2017/9/13
 * 19:30
 */

public class Xq_view_Adater extends RecyclerView.Adapter<Xq_view_Adater.Holde> {

    private Context context;

    private Shop shop;

    public Xq_view_Adater(Context context, Shop shop) {
        this.context = context;
        this.shop = shop;
    }

    @Override
    public Holde onCreateViewHolder(ViewGroup parent, int viewType) {

        final View inflate = LayoutInflater.from(context).inflate(R.layout.cha_item, parent, false);


        final Holde holde=new Holde(inflate);

        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //自己获取position
                int position = holde.getLayoutPosition();
                //设置监听
                if (listener != null) {
                    listener.onRecyclerViewItemClick(position);
                }
            }
        });

        return holde;
    }

    @Override
    public void onBindViewHolder(Holde holder, int position) {

        Picasso.with(context).load(MyUtils.Unicode2GBK(shop.getDatas().getGoods_commend_list().get(position).getGoods_image_url())).into(holder.imageView);

        holder.textView.setText(MyUtils.Unicode2GBK(shop.getDatas().getGoods_commend_list().get(position).getGoods_name()));

        shop.getDatas().getGoods_commend_list().get(position).getGoods_promotion_price();

       holder.textView2.setText( shop.getDatas().getGoods_commend_list().get(position).getGoods_promotion_price());

    }

    @Override
    public int getItemCount() {
        return shop.getDatas().getGoods_commend_list().size();
    }

    class Holde extends RecyclerView.ViewHolder {

        ImageView imageView;

        TextView textView,textView2;

        public Holde(View itemView) {
            super(itemView);

            imageView= (ImageView) itemView.findViewById(R.id.cha_item_ima);

            textView= (TextView) itemView.findViewById(R.id.cha_item_text);

            textView2= (TextView) itemView.findViewById(R.id.cha_item_text2);
        }
    }
    private ChaAdapter.OnRrecyclerViewItemClickListener listener;

    //定义接口 和抽象方法
    public interface OnRrecyclerViewItemClickListener {
        void onRecyclerViewItemClick(int position);
    }

    //提供设置监听的方法
    public void setOnRrecyclerViewItemClickListener(ChaAdapter.OnRrecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

}
