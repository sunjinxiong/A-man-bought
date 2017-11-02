package sjx.bawei.com.mytaobao.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import sjx.bawei.com.mytaobao.R;
import sjx.bawei.com.mytaobao.bean.Address;

/**
 * dell 孙劲雄
 * 2017/9/15
 * 9:39
 */

public class Adress_litview extends BaseAdapter {

    private Context context;
    private Address address;

    public Adress_litview(Context context, Address address) {
        this.context = context;
        this.address = address;
    }

    @Override
    public int getCount() {
        return address.getDatas().getAddress_list().size();
    }

    @Override
    public Object getItem(int position) {

        return address.getDatas().getAddress_list().get(position);
    }

    @Override
    public long getItemId(int position) {


        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder;

        if(convertView==null){

            holder=new Holder();

            convertView=View.inflate(context,R.layout.adress_listview_item,null);

            holder.phone= (TextView) convertView.findViewById(R.id.manage_phone);

            holder.bianjia= (TextView) convertView.findViewById(R.id.manage_bainji);

            holder.delete= (TextView) convertView.findViewById(R.id.manage_delete);

            holder.name= (TextView) convertView.findViewById(R.id.manage_name);

            holder.xx= (TextView) convertView.findViewById(R.id.manage_xx);

            holder.checkBox= (CheckBox) convertView.findViewById(R.id.manage_check);

            convertView.setTag(holder);
        }else
            {
                holder= (Holder) convertView.getTag();

            }


         holder.phone.setText(address.getDatas().getAddress_list().get(position).getMob_phone());

        holder.name.setText(address.getDatas().getAddress_list().get(position).getTrue_name());

        holder.xx.setText(address.getDatas().getAddress_list().get(position).getArea_info());

        holder.checkBox.setChecked(true);

        return convertView;
    }


    class Holder{


        TextView name,phone,bianjia,delete,xx;

        CheckBox checkBox;


    }

}
