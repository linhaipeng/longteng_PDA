package longteng.pda.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import longteng.pda.R;
import longteng.pda.utils.Utils;


public class ContactActivity extends BaseActivity {
    private TextView tv_title_name;
    private ImageView iv_title_left;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        tv_title_name = (TextView) findViewById(R.id.tv_title_name);
        tv_title_name.setText("联系我们");
        iv_title_left = (ImageView)findViewById(R.id.iv_title_left);
        iv_title_left.setVisibility(View.VISIBLE);
        iv_title_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.finishActivity(ContactActivity.this);
            }
        });
    }

}
