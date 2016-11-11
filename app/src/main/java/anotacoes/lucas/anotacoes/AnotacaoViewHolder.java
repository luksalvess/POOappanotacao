package anotacoes.lucas.anotacoes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class AnotacaoViewHolder extends RecyclerView.ViewHolder{
    public TextView uid;
    public EditText valor;
    public AnotacaoViewHolder(View itemView) {
        super(itemView);
        uid = (TextView) itemView.findViewById(R.id.xuid);
        valor = (EditText) itemView.findViewById(R.id.xvalor);
    }
}
