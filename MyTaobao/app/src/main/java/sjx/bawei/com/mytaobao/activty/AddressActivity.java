package sjx.bawei.com.mytaobao.activty;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.x;

import sjx.bawei.com.mytaobao.R;

public class AddressActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText shouhuo,address_phone,address_diqu,street,xx_address;
    private Button save;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);


        shouhuo= (EditText) findViewById(R.id.shouhuo);

        address_phone= (EditText) findViewById(R.id.address_phone);

        address_diqu= (EditText) findViewById(R.id.address_diqu);

        street= (EditText) findViewById(R.id.street);

        xx_address= (EditText) findViewById(R.id.xx_address);

        save= (Button) findViewById(R.id.save);

        sp = getSharedPreferences("name", MODE_PRIVATE);

        save.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save:
                         getdata();
                break;
        }
    }

  public void   getdata(){
        String key = sp.getString("key", "");

        String path="http://169.254.254.142/mobile/index.php?act=member_address&op=address_add";
        RequestParams requestParams = new RequestParams(path);
        requestParams.addBodyParameter("key",key);
        requestParams.addBodyParameter("true_name",shouhuo.getText().toString().trim());
        requestParams.addBodyParameter("mob_phone",address_phone.getText().toString().trim());
        requestParams.addBodyParameter("city_id",address_diqu.getText().toString().trim());
        requestParams.addBodyParameter("area_id",address_diqu.getText().toString().trim());
        requestParams.addBodyParameter("address",street.getText().toString().trim());
        requestParams.addBodyParameter("area_info",xx_address.getText().toString().trim());
        x.http().post(requestParams, new org.xutils.common.Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {


                try {
                    JSONObject ob=new JSONObject(result);

                    int code = ob.optInt("code");
                    Log.i("address",result);

                    if(code==200){

                        Toast.makeText(AddressActivity.this,"添加成功",Toast.LENGTH_LONG).show();

                        finish();
                    }else {

                        Toast.makeText(AddressActivity.this,"登录失败",Toast.LENGTH_LONG).show();

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
}
