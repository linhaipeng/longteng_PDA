package longteng.pda.utils;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Vibrator;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 *
 * @ClassName: TipHelper
 * @Description: 自定义震动
 * @author hgf
 * @date 2013-8-20 下午5:26:02
 */
public class TipHelper {

	protected static MediaPlayer mediaPlayer = null;

	/**
	 *
	 * @Title: Vibrate
	 * @Description: 在时间范围内震动
	 * @param @param activity	调用该方法的Activity实例
	 * @param @param milliseconds    震动的时长，单位是毫秒
	 * @return void    返回类型
	 * @author: hgf
	 * @version: 2013-3-25 下午11:45:01
	 */
	public static void Vibrate(final Activity activity, long milliseconds) {
		Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
		vib.vibrate(milliseconds);
	}

	/**
	 *
	 * @Title: Vibrate
	 * @Description: 自定义震动模式
	 * @param @param activity	调用该方法的Activity实例
	 * @param @param pattern	自定义震动模式 。数组中数字的含义依次是静止的时长，震动时长，静止时长，震动时长。。。时长的单位是毫秒
	 * @param @param isRepeat   是否反复震动，如果是true，反复震动，如果是false，只震动一次
	 * @return void    返回类型
	 * @author: hgf
	 * @version: 2013-3-25 下午11:46:28
	 */
	public static void Vibrate(final Activity activity, long[] pattern, boolean isRepeat) {
		Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
		vib.vibrate(pattern, isRepeat ? 1 : -1);
	}


	/**
	 * 播放声音
	 * @param context
	 */
	public  static void  play_sound(final Context context, int id){
		try {
			if (mediaPlayer == null)
				mediaPlayer = MediaPlayer.create(context, id);
			mediaPlayer.stop();

			mediaPlayer.prepare();

			mediaPlayer.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String lastsoundpath="";

	/**
	 *
	 * @Title: playSound
	 * @Description: 播放系统音频文件
	 * @param @param activity
	 * @param @param soundPath
	 * @param @param rid    设定文件
	 * @return void    返回类型
	 * @author: hgf
	 * @version: 2013-6-6 下午6:23:11
	 */
	public static void playSound(Activity activity, String soundPath, int rid) {
		try {
			File sdpath ;
			if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
				sdpath = Environment.getExternalStorageDirectory();
				File sound = new File(sdpath, soundPath);
				if (!sound.exists()) {
					FileOutputStream out = new FileOutputStream(sound);
					byte[] bytes = new byte[10 * 1024];
					InputStream is =  activity.getResources().openRawResource(rid);
					int n = 0;
					while ((n = is.read(bytes)) > 0) {
						out.write(bytes, 0, n);
					}
				}
				if (lastsoundpath.equals(sound) && mediaPlayer!=null) {
					mediaPlayer.start();
				}else {
					lastsoundpath = soundPath;
					mediaPlayer = MediaPlayer.create(activity.getApplicationContext(), Uri.fromFile(sound));
					mediaPlayer.start();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			Log.println(1, "", e.getMessage());
		}
	}
}
