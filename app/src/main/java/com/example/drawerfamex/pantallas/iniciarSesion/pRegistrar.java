package com.example.drawerfamex.pantallas.iniciarSesion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawerfamex.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class pRegistrar extends AppCompatActivity {

    EditText etxtCorreo, etxtContraseña, etxtConfirmarContraseña;
    Button btnRegistrar, back;
    TextView txtTengoCuenta;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    String correo = "";
    String contraseña = "";
    String confirmarcontraseña = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aab_2_registro);

        etxtCorreo = findViewById(R.id.rgr_1_txt_iCorreo);
        etxtContraseña = findViewById(R.id.rgr_2_txt_iContra);
        etxtConfirmarContraseña = findViewById(R.id.rgr_3_txt_iRepContra);

        btnRegistrar = findViewById(R.id.rgr_4_btn_rgr);
        back = findViewById(R.id.rgr_5_btn_back);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(pRegistrar.this);
        progressDialog.setTitle("Espere un momento por favor");
        progressDialog.setCanceledOnTouchOutside(false); //bloquea la pantalla para que no lo cancele

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarDatos();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(pRegistrar.this, MainActivity.class));
                finish();
            }
        });

    }

    public void validarDatos() {
        correo = etxtCorreo.getText().toString();
        contraseña = etxtContraseña.getText().toString();
        confirmarcontraseña = etxtConfirmarContraseña.getText().toString();

        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) { // valida @ y .com
            Toast.makeText(this, "Ingrese correo valido", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(contraseña)) {
            Toast.makeText(this,"Ingrese contraseña ", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(confirmarcontraseña)){
            Toast.makeText(this,"Confirme contraseña ", Toast.LENGTH_SHORT).show();
        } else if(!contraseña.equals(confirmarcontraseña)){
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        } else{
            crearCuenta();
        }
    }

    private void crearCuenta() {
        progressDialog.setMessage("Creando su cuenta...");
        progressDialog.show();

        //Creando usuario en firebase
        firebaseAuth.createUserWithEmailAndPassword(correo, contraseña)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) { // registro exitoso
                        guardarInformacion();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) { //registro no exitoso
                        /* causas de registro no exitoso es por el internes*/
                        progressDialog.dismiss();
                        Toast.makeText(pRegistrar.this,"" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void guardarInformacion() {
        progressDialog.setTitle("Guardando su información");
        progressDialog.dismiss();

        //Obtener la identificacion de usuario
        String iduser = firebaseAuth.getUid();

        //Se designa las claves para los valores correspondientes
        HashMap<String, String> Datos = new HashMap<>();
        Datos.put("uid",iduser);
        Datos.put("correo", correo);

        String [] correopartes = correo.split("(@)");
        Datos.put("nombres", correopartes[0]);
        Datos.put("contraseña",contraseña);

        //usuarios nombre de la base de datos
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Usuarios");
        databaseReference.child(iduser)     // enlistar la bd por el id
                .setValue(Datos) // cada usuario que se registre tendra los datos
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressDialog.dismiss();
                        Toast.makeText(pRegistrar.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(pRegistrar.this, MainActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(pRegistrar.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // presionar la flecha atras te regresa
        return super.onSupportNavigateUp();
    }

    public void ClickVolver(View view) {
        MainActivity.redirectActivity(this, MainActivity.class);
    }
}