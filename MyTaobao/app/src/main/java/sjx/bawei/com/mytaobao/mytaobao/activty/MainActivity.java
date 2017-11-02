package sjx.bawei.com.mytaobao.mytaobao.activty;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import sjx.bawei.com.mytaobao.R;

public class MainActivity extends AppCompatActivity {

    private Handler handler=new Handler();
    int i=1;
    Runnable runnable=new Runnable() {
        @Override
        public void run() {

            if(i==0){
                Intent it=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(it);
                finish();
            }

            if(i>0){
                i--;
                handler.postDelayed(runnable,1000);

            }else {

                handler.removeCallbacks(runnable);

            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler.postDelayed(runnable,1000);

    }
}
