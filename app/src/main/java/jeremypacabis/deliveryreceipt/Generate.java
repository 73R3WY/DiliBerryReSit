package jeremypacabis.deliveryreceipt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Jeremy Patrick on 4/30/2016.
 * Author: Jeremy Patrick G. Pacabis
 */
public class Generate extends Activity implements View.OnClickListener {
    private EditText ET_COMPANY, ET_RECEIPT_NO, ET_DELIVERED_TO, ET_DATE, ET_TIME_IN, ET_TIME_OUT, ET_VESSEL, ET_SOURCE, ET_PH_NO, ET_LOAD_NO, ET_VOYAGE_NO, ET_PORT, ET_HAULER, ET_TRUCK_NO, ET_DRIVER, ET_PREPARED_BY, ET_CHECKED_VERIFIED_BY, ET_APPROVED_BY, ET_RECEIVED_BY;
    private Button BTN_GENERATE;

    private Intent generateIntent;

    private String[] FORM_DATA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generate);

        initialize();
    }

    private void initialize() {
        ET_COMPANY = (EditText) findViewById(R.id.etxt_company);
        ET_RECEIPT_NO = (EditText) findViewById(R.id.etxt_receipt_no);
        ET_DATE = (EditText) findViewById(R.id.etxt_date);
        ET_DELIVERED_TO = (EditText) findViewById(R.id.etxt_delivered_to);
        ET_VESSEL = (EditText) findViewById(R.id.etxt_vessel);
        ET_SOURCE = (EditText) findViewById(R.id.etxt_source);
        ET_PH_NO = (EditText) findViewById(R.id.etxt_ph_no);
        ET_LOAD_NO = (EditText) findViewById(R.id.etxt_load_no);
        ET_VOYAGE_NO = (EditText) findViewById(R.id.etxt_voyage_no);
        ET_PORT = (EditText) findViewById(R.id.etxt_port);
        ET_TIME_IN = (EditText) findViewById(R.id.etxt_time_in);
        ET_TIME_OUT = (EditText) findViewById(R.id.etxt_time_out);
        ET_HAULER = (EditText) findViewById(R.id.etxt_hauler);
        ET_TRUCK_NO = (EditText) findViewById(R.id.etxt_truck_no);
        ET_DRIVER = (EditText) findViewById(R.id.etxt_truck_driver);
        ET_PREPARED_BY = (EditText) findViewById(R.id.etxt_prepared_by);
        ET_CHECKED_VERIFIED_BY = (EditText) findViewById(R.id.etxt_checked_verified_by);
        ET_APPROVED_BY = (EditText) findViewById(R.id.etxt_approved_by);
        ET_RECEIVED_BY = (EditText) findViewById(R.id.etxt_received_by);
        BTN_GENERATE = (Button) findViewById(R.id.btn_generate_qr);

        ET_DATE.setOnClickListener(this);
        ET_TIME_IN.setOnClickListener(this);
        ET_TIME_OUT.setOnClickListener(this);
        BTN_GENERATE.setOnClickListener(this);
    }

    private void getValuesFromForm() {
        FORM_DATA = new String[19];
        FORM_DATA[Constants.COMPANY] = returnDataFromThisET(ET_COMPANY);
        FORM_DATA[Constants.RECEIPT_NO] = returnDataFromThisET(ET_RECEIPT_NO);
        FORM_DATA[Constants.DATE] = returnDataFromThisET(ET_DATE);
        FORM_DATA[Constants.DELIVERED_TO] = returnDataFromThisET(ET_DELIVERED_TO);
        FORM_DATA[Constants.VESSEL] = returnDataFromThisET(ET_VESSEL);
        FORM_DATA[Constants.SOURCE] = returnDataFromThisET(ET_SOURCE);
        FORM_DATA[Constants.PH_NO] = returnDataFromThisET(ET_PH_NO);
        FORM_DATA[Constants.LOAD_NO] = returnDataFromThisET(ET_LOAD_NO);
        FORM_DATA[Constants.VOYAGE_NO] = returnDataFromThisET(ET_VOYAGE_NO);
        FORM_DATA[Constants.PORT] = returnDataFromThisET(ET_PORT);
        FORM_DATA[Constants.TIME_IN] = returnDataFromThisET(ET_TIME_IN);
        FORM_DATA[Constants.TIME_OUT] = returnDataFromThisET(ET_TIME_OUT);
        FORM_DATA[Constants.HAULER] = returnDataFromThisET(ET_HAULER);
        FORM_DATA[Constants.TRUCK_NO] = returnDataFromThisET(ET_TRUCK_NO);
        FORM_DATA[Constants.DRIVER] = returnDataFromThisET(ET_DRIVER);
        FORM_DATA[Constants.PREPARED_BY] = returnDataFromThisET(ET_PREPARED_BY);
        FORM_DATA[Constants.CHECKED_VERIFIED_BY] = returnDataFromThisET(ET_CHECKED_VERIFIED_BY);
        FORM_DATA[Constants.APPROVED_BY] = returnDataFromThisET(ET_APPROVED_BY);
        FORM_DATA[Constants.RECEIVED_BY] = returnDataFromThisET(ET_RECEIVED_BY);

    }

    private String returnDataFromThisET(EditText et) {
        return et.getText().toString().equals("") ? "%null%" : et.getText().toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_generate_qr:
                getValuesFromForm();
                generateIntent = new Intent(getApplicationContext(), GeneratedQR.class);
                generateIntent.putExtra(Constants.FORM_DATA, FORM_DATA);
                startActivity(generateIntent);
                break;
            case R.id.etxt_date:
                Toast.makeText(this, "Date pressed! " + (ET_DATE.getText().toString().equals("") ? "%null%" : ET_DATE.getText().toString()), Toast.LENGTH_SHORT).show();
                break;
            case R.id.etxt_time_in:
                Toast.makeText(this, "Time in!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.etxt_time_out:
                Toast.makeText(this, "Time out!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
