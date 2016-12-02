package longteng.pda.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import longteng.pda.Application;
import longteng.pda.R;
import longteng.pda.activity.AboutActivity;
import longteng.pda.activity.ContactActivity;
import longteng.pda.service.PreferencesService;
import longteng.pda.utils.Utils;

/**
 * Created by Administrator on 2016/11/30 0030.
 */

public class FragmentSettings extends Fragment implements View.OnClickListener {

    private TextView tv_title_name;
    private RelativeLayout help,problem,call,about,update;
    private Application application;
    private PreferencesService preferencesService;
    private Switch Sound,OfflineData;

    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_settings, container, false);
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            application = (Application) getActivity().getApplication();
            preferencesService = application.getPreferencesService();
            init();

        }

    public void init() {
        tv_title_name = (TextView) getView().findViewById(R.id.tv_title_name);
        tv_title_name.setText("设置");
        help = (RelativeLayout)getView().findViewById(R.id.help);
        help.setOnClickListener(this);
        problem = (RelativeLayout)getView().findViewById(R.id.problem);
        problem.setOnClickListener(this);
        call = (RelativeLayout)getView().findViewById(R.id.call);
        call.setOnClickListener(this);
        about = (RelativeLayout)getView().findViewById(R.id.about);
        about.setOnClickListener(this);
        update = (RelativeLayout)getView().findViewById(R.id.update);
        update.setOnClickListener(this);
        Sound = (Switch)getView().findViewById(R.id.switch_sound);
        Sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    preferencesService.saveSound(isChecked);
            }
        });
        OfflineData.setChecked(preferencesService.getSound());
        OfflineData = (Switch)getView().findViewById(R.id.switch_db);
        OfflineData.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                preferencesService.saveOfflineData(isChecked);
            }
        });
        OfflineData.setChecked(preferencesService.getSound());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.help:
                break;
            case R.id.problem:
                break;
            case R.id.call:
                Utils.gotoActivity(getActivity(), ContactActivity.class,false,null);
                break;
            case R.id.about:
                Utils.gotoActivity(getActivity(), AboutActivity.class,false,null);
                break;
            case R.id.update:
                break;

        }
    }
}
