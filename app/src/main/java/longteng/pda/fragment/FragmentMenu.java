package longteng.pda.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import longteng.pda.R;
import longteng.pda.activity.TakeStockActivity;
import longteng.pda.utils.Utils;

/**
 * Created by Administrator on 2016/11/30 0030.
 */

public class FragmentMenu extends Fragment implements View.OnClickListener{

    private TextView tv_title_name;
    private LinearLayout menu_take_stock,menu_sales_volume,menu_order_put,menu_copy_sheet,menu_quick_take_stock,
          menu_tag_print,menu_inventory,menu_no_order_put,menu_replenishment;
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
            init();
        }
        public void init(){
            menu_take_stock = (LinearLayout) getActivity().findViewById(R.id.menu_take_stock);
            menu_take_stock.setOnClickListener(this);
            menu_sales_volume = (LinearLayout) getActivity().findViewById(R.id.menu_sales_volume);
            menu_sales_volume.setOnClickListener(this);
            menu_order_put = (LinearLayout) getActivity().findViewById(R.id.menu_order_put);
            menu_order_put.setOnClickListener(this);
            menu_copy_sheet = (LinearLayout) getActivity().findViewById(R.id.menu_copy_sheet);
            menu_copy_sheet.setOnClickListener(this);
            menu_quick_take_stock = (LinearLayout) getActivity().findViewById(R.id.menu_quick_take_stock);
            menu_quick_take_stock.setOnClickListener(this);
            menu_tag_print = (LinearLayout) getActivity().findViewById(R.id.menu_tag_print);
            menu_tag_print.setOnClickListener(this);
            menu_inventory = (LinearLayout) getActivity().findViewById(R.id.menu_inventory);
            menu_inventory.setOnClickListener(this);
            menu_no_order_put = (LinearLayout) getActivity().findViewById(R.id.menu_no_order_put);
            menu_no_order_put.setOnClickListener(this);
            menu_replenishment = (LinearLayout) getActivity().findViewById(R.id.menu_replenishment);
            menu_replenishment.setOnClickListener(this);
        }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.menu_take_stock:
                Utils.gotoActivity(getActivity(), TakeStockActivity.class,false,null);
                break;
            case R.id.menu_sales_volume:
                break;
            case R.id.menu_order_put:
                break;
            case R.id.menu_copy_sheet:
                break;
            case R.id.menu_quick_take_stock:
                break;
            case R.id.menu_inventory:
                break;
            case R.id.menu_no_order_put:
                break;
            case R.id.menu_tag_print:
                break;
            case R.id.menu_replenishment:
                break;
        }
    }
}
