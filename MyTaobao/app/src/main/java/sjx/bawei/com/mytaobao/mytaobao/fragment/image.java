package sjx.bawei.com.mytaobao.mytaobao.fragment;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * dell 孙劲雄
 * 2017/9/7
 * 13:49
 */

public class image extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {

        Glide.with(context).load(path).into(imageView);

    }
}
