# Heroes of Might & Magic

## A Játék futtatása:

A játék futtatásához Java SE Development Kit 17 szükséges (Itt tudod letolteni: https://www.oracle.com/java/technologies/downloads/#java17 )

# Fontos
- Kérlek a játékot csak cmd, powershell (Ezt nem szeretem, mert alapértelmezetten kék, de ha nem zavar a ríkító szín akkor ott is működik), vagy terminálból indítsd. Véletlenül sem IDE-ből, különben a játék nem tudja clearelni a képernyőt. Pls.

### Windows
# fontos hozzáadni: -encoding utf-8  -at különben nem fogja jól megjeleníteni, mert ilyen jó a windows 

    A kicsomagolt mappában találsz egy r.bat nevű file-t. Ha duplán rákattintasz akkor indul is a játék.
    Ha a pálya megjelenítése rossz, az valószínűleg azért van, mert tűl nagy a betűméret. Ha nem jó Ctrl+Görgő -vel tudod kicsinyíteni. Ha úgy nem tudod, akkor jobb click és cmd beállításokon belül.
- Ha a r.bat nevű file nem működik:
    - Nyisd meg a cmd-t vagy powershell-t(Win+R cmd Enter, Search powershell Enter), navigálj el a jatek root mappájába (ha nem tudod hogyan: file kezelőben megnyitod a játék mappáját, dupla klikk az elérési útvonalra, azt másolod, majd a cmd-be:
     cd <<elérési útvonal a játék mappájához>>)
    - javac -encoding utf-8 *.java
    - java Main
- Ha továbbra sem sikerült elindítani a játékot -> ssh - val tudod futtatni az egyetem szerverén. Erröl lentebb részeletes leírás  

### Linux
A kicsomagolt mappában találsz egy r.sh-nevű file-t. Ha ezt futtatod terminálban akkor indul is a játék.
- terminal# ./r.sh
- ha nem tudod futtatni próbáld->
    - terminal# chmod 755 r.sh
    - Ha valamiért nem működne próbáld->
        - terminal# javac *.java
        - terminal# java Main.java
## SSH-s megoldás
- Ha tudod, hogyan kell akkor csak felmasolod valahogy (scp, sftp) es ugyanugy tudod futtatni.


- Ha valami oknál fogva sehogy sem akarna működni akkor ssh-val az egyetem szerverén biztosan tudod futtatni:

terminal# ssh h000000@linux.inf.u-szeged.hu
                ^
                |
             A h-s azonosítód
h142508@linux.inf.u-szeged.hu's password:   
- Beíród a jelszavadat hozzá (az a jelszó ami biron is a jelszavad)
terminal# mkdir jatek
terminal# exit
terminal# scp ./* h000000@linux.inf.u-szeged.hu:/home/h000000/jatek
terminal# ssh h000000@linux.inf.u-szeged.hu
terminal# cd jatek
terminal# javac *.java
terminal# java Main

Remélem sikerül futtatni nagyobb gondok nélkül, és remélem tetszeni fog amit csináltam.

Jó játékot és gyors kiértékelést :)




________________________________________________________________

## Játékmenet
### Előkészítés
3 nehézségi fokozat
- könnyű (1300 arany kezdésnek)
- közepes (1000 arany kezdésnek)
- nehéz (700 arany kezdésnek)

Az ellenfél mindig 1000 arannyal kezd.

### Játékmenet

** Sebzés magyarázat: **
Adott típusú egységekből mindig legfeljebb egy lehet sérült, amelyiknek az életereje nem maximális. Tehát ha volt 100 íjászunk, akik kaptak 5 sebzést, akkor az összesített életerejük 695, ami azt jelenti, hogy 99 íjászunk van 7 életerővel és 1 íjászunk van 2 életerővel. Ha ezután kapnak az íjászok még 14 sebzést, akkor az összesített életerő 684 lesz. Ez azt jelenti, hogy 98 íjászunk marad, ebből 97 íjásznak lesz teljes életereje, míg 1 íjásznak lesz 5 életereje.


## Hősök
Tulajdonságok:

- Támadás ( sebzés +10% / pont )
- Védekezés ( sebzés -5% / pont )
- Varázserő ( Varazs sebzes növelése )
- Tudás ( +10 mana / pont)
- Morál ( + kezdeményezés / pont)
- Szerencse ( crit +5% / pont)

Default:
Mindenen 1 pont ( ez ingyenes )

Megkötés:
max 10 pont / tulajdonság

Első tulajdonság pont 5 arany, utána mindig 10%-al nő és mindig felfele kerekítődik. (5, 6, 7, 8, 9, 10, 11, 13)

A hős tud varázsolni, amihez varázslatra van szükség
* legalább 5 varázslat implementálása
Varázslatnak van:
- ára
- manaköltség
- hatás


## Beadási határidő: 2022.04.17. 23:59:00

## beadás:
- zip-ként csatolni minden működéshez szükséges forrás file-t
- max 20 MB
- relatív útvonalak használata
- ide kell beadni: https://moodle2.inf.u-szeged.hu/moodle38

## Követelmények:
- Java 17 
