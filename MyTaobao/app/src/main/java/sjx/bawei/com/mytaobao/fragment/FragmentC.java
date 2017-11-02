package sjx.bawei.com.mytaobao.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.x;

import sjx.bawei.com.mytaobao.R;
import sjx.bawei.com.mytaobao.activty.ShopxqActivity;

/**
 * dell 孙劲雄
 * 2017/9/1
 * 9:08
 */

public class FragmentC extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=View.inflate(getActivity(), R.layout.fragmentc,null);



        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);




    }
}
