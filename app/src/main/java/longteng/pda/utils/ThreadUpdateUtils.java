package longteng.pda.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

/**
 * UI线程更新工具类
 *
 * @author qinzhy
 * @date 2015年10月13日
 */
public class ThreadUpdateUtils {

    public static void threadUpdateUI(Context context, final ThreadUpdateUI threadUpdateUI) {

        if (((Activity) context).isFinishing()) return;

        if (Looper.myLooper() != Looper.getMainLooper()) {
            final Thread thread = Thread.currentThread();
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    threadUpdateUI.doUpdate();
                    thread.interrupt();
                }
            });
            try {
                thread.join();
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        } else {
            threadUpdateUI.doUpdate();
        }
    }


}
