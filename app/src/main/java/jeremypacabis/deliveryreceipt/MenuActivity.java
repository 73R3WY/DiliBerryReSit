package jeremypacabis.deliveryreceipt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Jeremy Patrick on 4/30/2016 6:27 PM.
 * Author: Jeremy Patrick G. Pacabis
 */
public class MenuActivity extends Activity {
    private Button BTN_GEN, BTN_SCAN, BTN_DB, BTN_PREFS, BTN_EXIT;
    private View.OnClickListener BTN_OCL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        initialize();
    }

    private void initialize() {
        BTN_GEN = (Button) findViewById(R.id.btn_generate);
        BTN_SCAN = (Button) findViewById(R.id.btn_scan);
        BTN_DB = (Button) findViewById(R.id.btn_database);
        BTN_PREFS = (Button) findViewById(R.id.btn_settings);
        BTN_EXIT = (Button) findViewById(R.id.btn_exit);

        BTN_OCL = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_generate:
                        startThisActivity("Generate");
                        break;
                    case R.id.btn_scan:
                        startThisActivity("Scan");
                        break;
                    case R.id.btn_database:
                        startThisActivity("Database");
                        break;
                    case R.id.btn_settings:
                        startThisActivity("Settings");
                        break;
                    case R.id.btn_exit:
                        System.runFinalization();
                        finish();
                        System.exit(0);
                }
            }
        };

        BTN_GEN.setOnClickListener(BTN_OCL);
        BTN_SCAN.setOnClickListener(BTN_OCL);
        BTN_DB.setOnClickListener(BTN_OCL);
        BTN_PREFS.setOnClickListener(BTN_OCL);
        BTN_EXIT.setOnClickListener(BTN_OCL);
    }

    private void startThisActivity(String activity) {
        try {
            startActivity(new Intent(MenuActivity.this, Class.forName("jeremypacabis.deliveryreceipt." + activity)));
        } catch (ClassNotFoundException cnfe) {
            Toast.makeText(this, cnfe.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
