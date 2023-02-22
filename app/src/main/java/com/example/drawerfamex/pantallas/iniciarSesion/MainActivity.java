package com.example.drawerfamex.pantallas.iniciarSesion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawerfamex.API.api.ApiFamexService;
import com.example.drawerfamex.API.models.ApiFichaAnuncio;
import com.example.drawerfamex.API.models.ApiFichaChalet;
import com.example.drawerfamex.API.models.ApiFichaConferencia;
import com.example.drawerfamex.API.models.ApiFichaExpo;
import com.example.drawerfamex.API.models.ApiFichaFrancia;
import com.example.drawerfamex.API.models.ApiFichaHoteles;
import com.example.drawerfamex.API.models.ApiFichaInicio;
import com.example.drawerfamex.API.models.ApiFichaPDFS;
import com.example.drawerfamex.API.models.ApiFichaPabellon;
import com.example.drawerfamex.API.models.ApiNumeroVersionesRes;
import com.example.drawerfamex.API.models.ApiRespuesta;
import com.example.drawerfamex.BaseDatos.entidades.fichaAnuncio;
import com.example.drawerfamex.BaseDatos.entidades.fichaChalets;
import com.example.drawerfamex.BaseDatos.entidades.fichaConferencia;
import com.example.drawerfamex.BaseDatos.entidades.fichaExpo;
import com.example.drawerfamex.BaseDatos.entidades.fichaHoteles;
import com.example.drawerfamex.BaseDatos.entidades.fichaInicio;
import com.example.drawerfamex.BaseDatos.entidades.fichaPabellon;
import com.example.drawerfamex.BaseDatos.viewModel.FichasAnuncioVM;
import com.example.drawerfamex.BaseDatos.viewModel.FichasChalViewModel;
import com.example.drawerfamex.BaseDatos.viewModel.FichasConfViewModel;
import com.example.drawerfamex.BaseDatos.viewModel.FichasExpoViewModel;
import com.example.drawerfamex.BaseDatos.viewModel.FichasHotelesViewModel;
import com.example.drawerfamex.BaseDatos.viewModel.FichasInicioVM;
import com.example.drawerfamex.BaseDatos.viewModel.FichasPabVieModel;
import com.example.drawerfamex.Constants;
import com.example.drawerfamex.adapters.BasicImageDownloader;
import com.example.drawerfamex.pantallas.bienvenida.pVideoBienv;
import com.example.drawerfamex.item.ItemAdapter;
import com.example.drawerfamex.R;
import com.example.drawerfamex.pantallas.inicio.pInicio;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    //QUE SOLO INGRESE NOMBRE E INICIE LA APP
    Button btnIniciarSesion;
    int[] flagsIcons = {R.drawable.mx,R.drawable.france,R.drawable.usa};


    //CLAVES DE ACCESO DEFAULT PREFERENCES POP UP / MENU HAMBURGESA
    String IDIOMA_POP = "POP";
    String IDIOMA_SPINNER = "SPINNER";
    String VERSION = "Version";
    protected String NOMBRE_USUARIO = "NOMBRE USUARIO";
    private Retrofit APInVersiones;

    //Karen
    EditText nombreUsuario;
    Button btningresar;
    //BD
    private FichasPabVieModel PabViewModel;
    private FichasChalViewModel ChalViewModel;
    private FichasExpoViewModel ExpoViewModel;
    private FichasConfViewModel ConfViewModel;
    private FichasInicioVM InitViewModel;
    private FichasAnuncioVM AnunViewModel;
    private FichasHotelesViewModel HotelViewModel;

    //Actualizacion
    private BasicImageDownloader downloader;
    private ProgressDialog progressDialog;
    private int max = 1, maxf = 1, maxT;
    private static final String fversion = "version";
    private String URLnVer = "https://appfamex23.web.app/";
    private boolean check = false;
    private int version, versionMAX, contador=0;
    ArrayList<String> links;
    ConstraintLayout contenedor;

    private SharedPreferences prefs;
    private SharedPreferences.Editor edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Idioma Inicial
        prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        edit = prefs.edit();
        cambioIdioma(prefs.getInt(IDIOMA_SPINNER,0));

        //Inicio de Activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicilizar Variables
        contenedor = (ConstraintLayout) findViewById(R.id.constrainLogIn);
        nombreUsuario = findViewById(R.id.log_1_lbl_iNombreUsuario);
        btningresar = findViewById(R.id.log_3_btn_log);

        nombreUsuario.setText(prefs.getString(NOMBRE_USUARIO,""));
        version = prefs.getInt(VERSION,0);


        nombreUsuario.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE ||
                        actionId == EditorInfo.IME_ACTION_GO ||
                        actionId == EditorInfo.IME_ACTION_NEXT ||
                        actionId == EditorInfo.IME_ACTION_SEND) {
                    // Ocultar teclado
                    String userName = nombreUsuario.getText().toString();
                    if(userName.isEmpty()){
                        Toast.makeText(getBaseContext(), getString(R.string.LOGIN_EDIT_TOAST), Toast.LENGTH_SHORT).show();
                    }else {
                        edit.putString(NOMBRE_USUARIO, nombreUsuario.getText().toString());
                        edit.apply();
                        boolean previouslyStarted = prefs.getBoolean(getString(R.string.prev_star), false);

                        if(!previouslyStarted) {
                            SharedPreferences.Editor edit = prefs.edit();
                            edit.putBoolean(getString(R.string.prev_star), Boolean.TRUE);
                            edit.commit();
                            fadeTransitionJava(contenedor, pVideoBienv.class);
                        }else{
                            fadeTransitionJava(contenedor, pInicio.class);

                        }
                    }
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(),getString(R.string.LOGIN_LOG_TOAST), Toast.LENGTH_SHORT).show();
            }
        });




        //Spinner for Language Selection
        Spinner flagSpinner = findViewById(R.id.mySpinner);
        flagSpinner.setOnItemSelectedListener(this);
        ItemAdapter myAdapter = new ItemAdapter(this,flagsIcons);
        flagSpinner.setAdapter(myAdapter);
        flagSpinner.setSelection(prefs.getInt(IDIOMA_SPINNER,0));

        flagSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if(prefs.getInt(IDIOMA_SPINNER,0) != position){
                    edit.putInt(IDIOMA_POP, position );
                    edit.putInt(IDIOMA_SPINNER, position);
                    edit.commit();

                    cambioIdioma(position);
                    finishAffinity();
                    redirectActivity(MainActivity.this, MainActivity.class);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        downloader = new BasicImageDownloader(new BasicImageDownloader.OnImageLoaderListener() {
            @Override
            public void onError(BasicImageDownloader.ImageError error) {
                Toast.makeText(MainActivity.this, "Error code " + error.getErrorCode() + ": " +
                        error.getMessage(), Toast.LENGTH_LONG).show();
                error.printStackTrace();
                downloader.DWN--;
                actualizarDialog();

                //Actualizar Dialog ->

            }

            @Override
            public void onProgressChange(int percent) {

            }

            @Override
            public void onComplete(Bitmap result, String Name) {
                /* save the image - I'm gonna use JPEG */
                final Bitmap.CompressFormat mFormat = Bitmap.CompressFormat.PNG;
                /* don't forget to include the extension into the file name */
                final File myImageFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() +
                        File.separator + "recursos" + File.separator + Name + "." + mFormat.name().toLowerCase());

                BasicImageDownloader.writeToDisk(myImageFile, result, new BasicImageDownloader.OnBitmapSaveListener() {
                    @Override
                    public void onBitmapSaved() {

                        //Toast.makeText(MainActivity.this, "Image saved as: " + myImageFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
                        //Actualizar Dialog
                        downloader.DWN--;
                        actualizarDialog();
                    }

                    @Override
                    public void onBitmapSaveError(BasicImageDownloader.ImageError error) {
                        Toast.makeText(MainActivity.this, "Error code " + error.getErrorCode() + ": " +
                                error.getMessage(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                        //Actualizar Dialog
                        downloader.DWN--;
                        actualizarDialog();
                    }


                }, mFormat, true);


            }
        });
           checarVersion();
    }

    //METODO CAMBIO IDIOMA
    public void cambioIdioma(int i){
        // Obtener la configuración actual
        Configuration config = getResources().getConfiguration();
        Locale locale = null;

        // Crear un nuevo objeto Locale con el idioma deseado
        if(i == 0){
            locale = new Locale("es");
        }else if(i == 1){
            locale = new Locale("fr");
        }else{
            locale = new Locale("en");
        }


        // Establecer el nuevo idioma en la configuración
        config.setLocale(locale);

        // Actualizar la configuración de la actividad
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }

    //Funcion Transicion
    public void fadeTransitionJava(View view, Class clase){
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        Intent i = new Intent(MainActivity.this,clase);
        i.putExtra(Constants.KEY_ANIM_TYPE,Constants.TransitionType.FadeJava);
        i.putExtra(Constants.KEY_TITLE,"Fade by Java");
        startActivity(i,options.toBundle());
    }

    //Super Cool Method to Redirect Activities!!
    public static void redirectActivity(Activity activity,Class aClass) {
        //Initialize Intent
        Intent intent = new Intent(activity,aClass);
        //SetFlag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //Start Activity
        activity.startActivity(intent);

    }

    private void checarVersion(){
        APInVersiones = new Retrofit.Builder()
                .baseUrl(URLnVer)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiFamexService service = APInVersiones.create(ApiFamexService.class);
        Call<ApiNumeroVersionesRes> FamexRespuestaCall = service.obtenerNumeroVersiones();

        FamexRespuestaCall.enqueue(new Callback<ApiNumeroVersionesRes>(){

            @Override
            public void onResponse(Call<ApiNumeroVersionesRes> call, Response<ApiNumeroVersionesRes> response) {
                if (response.isSuccessful()) {
                    ApiNumeroVersionesRes respuesta = response.body();
                    if(respuesta.getNumeroVersiones() != version){
                        versionMAX = respuesta.getNumeroVersiones();
                        Log.e("ERROR","a" + respuesta.getNumeroVersiones());
                        links = new ArrayList<>();
                        for(int i = version;i<versionMAX;i++){
                            String URL = "https://appfamex23.web.app/";
                            URL = URL + fversion + i + "/";
                            Log.e("ERROR","a" + URL);
                            links.add(URL);
                        }
                        jsonDB(links.get(contador++));
                    }else{
                        accionBtnIngresar();
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiNumeroVersionesRes> call, Throwable t) {

            }
        });

    }

    private void jsonDB(String link){
        Retrofit apiVersiones = new Retrofit.Builder()
                .baseUrl(link)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ApiFamexService service = apiVersiones.create(ApiFamexService.class);
        Call<ApiRespuesta> FamexRespuestaCall = service.obtenerVersion();

        FamexRespuestaCall.enqueue(new Callback<ApiRespuesta>() {
            @Override
            public void onResponse(Call<ApiRespuesta> call, Response<ApiRespuesta> response) {

                if (response.isSuccessful()) {

                    progressDialog = new ProgressDialog(MainActivity.this);


                    initViewModels();

                    ApiRespuesta respuesta = response.body();

                    final Bitmap.CompressFormat mFormat = Bitmap.CompressFormat.PNG;

                    String ID = "CONF";
                    if(respuesta.getVersion().getFichasConferencias() != null)
                    for(int i = 0; i<respuesta.getVersion().getFichasConferencias().size(); i++){
                        fichaConferencia dbFicha;
                        ApiFichaConferencia apiFicha = respuesta.getVersion().getFichasConferencias().get(i);
                        dbFicha = new fichaConferencia(apiFicha.getId(),apiFicha.getNombre(),apiFicha.gethInicio(),apiFicha.gethFin(),apiFicha.getExpositor(),apiFicha.getnBlok(),apiFicha.isFrancia(),apiFicha.getnDia(), apiFicha.getDescEs(), apiFicha.getDescEn(), apiFicha.getDescFr(), false);
                        ConfViewModel.insertFicha(dbFicha, new FichasConfViewModel.OnFichaSaveListenerVM() {
                            @Override
                            public void onFichaSavedVM() {
                                actualizarDialog();
                            }
                        });
                    }

                    //Log.e("ERROR","a"+ respuesta.getVersion().getFichasConferencias().get(0).getNombre());


                    ID = "CHAL";
                    if(respuesta.getVersion().getFichasChalets() != null)
                    for(int i = 0;i<respuesta.getVersion().getFichasChalets().size();i++){
                        Log.e("ERROR","a"+ respuesta.getVersion().getFichasChalets().get(i).getImagen());
                        fichaChalets dbFicha;
                        ApiFichaChalet apiFicha = respuesta.getVersion().getFichasChalets().get(i);
                        String nombreIMG = ID+apiFicha.getId();
                        downloader.download(apiFicha.getImagen(),false,nombreIMG);
                        String path = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() +
                                File.separator + "recursos" + File.separator + nombreIMG + "." + mFormat.name().toLowerCase();
                        dbFicha = new fichaChalets(apiFicha.getId(),apiFicha.getNombre(),path, apiFicha.getnBloque(),apiFicha.isFrancia(),apiFicha.getDescripcionES(),apiFicha.getDescripcionEN(), apiFicha.getDescripcionFR());
                        ChalViewModel.insertFicha(dbFicha, new FichasChalViewModel.OnFichaSaveListenerVM() {
                            @Override
                            public void onFichaSavedVM() {
                                actualizarDialog();
                            }
                        });
                    }

                    ID = "PAB";
                    if(respuesta.getVersion().getFichasPabellones() != null)
                    for(int i = 0;i<respuesta.getVersion().getFichasPabellones().size();i++){
                        fichaPabellon dbFicha;
                        ApiFichaPabellon apiFicha = respuesta.getVersion().getFichasPabellones().get(i);
                        String nombreIMG = ID+apiFicha.getId();
                        downloader.download(apiFicha.getImagen(),false,nombreIMG);
                        String path = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() +
                                File.separator + "recursos" + File.separator + nombreIMG + "." + mFormat.name().toLowerCase();
                        dbFicha = new fichaPabellon(apiFicha.getId(), apiFicha.getDescripcionES(), apiFicha.getDescripcionEN(), apiFicha.getDescripcionFR(), path, apiFicha.getnPabellon(), apiFicha.isFrancia(), apiFicha.getPosicion());
                        PabViewModel.insertFicha(dbFicha, new FichasPabVieModel.OnFichaSaveListenerVM() {
                            @Override
                            public void onFichaSavedVM() {
                                actualizarDialog();
                            }
                        });
                    }

                    ID = "EXPO";
                    if(respuesta.getVersion().getFichasExpo() != null)
                    for(int i = 0;i<respuesta.getVersion().getFichasExpo().size();i++){
                        fichaExpo dbFicha;
                        ApiFichaExpo apiFicha = respuesta.getVersion().getFichasExpo().get(i);
                        String nombreIMG = ID+apiFicha.getId();
                        downloader.download(apiFicha.getImagen(),false,nombreIMG);
                        String path = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() +
                                File.separator + "recursos" + File.separator + nombreIMG + "." + mFormat.name().toLowerCase();
                        dbFicha = new fichaExpo(apiFicha.getId(), apiFicha.getNombre(), apiFicha.isFrancia(), apiFicha.getDescEs(), apiFicha.getDescEn(), apiFicha.getDescFr(),path,apiFicha.getnBlok());
                        ExpoViewModel.insertFicha(dbFicha, new FichasExpoViewModel.OnFichaSaveListenerVM() {
                            @Override
                            public void onFichaSavedVM() {
                                actualizarDialog();
                            }
                        });
                    }

                    ID = "INICIO";
                    String path1= null;
                    String path2 = null;
                    String path3= null;
                    if(respuesta.getVersion().getFichasInicio() != null)
                    for(int i = 0;i<respuesta.getVersion().getFichasInicio().size();i++){
                        fichaInicio dbFicha;
                        ApiFichaInicio apiFicha = respuesta.getVersion().getFichasInicio().get(i);
                        if(apiFicha.getImagen1() != null){
                            String nombreIMG = ID+apiFicha.getId()+1;
                            downloader.download(apiFicha.getImagen1(),false,nombreIMG);
                            path1 = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + "recursos" +
                                    File.separator + nombreIMG + "." + mFormat.name().toLowerCase();
                        }
                        if(apiFicha.getImagen2() != null){
                            String nombreIMG = ID+apiFicha.getId()+2;
                            downloader.download(apiFicha.getImagen2(),false,nombreIMG);
                            path2 = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + "recursos" +
                                    File.separator + nombreIMG + "." + mFormat.name().toLowerCase();
                        }
                        if(apiFicha.getImagen3() != null){
                            String nombreIMG = ID+apiFicha.getId()+3;
                            downloader.download(apiFicha.getImagen3(),false,nombreIMG);
                            path3 = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + "recursos" +
                                    File.separator + nombreIMG + "." + mFormat.name().toLowerCase();
                        }

                        dbFicha = new fichaInicio(apiFicha.getId(),path1,path2,path3);
                        InitViewModel.insertFicha(dbFicha, new FichasInicioVM.OnFichaSaveListenerVM() {
                            @Override
                            public void onFichaSavedVM() {
                                actualizarDialog();
                            }
                        });

                    }

                    ID = "ANUN";
                    path1 = null;
                    path2 = null;
                    path3 = null;
                    if(respuesta.getVersion().getFichasAnuncios() != null)
                    for(int i = 0;i<respuesta.getVersion().getFichasAnuncios().size();i++){
                        fichaAnuncio dbFicha;
                        ApiFichaAnuncio apiFicha = respuesta.getVersion().getFichasAnuncios().get(i);
                        if(apiFicha.getImagen1() != null){
                            String nombreIMG = ID+apiFicha.getId()+1;
                            downloader.download(apiFicha.getImagen1(),false,nombreIMG);
                            path1 = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + "recursos" +
                                    File.separator + nombreIMG + "." + mFormat.name().toLowerCase();
                        }
                        if(apiFicha.getImagen2() != null){
                            String nombreIMG = ID+apiFicha.getId()+2;
                            downloader.download(apiFicha.getImagen2(),false,nombreIMG);
                            path2 = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + "recursos" +
                                    File.separator + nombreIMG + "." + mFormat.name().toLowerCase();
                        }
                        if(apiFicha.getImagen3() != null){
                            String nombreIMG = ID+apiFicha.getId()+3;
                            downloader.download(apiFicha.getImagen3(),false,nombreIMG);
                            path3 = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + "recursos" +
                                    File.separator + nombreIMG + "." + mFormat.name().toLowerCase();
                        }
                        dbFicha = new fichaAnuncio(apiFicha.getId(),path1,path2,path3);
                        AnunViewModel.insertFicha(dbFicha, new FichasAnuncioVM.OnFichaSaveListenerVM() {
                            @Override
                            public void onFichaSavedVM() {
                                //actualizar Dailog
                                actualizarDialog();
                            }
                        });
                    }

                    ID = "HOTEL";
                    path1 = null;
                    path2 = null;
                    path3 = null;
                    if(respuesta.getVersion().getFichasHoteles() != null)
                    for(int i = 0;i<respuesta.getVersion().getFichasHoteles().size();i++){
                        fichaHoteles dbFicha;
                        ApiFichaHoteles apiFicha =  respuesta.getVersion().getFichasHoteles().get(i);
                        if(apiFicha.getImagenEsp() != null){
                            String nombreIMG = ID+apiFicha.getId()+1;
                            downloader.download(apiFicha.getImagenEsp(),false,nombreIMG);
                            path1 = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + "recursos" +
                                    File.separator + nombreIMG + "." + mFormat.name().toLowerCase();
                        }
                        if(apiFicha.getImagenEn() != null){
                            String nombreIMG = ID+apiFicha.getId()+2;
                            downloader.download(apiFicha.getImagenEn(),false,nombreIMG);
                            path2 = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + "recursos" +
                                    File.separator + nombreIMG + "." + mFormat.name().toLowerCase();
                        }
                        if(apiFicha.getImagenFr() != null){
                            String nombreIMG = ID+apiFicha.getId()+3;
                            downloader.download(apiFicha.getImagenFr(),false,nombreIMG);
                            path3 = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + "recursos" +
                                    File.separator + nombreIMG + "." + mFormat.name().toLowerCase();
                        }

                        dbFicha = new fichaHoteles(apiFicha.getId(), path1,path2,path3, apiFicha.getLinkES(), apiFicha.getLinkEN(), apiFicha.getLinkFR());
                        HotelViewModel.insertFicha(dbFicha, new FichasHotelesViewModel.OnFichaSaveListenerVM() {
                            @Override
                            public void onFichaSavedVM() {
                                actualizarDialog();
                            }
                        });
                    }

                    ID = "FR";
                    ApiFichaFrancia franciaApificha = respuesta.getVersion().getFichaFrancia();
                    if(franciaApificha != null){
                        if(franciaApificha.getImagen0() != null){
                            String nombreIMG = ID+0;
                            downloader.download(franciaApificha.getImagen0(), false,nombreIMG);
                            path1 = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + "recursos" +
                                    File.separator + nombreIMG + "." + mFormat.name().toLowerCase();
                            edit.putString("FR0",path1);
                        }

                        if(franciaApificha.getImagen1() != null){
                            String nombreIMG = ID+1;
                            downloader.download(franciaApificha.getImagen1(), false,nombreIMG);
                            path1 = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + "recursos" +
                                    File.separator + nombreIMG + "." + mFormat.name().toLowerCase();
                            edit.putString("FR1",path1);
                        }

                        if(franciaApificha.getImagen2() != null){
                            String nombreIMG = ID+2;
                            downloader.download(franciaApificha.getImagen2(), false,nombreIMG);
                            path1 = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + "recursos" +
                                    File.separator + nombreIMG + "." + mFormat.name().toLowerCase();
                            edit.putString("FR2",path1);
                        }

                        edit.apply();
                    }


                    ID = "PDF";
                    ApiFichaPDFS pdfs = respuesta.getVersion().getFichaPDFs();
                    DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);

                    if(pdfs != null){
                        if(pdfs.getLink0() != null){
                            Uri uri = Uri.parse(pdfs.getLink0());
                            DownloadManager.Request request = new DownloadManager.Request(uri);
                            File pdfFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "recursos"+File.separator+ID+0+ "." + "pdf");
                            if(pdfFile.exists()){
                                pdfFile.delete();
                            }
                            Uri destinationUri = Uri.fromFile(pdfFile);
                            request.setDestinationUri(destinationUri);
                            downloadManager.enqueue(request);
                        }

                        if(pdfs.getLink1() != null){
                            Uri uri = Uri.parse(pdfs.getLink1());
                            DownloadManager.Request request = new DownloadManager.Request(uri);
                            File pdfFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "recursos"+File.separator+ID+1+ "." + "pdf");
                            if(pdfFile.exists()){
                                pdfFile.delete();
                            }
                            Uri destinationUri = Uri.fromFile(pdfFile);
                            request.setDestinationUri(destinationUri);
                            downloadManager.enqueue(request);
                        }

                        if(pdfs.getLink2() != null){
                            Uri uri = Uri.parse(pdfs.getLink2());
                            DownloadManager.Request request = new DownloadManager.Request(uri);
                            File pdfFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "recursos"+File.separator+ID+2+ "." + "pdf");
                            if(pdfFile.exists()){
                                pdfFile.delete();
                            }
                            Uri destinationUri = Uri.fromFile(pdfFile);
                            request.setDestinationUri(destinationUri);
                            downloadManager.enqueue(request);
                        }

                        if(pdfs.getLink3() != null){
                            Uri uri = Uri.parse(pdfs.getLink3());
                            DownloadManager.Request request = new DownloadManager.Request(uri);
                            File pdfFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "recursos"+File.separator+ID+3+ "." + "pdf");
                            if(pdfFile.exists()){
                                pdfFile.delete();
                            }
                            Uri destinationUri = Uri.fromFile(pdfFile);
                            request.setDestinationUri(destinationUri);
                            downloadManager.enqueue(request);
                        }

                        if(pdfs.getLink4() != null){
                            Uri uri = Uri.parse(pdfs.getLink4());
                            DownloadManager.Request request = new DownloadManager.Request(uri);
                            File pdfFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "recursos"+File.separator+ID+4+ "." + "pdf");
                            if(pdfFile.exists()){
                                pdfFile.delete();
                            }
                            Uri destinationUri = Uri.fromFile(pdfFile);
                            request.setDestinationUri(destinationUri);
                            downloadManager.enqueue(request);
                        }

                        if(pdfs.getLink5() != null){
                            Uri uri = Uri.parse(pdfs.getLink5());
                            DownloadManager.Request request = new DownloadManager.Request(uri);
                            File pdfFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "recursos"+File.separator+ID+5+ "." + "pdf");
                            if(pdfFile.exists()){
                                pdfFile.delete();
                            }
                            Uri destinationUri = Uri.fromFile(pdfFile);
                            request.setDestinationUri(destinationUri);
                            downloadManager.enqueue(request);
                        }

                        if(pdfs.getLink6() != null){
                            Uri uri = Uri.parse(pdfs.getLink6());
                            DownloadManager.Request request = new DownloadManager.Request(uri);
                            File pdfFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "recursos"+File.separator+ID+6+ "." + "pdf");
                            if(pdfFile.exists()){
                                pdfFile.delete();
                            }
                            Uri destinationUri = Uri.fromFile(pdfFile);
                            request.setDestinationUri(destinationUri);
                            downloadManager.enqueue(request);
                        }

                        if(pdfs.getLink7() != null){
                            Uri uri = Uri.parse(pdfs.getLink7());
                            DownloadManager.Request request = new DownloadManager.Request(uri);
                            File pdfFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "recursos"+File.separator+ID+7+ "." + "pdf");
                            if(pdfFile.exists()){
                                pdfFile.delete();
                            }
                            Uri destinationUri = Uri.fromFile(pdfFile);
                            request.setDestinationUri(destinationUri);
                            downloadManager.enqueue(request);
                        }

                        if(pdfs.getLink8() != null){
                            Uri uri = Uri.parse(pdfs.getLink8());
                            DownloadManager.Request request = new DownloadManager.Request(uri);
                            File pdfFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "recursos"+File.separator+ID+8+ "." + "pdf");
                            if(pdfFile.exists()){
                                pdfFile.delete();
                            }
                            Uri destinationUri = Uri.fromFile(pdfFile);
                            request.setDestinationUri(destinationUri);
                            downloadManager.enqueue(request);
                        }

                        if(pdfs.getLink9() != null){
                            Uri uri = Uri.parse(pdfs.getLink9());
                            DownloadManager.Request request = new DownloadManager.Request(uri);
                            File pdfFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "recursos"+File.separator+ID+9+ "." + "pdf");
                            if(pdfFile.exists()){
                                pdfFile.delete();
                            }
                            Uri destinationUri = Uri.fromFile(pdfFile);
                            request.setDestinationUri(destinationUri);
                            downloadManager.enqueue(request);
                        }
                    }

                    max = BasicImageDownloader.DWN;
                    maxf = BasicImageDownloader.FICHAS;
                    maxT = max + maxf;

                    progressDialog.setTitle(getString(R.string.LOGIN_ACT_TOAST));
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.setCancelable(false);
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressDialog.setMax(maxT);
                    progressDialog.setProgress(1);
                    check = true;


                    progressDialog.show();
                    if(maxT ==0){
                        progressDialog.dismiss();
                    }


                } else {
                    Log.e("ERROR","ERROR"+  response.errorBody());
                }
            }



                @Override
            public void onFailure(Call<ApiRespuesta> call, Throwable t) {
                Log.e("ERROR", " onFailure aaa: " + t.getMessage());
            }



        });


    }

    private void initViewModels(){
       PabViewModel = ViewModelProviders.of(this).get(FichasPabVieModel.class);
       ChalViewModel = ViewModelProviders.of(this).get(FichasChalViewModel.class);
       ExpoViewModel = ViewModelProviders.of(this).get(FichasExpoViewModel.class);
       ConfViewModel = ViewModelProviders.of(this).get((FichasConfViewModel.class));
       InitViewModel = ViewModelProviders.of(this).get((FichasInicioVM.class));
       AnunViewModel = ViewModelProviders.of(this).get((FichasAnuncioVM.class));
       HotelViewModel = ViewModelProviders.of(this).get((FichasHotelesViewModel.class));
    }

    private void actualizarDialog(){
        progressDialog.setProgress(maxT-(BasicImageDownloader.DWN+BasicImageDownloader.FICHAS));
        progressDialog.setTitle(getString(R.string.LOGIN_ACT_TOAST));
        if(BasicImageDownloader.DWN == 0 && BasicImageDownloader.FICHAS == 0){
            if(contador != versionMAX-version){
                edit.putInt(VERSION,(contador+version));
                jsonDB(links.get(contador++));
                edit.apply();
                progressDialog.dismiss();
            }else{
                accionBtnIngresar();
                progressDialog.dismiss();
                edit.putInt(VERSION,versionMAX);
                edit.apply();
            }
        }
    }

    private void accionBtnIngresar(){
        btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = nombreUsuario.getText().toString();
                if(userName.isEmpty()){
                    Toast.makeText(getBaseContext(), getString(R.string.LOGIN_EDIT_TOAST), Toast.LENGTH_SHORT).show();
                }else{
                    edit.putString(NOMBRE_USUARIO,nombreUsuario.getText().toString());
                    edit.apply();
                    boolean previouslyStarted = prefs.getBoolean(getString(R.string.prev_star), false);

                    if(!previouslyStarted) {
                        SharedPreferences.Editor edit = prefs.edit();
                        edit.putBoolean(getString(R.string.prev_star), Boolean.TRUE);
                        edit.commit();
                        fadeTransitionJava(contenedor, pVideoBienv.class);
                    }else{
                        fadeTransitionJava(contenedor, pInicio.class);

                    }
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
