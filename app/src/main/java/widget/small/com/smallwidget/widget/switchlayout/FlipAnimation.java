package widget.small.com.smallwidget.widget.switchlayout;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * 锟斤拷锟斤拷为使锟斤拷锟斤拷锟剿碉拷锟斤拷
 *
 * @author 85204_000
 *
 */
public class FlipAnimation extends Animation {
	/** 值为true时锟斤拷锟斤拷确锟介看锟斤拷锟斤拷锟斤拷锟斤拷转锟斤拷锟斤拷 */
	public static final boolean DEBUG = false;
	/** 锟斤拷Y锟斤拷锟斤拷锟斤拷锟津看ｏ拷锟斤拷值锟斤拷1时锟斤拷锟斤拷锟斤拷时锟斤拷锟斤拷转锟斤拷 */
	public static final boolean ROTATE_DECREASE = true;
	/** 锟斤拷Y锟斤拷锟斤拷锟斤拷锟津看ｏ拷锟斤拷值锟斤拷1时锟斤拷锟斤拷顺时锟斤拷锟斤拷转锟斤拷 */
	public static final boolean ROTATE_INCREASE = false;
	/** Z锟斤拷锟斤拷锟斤拷锟斤拷锟饺★拷 */
	public static final float DEPTH_Z = 310.0f;
	/** 锟斤拷锟斤拷锟斤拷示时锟斤拷锟斤拷 */
	public static final long DURATION = 800l;
	/** 图片锟斤拷转锟斤拷锟酵★拷 */
	private final boolean type;
	private final float centerX;
	private final float centerY;
	private Camera camera;
	/** 锟斤拷锟节硷拷锟斤拷锟斤拷锟斤拷锟斤拷锟饺★拷锟斤拷值锟斤拷锟斤拷时锟斤拷锟斤拷锟絫xtNumber锟斤拷锟斤拷锟捷★拷 */
	private InterpolatedTimeListener listener;

	public FlipAnimation(float cX, float cY, boolean type) {
		centerX = cX;
		centerY = cY;
		this.type = type;
		setDuration(DURATION);
	}

	public void initialize(int width, int height, int parentWidth,
						   int parentHeight) {
		// 锟节癸拷锟届函锟斤拷之锟斤拷getTransformation()之前锟斤拷锟矫憋拷锟斤拷锟斤拷锟斤拷
		super.initialize(width, height, parentWidth, parentHeight);
		camera = new Camera();
	}

	public void setInterpolatedTimeListener(InterpolatedTimeListener listener) {
		this.listener = listener;
	}

	protected void applyTransformation(float interpolatedTime,
									   Transformation transformation) {
		// interpolatedTime:锟斤拷锟斤拷锟斤拷锟斤拷值锟斤拷锟斤拷围为[0.0f,10.f]
		if (listener != null) {
			listener.interpolatedTime(interpolatedTime);
		}
		float from = 0.0f, to = 0.0f;
		if (type == ROTATE_DECREASE) {
			from = 0.0f;
			to = 180.0f;
		} else if (type == ROTATE_INCREASE) {
			from = 360.0f;
			to = 180.0f;
		}
		float degree = from + (to - from) * interpolatedTime;
		boolean overHalf = (interpolatedTime > 0.5f);
		if (overHalf) {
			// 锟斤拷转锟斤拷锟斤拷锟斤拷锟斤拷锟铰ｏ拷为锟斤拷证锟斤拷锟斤拷锟斤拷为锟缴讹拷锟斤拷锟斤拷锟街讹拷锟角撅拷锟斤拷效锟斤拷锟斤拷锟斤拷锟街ｏ拷锟借翻转180锟饺★拷
			degree = degree - 180;
		}
		// float depth = 0.0f;
		float depth = (0.5f - Math.abs(interpolatedTime - 0.5f)) * DEPTH_Z;
		final Matrix matrix = transformation.getMatrix();
		camera.save();
		camera.translate(0.0f, 0.0f, depth);
		camera.rotateY(degree);
		camera.getMatrix(matrix);
		camera.restore();
		if (DEBUG) {
			if (overHalf) {
				matrix.preTranslate(-centerX * 2, -centerY);
				matrix.postTranslate(centerX * 2, centerY);
			}
		} else {
			// 确锟斤拷图片锟侥凤拷转锟斤拷锟斤拷一直锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷牡锟轿伙拷锟?
			matrix.preTranslate(-centerX, -centerY);
			matrix.postTranslate(centerX, centerY);
		}
	}

	/** 锟斤拷锟斤拷锟斤拷锟饺硷拷锟斤拷锟斤拷锟斤拷 */
	public static interface InterpolatedTimeListener {
		public void interpolatedTime(float interpolatedTime);
	}
}
