
package tinderproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 *
 * @author User
 */
public class TinderProject {

    static ArrayList<User> users = new ArrayList<>();
    
    public static void main(String[] args) throws FileNotFoundException {
        
    readFile();    
    
    // for (User user  : users) {
    //   System.out.println(user.getId() + "  " + user.getName() + "  " + user.getGender() + "  " + user.getAge() + "  " + user.getHair() + "  " + user.getEye() );
    // }
    // System.out.println(users.get(2).getName() + " id:" + users.get(2).getId()  +"  likes: " + users.get(2).getLikes() + "   dislikes: " + users.get(2).getDisLikes() + "   ki az aki like-olta: " + users.get(2).getWhoLikesMe()   );
    // getQuennOfApp();
    // getMostChooseyUser();
    // getSingles();
    // biggestDifferenceOfAgeBetweenLikes();
    // getDesperateUsersWhoHasLikesOnly();
    // isNonHeteroSexualLikes();
    
    
    
    }
    
    // Ki az alkalmazás “királynője”? (Melyik női felhasználót jelölték be a legtöbben kedvencnek?) 
    // Ha holtverseny alakul ki, akkor írd ki az összes felhasználó nevét!
    public static void getQuennOfApp () {
        int maxLikes = 0;
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getGender().equals(GenderType.FEMALE)) {
                int actLikes = users.get(i).getWhoLikesMe().size();
                if(actLikes > maxLikes) {
                    maxLikes = actLikes;
                }
            }
        }
        
        for (User user : users) {
            
             if( user.getGender().equals(GenderType.FEMALE) && ( user.getWhoLikesMe().size() == maxLikes ) ) {
                 System.out.println(user.getName() + " like-ok száma: " + user.getWhoLikesMe().size());
             }
        }
        
    }
    
    // Melyik felhasználó a legválogatósabb? (Melyik felhasználónál a legkisebb az az arány, 
    // hogy hány felhasználót jelölt szimpatikusnak az összes jelöléséhez képest.)
    public static void getMostChooseyUser () {
        double minRatio = 1.0;
        String chooseyName = "";
        for (User user : users) {
            int like = user.getLikes().size();
            int sumNomination = user.getLikes().size() + user.getDisLikes().size();
            double actRatio = (double)like / sumNomination;
            if(actRatio < minRatio) {
                minRatio = actRatio;
                chooseyName = user.getName();
            }
        }
        System.out.println(chooseyName + " a legválogatósabb...");
        
        
    }

    // Hány olyan elkeseredett felhasználó volt, aki mindenkit szimpatikusnak jelölt? 
    // (Azaz nem volt DISLIKE jelölése, csak LIKE???  vagy olyan aki nemre való tekintet nélkül mindenkit like-olt???)
    public static int getDesperateUsersWhoHasLikesOnly () {
        int despUsersNum = 0;
        for (User user : users) {
            if(user.getLikes().size() > 0 && (user.getDisLikes().size() == 0)) {
                despUsersNum++;
                System.out.println(user.getName() + " likes:" + user.getLikes() + "  dislikes: " + user.getDisLikes());
            }
        }
        return despUsersNum;
    }

    
    // Hány olyan felhasználó volt, akit senki sem húzott jobbra az alkalmazásban?
    public static void getSingles () {
        int numSingles = 0;
        ArrayList<String> singles = new ArrayList<>();
        for (User user : users) {
            if(user.getWhoLikesMe().size() == 0) {
                numSingles++;
                singles.add(user.getName());
            }
        }
        Collections.sort(singles);
        System.out.println(numSingles + "  felhasználót senki nem húzott jobbra az alkalmazásban");
        System.out.println("név szerint");
        System.out.println("= = = = = = = = = = = = = = = = ");
        for (String name : singles) {
            System.out.println(name);
        }
        
    }
    
    // Hány “match” van a felhasználók között a jelenlegi állapot szerint? 
    // (Match-nek hívjuk azt az állapotot, amikor két felhasználó egymást kölcsönösen szimpatikusnak jelöli.)
    
    
    // Hány olyan kék szemű felhasználó van, aki több szőke felhasználót húzott jobbra, mint barna hajút balra?
    
    // Melyik a legnagyobb korkülönbségű szimpatikusnak jelölés az adatbázisban?
    public static int biggestDifferenceOfAgeBetweenLikes() {
        int biggestDiff = 0;
        for (User user : users) {
            for (User userLiked : users) {
                if (user.getLikes().contains(userLiked.getId())) {
                    int actDiffAge = Math.abs(user.getAge() - userLiked.getAge());
                    biggestDiff = Math.max(actDiffAge, biggestDiff);
                }
            }
        }
        System.out.println("maxDiff: " + biggestDiff );
        return biggestDiff;
    }
    
    // Van-e nem heteroszexuális szimpatikusnak jelölés az alkalmazás adatbázisában?
    public static boolean isNonHeteroSexualLikes() {
        for (User user : users) {
            for (User user1 : users) {
                if (user.getLikes().contains(user1.getId()) && user.getGender().equals(user1.getGender())) {
                    System.out.println("van nem hetero jelölés");
                    System.out.println(user.getName() + " - " + user1.getName());
                    return true;
                }
            }
        }
        System.out.println("csak hetero jelölés van");
        return false;
    }

    
    public static void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("users.txt"));
        while (sc.hasNext()) {
            String[] lineParts = sc.nextLine().split(",");
            int id = Integer.parseInt(lineParts[0]);        // id
            String name = lineParts[1];                     // név    

            // nő v. ffi
            GenderType gender;
            if (lineParts[2].equals("MALE")) {
                gender = GenderType.MALE;
            } else {
                gender = GenderType.FEMALE;
            }
            int age = Integer.parseInt(lineParts[3]);

            // hajszín beállítása
            HairColor hair;
            if (lineParts[4].equals("BROWN")) {
                hair = HairColor.BROWN;
            } else if (lineParts[4].equals("BLONDE")) {
                hair = HairColor.BLONDE;
            } else if (lineParts[4].equals("BLACK")) {
                hair = HairColor.BLACK;
            } else {
                hair = HairColor.RED;
            }

            // szem színének beállítása
            EyeColor eye;
            if (lineParts[5].equals("BLUE")) {
                eye = EyeColor.BLUE;
            } else if (lineParts[5].equals("GREEN")) {
                eye = EyeColor.GREEN;
            } else if (lineParts[5].equals("BROWN")) {
                eye = EyeColor.BROWN;
            } else {
                eye = EyeColor.GRAY;
            }

            // példányosítom a usert és beleteszem az ArrayList-be 'users'
            User u = new User(id, name, gender, age, hair, eye);
            users.add(u);
        }
        sc.close();

        sc = new Scanner(new File("swipes.txt"));
        while (sc.hasNext()) {
            String[] lineParts = sc.nextLine().split(",");
            int id = Integer.parseInt(lineParts[0]);        // Az aki jelöl valakit
            int idWhom = Integer.parseInt(lineParts[1]);    // Akit jelölnek ( jobbra(LIKE) v. balra(DISLIKE) húznak)
            String likeOrDislike = lineParts[2];
            
            // LIKE esetén: 
            if (likeOrDislike.equals("LIKE")) {
                users.get(id - 1).getLikes().add(idWhom);           // aki LIKE-ol valakit azt beletesszük az Ő likes Set-jébe
                    // kivesszük a dislike-ból ha benne volt esetleg
                    if(users.get( id - 1 ).getDisLikes().contains(idWhom)) {
                       users.get( id - 1 ).getDisLikes().remove(idWhom);
                    }
                 // ill akit megjelölt annak a whoLikesMe -be belerakjuk az Ő id-jét    
                users.get( idWhom - 1).getWhoLikesMe().add(id);    
            } else {        // DISLIKE esetén
                users.get(id - 1).getDisLikes().add(idWhom);        // akit DISLIKE-ol azt(annak az id-jét  >>> idWhom) belerakjuk az Ő dislikes Set jébe
                // vegyük ki a like Set-ből ha benne volt esetleg
                if(users.get( id - 1 ).getLikes().contains(idWhom)) {
                   users.get( id - 1 ).getLikes().remove(idWhom);
                }
                // vegyük ki annak a whoLikesMe Set-ből ha benne van.
                if(users.get(idWhom - 1).getWhoLikesMe().contains(id)) {
                   users.get(idWhom -1 ).getWhoLikesMe().remove(id);
                }
            }
            
        }
        sc.close();

    }
    
}
