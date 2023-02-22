package com.example.drawerfamex.pantallas.itinerario;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.drawerfamex.BaseDatos.entidades.fichaConferencia;
import com.example.drawerfamex.BaseDatos.viewModel.FichasConfViewModel;
import com.example.drawerfamex.BuildConfig;
import com.example.drawerfamex.adapters.confAdapter;
import com.example.drawerfamex.adapters.itAdapter;
import com.example.drawerfamex.adapters.pMenu;
import com.example.drawerfamex.pantallas.evento.conferencias.fragAuditorio;
import com.example.drawerfamex.popups.tutorial.fragPantallaTutorial;
import com.example.drawerfamex.item.Item;
import com.example.drawerfamex.item.Item4;
import com.example.drawerfamex.pantallas.perfil.pPerfil;
import com.example.drawerfamex.R;
import com.example.drawerfamex.RecyclerAdapter;
import com.example.drawerfamex.documentPDF;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class pItinerario extends pMenu {

    //Initialize Variable
    private ConstraintLayout clsinfo1,clsinfo2,clsinfo3;
    private ImageView imgcompatir;
    private RecyclerView rcvdia1,rcvdia2,rcvdia3;
    private itAdapter adapter,adapter2,adapter3;
    private List<fichaConferencia> dia_1 = new ArrayList<>();
    private List<fichaConferencia> dia_2 = new ArrayList<>();
    private List<fichaConferencia> dia_3 = new ArrayList<>();
    private boolean readpermission = false;
    private boolean writepermission = false;
    private ActivityResultLauncher<String[]> permissionresultlauncher;
    private RecyclerView.LayoutManager layoutManager,layoutManager2,layoutManager3;
    private Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.aad_4_itinerario);
        super.onCreate(savedInstanceState);
     //   setMenu();

        //Assign Variable
        myDialog = new Dialog(this);
        clsinfo1 = findViewById(R.id.clsinfo1);
        clsinfo2 = findViewById(R.id.clsinfo2);
        clsinfo3 = findViewById(R.id.clsinfo3);

        final ScrollView scrollview = ((ScrollView) findViewById(R.id.scrollView3));

        imgcompatir = findViewById(R.id.imgvshareditinerario);
        //Icono Controlador
        ImageView igvabajo1 = findViewById(R.id.igvabajo1);
        ImageView igvabajo2 = findViewById(R.id.igvabajo2);
        ImageView igvabajo3 = findViewById(R.id.igvabajo3);
        ImageView igvarriba1 = findViewById(R.id.igvarriba1);
        ImageView igvarriba2 = findViewById(R.id.igvarriba2);
        ImageView igvarriba3 = findViewById(R.id.igvarriba3);
        ImageView igvCambiarfoto = findViewById(R.id.immviusuario);


        //Foto perfil
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        igvCambiarfoto.setImageResource(prefs.getInt(ICONO,iconouser[0]));

        //Incializa RV
        rcvdia1 = findViewById(R.id.rcvdia1);
        rcvdia1.setHasFixedSize(true);
        rcvdia1.setNestedScrollingEnabled(false); //
        layoutManager = new LinearLayoutManager(this);

        rcvdia2 = findViewById(R.id.rcvdia2);
        rcvdia2.setHasFixedSize(true);
        rcvdia2.setNestedScrollingEnabled(true); //
        layoutManager2 = new LinearLayoutManager(this);

        rcvdia3 = findViewById(R.id.rcvdia3);
        rcvdia3.setHasFixedSize(true);
        rcvdia3.setNestedScrollingEnabled(true); //
        layoutManager3 = new LinearLayoutManager(this);
//
        //Inicaliza adapter
        adapter = new itAdapter(pItinerario.this.getBaseContext(), 0,pItinerario.this);
        adapter2 = new itAdapter(pItinerario.this.getBaseContext(), 1,pItinerario.this);
        adapter3 = new itAdapter(pItinerario.this.getBaseContext(), 2,pItinerario.this);

        //get Datos
        FichasConfViewModel viewModel = ViewModelProviders.of(this).get(FichasConfViewModel.class);
        viewModel.getFichasConfIT(0,true);
        viewModel.getListaFichas().observe(this, lista -> {

            if(lista == null || adapter.getItemCount() != 0){
                return;
            }

            adapter.adicionarListaFichas(lista);
            dia_1 = lista;

        });

        viewModel.getFichasConfIT(1,true);
        viewModel.getListaFichas().observe(this, lista -> {

            if(lista == null || adapter2.getItemCount() != 0){
                return;
            }

            adapter2.adicionarListaFichas(lista);
            dia_2 = lista;

        });

        viewModel.getFichasConfIT(2,true);
        viewModel.getListaFichas().observe(this, lista -> {

            if(lista == null || adapter3.getItemCount() != 0){
                return;
            }

            adapter3.adicionarListaFichas(lista);
            dia_3 = lista;

        });



        //verificar permisos
        try {
            permissionresultlauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
                if(result.get(Manifest.permission.READ_EXTERNAL_STORAGE)!=null){
                    readpermission = result.get(Manifest.permission.READ_EXTERNAL_STORAGE);
                }
                if(result.get(Manifest.permission.READ_EXTERNAL_STORAGE)!=null){
                    writepermission = result.get(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                }
            });
        }catch (Exception e){

        }

        requestpermissions();
        /*

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rcvdia1);

        ItemTouchHelper itemTouchHelper2 = new ItemTouchHelper(simpleCallback);
        itemTouchHelper2.attachToRecyclerView(rcvdia2);

        ItemTouchHelper itemTouchHelper3 = new ItemTouchHelper(simpleCallback);
        itemTouchHelper3.attachToRecyclerView(rcvdia3);


        //Even for the Tickets Butons
        boletos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri Link = Uri.parse(link);
                Intent i = new Intent(Intent.ACTION_VIEW,Link);
                startActivity(i);
            }
        });

         */

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        imgcompatir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                documentPDF.createPDF(getBaseContext());
                String name = "FAMEX.pdf";
                File file;
                Uri uri;
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                    file = getBaseContext().getExternalFilesDir(null);
                    file = new File(file,name);
                }else{
                    file = new File(Environment.getExternalStorageDirectory() + "/"+name);
                }
                try {
                    if (file.exists()) {
                        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            uri =  FileProvider.getUriForFile(getBaseContext(), BuildConfig.APPLICATION_ID + ".provider", file);
                            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            //intent.setDataAndType(uri, "application/pdf");
                        } else {
                            uri = Uri.fromFile(file);
                            //intent.setDataAndType(uri, "application/pdf");
                        }
                        if (uri != null) {
                            intent.setType("application/pdf");
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            intent.putExtra(Intent.EXTRA_STREAM, uri);
                            // set flag to give temporary permission to external app to use your FileProvider
                            intent.putExtra(Intent.EXTRA_SUBJECT, "FAMEX");
                            intent.putExtra(Intent.EXTRA_TEXT, "Te hacemos la cordial bienvenida a FAMEX 2023");
                            Toast.makeText(getBaseContext(), "¡Disfruta de tus actividades y comparte con tus amigos!", Toast.LENGTH_SHORT).show();
                            Intent shareIntent = Intent.createChooser(intent, "Compatir");
                            PackageManager pm = getApplicationContext().getPackageManager();
                            try{
                                if (intent.resolveActivity(pm) != null) {
                                    startActivity(shareIntent);
                                }
                            }catch (Exception e){
                                Log.e("ERROR","ERROR"+e);
                                Toast.makeText(getBaseContext(), "No esta disponible", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }else{
                        Toast.makeText(getBaseContext(), "El archivo no existe", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Log.e("ERROR","ERROR"+e);
                    Toast.makeText(getBaseContext(), "No se pudo compartir", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final boolean[] bandera = {true};
        final boolean[] bandera2 = {true};
        final boolean[] bandera3 = {true};



        igvabajo1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (bandera[0] == true){
                    rcvdia1.setLayoutManager(layoutManager);
                    rcvdia1.setAdapter(adapter);
                }
                clsinfo1.setVisibility(View.VISIBLE);
                igvabajo1.setVisibility(View.INVISIBLE);
                igvarriba1.setVisibility(View.VISIBLE);
                scrollview.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollview.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });
            }
        });

        igvarriba1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                clsinfo1.setVisibility(View.GONE);
                igvabajo1.setVisibility(View.VISIBLE);
                igvarriba1.setVisibility(View.INVISIBLE);
                bandera[0] = false;
            }
        });

        igvabajo2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (bandera2[0] == true){
                    rcvdia2.setLayoutManager(layoutManager2);
                    rcvdia2.setAdapter(adapter2);
                }
                clsinfo2.setVisibility(View.VISIBLE);
                igvabajo2.setVisibility(View.INVISIBLE);
                igvarriba2.setVisibility(View.VISIBLE);
                scrollview.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollview.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });
            }
        });

        igvarriba2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                clsinfo2.setVisibility(View.GONE);
                igvabajo2.setVisibility(View.VISIBLE);
                igvarriba2.setVisibility(View.INVISIBLE);
                bandera2[0] = false;

            }
        });

        igvabajo3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (bandera3[0] == true){
                    rcvdia3.setLayoutManager(layoutManager3);
                    rcvdia3.setAdapter(adapter3);
                }
                clsinfo3.setVisibility(View.VISIBLE);
                igvabajo3.setVisibility(View.INVISIBLE);
                igvarriba3.setVisibility(View.VISIBLE);
                scrollview.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollview.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });
            }
        });

        igvarriba3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                clsinfo3.setVisibility(View.GONE);
                igvabajo3.setVisibility(View.VISIBLE);
                igvarriba3.setVisibility(View.INVISIBLE);
                bandera3[0] = false;
            }
        });


        igvCambiarfoto.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                    startButtonAnimationUser(igvCambiarfoto);
            }
        });

    }

    /*

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,0) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int fromPosition  = viewHolder.getAbsoluteAdapterPosition();
            int toPosition =target.getAdapterPosition();
            Collections.swap(items,fromPosition,toPosition);
            recyclerView.getAdapter().notifyItemMoved(fromPosition,toPosition);
            return false;
        }
        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };

     */

    //Animation Shit
    public void startButtonAnimationUser(ImageView view) {
        Animation scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);
        view.setAnimation(scaleDown);
        view.startAnimation(scaleDown);

        scaleDown.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(getApplicationContext(), pPerfil.class));
            }
        });
    }
    private void requestpermissions( ){
        boolean minSDK = Build.VERSION.SDK_INT>=Build.VERSION_CODES.Q;
        readpermission = ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) ==PackageManager.PERMISSION_GRANTED;
        writepermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ==PackageManager.PERMISSION_GRANTED;
        writepermission =  writepermission||minSDK;
        List<String> permissionRequest = new ArrayList<String>();
        if(!readpermission){
            permissionRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if(!writepermission){
            permissionRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(!permissionRequest.isEmpty()){
            permissionresultlauncher.launch(permissionRequest.toArray(new String[0]));
        }
        documentPDF pdf = new documentPDF();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Pantalla tutorial
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = prefs.getBoolean(getString(R.string.p_itinerario), false);

        if(!previouslyStarted) {
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean(getString(R.string.p_itinerario), Boolean.TRUE);
            edit.commit();
            fragPantallaTutorial popup = new fragPantallaTutorial(10);
            popup.show(getSupportFragmentManager(),"DialogFrag");
        }
    }


}