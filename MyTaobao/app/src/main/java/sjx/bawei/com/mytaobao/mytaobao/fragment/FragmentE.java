package sjx.bawei.com.mytaobao.mytaobao.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import sjx.bawei.com.mytaobao.R;
import sjx.bawei.com.mytaobao.mytaobao.adapter.ListviewAdapter;
import view.XListView;

/**
 * dell 孙劲雄
 * 2017/9/1
 * 9:08
 */

public class FragmentE extends Fragment {


    private List<View> listview;
    private XListView xListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=View.inflate(getActivity(), R.layout.fragmente,null);

        xListView= (XListView) view.findViewById(R.id.xlistview);


        return view;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listview=new ArrayList<>();

        View view2=View.inflate(getActivity(),R.layout.listview,null);

        listview.add(view2);

        xListView.setPullLoadEnable(true);

        xListView.setPullRefreshEnable(true);

        xListView.setAdapter(new ListviewAdapter(getActivity(),listview));




    }

}
