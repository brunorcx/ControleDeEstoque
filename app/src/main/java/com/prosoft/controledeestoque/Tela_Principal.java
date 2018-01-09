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
import android.widget.ImageView;
import android.widget.Toast;

public class Tela_Principal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Dialog dialog_sobre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__principal);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dialog_sobre = new Dialog(this);

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
        }else if (id == R.id.nav_camera) {
            // Handle the camera action
            Toast.makeText(this, R.string.função_indisponivel, Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_gallery) {
            Toast.makeText(this, R.string.função_indisponivel, Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_config) {
            Toast.makeText(this, R.string.função_indisponivel, Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_share) {
            Toast.makeText(this, R.string.função_indisponivel, Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_reclamar) {
            Toast.makeText(this, R.string.função_indisponivel, Toast.LENGTH_SHORT).show();
        } else if(id == R.id.nav_sair) {
            //Chamando a função para sair do aplicativo
            action_sair();
        }else if(id == R.id.nav_sobre) {
            mostrarDialogo();
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
    public void mostrarDialogo(){
        ImageView fechar;
        Button ok;
        dialog_sobre.setContentView(R.layout.activity_sobre);
        fechar = dialog_sobre.findViewById(R.id.img_close);
        ok = dialog_sobre.findViewById(R.id.btn_ok);

        fechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_sobre.dismiss();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_sobre.dismiss();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        dialog_sobre.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_sobre.show();
    }
}
