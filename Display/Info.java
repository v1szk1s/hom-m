package Display;

public class Info {
     /**
     * @param num Az error kod szama.
     * @return A megfelelo szamú error kod szovege
     * */
    public static String error(int num){
        switch (num){
            case 1:
                return Color.RED  + "Nincs eleg aranyad!\n" + Color.WHITE;
            case 2:
                return Color.RED  + "Maximum 10 pont lehet egy kepessegen!\n" + Color.WHITE;
            case 3:
                return Color.RED  + "Egy varazslatot csak egyszer tudsz megvenni!\n" + Color.WHITE;
            case 4:
                return Color.RED + "A listabol adj meg elemet [1, 2, 3...]!\n" + Color.WHITE;
            case 5:
                return Color.RED + "Nem megfelelo formatum!\n" + Color.WHITE;
            case 6:
                return Color.RED + "Legalabb egy egyseg kell a tovabblepesehz!\n" + Color.WHITE;



            default:
                return "\n";
        }
    }

    public static void printInfo(String melyik){
        if("levelSystem".equals(melyik)) {

        }
        if("helptamadas".equals(melyik)){

        }
        switch (melyik){
            case "levelSystem":
                System.out.println("Itt tudod a Hosod tulajdonsagpontjait fejleszteni.\n A tovabblepeshez ird be hogy: tovabb aztan enter. Tovabbi informacioert ird: help<tulajdonsag nev> (pl. helptamadas)");
                break;
            case "helptamadas":
                System.out.println("Tamadas: az egysegek sebzeset noveli meg, tulajdonsagpontonkent 10%-kal.");
                break;
            case "helpvedekezes":
                System.out.println("Vedekezes: az egysegeket ert sebzest csokkenti, tulajdonsagpontonkent 5%-kal.");
                break;
            case "helpvarazsero":
                System.out.println("Varazsero: a hos altal idezett varazslatok erosseget noveli.");
                break;
            case "helptudas":
                System.out.println("Tudas: a hos maximalis mannapontjait noveli, tulajdonsagpontonkent 10-zel.");
                break;
            case "helpmoral":
                System.out.println("Moral: az egysegek kezdemenyezeset noveli, tulajdonsagpontonkent 1-gyel.");
                break;
            case "helpszerencse":
                System.out.println("Szerencse: az egysegek kritikus tamadasanak eselyet noveli, tulajdonsagpontonkent 5%- kal.");
                break;
            case "nehezseg":
                System.out.println("Valassz nehezsegi szintet!");
                break;
            case "helpalltulajdonsag":
                System.out.println("Itt tudod a Hosod tulajdonsagpontjait fejleszteni.\n A tovabblepeshez ird be hogy: tovabb aztan enter. Tovabbi informacioert ird: help<tulajdonsag nev> (pl. helptamadas)");
                System.out.println("Tamadas: az egysegek sebzeset noveli meg, tulajdonsagpontonkent 10%-kal.");
                System.out.println("Vedekezes: az egysegeket ert sebzest csokkenti, tulajdonsagpontonkent 5%-kal.");
                System.out.println("Varazsero: a hos altal idezett varazslatok erosseget noveli.");
                System.out.println("Tudas: a hos maximalis mannapontjait noveli, tulajdonsagpontonkent 10-zel.");
                System.out.println("Moral: az egysegek kezdemenyezeset noveli, tulajdonsagpontonkent 1-gyel.");
                System.out.println("Szerencse: az egysegek kritikus tamadasanak eselyet noveli, tulajdonsagpontonkent 5%- kal.");
                break;
            default:
                break;
        }
    }


    public static String error(String text){
        return Color.RED + text + Color.WHITE;
    }
}
