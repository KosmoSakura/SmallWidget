package autolayout;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import autolayout.widget.AutoCardView;
import autolayout.widget.AutoConstraintLayout;
import autolayout.widget.AutoFrameLayout;
import autolayout.widget.AutoLinearLayout;
import autolayout.widget.AutoRadioGroup;
import autolayout.widget.AutoRelativeLayout;
import autolayout.widget.AutoTableLayout;
import autolayout.widget.AutoTableRow;
import autolayout.widget.MetroLayout;


/**
 * Created by zhy on 15/11/19.
 */
public class AutoLayoutActivity extends AppCompatActivity {
    private static final String LAYOUT_LINEARLAYOUT = "LinearLayout";
    private static final String LAYOUT_FRAMELAYOUT = "FrameLayout";
    private static final String LAYOUT_RELATIVELAYOUT = "RelativeLayout";
    private static final String LAYOUT_AutoCardView = "CardView";
    private static final String LAYOUT_AutoRadioGroup = "RadioGroup";
    private static final String LAYOUT_AutoTableLayout = "TableLayout";
    private static final String LAYOUT_AutoTableRow = "TableRow";
    private static final String LAYOUT_MetroLayout = "MetroLayout";
    private static final String LayoutConstraintLayout = "ConstraintLayout";


    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = null;
        if (name.equals(LAYOUT_FRAMELAYOUT)) {
            view = new AutoFrameLayout(context, attrs);
        }

        if (name.equals(LAYOUT_LINEARLAYOUT)) {
            view = new AutoLinearLayout(context, attrs);
        }

        if (name.equals(LAYOUT_RELATIVELAYOUT)) {
            view = new AutoRelativeLayout(context, attrs);
        }
        if (name.equals(LAYOUT_AutoCardView)) {
            view = new AutoCardView(context, attrs);
        }
        if (name.equals(LAYOUT_AutoCardView)) {
            view = new AutoCardView(context, attrs);
        }
        if (name.equals(LayoutConstraintLayout)) {
            view = new AutoConstraintLayout(context, attrs);
        }
        if (name.equals(LAYOUT_AutoRadioGroup)) {
            view = new AutoRadioGroup(context, attrs);
        }
        if (name.equals(LAYOUT_AutoTableLayout)) {
            view = new AutoTableLayout(context, attrs);
        }
        if (name.equals(LAYOUT_AutoTableRow)) {
            view = new AutoTableRow(context, attrs);
        }
        if (name.equals(LAYOUT_MetroLayout)) {
            view = new MetroLayout(context, attrs);
        }

        if (view != null) return view;

        return super.onCreateView(name, context, attrs);
    }


}
