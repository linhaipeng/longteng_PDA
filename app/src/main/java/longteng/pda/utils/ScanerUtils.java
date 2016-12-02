package longteng.pda.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;


import com.android.barcodescandemo.ScannerInerface;

import java.util.List;

public class ScanerUtils {

	private static boolean canScan = false;
	private ScannerInerface Controll;
	private BroadcastReceiver mReceiver;
	private IntentFilter mFilter;
	private Context context;
	private ScanCallBack scanCallBack;
	private static ScanerUtils instance;

	public static ScanerUtils Instance(Context context) throws ScanerException {
		return Instance(context, null);
	}

	public static ScanerUtils Instance(Context context, final ScanCallBack scanCallBack) throws ScanerException {
		if (instance == null) {
			instance = new ScanerUtils(context, scanCallBack);
		} else {
			instance.scanCallBack = scanCallBack;
		}
		return instance;
	}

	private ScanerUtils(Context mContext, final ScanCallBack callBack) {
		this.context = mContext.getApplicationContext();
		this.scanCallBack = callBack;

		Controll = new ScannerInerface(context);
		mFilter = new IntentFilter("android.intent.action.SCANRESULT");
		mReceiver = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				// 此处获取扫描结果信息
				final String scanResult = intent.getStringExtra("value");
				if (scanCallBack != null) {
					scanCallBack.SacnResults(scanResult);
				}
			}
		};

	}

	public class ScanerException extends Exception {

		private static final long serialVersionUID = 1L;

		public ScanerException() {
			super("Can't open scaner");
		}

		public ScanerException(String msg) {
			super(msg);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#finalize()
	 */
	@Override
	protected void finalize() throws Throwable {
		mReceiver = null;
		mFilter = null;
		super.finalize();
	}

	private void Open() throws ScanerException {

		// 判断iscan是否安装及运行
		String iscanPackageName = "com.android.auto.iscan";
		if (!isIscanInstall(iscanPackageName)) {
			throw new ScanerException("系统未安装iscan工具，请安装！");
		} else if (!appIsBackgroundRunning(context, iscanPackageName)) {

			// iScan未启动，则需要打开
			Intent LaunchIntent = context.getPackageManager().getLaunchIntentForPackage(iscanPackageName);
			context.startActivity(LaunchIntent);
		}
		Controll.setOutputMode(1);
		Controll.open();
		if (!canScan) {
			context.registerReceiver(mReceiver, mFilter);
			canScan = true;
		}

	}

	private void Close() {
		if (canScan) {
			try {
				canScan = false;
				context.unregisterReceiver(mReceiver);
				Controll.setOutputMode(0);
				scanCallBack = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void Destroy() {
		instance = null;
	}

	private void setCallBack(ScanCallBack scanCallBack) {
		this.scanCallBack = scanCallBack;
	}

	public void scan_start() {
		Controll.scan_start();
	}

	public void scan_stop() {
		Controll.scan_stop();
	}

	/**
	 * 停止扫描
	 */
	public static void StopScan() {
		if (instance != null)
			instance.scan_stop();
	}

	/**
	 * 开始扫描
	 */
	public static void StartScan() {
		if (instance != null)
			instance.scan_start();
	}

	/**
	 * 设置扫描结果(回调)
	 *
	 * @param scanCallBack
	 */
	public static void setScanCallBack(ScanCallBack scanCallBack) {
		if (instance != null)
			instance.setCallBack(scanCallBack);
	}

	/**
	 * 开启扫描
	 *
	 * @throws ScanerException
	 */
	public static void OpenScan() throws ScanerException {
		if (instance != null)
			instance.Open();
	}

	/**
	 * 关闭扫描
	 */
	public static void CloseScan() {
		if (instance != null)
			instance.Close();
	}

	/**
	 * 销毁
	 */
	public static void DestroyScan() {
		if (instance != null)
			instance.Destroy();
	}

	// 判断iScan是否已安装
	private boolean isIscanInstall(String packageName) {
		boolean bool = false;
		List<PackageInfo> packages = context.getPackageManager().getInstalledPackages(0);
		for (int i = 0; i < packages.size(); i++) {
			PackageInfo packageinfo = packages.get(i);
			// 判断iscan是否安装
			if (packageinfo.packageName.equals(packageName)) {
				bool = true;
				break;
			}
		}
		return bool;
	}

	// 判断iScan是否已运行
	public boolean appIsBackgroundRunning(Context ctx, String packageName) throws ScanerException {
		ActivityManager am = (ActivityManager) ctx.getSystemService(Activity.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();
		if (runningAppProcesses != null) {
			for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
				if (runningAppProcessInfo.processName.startsWith(packageName)) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean canScan() {
		return canScan;
	}

	public interface ScanCallBack {
		void SacnResults(String barcode);
	}

}
