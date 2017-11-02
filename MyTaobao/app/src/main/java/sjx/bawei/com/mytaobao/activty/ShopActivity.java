package sjx.bawei.com.mytaobao.activty;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import sjx.bawei.com.mytaobao.OkHttp3Utils;
import sjx.bawei.com.mytaobao.R;
import sjx.bawei.com.mytaobao.adapter.ChaAdapter;
import sjx.bawei.com.mytaobao.bean.Laols;

public class ShopActivity extends AppCompatActivity {

    private EditText shop_edit;
    private RecyclerView recyclerView;
    private ChaAdapter adapter;
    private ImageView shop_back;
    private Laols lao;

    int page=30;
    private Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){

                case 0:

                    String obj = (String) msg.obj;

                    Gson gson=new Gson();

                    final Laols laols = gson.fromJson(obj, Laols.class);

                     adapter=new ChaAdapter(laols,ShopActivity.this);

                    recyclerView.setAdapter(adapter);

                    adapter.setOnRrecyclerViewItemClickListener(new ChaAdapter.OnRrecyclerViewItemClickListener() {
                        @Override
                        public void onRecyclerViewItemClick(int position) {

                            Intent intent=new Intent(ShopActivity.this,ShopxqActivity.class);

                            intent.putExtra("goods_id",laols.getDatas().getGoods_list().get(position).getGoods_id());

                            startActivity(intent);

                        }
                    });


                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        shop_edit= (EditText)findViewById(R.id.shop_edit);

        recyclerView= (RecyclerView) findViewById(R.id.cha_laolishi);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        shop_back= (ImageView) findViewById(R.id.shop_back_shop);

        shop_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

        shop_edit.setText(getIntent().getStringExtra("name"));

        if(getIntent().getStringExtra("name").equals("劳力士")){

                          getdata();
        }
        }
                  public void getdata(){

                      String path="http://169.254.254.142/mobile/index.php?act=goods&op=goods_list&page="+page+"";

                      OkHttp3Utils.doGet(path, new Callback() {
                          @Override
                          public void onFailure(Call call, IOException e) {
                          }

                          @Override
                          public void onResponse(Call call, Response response) throws IOException {

                              String string = response.body().string();

                              Message message=new Message();

                              message.what=0;

                              message.obj=string;

                              handler.sendMessage(message);


                          }
                      });

                  }
    }

