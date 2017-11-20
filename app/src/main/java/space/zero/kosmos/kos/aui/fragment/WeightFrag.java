package space.zero.kosmos.kos.aui.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import greendao.bean.Weight;
import space.zero.kosmos.R;
import space.zero.kosmos.kos.aui.adapter.GroupAdapter;
import space.zero.kosmos.kos.aui.adapter.recycle.CommonAdapter;
import space.zero.kosmos.mos.base.BaseFragment;
import space.zero.kosmos.kos.bean.WeightDto;
import space.zero.kosmos.mos.db.DaoWeight;
import space.zero.kosmos.mos.tools.CodeUtils;
import space.zero.kosmos.mos.tools.DialogUtils;
import space.zero.kosmos.mos.tools.ToastUtil;
import space.zero.kosmos.mos.tools.TxtUtil;
import space.zero.kosmos.mos.tools.Ways;
import space.zero.kosmos.mos.tools.base.Code;
import space.zero.kosmos.mos.widget.ArcMenu;
import space.zero.kosmos.mos.widget.adapterview.NoScrollListView;
import space.zero.kosmos.mos.widget.material.MaterialRefreshLayout;
import space.zero.kosmos.mos.widget.material.MaterialRefreshListener;
import space.zero.kosmos.mos.widget.recycler.MultiItemTypeAdapter;
import space.zero.kosmos.mos.widget.recycler.base.HYViewHolder;
import space.zero.kosmos.mos.widget.time.TimePopupWindow;

/**
 * Created by ZeroProject on 2016/5/25 17:00
 */
public class WeightFrag extends BaseFragment implements View.OnClickListener, PopupWindow.OnDismissListener {
    private DaoWeight dao_weight;
    private DisplayMetrics dm;

    private RelativeLayout relNodata, rootView;
    private LinearLayout layNow;
    private TextView now, del;
    private ImageView add, ivArc;
    private ArcMenu arc;
    private RecyclerView mRecyclerView;
    private MaterialRefreshLayout refreshLayout;

    //---Pop-----------------------------------------
    private PopupWindow popAddFirst, popGroup;
    private TimePopupWindow pwTime;

    //------------------
    private CommonAdapter<WeightDto> mAdapter;
    private GroupAdapter adapterName;
    private List<WeightDto> mList;
    private Weight temp;

    @Override
    protected int getLayoutId() {
        return R.layout.frag_weight;
    }

    @Override
    public void onResume() {
        super.onResume();
        refleash();
    }

    @Override
    protected void initView(View view) {

        dao_weight = DaoWeight.getInstance();
        dm = getResources().getDisplayMetrics();

        relNodata = findView(R.id.weight_nodata_rel);
        rootView = findView(R.id.group_main_mem_root);
        layNow = findView(R.id.weight_now_lay);
        add = findView(R.id.weight_save_t);
        ivArc = findView(R.id.wieght_id_button);
        now = findView(R.id.weight_now_t);
        del = findView(R.id.weight_del_t);
        arc = findView(R.id.weight_arc);
        refreshLayout = findView(R.id.weight_ref);
        mRecyclerView = findView(R.id.weight_rv);

        initMaterialRefreshLayout();

        mList = new ArrayList<>();
        //        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
//        mRecyclerView.setLayoutManager(new GridLayoutManager(context, 2));
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        mAdapter = new CommonAdapter<WeightDto>(context, R.layout.item_weight, mList) {
            @Override
            protected void convert(HYViewHolder holder, WeightDto dto, WeightDto old, int position) {
                Weight weight = dto.getWeight();

                Weight olds = null;
                if (old != null) {
                    olds = old.getWeight();
                }
                String str = "";
                if (weight.getUnit() == Code.StatusCode.jing) {
                    str = "斤";
                } else {
                    str = "千克";
                }
                String txt = weight.getYear() + "年" + weight.getMonth() + "月的第" + weight.getDay() + "天";
                holder.setText(R.id.item_theme_month, txt);
//                if (olds != null && weight.getYear() == olds.getYear() && weight.getMonth() == olds.getMonth() && weight.getDay() == olds.getDay()) {
//                    holder.setHide(R.id.item_theme_month, false);
//                } else {
//                    String txt = weight.getYear() + "年" + weight.getMonth() + "月的第" + weight.getDay() + "天";
//                    holder.setText(R.id.item_theme_month, txt);
//                }
//                if (olds != null) {
//                    if (weight.getYear() == olds.getYear() && weight.getMonth() == olds.getMonth()) {
//                        holder.setHide(R.id.item_theme_line, false);
//                        holder.setHide(R.id.item_theme_month, false);
//                        if (weight.getDay() == olds.getDay()) {
//                            holder.setHide(R.id.item_theme_day, false);
//                            holder.setHide(R.id.item_theme_line, false);
//                            holder.setHide(R.id.item_theme_month, false);
//                        } else {
//                            holder.setHide(R.id.item_theme_day, true);
//                            holder.setText(R.id.item_theme_day, "第" + weight.getDay() + "日");
//                        }
//                    } else {
//                        holder.setHide(R.id.item_theme_month, true);
//                        holder.setText(R.id.item_theme_month, weight.getYear() + "年" + weight.getMonth() + "月");
//                    }
//                } else {
//                    holder.setText(R.id.item_theme_day, "第" + weight.getDay() + "日");
//                    holder.setText(R.id.item_theme_month, weight.getYear() + "年" + weight.getMonth() + "月");
//                }


                holder.setText(R.id.item_theme_wei, weight.getValue() + "");
                holder.setText(R.id.item_theme_time, weight.getTime());
                holder.setText(R.id.item_theme_unit, str + "\t\t\t" + weight.getHour_str());
//                holder.setImageGlide(R.id.item_theme_icon, R.drawable.love_6);
            }
        };

        mRecyclerView.setAdapter(mAdapter);
        initPop();
    }

    @Override
    protected void initListener() {
        add.setOnClickListener(this);
        now.setOnClickListener(this);
        del.setOnClickListener(this);
        layNow.setOnClickListener(this);
        relNodata.setOnClickListener(this);
        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener<WeightDto>() {
            @Override
            public void onItemClick(WeightDto dto, int position) {
                Weight wei = dto.getWeight();
                Ways.sendBrad(wei.getValue().toString() + "斤", getActivity());
                ToastUtil.CustomShort(wei.getValue().toString() + "斤");
            }

            @Override
            public boolean onItemLongClick(RecyclerView.ViewHolder holder, final int position) {
                CodeUtils.showSimpleDialog(getActivity(), "删除不可恢复，确定要删除吗？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dao_weight.deleteOne(mList.get(position).getWeight().getId());
                        mList.remove(position);
                        mAdapter.notifyItemRemoved(position);
                        dialogInterface.dismiss();
                    }
                });
                return true;
            }
        });

        refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                refleash();
            }
        });
        arc.setOnMenuItemClickListener(new ArcMenu.OnMenuItemClickListener() {
            @Override
            public void onClick(View view, int pos) {
                ivArc.setSelected(false);
                rootView.setBackgroundColor(getResources().getColor(R.color.T_all));
                rootView.setClickable(false);
                switch (view.getId()) {
                    case R.id.wieght_id_add://添加
                        if (TxtUtil.isEmpty(now)) {
                            showAddFirst(true);
                        } else {
                            showNotFirst(true);
                        }
                        break;
                    case R.id.wieght_id_del://删除
                        new DialogUtils(context, Code.System.HideDialgeEdt, getResources().getDrawable(R.drawable.warning), "", "确定", "取消", "删除将不可恢复？", new DialogUtils.SureOnClickListener() {
                            @Override
                            public void OnClick(String result, Dialog dia) {
                                if (!TxtUtil.isEmpty(now.getText().toString())) {
                                    dao_weight.deleteNameAll(now.getText().toString());
                                    refleash();
                                } else {
                                    ToastUtil.CustomShort("没有默认显示记录");
                                }
                                dia.dismiss();
                            }
                        });
                        break;
                    case R.id.wieght_id_list://列表
                        showNameGroup(true);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void opening(boolean open) {
                ivArc.setSelected(open);
                if (open) {
                    rootView.setBackgroundColor(getResources().getColor(R.color.default_background));
                    rootView.setClickable(true);
                } else {
                    rootView.setBackgroundColor(getResources().getColor(R.color.T_all));
                    rootView.setClickable(false);
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.weight_del_t://清空
                dao_weight.deleteAll();
                refleash();
                break;
            case R.id.weight_nodata_rel://空页面:
                showAddFirst(true);
                break;
            case R.id.weight_now_lay://默认
                showNameGroup(true);
                break;
            default:
                break;
        }
    }

    @Override
    protected void initData() {

    }

    private TextView name;

    private void refleash() {
        if (dao_weight.checkEmpt()) {
            relNodata.setVisibility(View.VISIBLE);
        } else {
            relNodata.setVisibility(View.GONE);
            now.setText(dao_weight.getDefaultName() + "");
            List<Weight> temp = null;
            List<WeightDto> temp2 = new ArrayList<>();

            if (!TxtUtil.isEmpty(now.getText().toString())) {
                temp = dao_weight.searchByName(now.getText().toString());
                for (int i = 0; i < temp.size(); i++) {
                    WeightDto dto = new WeightDto();
                    if (i < 1) {
                        dto.setWeight(temp.get(i));
                    } else {
                        dto.setWeight(temp.get(i));
                        dto.setWeightOld(temp.get(i - 1));
                    }
                    temp2.add(dto);
                }
                mList.clear();
                mList.addAll(temp2);
                mAdapter.notifyDataSetChanged();
                temp.clear();
                temp = null;
                temp2.clear();
                temp2 = null;
            } else {
                temp = dao_weight.searchAll();
                for (int i = 0; i < temp.size(); i++) {
                    WeightDto dto = new WeightDto();
                    if (i < 1) {
                        dto.setWeight(temp.get(i));
                    } else {
                        dto.setWeight(temp.get(i));
                        dto.setWeightOld(temp.get(i - 1));
                    }
                    temp2.add(dto);
                }

                mList.clear();
                mList.addAll(temp2);
                mAdapter.notifyDataSetChanged();
                temp.clear();
                temp = null;
                temp2.clear();
                temp2 = null;
            }
            //----------------------------------------
            adapterName.setNox(now.getText().toString());
            List<String> strings = dao_weight.searchAllGroup();
            if (!TxtUtil.isEmpty(strings)) {
                adapterName.addList(strings);
                adapterName.notifyDataSetChanged();
            }
        }
        stopRefresh();
    }

    public void initMaterialRefreshLayout() {
        refreshLayout.setLoadMore(false);
        refreshLayout.finishRefreshLoadMore();
        refreshLayout.setSunStyle(true);
    }

    public void stopRefresh() {
//        refreshLayout.finishRefreshLoadMore();
        refreshLayout.finishRefreshing();
    }

    private void initPop() {

        LayoutInflater inflater = LayoutInflater.from(context);

        //第一次添加
        View v_add_first = inflater.inflate(R.layout.pop_add_first, null);
//        popAddFirst = new PopupWindow(v_add_first, ViewGroup.AutoLayoutParams.MATCH_PARENT, ViewGroup.AutoLayoutParams.MATCH_PARENT);
        popAddFirst = new PopupWindow(context);
//        popAddFirst.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, dm));
//        popAddFirst.setHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120, dm));
        popAddFirst.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popAddFirst.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popAddFirst.setContentView(v_add_first);
        popAddFirst.setBackgroundDrawable(new ColorDrawable(0));
        popAddFirst.setFocusable(true);
        popAddFirst.setOutsideTouchable(true);

        final TextView time = (TextView) v_add_first.findViewById(R.id.pop_add_first_time);
        name = (TextView) v_add_first.findViewById(R.id.pop_add_first_name);
        final TextView weig = (TextView) v_add_first.findViewById(R.id.pop_add_first_value);
        final TextView ok = (TextView) v_add_first.findViewById(R.id.pop_add_first_ok);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出时间选择器
                pwTime.showAtLocation(add, Gravity.BOTTOM, 0, 0, new Date());
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "";
                if (!TxtUtil.isEmpty(name.getText().toString())) {
                    str = name.getText().toString();
                } else if (!TxtUtil.isEmpty(now.getText().toString())) {
                    str = now.getText().toString();
                }

                if (temp == null && TxtUtil.isEmpty(time.getText().toString()) || TxtUtil.isEmpty(str) || TxtUtil.isEmpty(weig.getText().toString())) {
                    ToastUtil.CustomShort("信息不完整");
                    return;
                }
                Weight wei = new Weight(dao_weight.getDbSize() + 1);
                wei.setName(str);
                wei.setValue(Float.valueOf(weig.getText().toString()));
                wei.setUnit(Code.StatusCode.jing);
                wei.setTime(time.getText().toString());
                wei.setYear(temp.getYear());
                wei.setMonth(temp.getMonth());
                wei.setDay(temp.getDay());
                wei.setHour(temp.getHour());
                wei.setMin(temp.getMin());
                wei.setHour_str(temp.getHour_str());
                dao_weight.add(wei);
                Ways.sendBrad(weig.getText().toString() + "斤", getActivity());
                refleash();
                showAddFirst(false);
            }
        });

        //时间选择效果实现
        pwTime = new TimePopupWindow(context, TimePopupWindow.Type.ALL);
        pwTime.setTime(new Date());
        //控制时间范围
        Calendar calendar = Calendar.getInstance();
        pwTime.setRange(calendar.get(Calendar.YEAR) - 56, calendar.get(Calendar.YEAR));
        pwTime.setCyclic(true);
        //时间选择后回调
        pwTime.setOnTimeSelectListener(new TimePopupWindow.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(String date) {
                time.setText(date);
            }

            @Override
            public void onDate(int year, int month, int day, int hour, int min, String time) {
                super.onDate(year, month, day, hour, min, time);
                temp = new Weight();
                temp.setYear(year);
                temp.setMonth(month);
                temp.setDay(day);
                temp.setHour(hour);
                temp.setMin(min);
                temp.setHour_str(time);
            }
        });
        popAddFirst.setOnDismissListener(this);

        //第一次添加
        View popName = inflater.inflate(R.layout.pop_name_list, null);
        popGroup = new PopupWindow(context);
        popGroup.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popGroup.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popGroup.setContentView(popName);
        popGroup.setBackgroundDrawable(new ColorDrawable(0));
        popGroup.setFocusable(true);
        popGroup.setOutsideTouchable(true);
        popGroup.setOnDismissListener(this);
        NoScrollListView nlv = (NoScrollListView) popName.findViewById(R.id.pop_name_lv);
        TextView tvNew = (TextView) popName.findViewById(R.id.pop_name_new);
        List<String> strings = dao_weight.searchAllGroup();

        adapterName = new GroupAdapter(context);
        nlv.setAdapter(adapterName);
        nlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = adapterName.getItem(position);
                now.setText(item);
                dao_weight.setDefaultName(item);
                showNameGroup(false);
                refleash();
            }
        });
        tvNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddFirst(true);
                showNameGroup(false);
            }
        });
        refleash();
    }


    private void showNameGroup(boolean show) {
        if (show) {
            if (popGroup != null && !popGroup.isShowing()) {
                popGroup.showAtLocation(add, Gravity.CENTER, 0, 0);
                backgroundAlpha(0.7f);
            }
        } else {
            if (popGroup != null && popGroup.isShowing()) {
                popGroup.dismiss();
                backgroundAlpha(1f);
            }
        }
    }

    private void showAddFirst(boolean show) {
        name.setVisibility(View.VISIBLE);
        if (show) {
            if (popAddFirst != null && !popAddFirst.isShowing()) {
                popAddFirst.showAtLocation(add, Gravity.CENTER, 0, 0);
                backgroundAlpha(0.7f);
            }
        } else {
            if (popAddFirst != null && popAddFirst.isShowing()) {
                popAddFirst.dismiss();
                backgroundAlpha(1f);
            }
        }
    }

    private void showNotFirst(boolean show) {
        name.setVisibility(View.GONE);
        if (show) {
            if (popAddFirst != null && !popAddFirst.isShowing()) {
                popAddFirst.showAtLocation(add, Gravity.CENTER, 0, 0);
                backgroundAlpha(0.7f);
            }
        } else {
            if (popAddFirst != null && popAddFirst.isShowing()) {
                popAddFirst.dismiss();
                backgroundAlpha(1f);
            }
        }
    }

    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);
    }

    @Override
    public void onDismiss() {
        if (popAddFirst != null && !popAddFirst.isShowing()) {
            backgroundAlpha(1f);
        }
        if (popGroup != null && !popGroup.isShowing()) {
            backgroundAlpha(1f);
        }
    }
}
