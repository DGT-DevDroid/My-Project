package br.com.android.ppm.controleconsumodeagua;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
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
    private Context context;
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

//        listviewConsumo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                final Consumo paleteASerExcluido = (Consumo) listviewConsumo.getAdapter().getItem(position);
//
//                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setMessage("Você selecionou a exclusão do palete " + paleteASerExcluido.getId() + ". \n Deseja prosseguir?")
//                        .setTitle("Atenção!!!")
//                        .setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
//                            public void onClick(@SuppressWarnings("unused") final DialogInterface dialogInterface, @SuppressWarnings("unused") final int id) {
//
//                                    excluirConsumo(paleteASerExcluido.getId());
//
//                               //     excluirPalete(paleteASerExcluido.getNumPalete());
//
//                                dialogInterface.dismiss();
//                            }
//                        })
//                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//
//                                dialogInterface.dismiss();
//                            }
//                        }).show();
//            }
//        });

    }
//    public void excluirConsumo(int idConsumo){
//        dadosConsumoDAO.delete(idConsumo);
//        refreshInformacoes();
//    }
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
                paleteASerExibido.setData(paletePersistido.getDataConsumo());
                listaPaletesASeremExibidos.add(paleteASerExibido);
//                listaPaletesLidas.add(String.valueOf(paletePersistido.getQtd()));
//                listaPaletesLidas.add(paletePersistido.getDataConsumo());
//                paleteASerExibido.setQtd(paletePersistido.getQtd());
//                paleteASerExibido.setData(String.valueOf(paletePersistido.getDataConsumo()));
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