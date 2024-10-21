package com.drake.logcat.sample.ui.fragment;

import android.annotation.SuppressLint;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.drake.engine.base.EngineFragment;
import com.drake.logcat.LogCat;
import com.drake.logcat.sample.R;
import com.drake.logcat.sample.databinding.FragmentJavaLogBinding;

public class LogJavaFragment extends EngineFragment<FragmentJavaLogBinding> {

    public LogJavaFragment() {
        super(R.layout.fragment_java_log);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_log, menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_verbose: {
                LogCat.v("Verbose");
                break;
            }
            case R.id.menu_debug: {
                LogCat.d("Debug");
                break;
            }
            case R.id.menu_info: {
                LogCat.i("Info");
                break;
            }
            case R.id.menu_warn: {
                LogCat.w("Warn");
                break;
            }
            case R.id.menu_error: {
                LogCat.e("Error");
                break;
            }
            case R.id.menu_wtf: {
                LogCat.wtf("Assert");
                break;
            }
            case R.id.menu_json: {
                LogCat.json(getString(R.string.json));
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
