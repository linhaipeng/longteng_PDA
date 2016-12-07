package longteng.pda.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import longteng.pda.R;
import longteng.pda.utils.Utils;

public class ScannerActivity extends AppCompatActivity {
    private TextView tv_title_name;
    private ImageView iv_title_left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        init();
    }

    private void init() {
        tv_title_name = (TextView) findViewById(R.id.tv_title_name);
        tv_title_name.setText("盘点实盘");
        iv_title_left = (ImageView)findViewById(R.id.iv_title_left);
        iv_title_left.setVisibility(View.VISIBLE);
        iv_title_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.finishActivity(ScannerActivity.this);
            }
        });
      //  receipts = (Spinner) findViewById(R.id.sp_list);
    }

}
