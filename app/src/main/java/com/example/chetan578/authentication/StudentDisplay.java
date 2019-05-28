package com.example.chetan578.authentication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentDisplay extends AppCompatActivity {
    TextView name, age, school, subject;
    FirebaseDatabase database;
    DatabaseReference databaseStudents;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_display);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        school = findViewById(R.id.schoolName);
        subject = findViewById(R.id.subject);
        database = FirebaseDatabase.getInstance();
        Intent intent = getIntent();
        String id = intent.getStringExtra("ID");
            databaseStudents = database.getReference("students").child(id);

            databaseStudents.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    student = dataSnapshot.getValue(Student.class);
                    String myname = student.StudentName;
                    String myage = student.StudentAge;
                    String myschool = student.StudentSchool;
                    String mysubject = student.StudentSubject;
                    name.setText(myname);
                    age.setText(myage);
                    school.setText(myschool);
                    subject.setText(mysubject);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
