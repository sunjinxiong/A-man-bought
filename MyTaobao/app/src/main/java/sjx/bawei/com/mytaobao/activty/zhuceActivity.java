package sjx.bawei.com.mytaobao.activty;

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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import sjx.bawei.com.mytaobao.OkHttp3Utils;
import sjx.bawei.com.mytaobao.R;

public class zhuceActivity extends AppCompatActivity {

    private EditText user, pwd, qrpwd, youxaing;
    private Button zhuce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);

        user = (EditText) findViewById(R.id.user);

        pwd = (EditText) findViewById(R.id.pwd);

        qrpwd = (EditText) findViewById(R.id.qrpwd);

        youxaing = (EditText) findViewById(R.id.youxiang);

        zhuce = (Button) findViewById(R.id.zhuce);

        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String path = "http://169.254.254.142/mobile/index.php?act=login&op=register";

                RequestParams requestParams = new RequestParams(path);

                requestParams.addBodyParameter("username", user.getText().toString().trim());
                requestParams.addBodyParameter("password", pwd.getText().toString().trim());
                requestParams.addBodyParameter("password_confirm", qrpwd.getText().toString().trim());
                requestParams.addBodyParameter("email", youxaing.getText().toString());
                requestParams.addBodyParameter("client", "android");

                x.http().post(requestParams, new org.xutils.common.Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {

                        try {
                            JSONObject ob=new JSONObject(result);

                            String code = ob.optString("code");

                            if(code.equals(200)){

                                Toast.makeText(zhuceActivity.this,"注册成功",Toast.LENGTH_LONG).show();

                                finish();

                            }else {

                                Toast.makeText(zhuceActivity.this,"注册失败",Toast.LENGTH_LONG).show();

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



