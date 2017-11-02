package sjx.bawei.com.mytaobao.activty;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.LoginFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.x;

import sjx.bawei.com.mytaobao.R;

public class ShezhiActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;
    private Button button;
    private String name;
    private String key;
    private RelativeLayout layout;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shezhi);

        imageView= (ImageView) findViewById(R.id.shezhi_back);

        button= (Button) findViewById(R.id.tuichu);

        layout= (RelativeLayout) findViewById(R.id.address);

        sp = getSharedPreferences("name", MODE_PRIVATE);

        name = sp.getString("username", "");

        key = sp.getString("key", "");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getdata();

            }
        });
        layout.setOnClickListener(this);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void getdata(){


     String path="http://169.254.254.142/mobile/index.php?act=logout";

        RequestParams requestParams = new RequestParams(path);
        requestParams.addBodyParameter("username",name);
        requestParams.addBodyParameter("key",key);
        requestParams.addBodyParameter("client","android");

        x.http().post(requestParams, new org.xutils.common.Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                try {
                    JSONObject ob=new JSONObject(result);

                    Log.i("xxxxx",result);
                    int code = ob.optInt("code");

                    if(code==200){

                        Toast.makeText(ShezhiActivity.this,"退出成功",Toast.LENGTH_LONG).show();

                        sp.edit().putBoolean("user",false).commit();

                        button.setVisibility(View.INVISIBLE);
                        Intent it=new Intent(ShezhiActivity.this,DluActivity.class);

                        startActivity(it);

                        finish();

                    }else {

                        Toast.makeText(ShezhiActivity.this,"推出失败",Toast.LENGTH_LONG).show();

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

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.address:

              Intent it=new Intent(ShezhiActivity.this,Manage_addressActivity.class);


                startActivity(it);

                break;



        }
    }
}
