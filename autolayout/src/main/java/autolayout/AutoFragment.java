package autolayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @Description:
 * @Author: Kosmos
 * @Time: 2017/11/19 001923:11
 * @Email:ZeroProject@foxmail.com
 */
public class AutoFragment extends Fragment {
    private static final String LAYOUT_LINEARLAYOUT = "LinearLayout";
    private static final String LAYOUT_FRAMELAYOUT = "FrameLayout";
    private static final String LAYOUT_RELATIVELAYOUT = "RelativeLayout";
    private static final String LAYOUT_AutoCardView = "CardView";
    private static final String LAYOUT_AutoRadioGroup = "RadioGroup";
    private static final String LAYOUT_AutoTableLayout = "TableLayout";
    private static final String LAYOUT_AutoTableRow = "TableRow";
    private static final String LAYOUT_MetroLayout = "MetroLayout";
    private static final String LayoutConstraintLayout = "ConstraintLayout";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
