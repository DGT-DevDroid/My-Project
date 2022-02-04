package br.com.android.ppm.controleconsumodeagua;

import static java.lang.Integer.parseInt;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import br.com.android.ppm.controleconsumodeagua.dao.ConsumoDAO;
import br.com.android.ppm.controleconsumodeagua.entity.ConsumoEntity;
import br.com.android.ppm.controleconsumodeagua.persistence.AppDatabase;

public class CadastroConsumoActivity extends AppCompatActivity {
    private ConsumoDAO listaConsumoDAO;
    final Calendar myCalendar= Calendar.getInstance();
    private TextView txtqtd;
    private TextView txtdata;
    private Button btnCadastrar;
    EditText idData;
    private ConsumoDAO dadosConsumoDAO;
    private Double mediaConsumoDiario = 0.00;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_consumo);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        idData=(EditText) findViewById(R.id.idData);
        listaConsumoDAO = AppDatabase.getInstance(this).consumoDAO();
        txtqtd       = findViewById(R.id.idConsumo);
        txtdata      = findViewById(R.id.idData);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        List<ConsumoEntity> listaConsumo = this.listaConsumoDAO.lista();
//        if (listaConsumo.size()>0){
//            mediaConsumoDiario = listaConsumoDAO.maior() - getTop2();
//        }
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gravaDadosConsumo(Double.parseDouble(txtqtd.getText().toString()), txtdata.getText().toString());
                //limpaCamposCadastro(txtqtd, txtdata);
                Toast.makeText(CadastroConsumoActivity.this, getString(R.string.cadastro_com_sucesso), Toast.LENGTH_SHORT).show();
                goMainActivity();
            }
        });


        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        idData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(CadastroConsumoActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==android.R.id.home) {
            finish();
            return true;
        }
        return false;
    }
    private void limpaCamposCadastro(TextView txtqtd, TextView txtdata) {
        txtqtd.setText(null);
        txtdata.setText(null);
    }
    public Double getTop2(){
        List<ConsumoEntity> lista;
        lista = (ArrayList<ConsumoEntity>) listaConsumoDAO.top2();
        Double maior=0.00, menor=0.00;
        for (ConsumoEntity l : lista){
            menor = l.getQtd();
        }
        return menor;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void gravaDadosConsumo(Double qtd, String data) {
        //SimpleDateFormat fmtData = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        ConsumoEntity dadosConsumo = new ConsumoEntity();
        //int id =  +listaConsumoDAO.lista().size();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data1 = LocalDate.parse(data, formato);

        if (listaConsumoDAO.lista().size() >0){
            mediaConsumoDiario = qtd - listaConsumoDAO.maior();
        }

        dadosConsumo.setQtd(qtd);
        dadosConsumo.setDataConsumo(data1.toString());
        dadosConsumo.setConsumoDiario(mediaConsumoDiario);
        int id = listaConsumoDAO.ultimoId() + 1;
        dadosConsumo.setIdConsumo(id);
        listaConsumoDAO.adiciona(dadosConsumo);
    }
    public void goMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void updateLabel() {

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt","BR"));

        idData.setText(sdf.format(myCalendar.getTime()));
    }
}