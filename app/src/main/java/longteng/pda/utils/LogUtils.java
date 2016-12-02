package longteng.pda.utils;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import longteng.pda.common.CommonHelper;

/**
 * 日志辅助类
 *
 * @ClassName: LogHelper
 * @author hgf
 * @date 2013-12-2 下午11:04:54
 */
public class LogUtils {

	public enum Log_lv {
		E(1), W(2), D(3), I(4), V(5);
		private Log_lv(int i) {
		}

	}

	/**
	 * 输入日志类型 i: INFO log ; e: INFO log ; w: WARN log ; d: DEBUG log ; v:
	 * VERBOSE log
	 */
	@SuppressWarnings("unused")
	private static char LOG_TYPE = 'v';

	/**
	 * 日志文件总开关
	 */
	public static Boolean LOG_SWITCH = true;

	/**
	 * 日志写入文件开关
	 */
	public static Boolean LOG_WRITE_TO_FILE = true;

	/**
	 * sd卡中日志文件的最多保存天数
	 */
	private static int SDCARD_LOG_FILE_SAVE_DAYS = 7;

	/**
	 * 本类输出的日志文件名称
	 */
	private static String LOGFILENAME = "Log.txt";

	/**
	 * 日志文件在sdcard中的路径
	 */
	private static String getLogPath() {
		return Environment.getExternalStorageDirectory().getPath() + "/" + CommonHelper.AppFolder + "/log";
	}

	/**
	 * 接口日志文件在sdcard中的路径
	 */
	private static String getHttpLogPath() {
		return Environment.getExternalStorageDirectory().getPath() + "/" + CommonHelper.AppFolder + "/Logcat";
	}

	/**
	 * 日志的输出格式
	 */
	@SuppressLint("SimpleDateFormat")
	private static SimpleDateFormat LogSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static SimpleDateFormat httpfile = new SimpleDateFormat("yyyyMMdd");

	/**
	 * 日志文件格式
	 */
	@SuppressLint("SimpleDateFormat")
	private static SimpleDateFormat logfile = new SimpleDateFormat("yyyy-MM-dd");

	public static void w(String tag, String text) {
		log(tag, text, Log_lv.W);
	}

	public static void e(String tag, String text) {
		log(tag, text, Log_lv.E);
	}

	public static void d(String tag, String text) {
		log(tag, text, Log_lv.D);
	}

	public static void i(String tag, String text) {
		log(tag, text, Log_lv.I);
	}

	public static void v(String tag, String text) {
		log(tag, text, Log_lv.V);
	}

	/**
	 * 根据tag, msg和等级，输出日志
	 *
	 * @Title: log
	 * @param @param
	 *            tag tag标签
	 * @param @param
	 *            msg 日志信息
	 * @param @param
	 *            level 日志类型
	 * @return void 返回类型
	 * @author: hgf
	 * @version: 2013-12-2 下午11:08:55
	 */
	public static void log(String tag, String msg, Log_lv level) {
		if (LOG_SWITCH) {

			switch (level) {
				case V:
					Log.v(tag, msg);
					break;
				case D:
					Log.d(tag, msg);
					break;
				case E:
					Log.e(tag, msg);
					break;
				case I:
					Log.i(tag, msg);
					break;
				case W:
					Log.w(tag, msg);
					break;
				default:
					break;
			}

			if (LOG_WRITE_TO_FILE)
				writeLogtoFile(String.valueOf(level), tag, msg);
		}
	}

	/**
	 * 打开日志文件并写入日志
	 *
	 * @Title: writeLogtoFile
	 * @param @param
	 *            mylogtype
	 * @param @param
	 *            tag
	 * @param @param
	 *            text 设定文件
	 * @return void 返回类型
	 * @author: hgf
	 * @version: 2013-12-2 下午11:15:47
	 */
	private static void writeLogtoFile(String mylogtype, String tag, String text) {
		Date nowtime = new Date();
		String needWriteFiel = logfile.format(nowtime) + "_" + mylogtype + "_";
		String needWriteMessage = LogSdf.format(nowtime) + " " + mylogtype + " " + tag + "\r\n" + text + "\r\n";

		File file = new File(getLogPath(), needWriteFiel + LOGFILENAME);
		File dir = new File(getLogPath());

		try {
			if (!dir.exists()) {
				dir.mkdirs();
			}

			FileWriter filerWriter = new FileWriter(file, true);// 后面这个参数代表是不是要接上文件中原来的数据，不进行覆盖
			BufferedWriter bufWriter = new BufferedWriter(filerWriter);
			bufWriter.write(needWriteMessage);
			bufWriter.newLine();
			bufWriter.close();
			filerWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除超过有效期的日志文件
	 *
	 * @Title: delFile
	 * @return void 返回类型
	 * @author: hgf
	 * @version: 2013-12-2 下午11:55:03
	 */
	public static void delFile() {
		String needDelFiel = logfile.format(getDateBefore());
		String needDelHttpFile=httpfile.format(getDateBefore());
		File[] files = new File(getLogPath()).listFiles();
		File[] httpFiles=new File(getHttpLogPath()).listFiles();

		if (files != null && files.length > 0) {
			for (File file : files) {
				String filename = file.getName();
				String filedate = filename.substring(0, filename.indexOf("_"));
				if (DateLargeNow(filedate, needDelFiel, logfile)) {
					file.delete();
				}
			}
		}
		if (httpFiles != null && httpFiles.length > 0) {
			for (File file : httpFiles) {
				String filename = file.getName();
				String filedate = filename.substring(filename.indexOf("-")+1, filename.indexOf("."));
				if (DateLargeNow(filedate, needDelHttpFile, httpfile)) {
					file.delete();
				}
			}
		}
	}

	public static Boolean DateLargeNow(String strdate, String deldate, SimpleDateFormat simple) {
		Calendar c1 = Calendar.getInstance();
		Calendar cnow = Calendar.getInstance();
		try {
			Date date = simple.parse(strdate);
			Date nowtime = simple.parse(deldate);
			c1.setTime(date);
			cnow.setTime(nowtime);
			int result = c1.compareTo(cnow);
			if (result == 0) {
				return false;
			} else if (result < 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * 得到现在时间前的几天日期，用来得到需要删除的日志文件名
	 */
	private static Date getDateBefore() {
		Date nowtime = new Date();
		Calendar now = Calendar.getInstance();
		now.setTime(nowtime);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - SDCARD_LOG_FILE_SAVE_DAYS);
		return now.getTime();
	}

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

}