package partiel2018gaba.gaba.diiage.org;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import partiel2018gaba.gaba.diiage.org.Helpers.DbHelper;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String baseUrlApi = getResources().getString(R.string.base_url_api);
        URL baseUrl =null;

        try {
            baseUrl = new URL(baseUrlApi);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        final URL finalBaseUrl = baseUrl;
        AsyncTask<URL, Integer, ArrayList<Release>> task = new AsyncTask<URL, Integer, ArrayList<Release>>() {
            @Override
            protected ArrayList<Release> doInBackground(URL... urls) {




                ArrayList<Release> releases = new ArrayList<Release>();

                try {

                    // ouverture du stream depuis l'url
                    InputStream inputStream = urls[0].openStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                    StringBuilder stringBuilder = new StringBuilder();
                    String lineBuffer = null;

                    while ((lineBuffer = bufferedReader.readLine()) != null)
                    {
                        stringBuilder.append(lineBuffer);
                    }
                    String data = stringBuilder.toString();

                    JSONArray jsonArray = new JSONArray(data);
                    for (int i =0; i < jsonArray.length(); i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i); // 1 release

                        Release release = new Release(
                                jsonObject.getString("status"),
                                jsonObject.getString("thumb"),
                                jsonObject.getString("format"),
                                jsonObject.getString("title"),
                                jsonObject.getString("catno"),
                                jsonObject.getInt("year"),
                                jsonObject.getString("resource_url"),
                                jsonObject.getString("artist"),
                                jsonObject.getInt("id")
                        );
                        releases.add(release);
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return releases;
            }

            @Override
            protected void onPostExecute(ArrayList<Release> releases) // à la fin de l'éxection de la task
            {
                super.onPostExecute(releases);
                ListView lstRelease = (ListView) findViewById(R.id.lstRelease);// on récupère la listview
                lstRelease.setAdapter(new ArrayAdapter<Release>(MainActivity.this, android.R.layout.simple_list_item_1, releases)); // on affecte les data dans la lv

                // PARTIE BDD

                DbHelper dbHelper = new DbHelper(MainActivity.this);

                SQLiteDatabase db = dbHelper.getWritableDatabase();

                ContentValues cv = new ContentValues();

                try{
                    db.beginTransaction();
                    for (Release r: releases) // on parcours les releases et pour chacune, on on ajoute les infos dans un ContentValue
                    {
                        cv.put("status", r.getStatus());
                        cv.put("thumb", r.getThumb());
                        cv.put("format", r.getFormat());
                        cv.put("title", r.getTitle());
                        cv.put("catno", r.getCatno());
                        cv.put("year", r.getYear());
                        cv.put("resource_url", r.getResourceUrl());
                        cv.put("artist", r.getArtist());
                        cv.put("id", r.getId());
                    }

                    db.insert("release", null, cv); // ensuite on spécifie la table dans laquelle on veut enregistrer les values

                    if(true) // pour passer dans le catch qui supprimera les data
                    {
                        throw new Exception("suppression");
                    }

                    db.setTransactionSuccessful();
                    db.endTransaction();


                }
                catch (Exception ex)
                {
                    db.delete("release",null,null); // on supprime tous les objets de la table release
                    db.endTransaction();
                }

                db.close();


            }
        }.execute(baseUrl);


    }
}
