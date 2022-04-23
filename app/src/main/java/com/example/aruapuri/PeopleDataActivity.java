package com.example.aruapuri;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aruapuri.databinding.ActivityMainBinding;
import com.example.aruapuri.databinding.ActivityPeopleDataBinding;
import com.example.aruapuri.models.PeopleData;
import com.example.aruapuri.utils.DatePickerUtils;

public class PeopleDataActivity extends AppCompatActivity {

    ActivityPeopleDataBinding binding;

    PeopleData peopleData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPeopleDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        peopleData = (PeopleData)getIntent().getParcelableExtra("data");
        if (peopleData == null) {
            Toast.makeText(getBaseContext(),"Null Data",Toast.LENGTH_LONG).show();
            finish();
        }

        init();
    }

    private void init() {

        binding.viewNik.setText(peopleData.nik);
        binding.viewName.setText(peopleData.name);
        binding.viewBirthPlace.setText(peopleData.birthPlace);
        binding.viewBirthDay.setText(DatePickerUtils.longToString(DatePickerUtils.pattern1, peopleData.birthDay));
        binding.viewAddress.setText(peopleData.address);
        binding.viewGender.setText(peopleData.gender);
        binding.viewOccupation.setText(peopleData.occupation);
        binding.viewMarriageStatus.setText(peopleData.marriageStatus);

        binding.backBTN.setOnClickListener(view -> {
            finish();
        });
    }
}