package sjx.bawei.com.mytaobao;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * dell 孙劲雄
 * 2017/8/24
 * 9:14
 */

public class AlphaPageTransformer implements ViewPager.PageTransformer {
/*
    private static final float DEFAULT_MIN_ALPHA = 0.5f;
    private float mMinAlpha = DEFAULT_MIN_ALPHA;


    @Override
    public void transformPage(View view, float position) {

        if (position < -1)
        {
            view.setAlpha(mMinAlpha);
        } else if (position <= 1)
        { // [-1,1]

            if (position < 0) //[0，-1]
            {
                float factor = mMinAlpha + (1 - mMinAlpha) * (1 + position);
                view.setAlpha(factor);
            } else//[1，0]
            {
                float factor = mMinAlpha + (1 - mMinAlpha) * (1 - position);
                view.setAlpha(factor);
            }
        } else
        { // (1,+Infinity]
            view.setAlpha(mMinAlpha);
        }
    }
     */

    private static final float DEFAULT_MAX_ROTATE = 15.0f;
    private float mMaxRotate = DEFAULT_MAX_ROTATE;

    public void transformPage(View view, float position)
    {
        if (position < -1)
        { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setRotation(mMaxRotate * -1);
            view.setPivotX(view.getWidth());
            view.setPivotY(view.getHeight());

        } else if (position <= 1)
        { // [-1,1]

            if (position < 0)//[0，-1]
            {
                view.setPivotX(view.getWidth() * (0.5f + 0.5f * (-position)));
                view.setPivotY(view.getHeight());
                view.setRotation(mMaxRotate * position);
            } else//[1,0]
            {
                view.setPivotX(view.getWidth() * 0.5f * (1 - position));
                view.setPivotY(view.getHeight());
                view.setRotation(mMaxRotate * position);
            }
        } else
        { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setRotation(mMaxRotate);
            view.setPivotX(view.getWidth() * 0);
            view.setPivotY(view.getHeight());
        }
    }


}
