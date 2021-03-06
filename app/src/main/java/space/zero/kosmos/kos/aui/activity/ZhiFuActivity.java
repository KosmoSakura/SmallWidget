package space.zero.kosmos.kos.aui.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import space.zero.kosmos.R;
import space.zero.kosmos.mos.base.BaseActivity;
import space.zero.kosmos.mos.tools.DialogUtilsZhi;

/**
 * Description:
 * <p>
 * Author: Kosmos
 * Email:ZeroProject@foxmail.com
 */
public class ZhiFuActivity extends BaseActivity {
    private TextView desc, price, sure;

    @Override
    protected void initView() {
        desc = findView(R.id.zhi_desc);
        price = findView(R.id.zhi_price);
        sure = findView(R.id.main_sure);
    }

    @Override
    protected void initIntent(Intent intent) {
        String price = intent.getStringExtra("price");
        String name = intent.getStringExtra("name");

        desc.setText(name + "已收到你的转账");
        setSpannableSize(price);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_zhi_fu;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public void setSpannableSize(String ss) {
        BigDecimal big = new BigDecimal(ss);
        DecimalFormat df = new DecimalFormat("#0.00");
        String str = df.format(big) + "元";
        price.setText(str);
    }

    @Override
    protected void initListener() {
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DialogUtilsZhi(ZhiFuActivity.this, new DialogUtilsZhi.SureOnClickListener() {
                    @Override
                    public void OnClick() {
                        finish();
                    }
                });
            }
        });
    }

}
