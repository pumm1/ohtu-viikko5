package ohtu.intjoukkosovellus;

import java.util.ArrayList;

public class StubIO implements IO {

    private ArrayList<String> rivit;
    private int i;
    private ArrayList<String> tulosteet;

    public StubIO(ArrayList<String> rivit) {
        this.rivit = rivit;
        this.tulosteet = new ArrayList<>();
    }

    @Override
    public String nextLine() {
        if (i < rivit.size()) {
            return rivit.get(i++);
        }
        return "";
    }

    @Override
    public void print(String merkkijono) {
        tulosteet.add(merkkijono);
    }

    public ArrayList<String> getPrints() {
        return tulosteet;
    }

}
