package sjx.bawei.com.mytaobao.activty;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.Serializable;
import java.util.List;

import sjx.bawei.com.mytaobao.R;
import sjx.bawei.com.mytaobao.adapter.Adress_litview;
import sjx.bawei.com.mytaobao.bean.Address;
import sjx.bawei.com.mytaobao.bean.Cart;
import sjx.bawei.com.mytaobao.bean.Dingdan;

public class DingdanActivity extends AppCompatActivity {


    private SharedPreferences sp;

    private TextView dingdan_phone,dingdan_xx,dingdan_name,heji;
    private ListView litview;
    private Button dingdan;
    private ImageView back;
    private List<Cart.DatasBean.CartListBean.GoodsBean> cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingdan);

        getinit();

        cart = (List<Cart.DatasBean.CartListBean.GoodsBean>) getIntent().getSerializableExtra("cart");

        litview.setAdapter(new Myadapter());

        sp = getSharedPreferences("name", MODE_PRIVATE);

        heji.setText(getIntent().getStringExtra("num"));

        getdata();
//点击提交第一次提交订单
        dingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdata2();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


   public void getinit(){

       litview= (ListView) findViewById(R.id.lis);

       dingdan= (Button) findViewById(R.id.dingdan);

       dingdan_name= (TextView) findViewById(R.id.dingdna_name);

       dingdan_phone= (TextView) findViewById(R.id.dindan_phone);

       dingdan_xx= (TextView) findViewById(R.id.dingdan_xx);

       heji= (TextView) findViewById(R.id.heji);

       back=(ImageView)findViewById(R.id.back);

   }

   public  void getdata2(){

       String cart_id="";
       String path="http://169.254.254.142/mobile/index.php?act=member_buy&op=buy_step1";

       for (int i=0;i<cart.size();i++){

           cart_id+=cart.get(i).getCart_id()+"|"+cart.get(i).getGoods_num()+",";

       }

       RequestParams requestParams = new RequestParams(path);
       requestParams.addBodyParameter("key",sp.getString("key",""));
       requestParams.addBodyParameter("cart_id",cart_id.substring(0,cart_id.length()-1));
       requestParams.addBodyParameter("ifcart","1");

       x.http().post(requestParams, new org.xutils.common.Callback.CommonCallback<String>() {
           @Override
           public void onSuccess(String result) {


               Log.i("xxxx",result+"");

               JSONObject jsonObject= null;
               try {
                   jsonObject = new JSONObject(result);
                   int s = jsonObject.optInt("code");
                   if(s==200){

                       Toast.makeText(DingdanActivity.this,"提交成功",Toast.LENGTH_SHORT).show();

                       Intent intent=new Intent(DingdanActivity.this,PayDemoActivity.class);

                       startActivity(intent);

                   }
               } catch (JSONException e) {
                   e.printStackTrace();
               }

               Gson gson=new Gson();

               Dingdan dingdan = gson.fromJson(result, Dingdan.class);




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

//地址请求
    public void getdata(){

        String path="http://169.254.254.142/mobile/index.php?act=member_address&op=address_list";

        RequestParams requestParams = new RequestParams(path);

        requestParams.addBodyParameter("key",sp.getString("key",""));

        x.http().post(requestParams, new org.xutils.common.Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                try {
                    JSONObject ob=new JSONObject(result);

                    Gson gson=new Gson();

                    Address address = gson.fromJson(result, Address.class);

                    dingdan_xx.setText(address.getDatas().getAddress_list().get(0).getArea_info());

                    dingdan_phone.setText(address.getDatas().getAddress_list().get(0).getMob_phone());

                    dingdan_name.setText(address.getDatas().getAddress_list().get(0).getTrue_name());

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

    class Myadapter extends BaseAdapter{
        @Override
        public int getCount() {


            return cart.size();
        }

        @Override
        public Object getItem(int position) {

            return cart.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Holder holder;
            if(convertView==null){

                holder=new Holder();

                convertView=View.inflate(DingdanActivity.this,R.layout.dindan,null);

                holder.imageView= (ImageView) convertView.findViewById(R.id.dindan_ima);

                holder.textView= (TextView) convertView.findViewById(R.id.dingdan_num);

                holder.textView2= (TextView) convertView.findViewById(R.id.ding_name);

                holder.textView3= (TextView) convertView.findViewById(R.id.ding_price);

                convertView.setTag(holder);

            }else {

                holder= (Holder) convertView.getTag();

            }

            Picasso.with(DingdanActivity.this).load(cart.get(position).getGoods_image_url()).into(holder.imageView);

            holder.textView.setText(cart.get(position).getGoods_num());
            holder.textView2.setText(cart.get(position).getGoods_name());
            holder.textView3.setText(cart.get(position).getGoods_price());




            return convertView;
        }
    }


    class Holder{

        TextView textView,textView2,textView3;

        ImageView imageView;


    }

}
