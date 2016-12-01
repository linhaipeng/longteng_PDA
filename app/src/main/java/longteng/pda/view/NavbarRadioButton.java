package longteng.pda.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;

import longteng.pda.R;

public class NavbarRadioButton extends RadioButton {

	private int mDrawableSize;

	public NavbarRadioButton(Context context) {
		this(context, null, 0);
	}

	public NavbarRadioButton(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public NavbarRadioButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		Drawable drawableLeft = null, drawableTop = null, drawableRight = null, drawableBottom = null;
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.NavbarRadioButton);

		int n = a.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = a.getIndex(i);
			switch (attr) {
			case R.styleable.NavbarRadioButton_drawableSize:
				mDrawableSize = a.getDimensionPixelSize(R.styleable.NavbarRadioButton_drawableSize, 50);
				break;
			case R.styleable.NavbarRadioButton_drawableTop:
				drawableTop = a.getDrawable(attr);
				break;
			case R.styleable.NavbarRadioButton_drawableBottom:
				drawableRight = a.getDrawable(attr);
				break;
			case R.styleable.NavbarRadioButton_drawableRight:
				drawableBottom = a.getDrawable(attr);
				break;
			case R.styleable.NavbarRadioButton_drawableLeft:
				drawableLeft = a.getDrawable(attr);
				break;
			default :
					break;
			}
		}
		a.recycle();
		
		setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);

	}

	public void setCompoundDrawablesWithIntrinsicBounds(Drawable left,
			Drawable top, Drawable right, Drawable bottom) {

		if (left != null) {
			left.setBounds(0, 0, mDrawableSize, mDrawableSize);
		}
		if (right != null) {
			right.setBounds(0, 0, mDrawableSize, mDrawableSize);
		}
		if (top != null) {
			top.setBounds(0, 0, mDrawableSize, mDrawableSize);
		}
		if (bottom != null) {
			bottom.setBounds(0, 0, mDrawableSize, mDrawableSize);
		}
		setCompoundDrawables(left, top, right, bottom);
	}

}
