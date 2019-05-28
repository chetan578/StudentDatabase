package com.example.chetan578.authentication;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Spinner;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class StudentDetail extends AppCompatActivity {

    EditText studentName,schoolName;
    Spinner ageSpinner,subjectSpinner;
    Button addButton;
    FirebaseDatabase database;
    DatabaseReference databaseStudents;
    Student student;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);
        addButton = findViewById(R.id.addStudent);
        studentName = findViewById(R.id.editTextName);
        schoolName=findViewById(R.id.schoolName);
        ageSpinner=findViewById(R.id.ageSpinner);
        subjectSpinner= findViewById(R.id.subjectSpinner);
        database = FirebaseDatabase.getInstance();
        databaseStudents= database.getReference("students");

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStudent();
                Intent intent=new Intent(StudentDetail.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
        private void addStudent()
        {
            String name = studentName.getText().toString().trim();
            String spinnerAge =  ageSpinner.getSelectedItem().toString();
            String spinnerSubject= subjectSpinner.getSelectedItem().toString();
            String school=schoolName.getText().toString();
                String id = databaseStudents.push().getKey();
                student = new Student(id,name,spinnerAge,spinnerSubject,school);
                databaseStudents.child(id).setValue(student);
               // Toast.makeText(this, "artist added", Toast.LENGTH_SHORT).show();
        }
    @Override
    public void onBackPressed() {
        Intent intent =new Intent(StudentDetail.this,MainActivity.class);
        startActivity(intent);
    }

    }
