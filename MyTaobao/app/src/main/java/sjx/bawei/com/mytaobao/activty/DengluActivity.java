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

public class DengluActivity extends AppCompatActivity {


    private Button button;

    private EditText mima,shouji;
    private SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denglu);

        mima= (EditText) findViewById(R.id.mima);

        shouji= (EditText) findViewById(R.id.shouji);

        button= (Button) findViewById(R.id.button);


        sp = getSharedPreferences("name", MODE_PRIVATE);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "http://169.254.254.142/mobile/index.php?act=login";

                RequestParams requestParams = new RequestParams(path);

                requestParams.addBodyParameter("username",shouji.getText().toString().trim());
                requestParams.addBodyParameter("password",mima.getText().toString().trim());
                requestParams.addBodyParameter("client","android");

                x.http().post(requestParams, new org.xutils.common.Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {

                        Log.i("+++",mima.getText().toString().trim());
                        Log.i("+++",result);

                        try {
                            JSONObject ob=new JSONObject(result);

                            int code = ob.optInt("code");

                            JSONObject jsonObject = ob.optJSONObject("datas");

                            String username = jsonObject.optString("username");

                            String key = jsonObject.optString("key");

                            if(code==200){

                                Toast.makeText(DengluActivity.this,"登录成功",Toast.LENGTH_LONG).show();

                                SharedPreferences.Editor edit = sp.edit();

                                edit.putString("username",username);

                                edit.putString("key",key);

                                edit.putBoolean("user",true);

                                edit.commit();

                                Intent it=new Intent(DengluActivity.this,Main2Activity.class);

                                startActivity(it);

                                finish();
                            }else {

                                Toast.makeText(DengluActivity.this,"登录失败",Toast.LENGTH_LONG).show();

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




    }
}
