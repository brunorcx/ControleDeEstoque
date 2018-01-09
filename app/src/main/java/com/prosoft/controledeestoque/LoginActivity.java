package com.prosoft.controledeestoque;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.net.Socket;
import java.util.logging.SocketHandler;

public class LoginActivity extends AppCompatActivity {
    EditText edEmail, edSenha, cadEmail, cadUsuario, cadSenha,cadTel;
    Button btn_login_entrar, btn_login_cadastrar, btn_cadastrar;
    LinearLayout login_layout, cadastro_layout;

    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edEmail = findViewById(R.id.login_email);
        edSenha = findViewById(R.id.login_senha);
        cadEmail = findViewById(R.id.email_cadastro);
        cadSenha = findViewById(R.id.senha_cadastro);
        cadUsuario = findViewById(R.id.usuario_cadastro);
        cadTel = findViewById(R.id.numero_cadastro);
        btn_login_cadastrar = findViewById(R.id.btn_login_cadastrar);
        btn_cadastrar = findViewById(R.id.btn_cad);
        btn_login_entrar = findViewById(R.id.btn_entrar);

        login_layout = findViewById(R.id.layout_login);
        cadastro_layout = findViewById(R.id.layout_cadastro);

        //Verificação de internet
        myDialog = new Dialog(this);
        new checkInternet().execute("www.google.com");

        //Login layout sempre ativado
        cadastro_layout.setVisibility(View.GONE);
        login_layout.setVisibility(View.VISIBLE);

        //Se login for bem sucedido, vá para a tela principal
        final Intent intent = new Intent(this, Tela_Principal.class);

        btn_login_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em, pass;
                //Quando clickar no ENTRAR, pegue os valores dos campos email e senha
                em = edEmail.getText().toString();
                pass = edSenha.getText().toString();

                //Por enquanto, email e senha definidos como admin@admin.com e admin
                if(!em.isEmpty() && !pass.isEmpty() && em.equals("admin") && pass.equals("admin")) {
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
        //Botão para criar nova conta
        btn_login_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_layout.setVisibility(View.GONE); //Layout do login é desativado aqui
                cadastro_layout.setVisibility(View.VISIBLE); // Layout do cadastro é ativado aqui
            }
        });

        //Botão para cadastrar nova conta do usuario
        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome, email, senha, tel;
                nome = cadUsuario.getText().toString();
                email = cadEmail.getText().toString();
                senha = cadSenha.getText().toString();
                tel = cadTel.getText().toString();

                if(nome.isEmpty() ||email.isEmpty() ||senha.isEmpty() || tel.isEmpty())
                    Toast.makeText(LoginActivity.this, R.string.aviso_campo_vazio, Toast.LENGTH_SHORT).show();
                else{
                    cadUsuario.setText("");
                    cadEmail.setText("");
                    cadSenha.setText("");
                    cadTel.setText("");
                    cadUsuario.setHint(R.string.usuario);
                    cadEmail.setHint(R.string.usuario_email);
                    cadSenha.setHint(R.string.senha);
                    cadTel.setHint(R.string.telefone);
                    edEmail.setText("");
                    edSenha.setText("");
                    edEmail.setHint(getString(R.string.email));
                    edSenha.setHint(getString(R.string.senha));
                    Toast.makeText(LoginActivity.this, R.string.cadastro_sucesso, Toast.LENGTH_SHORT).show();
                    startActivity(intent);
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
        cadastro_layout.setVisibility(View.GONE);
        login_layout.setVisibility(View.VISIBLE);
        DuasVezes = true;
        Thread timer = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(2000);
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

    @Override
    protected void onResume() {
        super.onResume();
        new checkInternet().execute("www.google.com");
        cadastro_layout.setVisibility(View.GONE);
        login_layout.setVisibility(View.VISIBLE);
    }
    //Função para mostrar a falhar de internet
    public void mostrarDialogo(){
        TextView fechar;
        Button tenteDeNovo;
        myDialog.setContentView(R.layout.no_internet);
        fechar = myDialog.findViewById(R.id.txtX);
        tenteDeNovo = myDialog.findViewById(R.id.btn_retry);

        fechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });
        tenteDeNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
                new checkInternet().execute("www.google.com");
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    public class checkInternet extends AsyncTask<String, Void, Integer>{

        @Override
        protected Integer doInBackground(String... strings) {
            Integer result = 0;
            try{
                Socket s = new Socket(strings[0], 80);
                s.close();
                result = 1;
            }catch (Exception e){
                result = 0;
            }
            return result;
        }
        @Override
        protected void onPostExecute(Integer result){
            if(result == 0){
                mostrarDialogo();
            }
        }
    }

}
