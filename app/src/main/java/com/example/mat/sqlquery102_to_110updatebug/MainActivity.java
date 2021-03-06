package com.example.mat.sqlquery102_to_110updatebug;

import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.mat.sqlquery102_to_110updatebug.model.DataStorage;
import com.example.mat.sqlquery102_to_110updatebug.model.UserEntity;
import com.example.mat.sqlquery102_to_110updatebug.model.UserGroupEntity;
import com.example.mat.sqlquery102_to_110updatebug.service.Generator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.Unbinder;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.txt_log)
    TextView txtView;
    @BindView(R.id.tgl_cipher)
    ToggleButton toggleButton;

    Unbinder unbinder;
    DataStorage dataStorage;
    Generator generator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        updateDataStorage(toggleButton.isChecked());
        generator = new Generator();
        Timber.plant(new Timber.DebugTree());
    }

    private void updateDataStorage(boolean checked) {
        if (dataStorage != null) {
            dataStorage.close();
            dataStorage = null;
        }
        dataStorage = new DataStorage(this, checked);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dataStorage != null) {
            dataStorage.close();
        }
        unbinder.unbind();
    }

    @OnCheckedChanged(R.id.tgl_cipher)
    public void onCipherToggle(CompoundButton button, boolean checked) {
        updateDataStorage(checked);
    }

    @OnClick(R.id.btn_store_group)
    public void onStoreGroup() {
        // generate userGroup
        UserGroupEntity group = generator.generateGroup(10);
        Timber.i("Generated userGroup %s with user count %d", group.name, group.userList.size());
        dataStorage.storeGroup(group);
    }

    @OnClick(R.id.btn_fetch_users)
    public void getAllUsers() {
        List<UserEntity> users = dataStorage.getUsers();
        if (users != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("user count %d", users.size()));
            sb.append("\n");
            for (UserEntity user : users) {
                sb.append(String.format("User: %s", user.name));
                sb.append("\n");
            }
            txtView.setText(sb.toString());
        }
    }

    @OnClick(R.id.btn_fetch_group)
    public void getGroup() {
        List<UserGroupEntity> groups = dataStorage.getGroups();
        if (groups != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("groups count %d", groups.size()));
            sb.append("\n");
            for (UserGroupEntity group : groups) {
                sb.append(String.format("group %s - users %d", group.name, group.getUserList().size()));
                sb.append("\n");
            }

            txtView.setText(sb.toString());
        }

    }

}
