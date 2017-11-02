package sjx.bawei.com.mytaobao.mytaobao.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import sjx.bawei.com.mytaobao.R;
import sjx.bawei.com.mytaobao.mytaobao.AlphaPageTransformer;
import sjx.bawei.com.mytaobao.mytaobao.activty.WebActivity;
import sjx.bawei.com.mytaobao.mytaobao.bean.YBean;
import sjx.bawei.com.mytaobao.mytaobao.fragment.image;

/**
 * dell 孙劲雄
 * 2017/9/7
 * 8:29
 */

public class RecycleVIewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private YBean yBean;
    List<String> list;

    public RecycleVIewAdapter(Context context, YBean yBean) {
        this.context = context;
        this.yBean = yBean;
    }

    public enum Item_Type {
        RECYCLEVIEW_ITEM_TYPE_1,
        RECYCLEVIEW_ITEM_TYPE_2,
        RECYCLEVIEW_ITEM_TYPE_3,
        RECYCLEVIEW_ITEM_TYPE_4
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        if(viewType== Item_Type.RECYCLEVIEW_ITEM_TYPE_1.ordinal()){


            View inflate = View.inflate(context,R.layout.recycleviewitem,null);

            ViewholderA viewholderA=new ViewholderA(inflate);

            return viewholderA;

        }else if(viewType== Item_Type.RECYCLEVIEW_ITEM_TYPE_2.ordinal()){
            View inflate = View.inflate(context,R.layout.recycleviewitem2,null);
            ViewholderB viewholderB=new ViewholderB(inflate);

            return viewholderB;


        }else if(viewType== Item_Type.RECYCLEVIEW_ITEM_TYPE_3.ordinal()){
            View inflate = View.inflate(context,R.layout.recycleviewitem3,null);

            ViewholderC viewholderc=new ViewholderC(inflate);

            return viewholderc;
        }else if(viewType== Item_Type.RECYCLEVIEW_ITEM_TYPE_4.ordinal())
        {

          View inflate = View.inflate(context,R.layout.recycleview,null);

            ViewholderD viewholderD=new ViewholderD(inflate);

            return viewholderD;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

      list=new ArrayList<>();
        if(holder instanceof ViewholderA){

             yBean.getData().getAd1().size();
             for(int i=0;i< yBean.getData().getAd1().size();i++){

               list.add(yBean.getData().getAd1().get(i).getImage());

                 Log.i("xxx","asdas");
            }
              ((ViewholderA) holder).banner.setImageLoader(new image());

            ((ViewholderA) holder).banner.setImages(list);
            ((ViewholderA) holder).banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {

                    Intent it=new Intent(context, WebActivity.class);

                    it.putExtra("url",yBean.getData().getAd1().get(position).getAd_type_dynamic_data());

                    context.startActivity(it);
                }
            });

            ((ViewholderA) holder).banner.start();


        }else if(holder instanceof ViewholderB){


            Picasso.with(context).load(yBean.getData().getAd8().get(0).getImage()).into(((ViewholderB) holder).imageView);

            ((ViewholderB) holder).imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,WebActivity.class);
                    intent.putExtra("url",yBean.getData().getAd8().get(0).getAd_type_dynamic_data());

                    context.startActivity(intent);

                }
            });
            Picasso.with(context).load(yBean.getData().getAd8().get(1).getImage()).into(((ViewholderB) holder).imageView2);
            ((ViewholderB) holder).imageView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,WebActivity.class);
                    intent.putExtra("url",yBean.getData().getAd8().get(1).getAd_type_dynamic_data());

                    context.startActivity(intent);

                }
            });
            Picasso.with(context).load(yBean.getData().getAd8().get(2).getImage()).into(((ViewholderB) holder).imageView3);
            ((ViewholderB) holder).imageView3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,WebActivity.class);
                    intent.putExtra("url",yBean.getData().getAd8().get(2).getAd_type_dynamic_data());

                    context.startActivity(intent);

                }
            });

        }else if(holder instanceof ViewholderC){


            List<String> list=new ArrayList<String>();

            for(int i=0;i<yBean.getData().getActivityInfo().getActivityInfoList().size();i++){

                list.add(yBean.getData().getActivityInfo().getActivityInfoList().get(i).getActivityImg());
            }

            ((ViewholderC) holder).banner.setImageLoader(new image());

            ((ViewholderC) holder).banner.setImages(list);

            ((ViewholderC) holder).banner.setPageTransformer(true,new AlphaPageTransformer());

             ((ViewholderC) holder).banner.start();


        } else if(holder instanceof  ViewholderD){

            ((ViewholderD) holder).recyclerView.setLayoutManager(new GridLayoutManager(context,2));


            ((ViewholderD) holder).recyclerView.setAdapter(new RecleviewAdapter(context,yBean));

        }
    }
    @Override
    public int getItemCount() {

        return 4;
    }

    @Override
    public int getItemViewType(int position) {

        if(position==0)
        {
            return Item_Type.RECYCLEVIEW_ITEM_TYPE_1.ordinal();
        }else  if(position==1){

            return Item_Type.RECYCLEVIEW_ITEM_TYPE_2.ordinal();
        }else if(position==2){

            return Item_Type.RECYCLEVIEW_ITEM_TYPE_3.ordinal();
        }else if(position==3){

            return Item_Type.RECYCLEVIEW_ITEM_TYPE_4.ordinal();

        }
        return -1;
    }



    class ViewholderA extends RecyclerView.ViewHolder {

        private Banner banner;

        public ViewholderA(View itemView) {
            super(itemView);

            banner= (Banner) itemView.findViewById(R.id.banner);
        }
    }
    class ViewholderB extends RecyclerView.ViewHolder {

        ImageView imageView,imageView2,imageView3;

        public ViewholderB(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.recleview_text);
            imageView2 = (ImageView) itemView.findViewById(R.id.recleview_text2);
            imageView3= (ImageView) itemView.findViewById(R.id.recleview_text3);

        }
    }

    class ViewholderC extends RecyclerView.ViewHolder {

        Banner banner;
        public ViewholderC(View itemView) {
            super(itemView);


            banner= (Banner) itemView.findViewById(R.id.viewpager_);

        }
    }

    class ViewholderD extends RecyclerView.ViewHolder {


        RecyclerView recyclerView;

        public ViewholderD(View itemView) {

            super(itemView);

            recyclerView= (RecyclerView) itemView.findViewById(R.id.recyveiw);


        }
    }




}
