package com.changos.photoshare.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.changos.photoshare.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        //AuthFireBase
        mAuth = FirebaseAuth.getInstance();

        emailEditText = (EditText) findViewById(R.id.createEmail);
        passwordEditText = (EditText) findViewById(R.id.createPassword);
        confirmPasswordEditText = (EditText) findViewById(R.id.createPasswordConfirm);
        Button btn = (Button) findViewById(R.id.registerBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();
                Log.d("jojito", password + " " + confirmPassword);
                if(password.equals(confirmPassword) && !TextUtils.isEmpty(password) ){
                    registerNewUser(email, password);
                }else Toast.makeText(Registration.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void registerNewUser(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent i = new Intent();
                    i.setClass(Registration.this, MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                }else{
                    Toast.makeText(Registration.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}