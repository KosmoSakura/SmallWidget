package autolayout.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.ViewGroup;

import autolayout.AutoLayoutInfo;
import autolayout.utils.AutoLayoutHelper;


/**
 * @Description:
 * @Author: Kosmos
 * @Time: 2017/11/16 001613:23
 * @Email:ZeroProject@foxmail.com
 */
public class AutoLinearLayoutCompat extends LinearLayoutCompat {
    private AutoLayoutHelper mHelper = new AutoLayoutHelper(this);

    public AutoLinearLayoutCompat(Context context) {
        super(context);
    }

    public AutoLinearLayoutCompat(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoLinearLayoutCompat(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isInEditMode())
            mHelper.adjustChildren();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new AutoLinearLayoutCompat.AutoLayoutParams(getContext(), attrs);
    }

    public static class AutoLayoutParams extends LinearLayoutCompat.LayoutParams implements AutoLayoutHelper.AutoLayoutParams {
        private AutoLayoutInfo mAutoLayoutInfo;

        public AutoLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            mAutoLayoutInfo = AutoLayoutHelper.getAutoLayoutInfo(c, attrs);
        }

        @Override
        public AutoLayoutInfo getAutoLayoutInfo() {
            return mAutoLayoutInfo;
        }


        public AutoLayoutParams(int width, int height) {
            super(width, height);
        }


        public AutoLayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        public AutoLayoutParams(MarginLayoutParams source) {
            super(source);
        }

    }
}
