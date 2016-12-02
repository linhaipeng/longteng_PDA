package longteng.pda.service;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/12/2 0002.
 */

public class PreferencesService {
    private SharedPreferences preferences;
    public PreferencesService(Context context) {
        preferences = context.getSharedPreferences("system", Context.MODE_PRIVATE);
    }
    //声音设置
    public void saveSound(boolean Sound) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("Power", Sound);
        editor.commit();
    }
    public boolean getSound() {
        return preferences.getBoolean("Power", true);
    }

    //启用离线
    public void saveOfflineData (boolean OfflineData) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("OfflineData", OfflineData);
        editor.commit();
    }
    public boolean getOfflineData() {
        return preferences.getBoolean("OfflineData", true);
    }

    //一码多行
    public void saveLines(boolean Lines) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("Lines", Lines);
        editor.commit();
    }
    public boolean getLines() {
        return preferences.getBoolean("Lines", true);
    }

    //手工输入
    public void saveManual(boolean Manual) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("Manual", Manual);
        editor.commit();
    }
    public boolean getManual() {
        return preferences.getBoolean("Manual", true);
    }


}
