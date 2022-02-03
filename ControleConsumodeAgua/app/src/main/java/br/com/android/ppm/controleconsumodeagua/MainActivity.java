package br.com.android.ppm.controleconsumodeagua;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.android.ppm.controleconsumodeagua.adapter.ListaConsumoAdapter;
import br.com.android.ppm.controleconsumodeagua.dao.ConsumoDAO;
import br.com.android.ppm.controleconsumodeagua.entity.ConsumoEntity;
import br.com.android.ppm.controleconsumodeagua.modal.Consumo;
import br.com.android.ppm.controleconsumodeagua.persistence.AppDatabase;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 100;
    private ConsumoDAO dadosConsumoDAO;
    private Button btnGravar;
    private List<String> listaPegarDate;
    private ListView listviewConsumo;
    private EditText edtMedia, edtMediaDiaria, edtLeituraAnterior, edtMeta;
    private Double mediaConsumo =0.00;
    private Double mediaConsumoDiario = 0.00;
    private Double leituraAnteior = 0.00;
    private Double meta = 0.00;
    private SharedPreferences preferences;
    private Double somaConsumo = 0.00;
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    private static DecimalFormat df = new DecimalFormat("0");
    private Context context;
    private Double maiorValor;
    private Double menorValor;
    private Date maiorData;
    private Date menorData;
    private int numDias;
    private String qtd ="0.00";
    private static SimpleDateFormat fmData = new SimpleDateFormat("dd/MM/yyyy");

    Date i;
    private String dadosArquivo = "";
    private Date data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listviewConsumo = (ListView) findViewById(R.id.idLancamentoConsumo);
        edtMedia = (EditText) findViewById(R.id.idMediaConsumo);
        edtLeituraAnterior = (EditText) findViewById(R.id.idLeituraAnterior);
        edtMeta = (EditText) findViewById(R.id.idMeta);
        edtMediaDiaria = (EditText) findViewById(R.id.idConsumoDiario);
        dadosConsumoDAO = AppDatabase.getInstance(this).consumoDAO();

        refreshInformacoes();

        btnGravar = (Button) findViewById(R.id.btnGravar);
        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gerarArquivo();
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> goCadastroConsumo());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                abreJanelaDeCadastro();
                return true;
            case R.id.action_delete:
                apagarDados();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void refreshInformacoes(){
        List<ConsumoEntity> listaPaletesPersistidos = this.dadosConsumoDAO.lista();
        SharedPreferences preferences = getSharedPreferences("UltimaLeitura", MODE_PRIVATE);
        String qtd = preferences.getString("Qtd", "");
        if (qtd == ""){
            qtd = "0.00";
        }
        ArrayList<Consumo> listaConsumoASeremExibidos = new ArrayList<>();
        //this.listaPaletesLidas = new ArrayList<>();
        if(listaPaletesPersistidos.size() > 0) {
            for (ConsumoEntity listaConsumo : listaPaletesPersistidos) {
                Consumo consumoASerExibido = new Consumo();
                consumoASerExibido.setQtd(listaConsumo.getQtd());
                consumoASerExibido.setData(listaConsumo.getDataConsumo());
                listaConsumoASeremExibidos.add(consumoASerExibido);
            }
            ListaConsumoAdapter listaAdapter = new ListaConsumoAdapter(this, listaConsumoASeremExibidos);
            listviewConsumo.setAdapter(listaAdapter);
            listviewConsumo.setVisibility(View.VISIBLE);
            somaConsumo = dadosConsumoDAO.maior() - dadosConsumoDAO.menor();
            menorData = Date.valueOf(dadosConsumoDAO.menorData());
            maiorData = Date.valueOf(dadosConsumoDAO.maiorData());
            numDias = dadosConsumoDAO.numDias();
            mediaConsumo = ((double) somaConsumo/numDias) *30;
            mediaConsumoDiario = dadosConsumoDAO.maior() - getTop2();
        }else{
            listviewConsumo.setVisibility(View.GONE);
        }
        meta = (Double.parseDouble(qtd) + 14.00)-0.499;
        edtMedia.setText(String.valueOf(df2.format(mediaConsumo)));
        edtMediaDiaria.setText(String.valueOf(df2.format(mediaConsumoDiario)));
        edtLeituraAnterior.setText(qtd);
        edtMeta.setText(String.valueOf(df.format(meta)));

    }

    public Double getTop2(){
        List<ConsumoEntity> lista;
        lista = (ArrayList<ConsumoEntity>) dadosConsumoDAO.top2();
        Double maior=0.00, menor=0.00;
        for (ConsumoEntity l : lista){
           menor = l.getQtd();
        }
        return menor;
    }
    public void abreJanelaDeCadastro(){
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View viewDialog = layoutInflater.inflate(R.layout.dialog_signin, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(viewDialog);

        TextView tvAdicionar = viewDialog.findViewById(R.id.tvAdicionar);
        TextView tvFechar = viewDialog.findViewById(R.id.tvFechar);

        final EditText editQtdUltimaLeitura = viewDialog.findViewById(R.id.editQtdUltimaLeitura);
        final EditText editDataUltimaLeitura = viewDialog.findViewById(R.id.editDataUltimaLeitura);

        //editQtdUltimaLeitura.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(9)});

        AlertDialog dialog = alertDialogBuilder.create();

        dialog.setCancelable(false);

        tvAdicionar.setOnClickListener(v -> {
            salvarUltimaLeitura(editQtdUltimaLeitura.getText().toString(), editDataUltimaLeitura.getText().toString());
            dialog.dismiss();
        });

        tvFechar.setOnClickListener(v -> {
            dialog.dismiss();
            //refreshInformacoes();
        });

        dialog.show();
    }
    private void salvarUltimaLeitura(String editQtdUltimaLeitura, String editDataUltimaLeitura) {
        preferences = getSharedPreferences("UltimaLeitura", MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();
        dados.putString("Qtd", String.valueOf(editQtdUltimaLeitura));
        dados.putString("Data", String.valueOf(editDataUltimaLeitura));
        dados.apply();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void gerarArquivo(){
        //Monta as informações a serem enviadas para o arquivo
        ArrayList<Consumo> lista = new ArrayList<>();
        List<ConsumoEntity> listas = dadosConsumoDAO.lista();

        if(listas.size() > 0){
            String state = Environment.getExternalStorageState();
            for(ConsumoEntity consumo : listas){

                String dataConsumo = consumo.getDataConsumo();
                Double qtdConsumo = consumo.getQtd();

                dadosArquivo = dadosArquivo + dataConsumo + "  " +qtdConsumo +"m³" + "\r\n";

            }
            LocalDate dataAtual = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy");
            String nomeArquivo = "Controle de Agua_" + dataAtual.format(formatter) + ".txt";
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                if (Build.VERSION.SDK_INT >= 23) {
                    if (checkPermission()) {
                        File sdcard = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                        File dir = new File(sdcard.getAbsolutePath() + "/ControdeDeAgua/");
                        dir.mkdirs();
                        File file = new File(dir, nomeArquivo);
                        FileOutputStream os = null;
                        try {
                            os = new FileOutputStream(file);
                            os.write(dadosArquivo.getBytes());
                            os.close();

                            transmitirDados();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        requestPermission(); // Code for permission
                    }
                } else {
                    File sdcard = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                    File dir = new File(sdcard.getAbsolutePath() + "/ControdeDeAgua/");
                    dir.mkdir();
                    File file = new File(dir, nomeArquivo);
                    FileOutputStream os = null;
                    try {
                        os = new FileOutputStream(file);
                        os.write(dadosArquivo.getBytes());
                        os.close();

                        transmitirDados();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else{
            Mensagem("Não há dados para gravar");
        }
    }

    public void transmitirDados(){
        //Deleta as informações Palete
        //dadosConsumoDAO.apagaTudo();
        //Envia mensagem
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Dados Gravados com Sucesso na pasta Downloads/ControdeDeAgua")
                .setTitle("Atenção!!!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialogInterface, @SuppressWarnings("unused") final int id) {
                        refreshInformacoes();
//                        Intent intent = new Intent(RecebimentoPaleteActivity.this, MenuPrincipalActivity.class);
//                        startActivity(intent);
                       // finish();
                    }
                });
        builder.show();
    }
    public void apagarDados(){
        new MaterialAlertDialogBuilder(this,
                R.style.Widget_AppCompat_ActionBar_Solid)
                .setTitle("Atenção")
                .setMessage("Deseja excluir os dados?")
                .setNeutralButton(R.string.label_cancelar,(dialogInterface, i) -> {
                    dialogInterface.dismiss();
                })
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    dadosConsumoDAO.apagaTudo();
                    dialogInterface.dismiss();
                    mediaConsumoDiario =0.00;
                    mediaConsumo = 0.00;
                    refreshInformacoes();

                })
                .show();

    }
    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            //customizaEExibeMensagem("Esta aplicação precisa de permissão para uso do drive local. ");
            Mensagem("Esta aplicação precisa de permissão para uso do drive local.");
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }
    public void Mensagem (String msg){
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(msg)
                .setTitle("Atenção!!!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialogInterface, @SuppressWarnings("unused") final int id) {
                    }
                }).show();
    }

}