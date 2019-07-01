package e.dell.project1.adapter;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import e.dell.project1.modeldata.Model;
import e.dell.project1.R;

public class DisplayDetail extends RecyclerView.Adapter<DisplayDetail.ViewHolder>{
    private List<Model> detail;
    private Context context;
    public DisplayDetail(List<Model> detail,Context context)
    {
        this.detail= detail;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Model userData=detail.get(position);
        holder.tvDate.setText(userData.getDatepik());
        holder.tvTime.setText(userData.getTimepik());
        holder.tvAmount.setText(userData.getAmount());
        holder.tvKm.setText(userData.getKm());

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.activity_main);

                Window window = dialog.getWindow();
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                final Model model = detail.get(position);
                final TextView tvDate=dialog.findViewById(R.id.tvDate);
                final TextView tvTime=dialog.findViewById(R.id.tvTime);
                final TextView tvAmount=dialog.findViewById(R.id.tvAmount);
                final TextView tvKm=dialog.findViewById(R.id.tvKm);

                tvDate.setText(model.getDatepik());
                tvTime.setText(model.getTimepik());
                tvAmount.setText(model.getAmount());
                tvKm.setText(model.getKm());

                final Button btnSubmit = dialog.findViewById(R.id.btnSubmit);
                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (btnSubmit.getText().toString().length() > 0) {
                            detail.get(position).setDatepik(tvDate.getText().toString().trim());
                            detail.get(position).setTimepik(tvTime.getText().toString().trim());
                            detail.get(position).setAmount(tvAmount.getText().toString().trim());
                            detail.get(position).setKm(tvKm.getText().toString().trim());

                            dialog.dismiss();
                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(context, "Please Add Note", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();


            }
        });




        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Alert");
                alert.setMessage("Are you sure you want to delete?");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        detail.remove(position);
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return detail.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvDate;
        TextView tvTime;
        TextView tvAmount;
        TextView tvKm;
        Button btnUpdate;
        Button btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDate=itemView.findViewById(R.id.tvDate);
            tvTime=itemView.findViewById(R.id.tvTime);
            tvAmount=itemView.findViewById(R.id.tvAmount);
            tvKm=itemView.findViewById(R.id.tvKm);
            btnUpdate=itemView.findViewById(R.id.btnUpdate);
            btnDelete=itemView.findViewById(R.id.btnDelete);
        }
    }
}
