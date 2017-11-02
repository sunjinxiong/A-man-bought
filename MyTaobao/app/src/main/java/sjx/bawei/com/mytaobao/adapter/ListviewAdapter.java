package sjx.bawei.com.mytaobao.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * dell 孙劲雄
 * 2017/9/5
 * 14:20
 */

public class ListviewAdapter extends BaseAdapter {

    private Context context;
    private List<View> list;
    public ListviewAdapter(Context context, List<View> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        return list.get(position);
    }
}
