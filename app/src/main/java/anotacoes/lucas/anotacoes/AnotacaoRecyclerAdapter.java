package anotacoes.lucas.anotacoes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class AnotacaoRecyclerAdapter extends RecyclerView.Adapter<AnotacaoViewHolder>  {
    private List<Anotacao> mLista;
    public AnotacaoRecyclerAdapter(MainActivity mainActivity, List<Anotacao> lista){
        mLista = lista;
    }


    @Override
    public AnotacaoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.anotacao, parent,false);
        return new AnotacaoViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(AnotacaoViewHolder holder, int position) {
        Anotacao item = mLista.get(position);
        holder.uid.setText(item.getUid());
        holder.valor.setText(item.getValor());

    }

    @Override
    public int getItemCount() {
        return mLista.size();
    }
    public Anotacao getItem(int position){
        return mLista.get(position);
    }

}
