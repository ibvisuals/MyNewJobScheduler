package com.ibvisuals.mynewjobscheduler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.KeyEventDispatcher;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    ToggleButton tg;
    JobScheduler myJob;
    JobInfo myJobInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tg = (ToggleButton)findViewById(R.id.myjobbutton);

        tg.setOnCheckedChangeListener(this);

        myJob = (JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked){
            JobInfo.Builder myJobBuilder = new JobInfo.Builder(1,new ComponentName(getPackageName(),MyNewJob.class.getName()))
                    .setRequiresCharging(true)
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                    .setRequiresDeviceIdle(false);

            myJobInfo = myJobBuilder.build();
            myJob.schedule(myJobInfo);

        }
        else {
            myJob.cancelAll();
        }

    }
}