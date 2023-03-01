public class Database {
    public String Name;
    public int Year;
    public float Uvazek;
    public static float Max = 1;

    public Database(String Name, int Year) {
        this.Name = Name;
        this.Year = Year;
    }
    public String GetName() {
        return Name;
    }

    public int GetYear() {
        return Year;
    }

    public float GetUvazek() {
        return Uvazek;
    }

    public static void SetMax(float Max) {
        Database.Max = Max;
    }

    public boolean SetUvazek(float newUvazek) {
        if (newUvazek + Uvazek > Max) {
            return false;
        } else {
            Uvazek += newUvazek;
            return true;
        }
    }

}
