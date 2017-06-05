package nikola.bottomnavigationview.CustomControls;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import nikola.bottomnavigationview.R;

/**
 * Created by Nikola on 6/1/2017.
 */

public class CustomCircleBar extends View {

    private float strokeWidth = 4;
    private float progress    = 0;
    private int min           = 0;
    private int max           = 100;

    // Da CircleBar pocne od vrsnje tacke kruga

    private int startAngle = -90;
    private int color      = Color.parseColor("#FF9800");
    private RectF rectF;
    //private Paint backgroundPaint;
    private Paint foregroundPaint;

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
        //backgroundPaint.setStrokeWidth(strokeWidth);
        foregroundPaint.setStrokeWidth(strokeWidth);
        invalidate();
        requestLayout();
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
        invalidate();
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
        invalidate();
    }

    public int getStartAngle() {
        return startAngle;
    }

    public void setStartAngle(int startAngle) {
        this.startAngle = startAngle;
        invalidate();
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        //backgroundPaint.setColor(adjustAlpha(color, 0.2f));
        foregroundPaint.setColor(color);
        invalidate();
        requestLayout();
    }

    public RectF getRectF() {
        return rectF;
    }

    public void setRectF(RectF rectF) {
        this.rectF = rectF;
        invalidate();
    }

    //public Paint getBackgroundPaint() {
    //    return backgroundPaint;
    //}

    //public void setBackgroundPaint(Paint backgroundPaint) {
    //    this.backgroundPaint = backgroundPaint;
    //}

    public Paint getForegroundPaint() {
        return foregroundPaint;
    }

    public void setForegroundPaint(Paint foregroundPaint) {
        this.foregroundPaint = foregroundPaint;
    }

    public CustomCircleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.rectF = new RectF();
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CustomCircleBar,
                0, 0);
        try {
            strokeWidth = typedArray.getDimension(R.styleable.CustomCircleBar_progressBarThickness, strokeWidth);
            progress    = typedArray.getFloat(R.styleable.CustomCircleBar_progress, progress);
            color       = typedArray.getColor(R.styleable.CustomCircleBar_progressColor, color);
            min         = typedArray.getInt(R.styleable.CustomCircleBar_min, min);
            max         = typedArray.getInt(R.styleable.CustomCircleBar_max, max);
        } finally {
            typedArray.recycle();
        }

        /*backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setColor(adjustAlpha(color, 0.4f));
        backgroundPaint.setStyle(Paint.Style.STROKE);
        backgroundPaint.setStrokeWidth(strokeWidth);*/

        foregroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        foregroundPaint.setColor(color);
        foregroundPaint.setStyle(Paint.Style.STROKE);
        foregroundPaint.setStrokeWidth(strokeWidth);
    }

    private int adjustAlpha(int color, float v) {
        int alpha = Math.round(Color.alpha(color) * v);
        int red   = Color.red(color);
        int green = Color.green(color);
        int blue  = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //canvas.drawOval(rectF, backgroundPaint);
        float angle = 360 * progress / max;
        canvas.drawArc(rectF, startAngle, angle, false, foregroundPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        final int width  = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        final int min    = Math.min(width, height);
        setMeasuredDimension(min, min);
        rectF.set(0 + strokeWidth / 2, 0 + strokeWidth / 2, min - strokeWidth / 2, min - strokeWidth / 2);
    }

    public int lightenColor(int color, float factor) {
        float r = Color.red(color) * factor;
        float g = Color.green(color) * factor;
        float b = Color.blue(color) * factor;
        int ir = Math.min(255, (int) r);
        int ig = Math.min(255, (int) g);
        int ib = Math.min(255, (int) b);
        int ia = Color.alpha(color);
        return Color.argb(ia, ir, ig, ib);
    }

    public void setProgressWithAnimation(float progress) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "progress", progress);
        objectAnimator.setDuration(1500);
        objectAnimator.setInterpolator(new DecelerateInterpolator());
        objectAnimator.start();
    }
}

