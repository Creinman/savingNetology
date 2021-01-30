import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class GameProgress implements Serializable {
        private static final long serialVersionUID = 1L;

        private int health;
        private int weapons;
        private int lvl;
        private double distance;

        public GameProgress(int health, int weapons, int lvl, double distance) {
            this.health = health;
            this.weapons = weapons;
            this.lvl = lvl;
            this.distance = distance;
        }
}
