package space.zero.kosmos.kos.aui.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import space.zero.kosmos.R;
import space.zero.kosmos.kos.aui.activity.FaceActivity;
import space.zero.kosmos.kos.aui.activity.TimeActivity;
import space.zero.kosmos.kos.aui.adapter.binder.FunctionItemBinder;
import space.zero.kosmos.kos.bean.MainItemBean;
import space.zero.kosmos.mos.base.BaseFragment;
import space.zero.kosmos.mos.tools.CodeUtils;
import space.zero.kosmos.mos.tools.ToastUtil;
import space.zero.kosmos.mos.tools.VibrateHelp;
import space.zero.kosmos.mos.tools.base.Code;
import space.zero.kosmos.mos.tools.glide.GlideUtils;

/**
 * Created by ZeroProject on 2016/5/25 17:00
 */
public class BackGroundFrag extends BaseFragment {
    private ImageView background;
    private View toTop;

    private List<MainItemBean> rvList;
    private FunctionItemBinder binder;

    @Override
    protected int getLayoutId() {
        return R.layout.frag_background;
    }


    @Override
    protected void initView(View view) {
        setlazyLoadOff();
        toTop = findView(R.id.main_back_to_top);
        background = findView(R.id.bg_background);
        rvListView = findView(R.id.rv_main_list);

        rvList = new ArrayList<>();
        binder = new FunctionItemBinder(getActivity());
        recInit(3, MainItemBean.class, binder);
    }

    @Override
    protected void initData() {
        GlideUtils.loadLargePic(context, R.drawable.background, background);
        rvList.add(new MainItemBean(R.drawable.ic_time_g, "时光轴", Code.StatusCode.Main_To_Timer));
        rvList.add(new MainItemBean(R.drawable.ic_face_g, "人脸识别", Code.StatusCode.Main_To_Face));
        rvList.add(new MainItemBean(R.drawable.ic_wait, "待定1"));
        rvList.add(new MainItemBean(R.drawable.ic_wait, "待定2"));
        rvList.add(new MainItemBean(R.drawable.ic_wait, "待定3"));
        rvList.add(new MainItemBean(R.drawable.ic_wait, "待定4"));
        rvList.add(new MainItemBean(R.drawable.ic_wait, "待定5"));
        rvList.add(new MainItemBean(R.drawable.ic_wait, "待定6"));
        rvList.add(new MainItemBean(R.drawable.ic_wait, "待定7"));
        rvList.add(new MainItemBean(R.drawable.ic_wait, "待定8"));
        rvList.add(new MainItemBean(R.drawable.ic_wait, "待定9"));
        rvList.add(new MainItemBean(R.drawable.ic_wait, "待定9"));
        rvList.add(new MainItemBean(R.drawable.ic_wait, "待定9"));
        recNotifyData(rvList);
    }

    @Override
    protected void initListener() {
        rvListView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
                // 当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // 判断是否滚动超过一屏
                    if (firstVisibleItemPosition != 0) {
                        toTop.setVisibility(View.VISIBLE);
                    } else {
                        toTop.setVisibility(View.GONE);
                    }
                } else {
                    toTop.setVisibility(View.GONE);
                }
            }
        });
        toTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvListView.smoothScrollToPosition(0);
            }
        });
        binder.setEventListener(new FunctionItemBinder.EventListener() {
            @Override
            public void onItemClick(int position) {
                if (position > -1 && position < rvList.size()) {
                    int id = rvList.get(position).getId();
                    switch (id) {
                        case Code.StatusCode.Main_To_Timer:
                            CodeUtils.showEditDialog(getActivity(), "进入时光轴需要校验你的身份", "请输入密码", new CodeUtils.PasswordCheck() {
                                @Override
                                public void onResult(DialogInterface dialog, String password) {
                                    if (password.equalsIgnoreCase("5277")) {
                                        dialog.dismiss();
                                        Intent intent = new Intent(context, TimeActivity.class);
                                        startActivity(intent);
                                    } else {
                                        ToastUtil.CustomShort("密码错误", ToastUtil.SHOW_DEFAULT_ICON);
                                        //10,200,10,200,10,200,10,200,400,200,10,200,10,200,10
                                        VibrateHelp.vComplicated(getActivity(), new long[]{10, 200, 400, 200, 400, 200, 10, 200, 10, 200, 400, 200, 10, 200, 10, 200, 10, 200, 10, 200, 10, 200, 10, 200, 10}, -1);
                                    }
                                }
                            });
                            break;
                        case Code.StatusCode.Main_To_Face:
                            Intent intent = new Intent(context, FaceActivity.class);
                            startActivity(intent);
                            break;
                    }
                }
            }
        });
    }

}
