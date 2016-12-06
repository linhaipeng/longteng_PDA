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
import longteng.pda.activity.DownloadDataActivity;
import longteng.pda.activity.GenerateDataActivity;
import longteng.pda.activity.UpdateActivity;
import longteng.pda.service.PreferencesService;
import longteng.pda.utils.Utils;

/**
 * Created by Administrator on 2016/11/30 0030.
 */

public class FragmentPersonal extends Fragment implements View.OnClickListener{

    private TextView tv_title_name;
    private RelativeLayout record,generateData,downloadData;
    private Application application;
    private PreferencesService preferencesService;
    private Switch Lines,Manual;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_personal, container, false);
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
        tv_title_name.setText("个人中心");
        record = (RelativeLayout)getView().findViewById(R.id.record);
        record.setOnClickListener(this);
        generateData = (RelativeLayout)getView().findViewById(R.id.generateData);
        generateData.setOnClickListener(this);
        downloadData = (RelativeLayout)getView().findViewById(R.id.downloadData);
        downloadData.setOnClickListener(this);
        Lines = (Switch)getView().findViewById(R.id.switch_lines);
        Lines.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                preferencesService.saveLines(isChecked);
            }
        });
        Lines.setChecked(preferencesService.getLines());
        Manual = (Switch)getView().findViewById(R.id.switch_manual);
        Manual.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                preferencesService.saveManual(isChecked);
            }
        });
        Manual.setChecked(preferencesService.getManual());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.record:

                break;
            case R.id.generateData:
                Utils.gotoActivity(getActivity(), GenerateDataActivity.class,false,null);
                break;
            case R.id.downloadData:
                Utils.gotoActivity(getActivity(), DownloadDataActivity.class,false,null);
                break;
        }
    }
}
