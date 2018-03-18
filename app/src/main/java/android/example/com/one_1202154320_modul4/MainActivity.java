package android.example.com.one_1202154320_modul4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void nama(View view) {
        Intent i = new Intent(this, List_Nama_Mahasiswa.class); //buat intent utk menuju ke kelas list nama mahasiswa
        startActivity(i); //memulai intent ketika button di klik
    }

    public void gambar(View view) {
        Intent j = new Intent(this, Pencari_Gambar.class);//buat intent utk menuju ke kelas pencari gambar
        startActivity(j); //memulai intent ketika button di klik
    }
}
