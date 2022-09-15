package com.ismawanto_203110011.a203110011_ismawantoagung_p_11

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
// TODO 1: Mengambil isi variabel dari kelas activity main
        val fileName = findViewById<EditText>(R.id.editFile)
        val fileData = findViewById<EditText>(R.id.editData)
        //Mengambil batton dari kelas activity main
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnView = findViewById<Button>(R.id.btnView)
        val btnDelete = findViewById<Button>(R.id.btnDelete)

        // TODO 2: Apabila btnSave ditekan fungsi-fungsi didalamnya akan dikerjakan
        btnSave.setOnClickListener {
            val file: String = fileName.text.toString()
            val data: String = fileData.text.toString()
            //Diguanakan untuk membuat aliran data berupa gambar
            val fileOutputStream: FileOutputStream
            try {
                fileOutputStream = openFileOutput(file, Context.MODE_PRIVATE)
                fileOutputStream.write(data.toByteArray())
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: NumberFormatException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            Toast.makeText(applicationContext, "data save", Toast.LENGTH_LONG).show()
            fileName.text.clear()
            fileData.text.clear()
        }

        // TODO 3: Apabila btnView ditekan fungsi-fungsi didalamnya akan dikerjakan
        btnView.setOnClickListener {
            //Mencoba
            try {
                //Membuat filename yang bertetipe string dan ketambahan txt
                val filename = fileName.text.toString()+".txt"
                //cek apakah filename tidak kosong
                if (filename.toString().trim() != "") {
                    //Pembuatan pembacaan file
                    var fileInputStream: FileInputStream? = null
                    fileInputStream = openFileInput(filename)
                    var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
                    val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
                    val stringBuilder: StringBuilder = StringBuilder()
                    var text: String? = null
                    //Membaca isi file yang mana di var text
                    while ({ text = bufferedReader.readLine(); text }() != null) {
                        //Menmabahan var text ke stringBuilder
                        stringBuilder.append(text)
                        //Set nilai hasil dari text

                    }
                    //Tampilkan data pada fileData/binding.txtHasil
                    fileData.setText(stringBuilder.toString()).toString()
                } else {
                    //Jika if tidak memenuhi, muncul pop up "file name cannot be blank"
                    Toast.makeText(
                        applicationContext,
                        "file name cannot be blank",
                        Toast.LENGTH_LONG
                    ).show()
                }
                //Menangkap error dan diabaikan
            }catch (e: Exception) {
                //muncul pop up "File tidak ada"
                Toast.makeText(this, "File tidak ada", Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }

        }

        btnDelete.setOnClickListener{
            try {
                val filename = fileName.text.toString()
                //Mencari direktori file
                val dir = filesDir
                //Menemukan file
                val file = File(dir, filename)
                //file dihapus
                file.delete()

                //Menangkap error dan diabaikan
            }catch (e: Exception) {
                //muncul pop up "Tidak bisa dihaspus"
                Toast.makeText(this, "Tidak bisa dihaspus", Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }

            //bersihkan text
            fileName.text.clear()
            fileData.text.clear()


    }

        }

    }
