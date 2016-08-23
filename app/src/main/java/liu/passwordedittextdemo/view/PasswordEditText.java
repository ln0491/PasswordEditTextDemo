package liu.passwordedittextdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.EditText;

import liu.passwordedittextdemo.R;

/**
 * Created by 刘楠 on 2016/8/23 0023.22:41
 */
public class PasswordEditText extends EditText {

    /**
     * 与边框相同
     */
    private static final int   defaultContMargin     = 5;
    /**
     * 分割线的宽度
     */
    private static final int   defaultSplitLineWidth = 3;
    /**
     * 边框宽度
     */
    private              float borderWidth           = 5;

    /**
     * 边框颜色
     */
    private int borderColor = 0xFFCCCCCC;

    /**
     * 边框圆角
     */
    private float borderRadius = 3;

    /**
     * 密码长度
     */
    private int passwordLength = 6;

    /**
     * 密码颜色
     */
    private int passwordColor = 0x00FF000;


    /**
     * 密码宽度
     */
    private float passwordWidth = 10;


    /**
     * 密码圆角 没什么用
     */
    private float passwordRadius = 5;

    /**
     * 文本长度
     */
    private int textLength;



    private Paint borderPaint;

    private Paint passwordPaint;

    public PasswordEditText(Context context) {
        this(context, null);
    }

    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();


        borderWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, borderWidth, displayMetrics);
        borderRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, borderRadius, displayMetrics);
        passwordLength = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, passwordLength, displayMetrics);
        passwordWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, passwordWidth, displayMetrics);
        //没什么用
        passwordRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, passwordRadius, displayMetrics);


        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PasswordEditText, 0, 0);


        borderWidth = typedArray.getDimension(R.styleable.PasswordEditText_borderWidth, borderWidth);

        borderColor = typedArray.getColor(R.styleable.PasswordEditText_borderColor, borderColor);


        borderRadius = typedArray.getDimension(R.styleable.PasswordEditText_borderRadius, borderRadius);


        passwordLength = typedArray.getInt(R.styleable.PasswordEditText_passwordLength, passwordLength);


        passwordColor = typedArray.getColor(R.styleable.PasswordEditText_passwordColor, passwordColor);


        passwordWidth = typedArray.getDimension(R.styleable.PasswordEditText_passwordWidth, passwordWidth);


        passwordRadius = typedArray.getDimension(R.styleable.PasswordEditText_passwordRadius, passwordRadius);

        typedArray.recycle();


        borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setStrokeWidth(borderWidth);
        borderPaint.setColor(borderColor);

        passwordPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        passwordPaint.setColor(passwordColor);
        passwordPaint.setStyle(Paint.Style.FILL);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        /**
         * 获取宽度与高度
         */
        int width  = getWidth();
        int height = getHeight();

        /**
         * 画边框
         */
        RectF rectF = new RectF(0, 0, width, height);

        canvas.drawRoundRect(rectF, borderRadius, borderRadius, borderPaint);


        /**
         * 内容区
         */
        RectF rectIn = new RectF(rectF.left + defaultContMargin, rectF.top + defaultContMargin, rectF.right - defaultContMargin, rectF.bottom - defaultContMargin);

        borderPaint.setColor(Color.WHITE);

        canvas.drawRoundRect(rectIn,borderRadius,borderRadius,borderPaint);

        /**
         * 画分割线
         */
        borderPaint.setColor(borderColor);

        /**
         * 设置画笔宽度
         */
        borderPaint.setStrokeWidth(defaultSplitLineWidth);

        for (int i = 0; i < passwordLength; i++) {

            float x = i*width/passwordLength;
            canvas.drawLine(x,0,x,height,borderPaint);
        }


        /**
         * 密码
         */

        // 密码
        float cx, cy = height/ 2;
        float half = width / passwordLength / 2;
        for(int i = 0; i < textLength; i++) {
            cx = width * i / passwordLength + half;
            canvas.drawCircle(cx, cy, passwordWidth, passwordPaint);
        }


    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        this.textLength = text.toString().length();
        invalidate();
    }

    public float getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(float borderWidth) {
        this.borderWidth = borderWidth;
        borderPaint.setStrokeWidth(borderWidth);
        invalidate();
    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;

        borderPaint.setColor(borderColor);
        invalidate();
    }

    public float getBorderRadius() {
        return borderRadius;
    }

    public void setBorderRadius(float borderRadius) {
        this.borderRadius = borderRadius;

        invalidate();
    }

    public int getPasswordLength() {
        return passwordLength;
    }

    public void setPasswordLength(int passwordLength) {
        this.passwordLength = passwordLength;


        invalidate();
    }

    public int getPasswordColor() {
        return passwordColor;
    }

    public void setPasswordColor(int passwordColor) {
        this.passwordColor = passwordColor;


        passwordPaint.setColor(passwordColor);
        invalidate();
    }

    public float getPasswordWidth() {
        return passwordWidth;
    }

    public void setPasswordWidth(float passwordWidth) {
        this.passwordWidth = passwordWidth;

        passwordPaint.setStrokeWidth(passwordWidth);
        invalidate();
    }

    public float getPasswordRadius() {
        return passwordRadius;
    }

    public void setPasswordRadius(float passwordRadius) {
        this.passwordRadius = passwordRadius;
        invalidate();
    }
}
