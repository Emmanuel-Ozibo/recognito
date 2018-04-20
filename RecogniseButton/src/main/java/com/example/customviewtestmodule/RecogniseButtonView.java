package com.example.customviewtestmodule;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by emmanuel on 1/3/2018.
 */


public class RecogniseButtonView extends View{

    private static final float SHADOW_OFFSET_DX = 0.5f;
    private static final float SHADOW_OFFSET_DY = 4.0f;
    private static final float SHADOW_RADIUS = 12.5f;


    private final int specifiedColor;
    private Bitmap finalIconBitmap = null;
    private int defaultWidth, defaultHeight;
    private Paint recogniseButtonPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint iconPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap iconBitmap;
    private int shadowColor;
    private RectF fingerRect;
    private boolean isAnimationEnabled;


    public RecogniseButtonView(Context context) {
        this(context, null);
    }

    public RecogniseButtonView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecogniseButtonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        shadowColor = R.color.shadow_color;
        TypedArray attributes = null;

        defaultWidth = getResources().getDimensionPixelSize(R.dimen.button_height);
        defaultHeight = getResources().getDimensionPixelSize(R.dimen.button_height);

        int default_icon_width = getResources().getDimensionPixelSize(R.dimen.icon_width);
        int default_icon_height = getResources().getDimensionPixelSize(R.dimen.icon_width);

        int buttonColor = getResources().getColor(R.color.default_color);

        try {

            attributes = getResources().obtainAttributes(attrs, R.styleable.RecogniseButtonView);
            Drawable iconDrawable = attributes.getDrawable(R.styleable.RecogniseButtonView_buttonIcon);
            specifiedColor = attributes.getColor(R.styleable.RecogniseButtonView_button_color, buttonColor);
            int iconWidth = attributes.getDimensionPixelSize(R.styleable.RecogniseButtonView_icon_size, default_icon_width);
            int iconHeight = attributes.getDimensionPixelSize(R.styleable.RecogniseButtonView_icon_size, default_icon_height);
            isAnimationEnabled = attributes.getBoolean(R.styleable.RecogniseButtonView_enable_animation, false);

            if (iconDrawable != null){
                iconBitmap = ((BitmapDrawable)iconDrawable).getBitmap();
                finalIconBitmap = Bitmap.createScaledBitmap(iconBitmap, iconWidth, iconHeight,false);
            }
        }finally {
            attributes.recycle();
        }

        recogniseButtonPaint.setColor(specifiedColor);

        recogniseButtonPaint.setShadowLayer(SHADOW_RADIUS, SHADOW_OFFSET_DX, SHADOW_OFFSET_DY, shadowColor);

        setLayerType(LAYER_TYPE_SOFTWARE,null);

    }

    private int darkenColor(int color){
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.8f;
        return Color.HSVToColor(hsv);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        float WidthX = getWidth()/2;float HeightY = getHeight()/2;
        float circleRadius = (float) (getHeight()/2.6);

        canvas.drawCircle(WidthX, HeightY, circleRadius, recogniseButtonPaint);
        if (iconBitmap != null)
            canvas.drawBitmap(finalIconBitmap, (getWidth() - finalIconBitmap.getHeight())/2, (getHeight() - finalIconBitmap.getHeight())/2, iconPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);

        int calulatedWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        int calculatedHeight = View.MeasureSpec.getSize(heightMeasureSpec);

        int width = checkMode(widthMode, calulatedWidth);
        int height = checkMode(heightMode, calculatedHeight);

        setMeasuredDimension(width, height);
    }

    private int checkMode(int viewMode, int calculatedDimension){
        switch (viewMode){
            case MeasureSpec.EXACTLY:
                return calculatedDimension;
            case MeasureSpec.AT_MOST:
                return defaultHeight;
            case MeasureSpec.UNSPECIFIED:
                return defaultHeight;
        }
        return 0;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            //clicked down
            fingerRect = new RectF(getLeft(), getTop(), getRight(), getBottom());
            recogniseButtonPaint.setColor(darkenColor(specifiedColor));
            performClick();
            invalidate();
        }else if (event.getAction() == MotionEvent.ACTION_UP){
            recogniseButtonPaint.setColor(specifiedColor);
            invalidate();
        }else if (event.getAction() == MotionEvent.ACTION_MOVE){
            if (!fingerRect.contains((getLeft() + (int)event.getX()), getTop() + (int)event.getY())){
                recogniseButtonPaint.setColor(specifiedColor);
            }
        }
        return true;
    }
}
