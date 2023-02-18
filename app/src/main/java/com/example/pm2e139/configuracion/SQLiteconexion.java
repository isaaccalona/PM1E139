package com.example.pm2e139.configuracion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteconexion extends SQLiteOpenHelper {

    public SQLiteconexion(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {

        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(Operaciones.createTableContact);
        sqLiteDatabase.execSQL(Operaciones.CreateTablePaises);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL(Operaciones.dropTableContact);
        sqLiteDatabase.execSQL(Operaciones.DropTablePaises);
        onCreate(sqLiteDatabase);
    }

}