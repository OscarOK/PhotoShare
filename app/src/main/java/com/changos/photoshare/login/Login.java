package com.changos.photoshare.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.changos.photoshare.MainActivity;
import com.changos.photoshare.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        //Texto registrar en negritas
        TextView textView = (TextView) findViewById(R.id.registerTextBtn);
        textView.setText(Html.fromHtml("No tienes cuenta? <b>Registrate.</b>"));

        Button loginButton = (Button) findViewById(R.id.login_btn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            startNewActivity();
        }
    }

    public void registerClick(View view){
        if(view.getId()==R.id.registerTextBtn){
            Intent myIntent = new Intent();
            myIntent.setClass(Login.this, Registration.class);
            startActivity(myIntent);
        }
    }

    public void attemptLogin(){
        final EditText emailEditText = (EditText) findViewById(R.id.emailRequest_editText);
        final EditText passwordEditText = (EditText) findViewById(R.id.passwordRequest_editText);

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        showProgressBar(true);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        showProgressBar(false);
                        if(task.isSuccessful()){
                            startNewActivity();
                        } else {
                            passwordEditText.setText("");
                            Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void startNewActivity(){
        Intent i = new Intent();
        i.setClass(Login.this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    public void showProgressBar(Boolean show){

        Log.d("MyLog", "showProgressBar: " + show.toString());
        final View viewToHide, viewToShow;

        if(show){
            viewToHide =  findViewById(R.id.login_form);
            viewToShow = findViewById(R.id.login_progressbar);
        } else {
            viewToHide = findViewById(R.id.login_progressbar);
            viewToShow =  findViewById(R.id.login_form);
        }

        viewToHide.setVisibility(View.GONE);
        viewToShow.setVisibility(View.VISIBLE);
    }
}
