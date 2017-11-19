package widget.small.com.smallwidget.business.fragment;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import widget.small.com.smallwidget.R;
import widget.small.com.smallwidget.business.activity.ZhiFuActivity;
import widget.small.com.smallwidget.helper.base.BaseFragment;
import widget.small.com.smallwidget.helper.tools.ToastUtil;
import widget.small.com.smallwidget.helper.tools.TxtUtil;

/**
 * Created by ZeroProject on 2016/5/25 17:00
 */
public class ZhiFuFrag extends BaseFragment {


    private EditText price, name;
    private TextView next;
    private ImageView show;


    @Override
    protected int getLayoutId() {
        return R.layout.frag_zhifu;
    }


    @Override
    protected void initView(View view) {
        price = findView(R.id.main_price);
        name = findView(R.id.main_name);
        next = findView(R.id.main_next);
        show = findView(R.id.main_zhuan);

    }

    @Override
    protected void initListener() {
        price.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (TxtUtil.isEmpty(name.getText().toString())) {
                        ToastUtil.CustomShort("总要告诉我收款人的名字吧...");
                        return true;
                    }
                    if (TxtUtil.isEmpty(price.getText().toString())) {
                        ToastUtil.CustomShort("没有金额，怎么接着搞事？！");
                        return true;
                    }
                    Intent intent = new Intent(context, ZhiFuActivity.class);
                    intent.putExtra("price", price.getText().toString());
                    intent.putExtra("name", name.getText().toString());
                    startActivity(intent);
                }
                return false;
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TxtUtil.isEmpty(name.getText().toString())) {
                    ToastUtil.CustomShort("总要告诉我收款人的名字吧...");
                    return;
                }
                if (TxtUtil.isEmpty(price.getText().toString())) {
                    ToastUtil.CustomShort("没有金额，怎么接着搞事？！");
                    return;
                }
                Intent intent = new Intent(context, ZhiFuActivity.class);
                intent.putExtra("price", price.getText().toString());
                intent.putExtra("name", name.getText().toString());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
//        GlideUtils.loadCirleAvatar(context, R.drawable.gang, show);
    }


}
