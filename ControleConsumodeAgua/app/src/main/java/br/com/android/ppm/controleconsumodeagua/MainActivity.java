package br.com.android.ppm.controleconsumodeagua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.android.ppm.controleconsumodeagua.adapter.ListaConsumoAdapter;
import br.com.android.ppm.controleconsumodeagua.dao.ConsumoDAO;
import br.com.android.ppm.controleconsumodeagua.entity.ConsumoEntity;
import br.com.android.ppm.controleconsumodeagua.modal.Consumo;
import br.com.android.ppm.controleconsumodeagua.persistence.AppDatabase;

public class MainActivity extends AppCompatActivity {
    private ConsumoDAO dadosConsumoDAO;
    private List<String> listaPaletesLidas;
    private ListView listviewConsumo;
    private EditText edtMedia;
    private Double mediaConsumo =0.00;
    private Double somaConsumo = 0.00;
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goCadastroConsumo();
            }
        });

        listviewConsumo = (ListView) findViewById(R.id.idLancamentoConsumo);
        edtMedia = (EditText) findViewById(R.id.idMediaConsumo);
        dadosConsumoDAO = AppDatabase.getInstance(this).consumoDAO();

    }

    public void goCadastroConsumo(){
        Intent intent = new Intent(this, CadastroConsumoActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onResume() {
        refreshInformacoes();
        super.onResume();
    }
    private void refreshInformacoes(){

        ArrayList<Consumo> listaPaletesASeremExibidos = new ArrayList<>();
        List<ConsumoEntity> listaPaletesPersistidos = this.dadosConsumoDAO.lista();
        this.listaPaletesLidas = new ArrayList<>();


        if(listaPaletesPersistidos.size() > 0) {
            for (ConsumoEntity paletePersistido : listaPaletesPersistidos) {
                Consumo paleteASerExibido = new Consumo();
                paleteASerExibido.setQtd(paletePersistido.getQtd());
                listaPaletesASeremExibidos.add(paleteASerExibido);
                listaPaletesLidas.add(String.valueOf(paletePersistido.getQtd()));
                listaPaletesLidas.add(paletePersistido.getDataConsumo());
                paleteASerExibido.setQtd(paletePersistido.getQtd());
                paleteASerExibido.setData(String.valueOf(paletePersistido.getDataConsumo()));
                somaConsumo += paletePersistido.getQtd();

            }
            ListaConsumoAdapter listaPaleteRecAdapter = new ListaConsumoAdapter(this, listaPaletesASeremExibidos);
            listviewConsumo.setAdapter(listaPaleteRecAdapter);
            listviewConsumo.setVisibility(View.VISIBLE);
        }else{
            listviewConsumo.setVisibility(View.GONE);
        }
        mediaConsumo = (double) somaConsumo/30;
        edtMedia.setText(String.valueOf(df2.format(mediaConsumo)));

    }
}