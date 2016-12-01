package longteng.pda.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import longteng.pda.R;

/**
 * Created by Administrator on 2016/11/30 0030.
 */

public class FragmentMenu extends Fragment {

    private TextView tv_title_name;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_menu, container, false);
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            tv_title_name = (TextView) getView().findViewById(R.id.tv_title_name);
            tv_title_name.setText("PDA盘点系统");
        }
}
