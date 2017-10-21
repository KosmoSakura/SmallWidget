package widget.small.com.smallwidget.widget.material;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by cjj on 2016/2/20.rato360
 */
public class CustomerFaceView extends View {

    private static final String Tag = CustomerFaceView.class.getSimpleName();

    private int mHeight, mWidth;
    private Paint mCirclePaint;//画圆的画笔
    private boolean isrun = true;
    private ArrayList<Bitmap> animList;

    public CustomerFaceView(Context context) {
        this(context, null);
    }

    public CustomerFaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomerFaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Log.i(Tag, "init");
        //获取图片资源

        animList = new ArrayList<Bitmap>();
        for (int i = 0; i < 8; i++) {
            String url = "/res/drawable-v21/refresh" + i + ".png";
            Bitmap anim = BitmapFactory.decodeStream(getClass().getResourceAsStream(url));
            animList.add(anim);
        }

        mCirclePaint = new Paint();

        StringText = new StringBuffer("载入数据");
        mCirclePaint.setStrokeWidth(3);
        mCirclePaint.setTextSize(30);
        mCirclePaint.setColor(Color.parseColor("#01b5c6"));
        mCirclePaint.setTextAlign(Paint.Align.LEFT);
        bounds = new Rect();
        mCirclePaint.getTextBounds(StringText.toString(), 0, StringText.length(), bounds);

        Paint.FontMetricsInt fontMetrics = mCirclePaint.getFontMetricsInt();
        baseline = (getMeasuredHeight() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;


        openThraad();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawAnim(canvas);
    }

    int index = 0;
    int baseline;
    StringBuffer StringText;
    Rect bounds;

    private void openThraad() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int index = 0;
                while (true) {
                    postInvalidate();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 开启线程画动画
     *
     * @param canvas
     */
    private void drawAnim(final Canvas canvas) {
        index++;
        StringText.append(".");
        if (index == 8) {
            index = 0;
            StringText = StringText.delete(7, StringText.length());
        }
        canvas.save();
        canvas.drawBitmap(animList.get(index), this.getWidth() / 2 - 50, this.getHeight() / 2 - 50, mCirclePaint);
//        canvas.drawText(StringText.toString(), (getMeasuredWidth() / 2 - bounds.width() / 2) - 20, this.getHeight() / 3 + 100, mCirclePaint);

        canvas.restore();
    }


}
