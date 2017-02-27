package finomena.android.info.finomena;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
    EditText edt_tilesNo;
    Button btn_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
       start();

    }

    private void  start(){
        edt_tilesNo=(EditText)findViewById(R.id.edt_tilesNo);
        edt_tilesNo.setText("");
        btn_submit=(Button)findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid()){
                    Intent i = new Intent(MainActivity.this, SecondActivity.class);
                String value = edt_tilesNo.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("value", value);
                i.putExtras(bundle);
                startActivity(i);
            }
            }
        });
    }

    private boolean isValid() {

            boolean valid=true;
            String str_tiles_no=edt_tilesNo.getText().toString();
            if (str_tiles_no.isEmpty() || Integer.parseInt(str_tiles_no) <= 1 || Integer.parseInt(str_tiles_no) >= 9) {
                edt_tilesNo.setError("Enter value between 1 to 9");
                valid = false;
            }


        return valid;

    }
}
