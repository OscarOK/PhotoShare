package com.changos.photoshare.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.changos.photoshare.R;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Texto registrar en negritas
        TextView textView = (TextView) findViewById(R.id.registerTextBtn);
        textView.setText(Html.fromHtml("No tienes cuenta? <b>Registrate.</b>"));
    }
}
