package br.com.android.ppm.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.AdapterView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class MainActivity extends AppCompatActivity {
    private String[] nomeArquivosString;
    private String nomeArquivoSelecionado;


    private File sdcard = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
    private File dir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        // Leitura dos nomes dos arquivos que estão dentro do diretório para importação
        dir = new File(sdcard.getAbsolutePath() + "/smartpa_params/");
        dir.mkdir();

        nomeArquivoSelecionado = "fs16xx_01s_mono.preset";
        xcopy teste /tmp

    }

    File file = new File(dir,nomeArquivoSelecionado);

    StringBuilder text = new StringBuilder();
    BufferedReader br = new BufferedReader(new FileReader(file));
    String line;


}