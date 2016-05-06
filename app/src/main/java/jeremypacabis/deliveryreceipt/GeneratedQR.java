package jeremypacabis.deliveryreceipt;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

/**
 * Created by Jeremy Patrick on 5/1/2016.
 * Author: Jeremy Patrick G. Pacabis
 */
public class GeneratedQR extends Activity implements View.OnClickListener {
    private ImageView IV_generatedQR;
    private Button BTN_SAVE_FILE, BTN_SAVE_DB;
    private Intent generateIntent;

    private String[] DATA;
    private String DATA_TO_BE_ENCODED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generated_qr);

        initialize();
        getIntentData();
        transformDataToBeEncoded();
        outputQRCode();
    }

    private void initialize() {
        generateIntent = getIntent();
        DATA_TO_BE_ENCODED = new String();

        IV_generatedQR = (ImageView) findViewById(R.id.img_generated_qr);
        BTN_SAVE_FILE = (Button) findViewById(R.id.btn_save_file);
        BTN_SAVE_DB = (Button) findViewById(R.id.btn_save_db);

        BTN_SAVE_FILE.setOnClickListener(this);
        BTN_SAVE_DB.setOnClickListener(this);
    }

    private void getIntentData() {
        DATA = generateIntent.getStringArrayExtra(Constants.FORM_DATA);
    }

    private void transformDataToBeEncoded() {
        for (int x = 0; x < 19; x++) {
            DATA_TO_BE_ENCODED += DATA[x] + "\n";
        }

        Toast.makeText(getApplicationContext(), DATA_TO_BE_ENCODED, Toast.LENGTH_SHORT).show();
    }

    private void outputQRCode() {
        // find screen size
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        int smallerDimension = width < height ? width : height;
        smallerDimension = smallerDimension * 9 / 10;

        // encode data to QRCode
        QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(DATA_TO_BE_ENCODED, null, Contents.Type.TEXT, BarcodeFormat.QR_CODE.toString(), smallerDimension);
        try {
            Bitmap qrBitmap = qrCodeEncoder.encodeAsBitmap();
            if (qrBitmap != null) {
                IV_generatedQR.setImageBitmap(qrBitmap);
            } else {
                IV_generatedQR.setImageResource(android.R.drawable.ic_menu_gallery);
            }
        } catch (WriterException we) {
            Toast.makeText(null, we.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save_file:
                Toast.makeText(getApplicationContext(), "Save to file!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_save_db:
                Toast.makeText(getApplicationContext(), "Save to database!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
