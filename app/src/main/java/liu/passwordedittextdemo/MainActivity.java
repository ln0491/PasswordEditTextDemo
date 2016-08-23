package liu.passwordedittextdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import liu.passwordedittextdemo.view.PasswordEditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "vivi";
    private PasswordEditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }



    private void initView() {

        mPasswordEditText = (PasswordEditText) findViewById(R.id.passwordEt);
    }

    private void initListener() {

        mPasswordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(TAG, "beforeTextChanged: "+ s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "onTextChanged: "+ s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

                Log.d(TAG, "afterTextChanged: "+ s.toString());
            }
        });
    }
}
