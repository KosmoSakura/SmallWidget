package autolayout.widget;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.ViewGroup;

import autolayout.AutoLayoutInfo;
import autolayout.utils.AutoLayoutHelper;

/**
 * @Description:
 * @Author: Kosmos
 * @Time: 2017/11/11 001112:21
 * @Email:ZeroProject@foxmail.com
 */
public class AutoConstraintLayout extends ConstraintLayout {
    private final AutoLayoutHelper mHelper = new AutoLayoutHelper(this);

    public AutoConstraintLayout(Context context) {
        super(context);
    }

    public AutoConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new AutoLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isInEditMode()) {
            mHelper.adjustChildren();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    public static class AutoLayoutParams extends ConstraintLayout.LayoutParams
        implements AutoLayoutHelper.AutoLayoutParams {
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
