package br.com.android.ppm.controleconsumodeagua.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import br.com.android.ppm.controleconsumodeagua.R;
import br.com.android.ppm.controleconsumodeagua.entity.ConsumoEntity;
import br.com.android.ppm.controleconsumodeagua.modal.Consumo;

public class ListaConsumoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Consumo> listaConsumo;

    public ListaConsumoAdapter(Context context, ArrayList<Consumo> listaConsumo){
        this.context = context;
        this.listaConsumo = listaConsumo;
    }


    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return listaConsumo.size();
    }

    @Override
    public Object getItem(int position) {
        return listaConsumo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_row_consumo, null, true);

            holder.idQdtConsumo  = convertView.findViewById(R.id.idQdtConsumo);
            holder.idData = convertView.findViewById(R.id.idData);

            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.idQdtConsumo.setText(String.valueOf(listaConsumo.get(position).getQtd()));
        holder.idData.setText(listaConsumo.get(position).getData());

        return convertView;
    }

    private class ViewHolder {

        protected TextView idQdtConsumo, idData;

    }

    public ArrayList<Consumo> getListaPaletesLidos() {
        return listaConsumo;
    }

    public void setListaPaletesLidos(ArrayList<Consumo> listaConsumo) {
        this.listaConsumo = listaConsumo;
    }

}
