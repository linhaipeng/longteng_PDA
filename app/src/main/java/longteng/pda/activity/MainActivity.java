package longteng.pda.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import longteng.pda.R;
import longteng.pda.view.NavbarRadioButton;

public class MainActivity extends BaseActivity {
    private Fragment[] mFragments;
    private NavbarRadioButton nav_menu, nav_receipts, nav_personal, nav_settings;
    private RadioGroup nav_bar;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Color1SwitchStyle);
        setContentView(R.layout.activity_main);
        mFragments = new Fragment[4];
        fragmentManager = getSupportFragmentManager();
        mFragments[0] = fragmentManager.findFragmentById(R.id.fragement_menu);
        mFragments[1] = fragmentManager.findFragmentById(R.id.fragement_receipts);
        mFragments[2] = fragmentManager.findFragmentById(R.id.fragement_personal);
        mFragments[3] = fragmentManager.findFragmentById(R.id.fragement_settings);
        nav_bar = (RadioGroup) findViewById(R.id.nav_bar);
        fragmentTransaction = fragmentManager.beginTransaction()
                .hide(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]).hide(mFragments[3]);
        fragmentTransaction.show(mFragments[0]).commit();
        setFragmentIndicator();

    }



    private void setFragmentIndicator() {

        nav_menu = (NavbarRadioButton) findViewById(R.id.nav_menu);
        nav_receipts = (NavbarRadioButton) findViewById(R.id.nav_receipts);
        nav_personal = (NavbarRadioButton) findViewById(R.id.nav_personal);
        nav_settings = (NavbarRadioButton) findViewById(R.id.nav_settings);
        nav_menu.setClickable(true);
        nav_bar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                fragmentTransaction = fragmentManager.beginTransaction()
                        .hide(mFragments[0]).hide(mFragments[1])
                        .hide(mFragments[2]).hide(mFragments[3]);
                nav_menu.setTextColor(Color.parseColor("#8a8a8a"));
                nav_receipts.setTextColor(Color.parseColor("#8a8a8a"));
                nav_personal.setTextColor(Color.parseColor("#8a8a8a"));
                nav_settings.setTextColor(Color.parseColor("#8a8a8a"));
                Log.i(TAG,"切换页面");
                switch (checkedId) {
                    case R.id.nav_menu:
                        nav_menu.setTextColor(Color.parseColor("#03a9f4"));
                        fragmentTransaction.show(mFragments[0]).commit();
                        break;
                    case R.id.nav_receipts:
                        nav_receipts.setTextColor(Color.parseColor("#03a9f4"));
                        fragmentTransaction.show(mFragments[1]).commit();
                        break;
                    case R.id.nav_personal:
                        nav_personal.setTextColor(Color.parseColor("#03a9f4"));
                        fragmentTransaction.show(mFragments[2]).commit();
                        break;
                    case R.id.nav_settings:
                        nav_settings.setTextColor(Color.parseColor("#03a9f4"));
                        fragmentTransaction.show(mFragments[3]).commit();
                        break;
                }
            }
        });
    }


}
