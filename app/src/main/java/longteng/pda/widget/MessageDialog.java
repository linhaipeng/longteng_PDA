package longteng.pda.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;


import java.lang.reflect.Method;

import longteng.pda.R;

/**
 * 自定义消息弹出窗
 *
 * @author [XIAO K | 20150917]
 */
public class MessageDialog extends Dialog {

	private Context mContext; // 上下文

	/**
	 * 初始化消息弹出窗
	 *
	 * @param context
	 *            上下文
	 */
	public MessageDialog(Context context) {
		super(context);
		mContext = context;
	}

	/**
	 * 初始化消息弹出窗
	 *
	 * @param context
	 *            上下文
	 * @param theme
	 *            主题
	 */
	public MessageDialog(Context context, int theme) {
		super(context, theme);
		mContext = context;
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// 屏蔽下拉状态栏
		super.onWindowFocusChanged(hasFocus);
		try {
			Object service = mContext.getSystemService("statusbar");
			Class<?> statusbarManager = Class.forName("android.app.StatusBarManager");
			Method test = statusbarManager.getMethod("collapse");
			test.invoke(service);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 自定义弹出窗生成类
	 *
	 * @author [XIAO K | 20150917]
	 */
	public static class Builder {

		private Context mContext;
		private Boolean mIsCancel = true;
		private String mMessage;
		private String mTitle;
		private View mContentView;
		private String mPositiveButtonText;
		private String mNegativeButtonText;
		private DialogInterface.OnClickListener mPositiveButtonClickListener;
		private DialogInterface.OnClickListener mNegativeButtonClickListener;

		/**
		 * 初始化弹出窗生成类
		 *
		 * @param context
		 *            上下文
		 */
		public Builder(Context context) {
			this.mContext = context;
		}

		/**
		 * 设置提示内容
		 *
		 * @param message
		 *            提示内容
		 */
		public Builder setMessage(String message) {
			this.mMessage = message;
			return this;
		}

		/**
		 * 设置标题
		 *
		 * @param title
		 *            标题内容
		 * @return
		 */
		public Builder setTitle(String title) {
			this.mTitle = title;
			return this;
		}

		/**
		 * 设置内容控件
		 *
		 * @param view
		 *            内容控件
		 */
		public Builder setContentView(View view) {
			this.mContentView = view;
			return this;
		}

		/**
		 * 设置第一个按钮
		 *
		 * @param positiveButtonText
		 *            按钮内容
		 * @param listener
		 *            按钮事件
		 */
		public Builder setPositiveButton(String positiveButtonText, DialogInterface.OnClickListener listener) {
			this.mPositiveButtonText = positiveButtonText;
			this.mPositiveButtonClickListener = listener;
			return this;
		}

		/**
		 * 设置第二个按钮
		 *
		 * @param negativeButtonText
		 *            按钮内容setFinishOnTouchOutside(true);
		 * @param listener
		 *            按钮事件
		 */
		public Builder setNegativeButton(String negativeButtonText, DialogInterface.OnClickListener listener) {
			this.mNegativeButtonText = negativeButtonText;
			this.mNegativeButtonClickListener = listener;
			return this;
		}

		/**
		 * 设置是否点击外部无效
		 *
		 * @param cancel
		 *            是否点击外部无效
		 * @return
		 */
		public Builder setCancelable(boolean cancel) {
			this.mIsCancel = cancel;
			return this;
		}

		/**
		 * 创建消息提示框
		 *
		 * @return 消息提示框
		 */
		@SuppressWarnings("deprecation")
		public MessageDialog create() {
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final MessageDialog dialog = new MessageDialog(mContext, R.style.Dialog);
			View layout = inflater.inflate(R.layout.dialog_layout, null);
			LayoutParams par = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			par.setMargins(10, 0, 10, 0);
			dialog.addContentView(layout, par);

			WindowManager windowManager = ((Activity) mContext).getWindowManager();
			Display display = windowManager.getDefaultDisplay();
			WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
			params.width = (int) (display.getWidth() - 20);
			dialog.getWindow().setAttributes(params);

			// 设置点击外部无效
			dialog.setCanceledOnTouchOutside(mIsCancel);
			dialog.setCancelable(mIsCancel);
			// 设置标题
			TextView txt_title = (TextView) layout.findViewById(R.id.txt_title);
			if (TextUtils.isEmpty(mTitle)) {
				txt_title.setVisibility(View.GONE);
				layout.findViewById(R.id.view_split).setVisibility(View.GONE);
			} else {
				txt_title.setVisibility(View.VISIBLE);
				txt_title.setText(mTitle);
				layout.findViewById(R.id.view_split).setVisibility(View.VISIBLE);
			}

			// 设置分割线
			if (TextUtils.isEmpty(mPositiveButtonText) || TextUtils.isEmpty(mNegativeButtonText)) {
				View view = layout.findViewById(R.id.split);
				view.setVisibility(View.GONE);
			}

			// 设置第一个按钮
			if (!TextUtils.isEmpty(mPositiveButtonText)) {
				((Button) layout.findViewById(R.id.btn_positive)).setText(mPositiveButtonText);
				if (mPositiveButtonClickListener != null) {
					((Button) layout.findViewById(R.id.btn_positive)).setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							mPositiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
						}
					});
				}
			} else {
				layout.findViewById(R.id.btn_positive).setVisibility(View.GONE);
			}

			// 设置第二个按钮
			if (!TextUtils.isEmpty(mNegativeButtonText)) {
				((Button) layout.findViewById(R.id.btn_negative)).setText(mNegativeButtonText);
				if (mNegativeButtonClickListener != null) {
					((Button) layout.findViewById(R.id.btn_negative)).setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							mNegativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
						}
					});
				}
			} else {
				layout.findViewById(R.id.btn_negative).setVisibility(View.GONE);
			}

			// 设置消息内容
			if (!TextUtils.isEmpty(mMessage)) {
				((TextView) layout.findViewById(R.id.txt_messgae)).setText(mMessage);
			} else if (mContentView != null) {
				((LinearLayout) layout.findViewById(R.id.lay_content)).removeAllViews();
				LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,
						LayoutParams.WRAP_CONTENT);
				layoutParams.setMargins(30, 30, 30, 30);
				((LinearLayout) layout.findViewById(R.id.lay_content)).addView(mContentView, layoutParams);
			}
			dialog.setContentView(layout);
			return dialog;
		}

	}

}
