package longteng.pda.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonHelper {
    public static final String IP = "IP";
    public static final String PORT = "PORT";
    public static final String DBNAME = "DB";
    public static final String DBUSER = "USER";
    public static final String DBPASS = "PASS";

    public static int fenye = 0;

    /**
     * PDA编号、备注
     */
//    public static S_Pda s_pda = null;

    /**
     * 启用虚拟键盘
     */
    public static boolean Oninput = true;

    /**
     * Server 地址
     */
    public static String SERVERCONNSTR_SERVER = "192.168.2.102";
    /**
     * Server端口
     */
    public static String SERVERCONNSTR_PORT = "1433";
    /**
     * Server 数据库名称
     */
    public static String SERVERCONNSTR_DATABASE = "lydata";
    /**
     * Server 登录id
     */
    public static String SERVERCONNSTR_UID = "hfuser";
    /**
     * Server 登录密码
     */
    public static String SERVERCONNSTR_PWD = "hfuser";
    /**
     * 程序目录
     */
    public static String AppFolder = "";

    /**
     * 判断当前设备网络状态
     *
     * @param context 上下文
     * @return 网络状态
     */
    public static boolean isConnectNetwork(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null) {
            return false;
        }
        NetworkInfo networkinfo = manager.getActiveNetworkInfo();
        if (networkinfo == null || !networkinfo.isAvailable()) {
            return false;
        }
        return true;
    }

    public static long dateDiff(Date startTime, Date endTime, String format) {
//按照传入的格式生成一个simpledateformate对象
//        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
        long nh = 1000 * 60 * 60;//一小时的毫秒数
        long nm = 1000 * 60;//一分钟的毫秒数
        long ns = 1000;//一秒钟的毫秒数
        long diff;
//获得两个时间的毫秒时间差异
        diff = endTime.getTime() - startTime.getTime();
        long day = diff / nd;//计算差多少天
        long hour = diff % nd / nh;//计算差多少小时
        long min = diff % nd % nh / nm;//计算差多少分钟
        long sec = diff % nd % nh % nm / ns;//计算差多少秒
//输出结果
        return min;
    }

    /**
     * 将日期转化为日期字符串。失败返回null。
     *
     * @param date 日期
     * @return 日期字符串
     */
    public static String DateToString(Date date) {
        String dateString = null;
        if (date != null) {
            try {
                dateString = new SimpleDateFormat("HH:mm:ss").format(date);
            } catch (Exception e) {
            }
        }
        return dateString;
    }


}
