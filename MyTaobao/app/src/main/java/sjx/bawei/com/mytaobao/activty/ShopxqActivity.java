package sjx.bawei.com.mytaobao.activty;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.internal.Streams;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import sjx.bawei.com.mytaobao.OkHttp3Utils;
import sjx.bawei.com.mytaobao.R;
import sjx.bawei.com.mytaobao.adapter.ChaAdapter;
import sjx.bawei.com.mytaobao.adapter.Xq_view_Adater;
import sjx.bawei.com.mytaobao.bean.Laols;
import sjx.bawei.com.mytaobao.bean.Shop;
import uk.co.senab.photoview.PhotoView;

public class ShopxqActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView xq_back,xq_kefu,xq_shoucang,xq_dainpu;
    private String goods_id;
    private PhotoView xq_ima;
    private TextView xq_name,xq_yogj,xq_price,xq_yuanjai,xq_shop;
    private RecyclerView xq_view;
    private Button xq_mai,xq_add;
     private Shop shop;
    private Handler handler=new Handler(){

        private Xq_view_Adater adapter;

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){

                case 0:
                    String obj = (String) msg.obj;

                    Gson gson=new Gson();

                    shop = gson.fromJson(obj, Shop.class);

                    Fuzhi();

                    adapter = new Xq_view_Adater(ShopxqActivity.this,shop);

                    xq_view.setAdapter(adapter);

                    adapter.setOnRrecyclerViewItemClickListener(new ChaAdapter.OnRrecyclerViewItemClickListener() {
                        @Override
                        public void onRecyclerViewItemClick(int position) {

                            Intent intent=new Intent(ShopxqActivity.this,ShopxqActivity.class);
                            intent.putExtra("goods_id",shop.getDatas().getGoods_commend_list().get(position).getGoods_id());
                            finish();
                            startActivity(intent);
                        }
                    });

                    break;

            }

        }
    };
    private SharedPreferences sp;
    private PopupWindow popupWindow;
    private TextView popo_num;
    private Button popo_jian;
    private Button popo_add;
    private Button popo_button;
    private int count=1;
    private int num=1;
    private String keymima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopxq);

        goods_id = getIntent().getStringExtra("goods_id");
        getinit();
        getdata();
        xq_view.setLayoutManager(new LinearLayoutManager(this));
        xq_back.setOnClickListener(this);
        xq_shop.setOnClickListener(this);
        xq_add.setOnClickListener(this);
        xq_mai.setOnClickListener(this);

        sp = getSharedPreferences("name", MODE_PRIVATE);
        keymima = sp.getString("key", "");


    }
    private void Fuzhi() {
        Picasso.with(ShopxqActivity.this).load(shop.getDatas().getGoods_image()).into(xq_ima);

        xq_name.setText(shop.getDatas().getGoods_info().getGoods_name());

        if(shop.getDatas().getGoods_info().getGoods_jingle().length()>0){

            xq_yogj.setText(shop.getDatas().getGoods_info().getGoods_jingle());

        }else{
            xq_yogj.setVisibility(View.GONE);
        }

        xq_yuanjai.setText(shop.getDatas().getGoods_info().getGoods_price());

        xq_price.setText("$"+ shop.getDatas().getGoods_info().getGoods_promotion_price());

        if(shop.getDatas().getGoods_info().getGoods_price().equals(shop.getDatas().getGoods_info().getGoods_promotion_price()))
        {

            xq_yuanjai.setVisibility(View.GONE);
        }

    }
    public  void getinit(){
          xq_back= (ImageView) findViewById(R.id.xq_back);
          xq_kefu= (ImageView) findViewById(R.id.xq_kefu);
          xq_shoucang= (ImageView) findViewById(R.id.xq_shoucang);
          xq_dainpu= (ImageView) findViewById(R.id.xq_dainpu);
          xq_ima= (PhotoView) findViewById(R.id.xq_ima);
          xq_name = (TextView) findViewById(R.id.xq_name);
          xq_yogj= (TextView) findViewById(R.id.xq_yogj);
          xq_price= (TextView) findViewById(R.id.xq_price);
          xq_yuanjai= (TextView) findViewById(R.id.xq_yuanjai);
          xq_shop= (TextView) findViewById(R.id.xq_shop);
          xq_view= (RecyclerView) findViewById(R.id.xq_view);
          xq_mai= (Button) findViewById(R.id.xq_mai);
          xq_add= (Button) findViewById(R.id.xq_add);

        xq_yuanjai.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

    }
    public void getdata(){


           String path="http://169.254.254.142/mobile/index.php?act=goods&op=goods_detail&goods_id="+goods_id+"";

           OkHttp3Utils.doGet(path, new Callback() {
               @Override
               public void onFailure(Call call, IOException e) {

               }

               @Override
               public void onResponse(Call call, Response response) throws IOException {

                   String s = response.body().string();
                   Message message=new Message();
                   message.what=0;
                   message.obj=s;

                   handler.sendMessage(message);


               }
           });

       }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.xq_back:

                finish();

                break;
            case R.id.xq_shop:
               String url="http://169.254.254.142/mobile/index.php?act=goods&op=goods_body&goods_id="+goods_id+"";

                Intent intent=new Intent(ShopxqActivity.this,WebActivity.class);

                intent.putExtra("url",url);

                startActivity(intent);

                break;

            case  R.id.xq_add:
                boolean user = sp.getBoolean("user", false);

                if(user){

                    getpopo();

                    popo_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String path="http://169.254.254.142/mobile/index.php?act=member_cart&op=cart_add";
                            RequestParams requestParams = new RequestParams(path);
                            requestParams.addBodyParameter("key",keymima);
                            requestParams.addBodyParameter("goods_id",shop.getDatas().getGoods_info().getGoods_id());
                            requestParams.addBodyParameter("quantity",count+"");

                            x.http().post(requestParams, new org.xutils.common.Callback.CommonCallback<String>() {
                                @Override
                                public void onSuccess(String result) {

                                    Log.i("+++",result);

                                    try {
                                        JSONObject ob=new JSONObject(result);

                                        int code = ob.optInt("code");

                                        if(code==200){

                                            Toast.makeText(ShopxqActivity.this,"添加购物车成功",Toast.LENGTH_LONG).show();
                                         finish();
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }

                                @Override
                                public void onError(Throwable ex, boolean isOnCallback) {

                                    Log.i("++++", "失败");
                                }

                                @Override
                                public void onCancelled(CancelledException cex) {

                                }

                                @Override
                                public void onFinished() {

                                }
                            });


                        }
                    });

                    popo_jian.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if(count==1){

                                Toast.makeText(ShopxqActivity.this,"不能在减了",Toast.LENGTH_LONG).show();

                            }else {

                                        Toast.makeText(ShopxqActivity.this, "diawo--", Toast.LENGTH_LONG).show();

                                        count--;
                                        popo_num.setText(count+"");

                                    }

                            }
                    });

                    popo_add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Toast.makeText(ShopxqActivity.this, "diwo", Toast.LENGTH_LONG).show();

                               count++;
                            popo_num.setText(count+"");
                        }
                    });


                }else{

                    Toast.makeText(ShopxqActivity.this,"请先登录",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.xq_mai:
                boolean user2 = sp.getBoolean("user", false);

                if(user2){


                    getpopo();

                    popo_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent it=new Intent(ShopxqActivity.this,DingdanActivity.class);

                            it.putExtra("name",shop.getDatas().getGoods_info().getGoods_name());

                            startActivity(it);
                        }
                    });

                    popo_jian.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if(num==1){

                                Toast.makeText(ShopxqActivity.this,"不能在减了",Toast.LENGTH_LONG).show();

                            }else {

                                Toast.makeText(ShopxqActivity.this, "diawo--", Toast.LENGTH_LONG).show();

                                num--;
                                popo_num.setText(num+"");

                            }

                        }
                    });

                    popo_add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Toast.makeText(ShopxqActivity.this, "diwo", Toast.LENGTH_LONG).show();

                            num++;
                            popo_num.setText(num+"");
                        }
                    });


                }else{

                    Toast.makeText(ShopxqActivity.this,"请先登录",Toast.LENGTH_SHORT).show();

                }
                break;
        }
    }
   public void getpopo(){
       View view=View.inflate(this,R.layout.popo,null);

       ImageView popo_ima= (ImageView) view.findViewById(R.id.popo_ima);

       TextView popo_price= (TextView) view.findViewById(R.id.popo_price);

       TextView popo_name= (TextView) view.findViewById(R.id.popo_name);

       TextView popo_back= (TextView) view.findViewById(R.id.popo_back);

       popo_num = (TextView) view.findViewById(R.id.popo_num);

       popo_jian = (Button) view.findViewById(R.id.popo_jian);

       popo_add = (Button) view.findViewById(R.id.popo_add);

       popo_button = (Button) view.findViewById(R.id.popo_button);

       Picasso.with(ShopxqActivity.this).load(shop.getDatas().getGoods_image()).into(popo_ima);
       popo_price.setText("$"+ shop.getDatas().getGoods_info().getGoods_promotion_price());
       popo_name.setText(shop.getDatas().getGoods_info().getGoods_name());


         popo_num.setText(count+"");
       popupWindow=new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

       popo_back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

                 popupWindow.dismiss();

           }
       });

       popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
       //焦点
       popupWindow.setFocusable(true);
       //触摸
       popupWindow.setTouchable(true);

       if(popupWindow.isShowing()){

           popupWindow.dismiss();

       }else {

           popupWindow.showAsDropDown(xq_add,0,3);

       }
   }


}
