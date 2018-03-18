package android.example.com.one_1202154320_modul4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class List_Nama_Mahasiswa extends AppCompatActivity {
    //inisialisasi tiap variabel
    private ListView mListView;
    private ProgressBar mProgBar;
    private String [] mUsers= {"One","Ayu","Virra","Fura","Nadia", "Rasyid","Dennes","Farhan","Anila","Julian",
            "Kumara","Gabriella","Octa","Raesa","Lina",
            "Adib","Rifqy","Leo"}; //buat isi array untuk list nama

    private AddItemToListView mAddItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__nama__mahasiswa);

        mProgBar = (ProgressBar) findViewById(R.id.progressbar); //mengambil variabel dari id progressbar
        mListView = (ListView) findViewById(R.id.listView); //mengambil variabel dari id ListVIew
        mListView.setVisibility(View.GONE); //sebelum button diklik, list tidak akan muncul
        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));
        //membuat untuk tampilan list dengan layout default dari android studio dan mengambil arraylist
    }

    public void ListNama(View view) { //method untuk list nama
        mAddItem = new AddItemToListView(); //mengisi list nama dengan kelas AddItemToListView
        mAddItem.execute(); //mengeksekusi method
    }

    public class AddItemToListView extends AsyncTask<Void, String, Void> {

        private ArrayAdapter<String> mAdapter; //menginisialisasi adapter
        private int counter=1; //buat counter
        ProgressDialog mProgDialog = new ProgressDialog(List_Nama_Mahasiswa.this); //buat progress dialog dari kelasnya sendiri

        @Override
        protected void onPreExecute() { //method sebelum mengeksekusi postexecute
            mAdapter = (ArrayAdapter<String>) mListView.getAdapter();

            //for progress dialog
            mProgDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); //buat style dari progress dialog
            mProgDialog.setTitle("Loading Data"); //judul dari progress dialog
            mProgDialog.setMessage("Please wait...."); //buat isi dalam progress dialog
            mProgDialog.setCancelable(false);
            mProgDialog.setProgress(0); //buat progressnya dari 0

            mProgDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //buat button cancel dan bisa di klik
                    mAddItem.cancel(true);
//                  mProgBar.setVisibility(View.VISIBLE);
                    dialogInterface.dismiss();//hilangkan dialognya ketika selesai
                }
            });
            mProgDialog.show(); //menampilkan progress dialog

            //Method ini digunakan untuk melakukan eksekusi progress dialog sebelum method onPostExecute dijalankan
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (String item : mUsers){  //buat jalanin proses di background dan manggil si array
                publishProgress(item); //memanggil variabel item
                try {
                    Thread.sleep(100); //foreground nya sleep 100ms
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(isCancelled()){
                    mAddItem.cancel(true); //manggil mAddItem
                }
            }
            return null;

            //Method ini digunakan untuk melakukan aktivitas dibackground menggunakan AsyncTask
        }
        @Override
        protected void onProgressUpdate(String... values) {
            mAdapter.add(values[0]); //manggil adapter dan array dimulai dari 0
            Integer current_status = (int) ((counter/(float)mUsers.length)*100);
            //buat menghitung progress persenannya dari jumlah array yang ada
            mProgBar.setProgress(current_status); //ambil current status
            mProgDialog.setProgress(current_status); //ambil current status
            mProgDialog.setMessage(String.valueOf(current_status+"%")); //ambil perhitungan current status dan nambahin string %
            counter++;

            //Method ini digunakan untuk menghitung presentase progress dialog
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mProgBar.setVisibility(View.GONE); //buat nampilin si progress bar
            mProgDialog.dismiss(); //menghilangkan progrdialog
            mListView.setVisibility(View.VISIBLE); //buat menampilkan si list nya

            //Method ini digunakan untuk melakukan eksekusi setImageBitmap setelah method doInBackground dijalankan
        }
    }

}
