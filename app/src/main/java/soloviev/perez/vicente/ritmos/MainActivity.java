package soloviev.perez.vicente.ritmos;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private AdView mAdView;
    private Menu menu;

    private InterstitialAd mInterstitialAd;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar_menu, menu);
        this.menu = menu;
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.sugerencias){

            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }

            /*Intent i = new Intent (this, sugerencias.class);
            startActivity(i);*/

        } else if (id == R.id.cambiar_idioma){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle(R.string.cambiar_idioma)
                    .setItems(R.array.lenguages, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            Resources res = getResources();
                            DisplayMetrics dm = res.getDisplayMetrics();
                            Configuration conf = res.getConfiguration();

                            Locale locale;

                            switch(which) {
                                case 0:
                                    locale = Locale.ENGLISH;
                                    Locale.setDefault(locale);
                                    conf.locale = locale;

                                    menu.getItem(0).setIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.usa));

                                    break;
                                case 1:
                                    locale = new Locale("es","ES");
                                    Locale.setDefault(locale);
                                    conf.locale = locale;

                                    menu.getItem(0).setIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.spain));

                                    break;
                                case 2:
                                    locale = new Locale("ru","RU");
                                    Locale.setDefault(locale);
                                    conf.locale = locale;

                                    menu.getItem(0).setIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rusia));

                                    break;
                            }

                            res.updateConfiguration(conf, dm);
                            onConfigurationChanged(conf);

                            actualizar();

                        }
                    });
            builder.create();
            builder.show();


        }

        return true;
    }


    public void actualizar() {
        Button buttonNat = findViewById(R.id.natacion);
        Button buttonCar = findViewById(R.id.carrera);
        Button buttonMar = findViewById(R.id.marcas);

        buttonNat.setText(getResources().getString(R.string.natacion));
        buttonCar.setText(getResources().getString(R.string.carrera));
        buttonMar.setText(getResources().getString(R.string.mejores_marcas));

        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //adds
        
        MobileAds.initialize(this, "ca-app-pub-4666536989991556~5464124026");

        //banner add

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //interstitial add

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-4666536989991556/7631337618");
        AdRequest adRequest_1 = new AdRequest.Builder()
                .build();
        mInterstitialAd.loadAd(adRequest_1);

    }

    public void lanzarCrono(View view) {
        Intent i= new Intent(this, crono.class);
        startActivity(i);
    }
    public void lanzarCarrera (View view) {
        Intent i= new Intent(this, carrera.class);
        startActivity(i);
    }

    public void lanzarNatacion (View view) {
        Intent i = new Intent (this, natacion.class);
        startActivity(i);
    }

    public void lanzarMarcas (View view) {
        Intent i = new Intent (this, marcas.class);
        startActivity(i);
    }
}
