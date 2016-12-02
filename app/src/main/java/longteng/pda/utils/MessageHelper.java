package longteng.pda.utils;

import android.content.Context;
import android.content.DialogInterface;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import longteng.pda.R;
import longteng.pda.widget.MessageDialog;

/**
 * 消息提示帮助类
 *
 * @author [huangjl | 2016-02-24]
 */
public class MessageHelper {
	/**
	 * Exception转具体异常内容String
	 *
	 * @param e
	 * @return
	 */
	public static String exception2String(Exception e) {
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		e.printStackTrace(printWriter);
		Throwable cause = e.getCause();
		while (cause != null) {
			cause.printStackTrace(printWriter);
			cause = cause.getCause();
		}
		printWriter.close();
		return writer.toString();
	}

	/**
	 * 确认提示框(只有一个按钮,无按钮操作事件)
	 *
	 * @param context
	 *            上下文
	 * @param content
	 *            提示内容
	 */
	public static void showMessage(Context context, String content) {
		MessageHelper.showMessage(context, content, null);
	}

	/**
	 * 确认提示框(只有一个按钮,有按钮操作事件)
	 *
	 * @param context
	 *            上下文
	 * @param content
	 *            提示内容
	 * @param listener
	 *            操作事件
	 */
	public static void showMessage(Context context, String content, DialogInterface.OnClickListener listener) {
		if (listener == null) {
			listener = new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					arg0.dismiss();
				}
			};
		}
		MessageDialog.Builder builder = new MessageDialog.Builder(context);
		builder.setMessage(content);
		builder.setNegativeButton(context.getString(R.string.dialog_btn_confirm), listener);
		builder.create().show();
	}

	/**
	 * 问题提示框(默认取消按钮，确认按钮)
	 *
	 * @param context
	 *            上下文
	 * @param content
	 *            提示内容
	 * @param positiveListener
	 *            取消按钮事件
	 * @param negativeListener
	 *            确认按钮事件
	 */
	public static void showQuestion(Context context, String content, DialogInterface.OnClickListener positiveListener,
									DialogInterface.OnClickListener negativeListener) {
		MessageHelper.showQuestion(context, content, context.getString(R.string.dialog_btn_cancel),
				context.getString(R.string.dialog_btn_confirm), positiveListener, negativeListener);
	}

	/**
	 * 问题提示框(自定义按钮内容)
	 *
	 * @param context
	 *            上下文
	 * @param content
	 *            提示内容
	 * @param positiveText
	 *            第一个按钮内容
	 * @param nagativeText
	 *            第二个按钮内容
	 * @param positiveListener
	 *            第一个按钮事件
	 * @param negativeListener
	 *            第二个按钮事件
	 */
	public static void showQuestion(Context context, String content, String positiveText, String nagativeText,
									DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener) {

		if (positiveListener == null) {
			positiveListener = new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					arg0.dismiss();
				}
			};
		}
		if (negativeListener == null) {
			negativeListener = new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					arg0.dismiss();
				}
			};
		}

		MessageDialog.Builder builder = new MessageDialog.Builder(context);
		builder.setMessage(content);
		builder.setPositiveButton(positiveText, positiveListener);
		builder.setNegativeButton(nagativeText, negativeListener);
		builder.create().show();
	}

}
