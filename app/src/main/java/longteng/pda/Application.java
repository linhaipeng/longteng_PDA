package longteng.pda;

/**
 * Created by Administrator on 2016/12/2 0002.
 */

import longteng.pda.db.AppDatabaseManager;
import longteng.pda.db.DatabaseHelper;
import longteng.pda.db.ServerDatabaseManager;
import longteng.pda.service.PreferencesService;

public class Application extends android.app.Application{

    private static Application Application;
    private static AppDatabaseManager appDatabaseManager; // 程序数据库
    private static ServerDatabaseManager serverDatabaseManager; // 服务器数据库
    private PreferencesService PreferencesService = null; //保存系统设置值
    @Override
    public void onCreate() {
        Application = this;
        if (PreferencesService == null) {
            PreferencesService = new PreferencesService(this);
        }
        DatabaseHelper db = new DatabaseHelper(this);
        appDatabaseManager = new AppDatabaseManager();
        appDatabaseManager.getInstance(db);
        serverDatabaseManager = new ServerDatabaseManager();
    }

    public PreferencesService getPreferencesService() {
        return PreferencesService;
    }

    public AppDatabaseManager getAppDatabaseManager() {
        return appDatabaseManager;
    }

    public static ServerDatabaseManager getServerDatabaseManager() {
        return serverDatabaseManager;
    }
}
