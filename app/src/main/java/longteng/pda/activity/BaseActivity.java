package longteng.pda.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import longteng.pda.Application;
import longteng.pda.R;
import longteng.pda.common.CommonHelper;
import longteng.pda.service.PreferencesService;
import longteng.pda.utils.LogUtils;
import longteng.pda.utils.MessageHelper;
import longteng.pda.utils.ScanerUtils;
import longteng.pda.utils.ThreadUpdateUI;
import longteng.pda.utils.ThreadUpdateUtils;
import longteng.pda.utils.TipHelper;
import longteng.pda.widget.LoadingDialog;


public abstract class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    protected Activity mActivity;
    private LoadingDialog mLoadingDialog;

    private boolean mIsOpenScan = false; // 标识是否已打开扫描
    private Application application;
    private PreferencesService preferencesService;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mIsOpenScan)
            OpenScanner();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 锁定竖屏
        // 屏蔽键盘
        if (CommonHelper.Oninput) {
            this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        } else {
            this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

        }

        mContext = this;
        mActivity = this;
        application = (Application) getApplication();
        preferencesService = application.getPreferencesService();
        // 实例加载动画并设置属性
        this.mLoadingDialog = new LoadingDialog(this);
        this.mLoadingDialog.setCanceledOnTouchOutside(false);
        this.mLoadingDialog.setCancelable(false);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    @Override
    public void finish() {
        super.finish();
        closeKeyboard();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public void closeKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        // 得到InputMethodManager的实例
        if (imm.isActive()) {
            try {
                imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
            } catch (Exception e) {

            }
//            imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * 打开扫描
     */
    public void OpenScanner() {
        try {
            ScanerUtils.Instance(this, scb);
            ScanerUtils.OpenScan();
        } catch (ScanerUtils.ScanerException e) {

            return;
        }
    }

    /**
     * 开启扫描
     */
    protected void closeSacnner() {
        ScanerUtils.CloseScan();
    }

    private ScanerUtils.ScanCallBack scb = new ScanerUtils.ScanCallBack() {

        @Override
        public void SacnResults(String barcode) {
            barcode = barcode.trim();
            scanBarcode(barcode);

        }
    };

    /**
     * 操作扫描
     *
     * @param obj 扫描内容
     */
    public void scanBarcode(String obj) {

        TipHelper.playSound(this, "beep.wav", R.raw.beep);
        TipHelper.Vibrate(this, 100);
    }

    /**
     * 线程更新UI
     *
     * @param threadUpdateUI UI操作内容
     */
    public void threadUpdateUI(ThreadUpdateUI threadUpdateUI) {
        ThreadUpdateUtils.threadUpdateUI(mContext, threadUpdateUI);
    }

    /**
     * 关闭处理框
     */
    public void dismissProgressDialog() {
        ThreadUpdateUtils.threadUpdateUI(mContext, new ThreadUpdateUI() {
            @Override
            public void doUpdate() {
                if (mLoadingDialog != null) {
                    mLoadingDialog.dismiss();
                }
            }
        });
    }

    /**
     * 弹出加载动画
     *
     * @param msg 内容
     */
    protected void showLoadingDialog(final String msg) {
        threadUpdateUI(new ThreadUpdateUI() {

            @Override
            public void doUpdate() {

                if (mLoadingDialog == null)
                    mLoadingDialog = new LoadingDialog(mContext);
                mLoadingDialog.setTitle("系统提示");
                mLoadingDialog.setMessage(msg);
                if (!mLoadingDialog.isShowing())
                    mLoadingDialog.show();

            }
        });
    }

    /**
     * 操作失败
     *
     * @param e 提示内容
     */
    public void showErrorDialog(Exception e) {
        if (!TextUtils.isEmpty(e.getMessage())) {
            LogUtils.log(getClass().getSimpleName(), e.getMessage(), LogUtils.Log_lv.E);
        } else {
            LogUtils.log(getClass().getSimpleName(), e.toString(), LogUtils.Log_lv.E);

        }
        showErrorDialog(e.getMessage());

    }

    public void showErrorDialog(Exception e, String msg) {
        dismissProgressDialog();
        if (!TextUtils.isEmpty(e.getMessage())) {
            LogUtils.log(getClass().getSimpleName(), e.getMessage(), LogUtils.Log_lv.E);
        } else {
            LogUtils.log(getClass().getSimpleName(), e.toString(), LogUtils.Log_lv.E);

        }
        showErrorDialog(msg);
    }

    public void showErrorDialog(final String msg) {
        threadUpdateUI(new ThreadUpdateUI() {

            @Override
            public void doUpdate() {
                dismissProgressDialog();
                TipHelper.Vibrate(mActivity, 100);
                MessageHelper.showMessage(mActivity, msg);

            }
        });

    }

    /**
     * 弹出气泡提示错误信息
     *
     * @param e
     */
    protected void showExceptionToast(final Exception e) {
        threadUpdateUI(new ThreadUpdateUI() {

            @Override
            public void doUpdate() {
                mLoadingDialog.dismiss();
                if (!TextUtils.isEmpty(e.getMessage())) {
                    LogUtils.log(getClass().getSimpleName(), e.getMessage(), LogUtils.Log_lv.E);
                } else {
                    LogUtils.log(getClass().getSimpleName(), e.toString(), LogUtils.Log_lv.E);

                }
                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }



}
