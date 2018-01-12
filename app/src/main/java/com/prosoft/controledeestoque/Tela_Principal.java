package com.prosoft.controledeestoque;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Tela_Principal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Dialog dialog;
    String rec_em, rec_msg; //String para reclamação
    String pdt_nome, pdt_desc, pdt_qtd, pdt_valor, pdt_codigo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__principal);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dialog = new Dialog(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Criar Nova Loja", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    //Esse é para fazer com que a pessoa aperte duas vezes o botão para sair do aplicativo
    Boolean DuasVezes=false;
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(DuasVezes){
                //Chamar a função para sair do aplicativo quando apertar duas vezes o botão de saída
                action_sair();
            }
            Toast.makeText(this, R.string.msg_saida, Toast.LENGTH_SHORT).show();
            //Quando apertar o botao para sair, o valor de DuasVezes será mudado para TRUE por 3segundos
            DuasVezes = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Depois de 3segundos, o valor de DuasVezes será mudado para FALSE de novo
                    DuasVezes = false;
                }
            }, 3000);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tela__principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.action_sair) {
            //Função para sair do aplicativo
            action_sair();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_pag_inicial){
            Toast.makeText(this, R.string.função_indisponivel, Toast.LENGTH_SHORT).show();
        }else if (id == R.id.nav_novo) {
            // Cadastrar novo produto
            mostrarDialogo(4);
        } else if (id == R.id.nav_gallery) {
            Toast.makeText(this, R.string.função_indisponivel, Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_config) {
            Toast.makeText(this, R.string.função_indisponivel, Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_share) {
            //Número 1 - compartilhar
            //mostrarDialogo(1);
            Toast.makeText(this, R.string.função_indisponivel, Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_reclamar) {
            //Número 2 - Reclamar
            mostrarDialogo(2);
        } else if(id == R.id.nav_sair) {
            //Chamando a função para sair do aplicativo
            action_sair();
        }else if(id == R.id.nav_sobre) {
            //Número 3 - Sobre
            mostrarDialogo(3);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //Função para sair do aplicativo
    public void action_sair(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        System.exit(0);
    }
    //Função para mostrar a falhar de internet
    public void mostrarDialogo(int i){
        ImageView fechar;
        Button btnA;

        if (i == 1) { //Tela de compartilhar
            Toast.makeText(this, R.string.função_indisponivel, Toast.LENGTH_SHORT).show();
        }
        else if (i == 2) { //Tela de reclamação
            final EditText recEm, recMsg;

            dialog.setContentView(R.layout.activity_reclamar);
            fechar = dialog.findViewById(R.id.img_fechar);
            btnA = dialog.findViewById(R.id.btn_enviar);
            recEm = dialog.findViewById(R.id.rec_email);
            recMsg = dialog.findViewById(R.id.rec_msg);

            fechar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            btnA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rec_em = recEm.getText().toString();
                    rec_msg = recMsg.getText().toString();
                    if(rec_em.isEmpty() || rec_msg.isEmpty())
                        Toast.makeText(Tela_Principal.this, R.string.aviso_campo_vazio, Toast.LENGTH_SHORT).show();
                    else{
                        Toast.makeText(Tela_Principal.this, R.string.reclamação_enviada, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            });
        }
        else if (i == 3) {//Tela de Sobre
            dialog.setContentView(R.layout.activity_sobre);
            fechar = dialog.findViewById(R.id.img_close);
            btnA = dialog.findViewById(R.id.btn_ok);

            fechar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            btnA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
        }
        else if (i == 4) { //Tela de cadastro de novo produto
            final EditText p_nome, p_des, p_qtd, p_valor, p_codigo;

            dialog.setContentView(R.layout.activity_cadastro_produto);

            p_nome = dialog.findViewById(R.id.pd_nome);
            p_des = dialog.findViewById(R.id.pd_desc);
            p_qtd = dialog.findViewById(R.id.pd_qtd);
            p_valor = dialog.findViewById(R.id.pd_valor);
            p_codigo = dialog.findViewById(R.id.pd_codigo);

            btnA = dialog.findViewById(R.id.btn_pd_cadastrar);
            fechar = dialog.findViewById(R.id.pdt_fechar);

            fechar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            btnA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Produto produto = new Produto();
                    pdt_nome = p_nome.getText().toString();
                    pdt_desc = p_des.getText().toString();
                    pdt_qtd = p_qtd.getText().toString();
                    pdt_valor = p_valor.getText().toString();
                    pdt_codigo = p_codigo.getText().toString();

                    produto.setNome(p_nome.getText().toString());
                    produto.setDescricao(p_des.getText().toString());
                    produto.setQuantidade(p_qtd.getText().toString());
                    produto.setValor(p_valor.getText().toString());
                    produto.setCodigo(p_codigo.getText().toString());

                    if(pdt_nome.isEmpty() || pdt_desc.isEmpty()|| pdt_qtd.isEmpty()|| pdt_valor.isEmpty()|| pdt_codigo.isEmpty())
                        Toast.makeText(Tela_Principal.this, R.string.aviso_campo_vazio, Toast.LENGTH_SHORT).show();
                    else{
                        //Enviando para o banco de dados no firebase versão beta
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference(produto.getDescricao());
                        myRef.setValue(produto);
                        Toast.makeText(Tela_Principal.this, R.string.produto_cadastrado, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            });
        }
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}
