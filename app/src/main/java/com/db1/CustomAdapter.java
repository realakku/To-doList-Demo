package com.db1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Dao> {
    int resource;
    List<Dao> ls;
    Context context;
    DatabaseHelper mydb;
    TextView t1,t2,t3;
    Dao da;

    public CustomAdapter(Context context,int resource,List<Dao> ls) {
        super(context,resource,ls);
        this.ls = ls;
        this.resource=resource;
        this.context = context;
    }

    public View getView(final int position, View view, ViewGroup parent){
        LayoutInflater lf=LayoutInflater.from(context);
        View view1=lf.inflate(resource,null,false);
        t1=view1.findViewById(R.id.textView);
        t2=view1.findViewById(R.id.textView2);
        t3=view1.findViewById(R.id.textView3);
        Button del=view1.findViewById(R.id.del);
        mydb=new DatabaseHelper(context);
        da=ls.get(position);
        t1.setText(da.getT1());
        t2.setText(da.getT2());
        t3.setText(da.getT3());
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                da=ls.get(position);
                DeclaredVariables.ID=Integer.parseInt(da.getT1());
                Log.i("my",da.getT1());
                removeDao(DeclaredVariables.ID);
            }
        });
        return view1;
    }


    private void removeDao(final int id) {
        AlertDialog.Builder ad=new AlertDialog.Builder(context);
        ad
                .setTitle("Delete Dialog")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mydb.delData(id);

                        Intent i=new Intent(context,MainActivity.class);

                        context.startActivity(i);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        AlertDialog ad2=ad.create();
        ad2.show();
    }

}

