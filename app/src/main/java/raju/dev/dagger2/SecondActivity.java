package raju.dev.dagger2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondActivity extends AppCompatActivity {

    @Inject  //Uncommenting This line will not Inject DI.. Please Try this case..
            SharedPreferences mSharedPreferences;//Sit back relax

    @Inject //DI
            ViewsApiEnd mViewsApiEnd;

    @BindView(R.id.tv_launchCounter)
    TextView tv_font;
    @BindView(R.id.response)
    TextView tv_response;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Inject  //Uncommenting This line will not Inject DI.. Please Try this case..
            SharedPreferences mSharedPreferences2;
    @Inject //DI
            ViewsApiEnd mViewsApiEnd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ((MyApp) getApplication()).getGitHubComponent().inject(this);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        updateCounter();

        logInstances();
    }

    private String updateCounter() {
        if (mSharedPreferences != null) {
            tv_response.setText("SharedPreferences Dependency Injection Worked!");
        } else {
            tv_response.setText(getString(R.string.sfnotworking));
            tv_font.setText("Oops! Something went wrong..");
            return getString(R.string.sfnotworking);
        }
        int countTemp = mSharedPreferences.getInt("launch_counter", 0);
        int counter = countTemp + 1;

        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt("launch_counter", counter);
        editor.commit();

        tv_font.setText("You've Got " + counter + " views");

        return "SharedPreferences Dependency Injection Worked!";
    }

    void logInstances() {
        Log.i("isSameInstance2:", "mSharedPreferences" + mSharedPreferences);
        Log.i("isSameInstance2:", "mSharedPreferences2" + mSharedPreferences2);
        Log.i("isSameInstance2:", "mViewsApiEnd" + mViewsApiEnd);
        Log.i("isSameInstance2:", "mViewsApiEnd2" + mViewsApiEnd2);
    }

}
