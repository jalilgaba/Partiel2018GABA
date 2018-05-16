package partiel2018gaba.gaba.diiage.org.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jalil on 16/05/2018.
 */

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context)
    {
        super(context, "db_releases", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE `release` ( `id` INTEGER PRIMARY KEY AUTOINCREMENT, `status` TEXT,`thumb` TEXT, `format` TEXT, `title` TEXT, `catno` TEXT,`year` INTEGER,`resource_url` TEXT,`artist` TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) // lors d'une modification du schéma de la base
    {
        if (oldVersion == 1 && newVersion == 2)
        {
            db.execSQL("CREATE TABLE `artist` ( `id` INTEGER PRIMARY KEY AUTOINCREMENT, `name` TEXT)");

            db.execSQL("ALTER TABLE `release` ADD `idArtist` INTEGER");
        }
    }
}
