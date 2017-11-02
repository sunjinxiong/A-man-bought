package sjx.bawei.com.mytaobao.activty;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;

import sjx.bawei.com.mytaobao.R;
import sjx.bawei.com.mytaobao.fragment.FragmentA;
import sjx.bawei.com.mytaobao.fragment.FragmentB;
import sjx.bawei.com.mytaobao.fragment.FragmentC;
import sjx.bawei.com.mytaobao.fragment.FragmentD;
import sjx.bawei.com.mytaobao.fragment.FragmentE;

public class Main2Activity extends AppCompatActivity {


    private Handler handler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    //定义FragmentTabHost对象
    private FragmentTabHost mTabHost;

    //定义一个布局
    private LayoutInflater layoutInflater;

    //定义数组来存放Fragment界面
    private Class fragmentArray[] = {FragmentA.class,FragmentB.class,FragmentC.class,FragmentD.class,FragmentE.class};

    //定义数组来存放按钮图片
    private int mImageViewArray[] = {R.drawable.shouye,R.drawable.weitao,R.drawable.message,
            R.drawable.cart,R.drawable.my};

    //Tab选项卡的文字
    private String mTextviewArray[] = {"首页", "微淘", "消息", "购物车", "我的淘宝"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();

        //改变监听
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {


            }
        });
    }
    /**
     * 初始化组件
     */
    private void initView(){
        //实例化布局对象
        layoutInflater = LayoutInflater.from(this);

        //实例化TabHost对象，得到TabHost
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        //得到fragment的个数
        int count = fragmentArray.length;

        for(int i = 0; i < count; i++){
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
            //将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            //设置Tab按钮的背景
            //mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.i11);
        }

        //去掉分隔的竖线
        mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
    }

    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index){

        View view = layoutInflater.inflate(R.layout.tab_item_view, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageViewArray[index]);
        return view;
    }
}
