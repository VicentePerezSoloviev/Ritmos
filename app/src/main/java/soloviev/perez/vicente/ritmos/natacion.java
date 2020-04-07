package soloviev.perez.vicente.ritmos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class natacion extends AppCompatActivity {

    private FragmentTabHost tabHost;

    private EditText distancia_ritmo;
    private EditText tiempo_horas_ritmo;
    private EditText tiempo_min_ritmo;
    private EditText tiempo_sec_ritmo;
    private TextView ritmo_min_ritmo;
    private TextView ritmo_sec_ritmo;

    private EditText distancia_tiempo, ritmo_min, ritmo_sec;
    private TextView horas_tiempo, min_tiempo, sec_tiempo;

    private EditText ritmo_min_distancia, ritmo_sec_distancia, tiempo_horas_distancia, tiempo_min_distancia, tiempo_sec_distancia;
    private TextView distancia_distancia;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.natacion);
        tabHost= (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(),android.R.id.tabcontent);
        tabHost.addTab(tabHost.newTabSpec("tiempo").setIndicator(getResources().getString(R.string.calcular_tiempo)), tiempo_nat.class, null);
        tabHost.addTab(tabHost.newTabSpec("ritmo").setIndicator(getResources().getString(R.string.calcular_ritmo)), ritmo_nat.class, null);
        tabHost.addTab(tabHost.newTabSpec("distancia").setIndicator(getResources().getString(R.string.calcular_distancia)), distancia_nat.class, null);

        //actualizo nombre de actionBar para idioma

        setTitle(R.string.calculadora_natacion);
    }

    public void lanzarError (View view) {
        Intent i = new Intent(this, error.class);
        startActivity(i);
    }

    public void calcular_ritmo (View view) {
        distancia_ritmo = (EditText) findViewById(R.id.distancia_ed);
        tiempo_horas_ritmo = (EditText) findViewById(R.id.horas_ed);
        tiempo_min_ritmo = (EditText) findViewById(R.id.min_ed);
        tiempo_sec_ritmo = (EditText) findViewById(R.id.sec_ed);

        ritmo_min_ritmo = (TextView) findViewById(R.id.min_ritmo);
        ritmo_sec_ritmo = (TextView) findViewById(R.id.sec_ritmo);

        if (TextUtils.isEmpty(distancia_ritmo.getText())) {
            distancia_ritmo.setText("0");
        }
        if (TextUtils.isEmpty(tiempo_horas_ritmo.getText())) {
            tiempo_horas_ritmo.setText("0");
        }
        if (TextUtils.isEmpty(tiempo_min_ritmo.getText())) {
            tiempo_min_ritmo.setText("0");
        }
        if (TextUtils.isEmpty(tiempo_sec_ritmo.getText())) {
            tiempo_sec_ritmo.setText("0");
        }

        if (Integer.decode(distancia_ritmo.getText().toString()).equals(0) && Integer.decode(tiempo_sec_ritmo.getText().toString()).equals(0)&&
                Integer.decode(tiempo_min_ritmo.getText().toString()).equals(0) && Integer.decode(tiempo_horas_ritmo.getText().toString()).equals(0)) {

            float r = 0;
            ritmo_min_ritmo.setText((CharSequence) Integer.toString(0));
            ritmo_sec_ritmo.setText((CharSequence) Float.toString(r));
        }
        else if (!TextUtils.isEmpty(distancia_ritmo.getText()) && !TextUtils.isEmpty(tiempo_sec_ritmo.getText())&& !TextUtils.isEmpty(tiempo_min_ritmo.getText()) && !TextUtils.isEmpty(tiempo_horas_ritmo.getText())) {
            String distanc= distancia_ritmo.getText().toString();
            int dist = Integer.decode(distanc);
            String h = tiempo_horas_ritmo.getText().toString();
            int hora = Integer.decode(h);
            String m = tiempo_min_ritmo.getText().toString();
            int min = Integer.decode(m);
            int sec = Integer.decode(tiempo_sec_ritmo.getText().toString());

            float segundo_m = (float) ((hora*60 + min)*60 + sec)/dist;
            float ritmo = segundo_m*100;
            int ritmo_min = (int) ritmo/60;
            float ritmo_sec = ritmo - (ritmo_min)*60;

            ritmo_min_ritmo.setText((CharSequence) Integer.toString(ritmo_min));
            ritmo_sec_ritmo.setText((CharSequence) Float.toString(ritmo_sec));


        } else{
            lanzarError(view);
        }

    }

    public void limpiar_ritmo (View view) {
        distancia_ritmo = (EditText) findViewById(R.id.distancia_ed);
        tiempo_horas_ritmo = (EditText) findViewById(R.id.horas_ed);
        tiempo_min_ritmo = (EditText) findViewById(R.id.min_ed);
        tiempo_sec_ritmo = (EditText) findViewById(R.id.sec_ed);

        distancia_ritmo.setText("0".toString());
        tiempo_horas_ritmo.setText("0".toString());
        tiempo_min_ritmo.setText("0".toString());
        tiempo_sec_ritmo.setText("0".toString());
    }

    public void calcular_tiempo (View view) {
        distancia_tiempo = (EditText) findViewById(R.id.distancia_ed);
        ritmo_min = (EditText) findViewById(R.id.min_ritmo_ed);
        ritmo_sec = (EditText) findViewById(R.id.sec_ritmo_ed);

        if (TextUtils.isEmpty(distancia_tiempo.getText())) {
            distancia_tiempo.setText("0");
        }
        if (TextUtils.isEmpty(ritmo_min.getText())) {
            ritmo_min.setText("0");
        }
        if (TextUtils.isEmpty(ritmo_sec.getText())) {
            ritmo_sec.setText("0");
        }

        horas_tiempo = (TextView) findViewById(R.id.horas_ed);
        min_tiempo = (TextView) findViewById(R.id.min_ed);
        sec_tiempo = (TextView) findViewById(R.id.sec_ed);

        /*si son todos 0*/
        if (Integer.decode(distancia_tiempo.getText().toString()).equals(0) && Integer.decode(ritmo_min.getText().toString()).equals(0)&& Integer.decode(ritmo_sec.getText().toString()).equals(0)) {
            horas_tiempo.setText((CharSequence) Integer.toString(0));
            float r = 0;
            min_tiempo.setText((CharSequence) Float.toString(r));
            sec_tiempo.setText((CharSequence) Float.toString(r));
        }

        /*si no estan vacios*/
        else if (!TextUtils.isEmpty(distancia_tiempo.getText()) && !TextUtils.isEmpty(ritmo_min.getText())&& !TextUtils.isEmpty(ritmo_sec.getText())) {
        /*else if (!distancia_tiempo.getText().toString().matches("") &&
                 !ritmo_min.getText().toString().matches("") &&
                 !ritmo_sec.getText().toString().matches("")) {
*/
            String distanc= distancia_tiempo.getText().toString();
            int dist = Integer.decode(distanc);

            String m = ritmo_min.getText().toString();
            int min = Integer.decode(m);
            int sec = Integer.decode(ritmo_sec.getText().toString());

            int tot = min*60 + sec;
            float mu = (float) tot/100;
            float mult = (float) dist * mu;

            int h= (int) mult/3600;
            int mi= (int) (mult-(3600*h))/60;
            float s = (float) (mult-((h*3600)+(mi*60)));

            horas_tiempo.setText((CharSequence) Integer.toString(h));
            min_tiempo.setText((CharSequence) Integer.toString(mi));
            sec_tiempo.setText((CharSequence) Float.toString(s));

        }

        else{
            lanzarError(view);
        }
    }

    public void limpiar_tiempo (View view) {
        distancia_tiempo = (EditText) findViewById(R.id.distancia_ed);
        ritmo_min = (EditText) findViewById(R.id.min_ritmo_ed);
        ritmo_sec = (EditText) findViewById(R.id.sec_ritmo_ed);

        distancia_tiempo.setText("0".toString());
        ritmo_min.setText("0".toString());
        ritmo_sec.setText("0".toString());
    }

    public void calcular_distancia (View view) {
        ritmo_min_distancia = (EditText) findViewById(R.id.min_ritmo_ed);
        ritmo_sec_distancia = (EditText) findViewById(R.id.sec_ritmo_ed);
        tiempo_horas_distancia = (EditText) findViewById(R.id.horas_ed);
        tiempo_min_distancia = (EditText) findViewById(R.id.min_ed);
        tiempo_sec_distancia = (EditText) findViewById(R.id.sec_ed);

        distancia_distancia = (TextView) findViewById(R.id.distancia_ed);

        if (TextUtils.isEmpty(ritmo_min_distancia.getText())) {
            ritmo_min_distancia.setText("0");
        }
        if (TextUtils.isEmpty(ritmo_sec_distancia.getText())) {
            ritmo_sec_distancia.setText("0");
        }
        if (TextUtils.isEmpty(tiempo_horas_distancia.getText())) {
            tiempo_horas_distancia.setText("0");
        }
        if (TextUtils.isEmpty(tiempo_min_distancia.getText())) {
            tiempo_min_distancia.setText("0");
        }
        if (TextUtils.isEmpty(tiempo_sec_distancia.getText())) {
            tiempo_sec_distancia.setText("0");
        }

        if (Integer.decode(ritmo_min_distancia.getText().toString()).equals(0) && Integer.decode(ritmo_sec_distancia.getText().toString()).equals(0) && Integer.decode(tiempo_horas_distancia.getText().toString()).equals(0) &&
                Integer.decode(tiempo_min_distancia.getText().toString()).equals(0) && Integer.decode(tiempo_sec_distancia.getText().toString()).equals(0)){
            float r=0;
            distancia_distancia.setText((CharSequence) Float.toString(r));
        }
        else if (!TextUtils.isEmpty(ritmo_min_distancia.getText()) && !TextUtils.isEmpty(ritmo_sec_distancia.getText()) && !TextUtils.isEmpty(tiempo_horas_distancia.getText()) &&
                !TextUtils.isEmpty(tiempo_min_distancia.getText()) && !TextUtils.isEmpty(tiempo_sec_distancia.getText())){
            int a = Integer.decode(tiempo_horas_distancia.getText().toString());
            int b = Integer.decode(tiempo_min_distancia.getText().toString());
            int c = Integer.decode(tiempo_sec_distancia.getText().toString());

            int tiempo_a_sec = ((a*60) + b)*60 + c;

            int q = Integer.decode(ritmo_min_distancia.getText().toString());
            int w = Integer.decode(ritmo_sec_distancia.getText().toString());

            float ritmo_a_sec = (float) ((q*60) + w)/100;

            float tot = (float) ((float) tiempo_a_sec / (float) ritmo_a_sec);

            distancia_distancia.setText((CharSequence) Float.toString(tot));

        }else{
            lanzarError(view);
        }
    }

    public void limpiar_distancia (View view) {
        ritmo_min_distancia = (EditText) findViewById(R.id.min_ritmo_ed);
        ritmo_sec_distancia = (EditText) findViewById(R.id.sec_ritmo_ed);
        tiempo_horas_distancia = (EditText) findViewById(R.id.horas_ed);
        tiempo_min_distancia = (EditText) findViewById(R.id.min_ed);
        tiempo_sec_distancia = (EditText) findViewById(R.id.sec_ed);

        ritmo_min_distancia.setText("0".toString());
        ritmo_sec_distancia.setText("0".toString());
        tiempo_horas_distancia.setText("0".toString());
        tiempo_min_distancia.setText("0".toString());
        tiempo_sec_distancia.setText("0".toString());
    }

}
