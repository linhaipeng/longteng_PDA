package longteng.pda.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import longteng.pda.Application;
import longteng.pda.R;
import longteng.pda.db.AppDatabaseManager;
import longteng.pda.service.PreferencesService;
import longteng.pda.vo.Order;

/**
 * Created by Administrator on 2016/11/30 0030.
 */

public class FragmentOrder extends Fragment {
    private TextView tv_title_name;
    private static ListView listViewData;
    private SimpleAdapter adapter;
    private Application application;
    private AppDatabaseManager appDatabaseManager;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_order, container, false);
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            tv_title_name = (TextView) getView().findViewById(R.id.tv_title_name);
            tv_title_name.setText("单据");
            listViewData = (ListView)getView().findViewById(R.id.list_order);
            application = (Application) getActivity().getApplication();
            appDatabaseManager = application.getAppDatabaseManager();
            listViewData.setOnItemClickListener(new OnItemClickListener());
            listViewData.setOnItemLongClickListener(new OnItemLongClickListener());
            updateOrderList();
//            for (int i = 0; i<100;i++){
//                Order order = new Order();
//                order.setAmendTime("2011111111");
//                order.setCreateTime("200000000");
//                order.setItems("21");
//                order.setOrderNO("012345");
//                order.setSUM("100");
//                order.setOrderName("名称"+i);
//                appDatabaseManager.addOrder(order);
//            }
        }


    /**
     *更新表格
     */
    public void updateOrderList() {
        ArrayList<Map<String, Object>> listMap =   appDatabaseManager.getOrderList();
        adapter = new SimpleAdapter(getActivity(),listMap, R.layout.listview_order,
                new String[] { "OrderName", "CreateTime","Items" }, new int[] { R.id.tv_name,
                R.id.tv_time,R.id.tv_items });
        listViewData.setAdapter(adapter);
    }

    /**
     * 点击事件
     */
 public class OnItemClickListener implements AdapterView.OnItemClickListener{

     @Override
     public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


     }
 }

    /**
     * 长按事件
     */
 public class OnItemLongClickListener implements AdapterView.OnItemLongClickListener{

     @Override
     public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

         return true;
     }
 }


}
