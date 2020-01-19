package com.db1;
/*this is just demo*/
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    DatabaseHelper mydb;
    EditText s_name,s_marks;
    Button s_add;
    Button s_up;
    List<Dao> ldao;
    ListView listView;
    String sid;
    TextView uid,uname,umarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb=new DatabaseHelper(this);
        listView=findViewById(R.id.lv);
        //initializing objects
        ldao = new ArrayList<>();
        Cursor res=mydb.getData();
        if(res.getCount()==0)
        {
            Toast.makeText(MainActivity.this,"Data Not Found",Toast.LENGTH_LONG).show();
        }
        else{
            while (res.moveToNext()) {
                ldao.add(new Dao(res.getString(0),res.getString(1),res.getString(2)));
                CustomAdapter customAdapter=new CustomAdapter(this,R.layout.custom_list,ldao);
                listView.setAdapter(customAdapter);
            }
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                    View oview=getLayoutInflater().inflate(R.layout.updel_student,null);
                    s_name=oview.findViewById(R.id.s_name);
                    s_marks=oview.findViewById(R.id.s_marks);
                    s_up=oview.findViewById(R.id.s_up);
                    sid =String.valueOf(id);

                    uid= (TextView) view.findViewById(R.id.textView);
                    uname= (TextView) view.findViewById(R.id.textView2);
                    umarks= (TextView) view.findViewById(R.id.textView3);

                    s_name.setText(uname.getText().toString());
                    s_marks.setText(umarks.getText().toString());

                    s_up.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            boolean isUpdated=mydb.updateData(uid.getText().toString(),s_name.getText().toString(),s_marks.getText().toString());
                            if(isUpdated=true) {
                                Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(MainActivity.this,"Data Not Updated",Toast.LENGTH_LONG).show();
                            }
                            Intent i=new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(i);
                        }
                    });
                    builder.setView(oview);
                    AlertDialog dialog=builder.create();
                    dialog.show();
                }
            });
        }


        /*ArrayList<String> list=new ArrayList<>();


        else{
            while (res.moveToNext()){
                list.add(res.getString(1));
                ListAdapter listAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
                listView.setAdapter(listAdapter);
            }
        }*/

    }



    @Override //add menu
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_student) {

            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
            View oview=getLayoutInflater().inflate(R.layout.activity_add_student,null);
            s_name=oview.findViewById(R.id.s_name);
            s_marks=oview.findViewById(R.id.s_marks);
            s_add=oview.findViewById(R.id.s_add);
            s_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isInserted=mydb.insertData(s_name.getText().toString(),s_marks.getText().toString());
                    if(isInserted=true) {
                        Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(MainActivity.this,"Data Not Inserted",Toast.LENGTH_LONG).show();
                    }
                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                }
            });
            builder.setView(oview);
            AlertDialog dialog=builder.create();
            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

     /* //to view data in alertdialog
                Cursor res=mydb.getData();
                if(res.getCount()==0)
                {
                    showMsg("Error","No Data Found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("ID: "+res.getString(0)+"\n");
                    buffer.append("Name: "+res.getString(1)+"\n");
                    buffer.append("Marks: "+res.getString(2)+"\n\n");
                }
                showMsg("Data",buffer.toString());
             public void showMsg(String title, String msg){
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setCancelable(true);
                builder.setTitle(title);
                builder.setMessage(msg);
                builder.show();
    }
                */
}
