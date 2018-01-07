package com.prosoft.controledeestoque;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText edEmail, edSenha;
    Button ent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edEmail = findViewById(R.id.email_edit_text);
        edSenha = findViewById(R.id.senha_edit_text);
        ent = findViewById(R.id.btn_entrar);

        //Se login for bem sucedido, vá para a tela principal
        final Intent intent = new Intent(this, Tela_Principal.class);

        ent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em, pass;
                //Quando clickar no ENTRAR, pegue os valores dos campos email e senha
                em = edEmail.getText().toString();
                pass = edSenha.getText().toString();

                //Por enquanto, email e senha definidos como admin@admin.com e admin
                if(!em.isEmpty() && !pass.isEmpty() && em.equals("admin@admin.com") && pass.equals("admin")) {
                    Toast.makeText(LoginActivity.this, R.string.login_sucesso, Toast.LENGTH_SHORT).show();
                    edEmail.setText("");
                    edSenha.setText("");
                    edEmail.setHint(getString(R.string.email));
                    edSenha.setHint(getString(R.string.senha));
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginActivity.this, R.string.login_invalido, Toast.LENGTH_SHORT).show();
                    //Pedir atenção a onde teve um erro
                    if(!em.equals("admin@admin.com"))
                        edEmail.requestFocus();
                    else if(!pass.equals("admin"))
                        edSenha.requestFocus();
                }
            }
        });
    }
    Boolean DuasVezes=false;
    @Override
    public void onBackPressed() {
        if(DuasVezes){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            System.exit(0);
        }
        Toast.makeText(this, R.string.msg_saida, Toast.LENGTH_SHORT).show();
        DuasVezes = true;
        Thread timer = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(3000);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
                finally{
                    DuasVezes=false;
                }
            }
        };timer.start();
    }

}
