package com.example.aruapuri;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.aruapuri.databinding.ActivityMainBinding;
import com.example.aruapuri.models.PeopleData;
import com.example.aruapuri.utils.DatePickerUtils;
import com.google.android.material.datepicker.MaterialDatePicker;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    MaterialDatePicker<Long> datePicker;
    RadioButton selectedRadioBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();

    }

    private void init() {

        datePicker = DatePickerUtils.buildBasicDatePicker("Tanggal Lahir");
        datePicker.addOnPositiveButtonClickListener(selection -> {
            binding.textFieldBirthDay.setText(DatePickerUtils.longToString(DatePickerUtils.pattern1, selection));
        });

        binding.textFieldBirthDay.setOnClickListener(view -> {
            Fragment element = getSupportFragmentManager().findFragmentByTag("DATE_PICKER");
            if (element != null && element.isAdded()) {
                return;
            };
            datePicker.show(getSupportFragmentManager(), "DATE_PICKER");
        });

        String[] listPekerjaan = {"Pegawai Negeri", "Pegawai Swasta", "Pelajar", "Wiraswasta"};
        ArrayAdapter<String> pekerjaanAdapter = new ArrayAdapter<>(this, R.layout.occupation_dropmenu_choice, listPekerjaan);
        binding.textFieldOccupation.setAdapter(pekerjaanAdapter);

        binding.nextBtn.setOnClickListener(view -> {
            PeopleData peopleData = PeopleData.CREATOR.createFromParcel(Parcel.obtain());
            peopleData.nik = binding.textFieldNik.getText().toString();
            peopleData.name = binding.textFieldName.getText().toString();
            peopleData.birthPlace = binding.textFieldBirthPlace.getText().toString();
            peopleData.birthDay = datePicker.getSelection();
            peopleData.address = binding.textFieldAddress.getText().toString();
            peopleData.occupation = binding.textFieldOccupation.getText().toString();
            peopleData.gender = getSelectedRadioBtn(binding.genderPicker).getText().toString();
            peopleData.marriageStatus = getSelectedRadioBtn(binding.marriageStatusPicker).getText().toString();

            Intent intent = new Intent(MainActivity.this, PeopleDataActivity.class);
            intent.putExtra("data", (Parcelable) peopleData);
            startActivity(intent);
        });
    }

    private RadioButton getSelectedRadioBtn(RadioGroup group) {
        return findViewById(group.getCheckedRadioButtonId());
    }

}