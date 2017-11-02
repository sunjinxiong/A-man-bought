package sjx.bawei.com.mytaobao.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import sjx.bawei.com.mytaobao.CustomExpandableListView;
import sjx.bawei.com.mytaobao.R;
import sjx.bawei.com.mytaobao.activty.DingdanActivity;
import sjx.bawei.com.mytaobao.bean.Cart;

import static android.R.attr.data;
import static android.R.attr.pointerIcon;

/**
 * dell 孙劲雄
 * 2017/9/1
 * 9:08
 */

public class FragmentD extends Fragment implements View.OnClickListener {

    private CheckBox checkBox;
    private TextView tv_num;
    private TextView tv_price;
    private SharedPreferences sp;
    private TextView textView;
    private Cart cart;
    private  PhoneAdapter adapter=new PhoneAdapter();
    private int num;
    private float jia;
    private int ha;
    private String keym;
    private CustomExpandableListView celv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=View.inflate(getActivity(), R.layout.fragmentd,null);


        celv = (CustomExpandableListView) view.findViewById(R.id.celv);
        checkBox = (CheckBox)view.findViewById(R.id.checkbox2);
        tv_num = (TextView)view. findViewById(R.id.tv_num);
        tv_price = (TextView) view.findViewById(R.id.tv_price);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sp = getActivity().getSharedPreferences("name",Context.MODE_PRIVATE);

        boolean user = sp.getBoolean("user", false);
        Log.i("user",user+"");
        keym = sp.getString("key","");

        celv.setGroupIndicator(null);
        //CheckBox点击事件

        if(user){

            getdata();

            checkBox.setOnClickListener(this);

            getha();
        }
    }

    @Override
    public void onClick(View v) {

        if (((CheckBox) v).isChecked()) {
            List<Cart.DatasBean.CartListBean> cart_list = cart.getDatas().getCart_list();
            for (int i = 0; i < cart_list.size(); i++) {

                Cart.DatasBean.CartListBean cartListBean = cart_list.get(i);

                cartListBean.setAllCheck(true);

                List<Cart.DatasBean.CartListBean.GoodsBean> goods = cartListBean.getGoods();

                for (int j = 0; j < goods.size(); j++) {

                    List<Cart.DatasBean.CartListBean.GoodsBean> goods1 = cart_list.get(i).getGoods();

                    for(Cart.DatasBean.CartListBean.GoodsBean good:goods1){

                             good.setItemCheck(true);

                    }

                }
            }
            //刷新界面
            notifyCheckAdapter();
        } else {
            List<Cart.DatasBean.CartListBean> cart_list = cart.getDatas().getCart_list();
            for (int i = 0; i < cart_list.size(); i++) {

                Cart.DatasBean.CartListBean cartListBean = cart_list.get(i);

                cartListBean.setAllCheck(false);

                List<Cart.DatasBean.CartListBean.GoodsBean> goods = cartListBean.getGoods();

                for (int j = 0; j < goods.size(); j++) {

                    List<Cart.DatasBean.CartListBean.GoodsBean> goods1 = cart_list.get(i).getGoods();

                    for(Cart.DatasBean.CartListBean.GoodsBean good:goods1){

                        good.setItemCheck(false);

                    }

                }
            }
            //刷新界面
            notifyCheckAdapter();
        }


    }
    private class PhoneAdapter implements ExpandableListAdapter {

        @Override
        public void registerDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public int getGroupCount() {
            return cart.getDatas().getCart_list().size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return cart.getDatas().getCart_list().get(groupPosition).getGoods().size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return cart.getDatas().getCart_list().get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {

            return cart.getDatas().getCart_list().get(pointerIcon).getGoods().get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        //一级
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            View view = View.inflate(getActivity(), R.layout.item_parent_market, null);

            CheckBox cb_parent = (CheckBox) view.findViewById(R.id.cb_parent);

           TextView tv_sign=(TextView)view.findViewById(R.id.tv_sign);

            tv_sign.setText(cart.getDatas().getCart_list().get(groupPosition).getStore_name());

            TextView tv_number = (TextView) view.findViewById(R.id.tv_number);

//            tv_number.setText(cart.getDatas().getCart_list().get(pointerIcon).getStore_name());

            if (cart.getDatas().getCart_list().get(groupPosition).isAllCheck()) {
                cb_parent.setChecked(true);
            } else {
                cb_parent.setChecked(false);
            }
            //一级监听
            cb_parent.setOnClickListener(new OnGroupClickListener(groupPosition, cb_parent));

            return view;
        }

        //二级
        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View view = View.inflate(getActivity(), R.layout.item_child_market, null);
            TextView tv_content = (TextView) view.findViewById(R.id.tv_content);
            TextView tv_pri = (TextView) view.findViewById(R.id.tv_pri);
            CheckBox cb_child = (CheckBox) view.findViewById(R.id.cb_child);

            TextView num= (TextView) view.findViewById(R.id.num);

             ImageView cart_ima=(ImageView)view.findViewById(R.id.cart_ima);

            Picasso.with(getActivity()).load(cart.getDatas().getCart_list().get(groupPosition).getGoods().get(childPosition).getGoods_image_url()).into(cart_ima);

            tv_pri.setText(cart.getDatas().getCart_list().get(groupPosition).getGoods().get(childPosition).getGoods_price());

            num.setText("数量："+cart.getDatas().getCart_list().get(groupPosition).getGoods().get(childPosition).getGoods_num());
            tv_content.setText(cart.getDatas().getCart_list().get(groupPosition).getGoods().get(childPosition).getGoods_name());
            if (cart.getDatas().getCart_list().get(groupPosition).getGoods().get(childPosition).isItemCheck()) {
                cb_child.setChecked(true);

            } else {
                cb_child.setChecked(false);
            }
            cb_child.setOnClickListener(new OnChildCheckListener(groupPosition, childPosition, cb_child));



            return view;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public void onGroupExpanded(int groupPosition) {

        }

        @Override
        public void onGroupCollapsed(int groupPosition) {

        }

        @Override
        public long getCombinedChildId(long groupId, long childId) {
            return 0;
        }

        @Override
        public long getCombinedGroupId(long groupId) {
            return 0;
        }
    }
    private class OnGroupClickListener implements View.OnClickListener {
        int groupPosition;
        CheckBox cb_parent;

        public OnGroupClickListener(int groupPosition, CheckBox cb_parent) {
            this.cb_parent = cb_parent;
            this.groupPosition = groupPosition;
        }


        @Override
        public void onClick(View v) {
            if (((CheckBox) v).isChecked()) {
                //一级全选
                setCheck(true);

            } else {
                //取消全选
                setCheck(false);
                checkBox.setChecked(false);
            }
            notifyCheckAdapter();
        }

        public void setCheck(boolean checkFlag) {

            Cart.DatasBean.CartListBean cartListBean = cart.getDatas().getCart_list().get(groupPosition);
            List<Cart.DatasBean.CartListBean> cart_list = cart.getDatas().getCart_list();
            //一级状态
            cartListBean.setAllCheck(checkFlag);

            //全选状态判断
            int num = 0;
            for (int i = 0; i < cart_list.size(); i++) {
                boolean allCheck = cart_list.get(i).isAllCheck();
                if (!allCheck) {
                    num++;
                }

            }
            if (num == 0) {
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }
            //二级状态
            List<Cart.DatasBean.CartListBean.GoodsBean> goods = cartListBean.getGoods();
            for (Cart.DatasBean.CartListBean.GoodsBean childData : goods) {
                //二级状态
                childData.setItemCheck(checkFlag);

            }
        }
    }    private class OnChildCheckListener implements View.OnClickListener {
        int groupPosition;
        int childPosition;
        CheckBox cb_child;

        public OnChildCheckListener(int groupPosition, int childPosition, CheckBox cb_child) {
            this.cb_child = cb_child;
            this.groupPosition = groupPosition;
            this.childPosition = childPosition;
        }

        @Override
        public void onClick(View v) {
            if (((CheckBox) v).isChecked()) {
//                子选中
                cart.getDatas().getCart_list().get(groupPosition).getGoods().get(childPosition).setItemCheck(true);
            } else {
                //子未选中
                cart.getDatas().getCart_list().get(groupPosition).getGoods().get(childPosition).setItemCheck(false);

            }
            //二级联动一级状态
            setParentCheckFlag();

            //检测状态 二级联动全选
            int num = 0;
            for (int i = 0; i <cart.getDatas().getCart_list().size(); i++) {

                boolean allCheck = cart.getDatas().getCart_list().get(groupPosition).isAllCheck();
                if (!allCheck) {
                    num++;
                }

            }
            if (num == 0) {
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }
        }

        //二级联动一级状态
        private void setParentCheckFlag() {

            Cart.DatasBean.CartListBean cartListBean = cart.getDatas().getCart_list().get(groupPosition);
            List<Cart.DatasBean.CartListBean.GoodsBean> goods = cartListBean.getGoods();
            for (int i = 0; i < goods.size(); i++) {
                if (!goods.get(i).isItemCheck()) {
                    //子未选中 父取消选中
                    cartListBean.setAllCheck(false);
                    notifyCheckAdapter();

                    return;
                }
                if (i == goods.size() - 1) {
                    //子选中 父选中
                    cartListBean.setAllCheck(true);
                    notifyCheckAdapter();

                    return;
                }


            }
//            没出现全选或者取消全选的时候执行的
            sum();
        }

    }

    private void sum() {
        int num = 0;
        float price = 0;

        final List<Cart.DatasBean.CartListBean> cart_list = cart.getDatas().getCart_list();
        for (Cart.DatasBean.CartListBean list : cart_list) {
            for (Cart.DatasBean.CartListBean.GoodsBean child : list.getGoods()) {
                if (child.isItemCheck()) {
                    num++;
                   price +=Float.valueOf(child.getGoods_price())*Float.valueOf(child.getGoods_num());

                }
            }
        }

        tv_num.setText("结算(" + num + ")");
        tv_price.setText("¥" + price);

        ha = num;
        Log.i("ha", ha +"");
        jia = price;

    }

    public void getha(){

        tv_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Cart.DatasBean.CartListBean.GoodsBean> list=new ArrayList<Cart.DatasBean.CartListBean.GoodsBean>();

                if(ha==0){

                    Toast.makeText(getActivity(),"你不能这么干啊",Toast.LENGTH_SHORT).show();

                }else {

                    for (int i = 0; i < cart.getDatas().getCart_list().size(); i++) {

                        for (int j = 0; j < cart.getDatas().getCart_list().get(i).getGoods().size(); j++) {

                            if (cart.getDatas().getCart_list().get(i).getGoods().get(j).isItemCheck()) {
                                list.add(cart.getDatas().getCart_list().get(i).getGoods().get(j));
                            } else {


                            }
                        }
                    }

                    Log.i("ha",ha+"");
                    Intent intent = new Intent(getActivity(), DingdanActivity.class);
                    intent.putExtra("cart", (Serializable)list);
                    intent.putExtra("num", jia + "");
                    startActivity(intent);

                }
            }
        });


    };


    private void notifyCheckAdapter() {
        sum();
        celv.setAdapter(adapter);
        int count = celv.getCount();
        for (int i = 0; i < count; i++) {
            celv.expandGroup(i);
        }
    }

    public void getdata(){

        String path="http://169.254.254.142/mobile/index.php?act=member_cart&op=cart_list";
        RequestParams requestParams = new RequestParams(path);
        requestParams.addBodyParameter("key",keym);
        x.http().post(requestParams, new org.xutils.common.Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {

                Gson gson=new Gson();

                cart = gson.fromJson(result, Cart.class);

                notifyCheckAdapter();

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
    public void onStop() {
        super.onStop();

        getdata();
    }
}
