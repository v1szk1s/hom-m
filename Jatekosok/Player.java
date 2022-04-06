package Jatekosok;
import Varazslatok.*;
import Bolt.Veheto;
import Display.Color;
import Egysegek.Egyseg;
import Egysegek.Foldmuves;
import Egysegek.Griff;
import Egysegek.Ijasz;
import Log.Log;

public class Player {
    private int arany;
    private int tulajdonsagAr;
    private Hos hos;
    private Varazslat[] varazslatok;
    private Egyseg[] egysegek;
    String nev;

    public Player(int szint){
        switch (szint){
            case 3:
                this.arany = 700;
                break;
            case 2:
                this.arany = 1000;
                break;
            case 1:
                this.arany = 1300;
                break;
        }
        this.tulajdonsagAr = 5;
        hos = new Hos();
        varazslatok = new Varazslat[5];
        egysegek = new Egyseg[]{new Foldmuves(), new Ijasz(), new Griff()};
        nev = "Player";
    }

    public boolean isMindenkiElhelyezve(){
        for(var e:getEgysegek()){
            if(e.getY() == -1 || e.getX() == -1){
                return false;
            }
        }
        return true;
    }

    public String elhelyez(Egyseg e, int hova){
        int y = (hova-1)/12;
        int x = (hova-1)%12;
        if(hova < 1 || hova > 120){
            return "Nem lehet ide lepni, mivel nincs a palyan!";
        }

        if(getEgysegOnPosition(y, x) == null){
            for(var egyseg: egysegek){
                if(Egyseg.equals(e, egyseg)){
                    egyseg.setY(y);
                    egyseg.setX(x);
                    return "";
                }
                //Log.log(e.getNev() + ", " + egyseg.getNev());
            }
        }
        return "Ezen a mezon mar vannak.";
    }

    public Egyseg getEgysegOnPosition(int y, int x){
        for(var e: egysegek){
            if(e.getY() == y && e.getX() == x){
                return e;
            }
        }
        return null;
    }

    public String[] getInfo(){
        String[] most = new String[5];
        most[0] = "Mana: [" +"#".repeat(Math.round(getHos().getMana()/10)) + "]";
        for(int i = 0, j = 1; i < getEgysegek().length; i++){
            if(getEgysegek()[i].getMennyiseg() > 0){
                Egyseg e = getEgysegek()[i];
                int db = e.getMennyiseg();
                int hp = e.getEletero();
                most[j] = e.getNev() + ": \t" + db + " db\t" + hp + " hp";
                //System.out.println(margo + player.getEgysegek()[i].getNev() + ":\t" + player.getEgysegek()[i].getMennyiseg());
            }
        }
        return most;
    }

    public boolean isLost(){
        for(var egyseg:egysegek){
            if(egyseg.getEletero() > 0){
                return false;
            }
        }
        return true;
    }

    public Egyseg[] getAllEgyseg(){
        return egysegek;
    }

    public Egyseg[] getEgysegek(){
        int counter = 0;
        for(var e:egysegek){
            if(e.getMennyiseg() > 0){
                counter++;
            }
        }
        Egyseg[] ek = new Egyseg[counter];
        for(int i = 0, j = 0; i < egysegek.length; i++){
            if(egysegek[i].getMennyiseg() > 0){
                ek[j] = egysegek[i];
                j++;
            }
        }
        return ek;
    }

    public String buyEgyseg(String nev, int mennyi){
        Egyseg most = null;

        for(int i = 0; i < egysegek.length; i++){
            switch(nev){
                case "1":
                    nev = "foldmuves";
                    break;
                case "2":
                    nev = "ijasz";
                    break;
                case "3":
                    nev = "griff";
                    break;
            }
            if(egysegek[i].getNev().toLowerCase().equals(nev.toLowerCase())){
                most = egysegek[i];
                if(most.getAr()*mennyi > arany){
                    return Color.RED + "Nincs eleg aranyad!" + Color.RESET;
                }
                arany -= most.getAr()* mennyi;
                egysegek[i].addMennyiseg(mennyi);
                return "";
            }
        }
        return "";
    }

    public int getEgysegSzam(){
        int sum = 0;
        for(int i = 0; i < egysegek.length; i++){
            sum += egysegek[i].getMennyiseg();
        }
        return sum;
    }

    public int maxMennyitVehet(Veheto valami){
        return arany / valami.getAr();
    }

    public String buyVarazslat(Varazslat varazs){

        if(varazs.getAr() > arany){
            return "Nincs eleg aranyod!";
        }

        for(int i = 0; i < 5; i++){
            if(varazslatok[i] == null){
                break;
            }
            if(varazs.getNev().equals(varazslatok[i].getNev())){
                return Color.RED + "Ezt a varázslatot már megvetted!" + Color.WHITE;
            }
        }

        for(int i = 0; i < 5; i++){
            if(varazslatok[i] == null){
                varazslatok[i] =  varazs;
                arany -= varazs.getAr();
                return "";
            }
        }
        return Color.RED + "Nem tudsz több varázslatot venni!" + Color.WHITE;
    }

    public Varazslat[] getVarazslatok(){
        int counter = 0;
        for(int i = 0; i < varazslatok.length; i++){
            if(varazslatok[i] == null)
                break;
            counter++;
        }
        Varazslat[] ek = new Varazslat[counter];
        for(int i = 0, j = 0; i < varazslatok.length; i++){
            ek[j] = varazslatok[i];
            j++;
            
        }
        return ek;
    }

    public boolean vanVarazslat(Varazslat varazs){
        if(varazs == null){
            return false;
        }
        for(int i = 0; i < 5; i++){
            if(varazslatok[i] == null){
                return false;
            }

            if(this.varazslatok[i].getNev().equals(varazs.getNev())){
                return true;
            }
        }
        return false;
    }

    public int levelUp(int melyik){
        if (arany >= tulajdonsagAr){
            if(hos.levelUp(melyik)){
                arany -= tulajdonsagAr;
                tulajdonsagAr = (int) Math.ceil((double)tulajdonsagAr*1.1);
                return 0;
            }
            return 2;
        }
        return 1;
    }

    public void setNev(String nev){
        this.nev = nev;
    }

    public String getNev(){
        return nev;
    }

    public String getAranyStr(){
        return Integer.toString(arany);
    }
    public int getArany(){
        return arany;
    }

    public int getTulajdonsagAr() {
        return tulajdonsagAr;
    }


    public Hos getHos() {
        return hos;
    }
}
