package com.example.cookieclicker;

import android.os.Bundle;
import android.os.health.TimerStat;
import android.renderscript.Sampler;
import android.support.annotation.DrawableRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.BoringLayout;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    long clicks = 0;
    long ClicksV2 = 1;
    long ClicksContas = 1;
    long ClicksContasUpgrade2 = 1;
    long ClicksContasUpgrade3 = 1;
    long idEscolha;
    long precoUpgradeClicksValue = 50;
    long precoUpgradeClicksValueUpgrade2 = 100;
    long precoUpgradeClicksValueUpgrade3 = 500;
    long tempo = 1000;
    long Upgrade2ValueDMG = 1;
    long Upgrade3ValueDMG = 1;
    String RunOrNot = "false";
    String tempoReset = "True";
    int id;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ImageButton imgBtn_Clicks = findViewById(R.id.imgBtn_Clicks);
        final TextView text_Clicks = findViewById(R.id.text_Clicks);



        navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_camera).setTitle("Muitos clicks! | $" + precoUpgradeClicksValue);
        nav_Menu.findItem(R.id.nav_gallery).setTitle("A sair! | $" + precoUpgradeClicksValueUpgrade2);
        nav_Menu.findItem(R.id.nav_slideshow).setTitle("E̶s̶t̶á̶ ̶m̶u̶i̶t̶o̶ ̶r̶á̶p̶i̶d̶o̶!̶");
        nav_Menu.findItem(R.id.nav_manage).setVisible(false);
        nav_Menu.findItem(R.id.nav_send).setVisible(false);
        nav_Menu.findItem(R.id.nav_share).setVisible(false);





        imgBtn_Clicks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(idEscolha == 1) {

                }else{

                }

                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoomin);
                imgBtn_Clicks.startAnimation(animation);

                clicks = clicks + ClicksV2;
                text_Clicks.setText("Bolachas: " + clicks);
            }
        });




        text_Clicks.setText("Bolachas: 0");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }

    public Void upgrade1(){
        idEscolha = 1;
        final TextView text_Clicksmethod = findViewById(R.id.text_Clicks);
        if(precoUpgradeClicksValue > clicks){
            Toast.makeText(MainActivity.this,
                    "Não tens guita suficiente!", Toast.LENGTH_LONG).show();
        }else{
            ClicksContas = ClicksContas + 1;
            clicks = clicks - precoUpgradeClicksValue;
            ClicksV2 = ClicksV2 * ClicksContas;
            precoUpgradeClicksValue = precoUpgradeClicksValue * (ClicksContas + 1);
            Menu nav_Menu = navigationView.getMenu();
            nav_Menu.findItem(R.id.nav_camera).setTitle("Muitos clicks! | $" + precoUpgradeClicksValue);
            text_Clicksmethod.setText("Bolachas: " + clicks);

        }
        return null;
    }


    TimerTask timerTask = new TimerTask()
    {
        public void run()
        {
            updateUI();

        }
    };

    public void upgrade2(){
        final TextView text_Clicksmethod = findViewById(R.id.text_Clicks);

        if(precoUpgradeClicksValueUpgrade2 > clicks){
            Toast.makeText(MainActivity.this,
                    "Não tens guita suficiente!", Toast.LENGTH_LONG).show();
        }else {
            if(RunOrNot == "false"){
                RunOrNot = "true";
                clicks = clicks - precoUpgradeClicksValueUpgrade2;
                text_Clicksmethod.setText("Bolachas: " + clicks);
                precoUpgradeClicksValueUpgrade2 = precoUpgradeClicksValueUpgrade2 * (ClicksContasUpgrade2 + 1);
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(timerTask, 0, tempo);
                Menu nav_Menu = navigationView.getMenu();
                nav_Menu.findItem(R.id.nav_gallery).setTitle("A sair! | $" + precoUpgradeClicksValueUpgrade2);
                nav_Menu.findItem(R.id.nav_slideshow).setTitle("Está muito rápido! | $" + precoUpgradeClicksValueUpgrade3);
            }else if (RunOrNot == "true"){
                clicks = clicks - precoUpgradeClicksValueUpgrade2;
                precoUpgradeClicksValueUpgrade2 = precoUpgradeClicksValueUpgrade2 * (ClicksContasUpgrade2 + 1);
                Upgrade2ValueDMG = Upgrade2ValueDMG * 2;
                text_Clicksmethod.setText("Bolachas: " + clicks);
                Menu nav_Menu = navigationView.getMenu();
                nav_Menu.findItem(R.id.nav_gallery).setTitle("A sair! | $" + precoUpgradeClicksValueUpgrade2);
                nav_Menu.findItem(R.id.nav_slideshow).setTitle("Está muito rápido! | $" + precoUpgradeClicksValueUpgrade3);

            }
        }
    }

    TimerTask timerTask1 = new TimerTask()
    {
        public void run()
        {
            updateUI();

        }
    };

    private void updateUI(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final TextView text_Clicksmethod = findViewById(R.id.text_Clicks);
                clicks = clicks + Upgrade2ValueDMG;
                text_Clicksmethod.setText("Bolachas: " + clicks);
            }
        });
    }


    public void upgrade3(){
        final TextView text_Clicksmethod = findViewById(R.id.text_Clicks);

        if (RunOrNot == "false"){
            Toast.makeText(MainActivity.this,
                    "Tens que ter o upgrade 'A sair!'.", Toast.LENGTH_LONG).show();
        }else if (RunOrNot == "true"){
            if(precoUpgradeClicksValueUpgrade3 < clicks){
                if (tempo > 500 ){
                    tempo = tempo - 500 ;
                    if(tempoReset == "True"){
                        clicks = clicks - precoUpgradeClicksValueUpgrade3;
                        text_Clicksmethod.setText("Bolachas: " + clicks);
                        timerTask.cancel();
                        Timer timer = new Timer();
                        timer.scheduleAtFixedRate(timerTask1, 0, tempo);
                        Menu nav_Menu = navigationView.getMenu();
                        nav_Menu.findItem(R.id.nav_slideshow).setTitle("E̶s̶t̶á̶ ̶m̶u̶i̶t̶o̶ ̶r̶á̶p̶i̶d̶o̶!̶");
                        tempoReset = "False";
                    }else if (tempoReset == "False"){

                    }

                }else{
                    Toast.makeText(MainActivity.this,
                            "Calma se nao ficas muito OP!", Toast.LENGTH_LONG).show();
                }
            }else{
                if (tempo <= 500 ){
                    Toast.makeText(MainActivity.this,
                            "Calma se nao ficas muito OP!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,
                            "Não tens guita suficiente!", Toast.LENGTH_LONG).show();
                }


            }


        }



    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            upgrade1();
        } else if (id == R.id.nav_gallery) {
            idEscolha = 2;
            upgrade2();
        } else if (id == R.id.nav_slideshow) {
            idEscolha = 3;
            upgrade3();
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }








}
