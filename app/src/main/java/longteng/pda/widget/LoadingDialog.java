package longteng.pda.widget;

import android.app.ProgressDialog;
import android.content.Context;

import java.lang.reflect.Method;

/**
 * 自定义加载动画窗(目的在于控制加载窗不允许下拉任务栏)
 * @author [XIAO K | 20150924]
 */
public class LoadingDialog extends ProgressDialog {

	/**
	 * 上下文
	 */
	private Context mContext;

	/**
	 * 初始化加载动画窗
	 * @param context	上下文
	 */
	public LoadingDialog(Context context) {
		super(context);
		this.mContext = context;
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		//屏蔽下拉状态栏
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

}
