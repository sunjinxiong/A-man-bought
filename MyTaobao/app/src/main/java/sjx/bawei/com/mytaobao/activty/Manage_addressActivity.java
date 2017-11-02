package sjx.bawei.com.mytaobao.activty;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

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
import sjx.bawei.com.mytaobao.adapter.Adress_litview;
import sjx.bawei.com.mytaobao.bean.Address;

public class Manage_addressActivity extends AppCompatActivity implements View.OnClickListener {

    private Button address_button;
    private SharedPreferences sp;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_address);


        address_button= (Button) findViewById(R.id.address_button);

        address_button.setOnClickListener(this);

        listView= (ListView) findViewById(R.id.addrress_list);

        sp = getSharedPreferences("name",MODE_PRIVATE);

        getdata();


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.address_button:

                Intent intent=new Intent(Manage_addressActivity.this,AddressActivity.class);

                startActivity(intent);

                break;
        }

    }

    public void getdata(){

        String key = sp.getString("key","");

        String path="http://169.254.254.142/mobile/index.php?act=member_address&op=address_list&";

        RequestParams requestParams = new RequestParams(path);

        requestParams.addBodyParameter("key",key);

        x.http().post(requestParams, new org.xutils.common.Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                try {
                    JSONObject ob=new JSONObject(result);

                    Log.i("xxxxx",result);

                    Gson gson=new Gson();

                    Address address = gson.fromJson(result, Address.class);

                    Adress_litview adpter=new Adress_litview(Manage_addressActivity.this,address);

                    listView.setAdapter(adpter);

                    adpter.notifyDataSetChanged();


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

}
