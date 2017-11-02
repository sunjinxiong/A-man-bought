package sjx.bawei.com.mytaobao.activty;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import sjx.bawei.com.mytaobao.R;

public class DluActivity extends AppCompatActivity {

    private Button deu;
    private Button zhuce;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dlu);

        deu= (Button) findViewById(R.id.delu_button);



            zhuce= (Button) findViewById(R.id.delu_zhuce);

            deu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent it=new Intent(DluActivity.this,DengluActivity.class);

                    startActivity(it);
                    finish();

                }
            });

            zhuce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent it=new Intent(DluActivity.this,zhuceActivity.class);

                    startActivity(it);
                    finish();

                }
            });

        }








}
