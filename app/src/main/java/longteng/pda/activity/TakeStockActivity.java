package longteng.pda.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import longteng.pda.R;
import longteng.pda.utils.Utils;

public class TakeStockActivity extends BaseActivity {
    private TextView tv_title_name;
    private ImageView iv_title_left;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_stock);
        tv_title_name = (TextView) findViewById(R.id.tv_title_name);
        tv_title_name.setText("盘点实盘");
        iv_title_left = (ImageView)findViewById(R.id.iv_title_left);
        iv_title_left.setVisibility(View.VISIBLE);
        iv_title_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.finishActivity(TakeStockActivity.this);
            }
        });
        findViewById(R.id.rl_bar).setBackground(getResources().getDrawable(R.color.menu_take_stock));
    }
}
