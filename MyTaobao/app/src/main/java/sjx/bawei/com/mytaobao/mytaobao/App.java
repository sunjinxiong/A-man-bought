package sjx.bawei.com.mytaobao.mytaobao;

import android.app.Application;

import org.xutils.x;

/**
 * dell 孙劲雄
 * 2017/9/12
 * 9:06
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);
    }
}
