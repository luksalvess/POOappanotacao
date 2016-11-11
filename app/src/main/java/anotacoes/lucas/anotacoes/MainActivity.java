package anotacoes.lucas.anotacoes;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private EditText mEditText;
    private TextView mTextViewCodigo;
    private TextView mTextViewAnotacao;
    private Anotacao mUltimaAnotacao;
    private AnotacaoRecyclerAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        mAdapter = new AnotacaoRecyclerAdapter(this,obterListanotas());

        recyclerView.setAdapter(mAdapter);




        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        findViewById(R.id.bt_salvar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarNovoRegistro();
            }
        });

        findViewById(R.id.bt_atualizar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atualizarRegistro();
            }
        });

        findViewById(R.id.bt_deletar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletarRegistro();
            }
        });

        obterTodos();
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(BlankFragment.newInstance(""), "1");
        adapter.addFragment(BlankFragment.newInstance(""), "2");
        adapter.addFragment(BlankFragment.newInstance(""), "3");
        adapter.addFragment(BlankFragment.newInstance(""), "4");
        viewPager.setAdapter(adapter);
    }
    private void deletarRegistro(){

        if(mUltimaAnotacao != null){

            DeletarAnotacao deletarAnotacao = new DeletarAnotacao();
            deletarAnotacao.deletar(mUltimaAnotacao);

            obterTodos();
        }
    }
    private void atualizarRegistro(){

        if(mUltimaAnotacao != null){
            mUltimaAnotacao.setValor(mEditText.getText().toString());
            AtualizarAnotacao atualizarAnotacao = new AtualizarAnotacao();
            atualizarAnotacao.atualizar(mUltimaAnotacao);
            obterTodos();

            obterTodos();
        }
    }
    private void salvarNovoRegistro() {

        SalvarAnotacao salvar = new SalvarAnotacao();
        salvar.novoRegistro(mEditText.getText().toString());

        mEditText.setText("");

        obterTodos();
    }
    private void obterTodos(){
        ObterNotas obterNotas = new ObterNotas();
        obterNotas.todos(new ObterNotas.OnObterAnotacoesListener() {
            @Override
            public void onAnotacoesObtidas(List<Anotacao> lista) {

                if(lista.size() > 0){
                    mUltimaAnotacao = lista.get(lista.size() - 1);
                    exibirAnotacao();
                }else{

                    mUltimaAnotacao = null;
                    mTextViewAnotacao.setText("");
                    mTextViewCodigo.setText("");
                }
            }
        });
    }
    private void exibirAnotacao(){

        mTextViewAnotacao.setText(mUltimaAnotacao.getValor());
        mTextViewCodigo.setText(mUltimaAnotacao.getUid());
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

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_camera) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void onItemClicado(int position){
        Toast.makeText(this,"Cliquei no item "+mAdapter.getItem(position).getUid(), Toast.LENGTH_SHORT).show();
    }
    private List<Anotacao> obterListanotas() {
        List<Anotacao> lista = new ArrayList<>();

        Anotacao p = new Anotacao();
        p.setUid("txt");
        p.setValor("funciona porra");

        lista.add(p);

        p = new Anotacao();
        p.setUid("T.N.T");
        p.setValor("funciona porra, CARALHO");

        return lista;
    }




}
