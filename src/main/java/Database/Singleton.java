package Database;

public class Singleton {
    private static Singleton instance;
    private static SQLiteDatabase sqLiteDatabase;
    private Singleton(){
        sqLiteDatabase= new SQLiteDatabase();
    }

    public static Singleton getInstance(){
        if(instance==null){
            instance = new Singleton();
            return instance;
        }
        return instance;
    }

    public SQLiteDatabase getDB(){
        return sqLiteDatabase;
    }

}
