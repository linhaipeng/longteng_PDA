package longteng.pda;

/**
 * Created by Administrator on 2016/12/2 0002.
 */

import longteng.pda.service.PreferencesService;

public class Application extends android.app.Application{

    private static Application Application;
    private PreferencesService PreferencesService = null; //保存系统设置值
    @Override
    public void onCreate() {
        Application = this;
        if (PreferencesService == null) {
            PreferencesService = new PreferencesService(this);
        }
    }


    public PreferencesService getPreferencesService() {
        return PreferencesService;
    }

}
