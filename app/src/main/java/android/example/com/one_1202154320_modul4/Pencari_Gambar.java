package android.example.com.one_1202154320_modul4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

public class Pencari_Gambar extends AppCompatActivity {
    //inisialisasi tiap variabel
    private EditText txtLink;
    private ImageView gambar;
    private ProgressDialog progDialog;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencari__gambar);

        txtLink = (EditText) findViewById(R.id.linkGambar); //mengambil variabel dari id LinkGambar
        gambar = (ImageView) findViewById(R.id.viewGambar); //mengambil variabel dari id viewGambar
    }

    public void CariGambar(View view) {
        text = txtLink.getText().toString();//Mengubah EditText ke dalam bentuk String
        if (text.isEmpty()) {
            Toast.makeText(this, "Isi Link Dulu Ya", Toast.LENGTH_LONG).show();//nampilin toast jika EditText kosong akan memunculkan Toast
        } else {
            new DownloadGambar().execute(text);//kalau EditText berisi String maka akan di eksekusi
        }
    }
    private class DownloadGambar extends AsyncTask<String, Void, Bitmap> { //buat kelas untuk proses eksekusi link

        @Override
        protected void onPreExecute() { //buat method utk sebelum eksekusi
            super.onPreExecute();
            progDialog = new ProgressDialog(Pencari_Gambar.this); //proses eksekusi diambil dari kelasnya sendiri
            progDialog.setTitle("Searching"); //buat judul utk alert dialog
            progDialog.setMessage("Loading"); //buat isi tulisan dalam alert dialog
            progDialog.setIndeterminate(false);
            progDialog.show(); //menampilkan alert dialog

            //Method ini digunakan untuk melakukan eksekusi progress dialog sebelum method onPostExecute dijalankan
        }
        @Override
        protected Bitmap doInBackground(String... urls) { //jalanin proses di background
            String imageURL = urls[0]; //buat variabel image utk urlnya
            Bitmap bimage = null; //buat nilainya jadi null dlu
            try {
                InputStream in = new java.net.URL(imageURL).openStream();
                bimage = BitmapFactory.decodeStream(in);

            } catch (Exception e) {
                Log.e("Error Message", e.getMessage()); //nampilin pesan di logcat jika ada error
                e.printStackTrace();
            }
            return bimage; //mengembalikan variabel bimage

            //Method ini digunakan untuk melakukan aktivitas dibackground menggunakan AsyncTask
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) { //method untuk mengeksekusi
            gambar.setImageBitmap(bitmap); //mengambil gambar dari method doInBackground
            progDialog.dismiss(); //menghilangkan alert dialog

            //Method ini digunakan untuk melakukan eksekusi setImageBitmap setelah method doInBackground dijalankan
        }
    }
}

