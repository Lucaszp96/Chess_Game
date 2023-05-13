package XXLChess.Player;

import static processing.core.PApplet.nf;

public class Timer {
    public static String showTimer(int timer){
        int m = timer / 60;
        int s = timer % 60;
        String timeText = nf(m, 2) + ":" + nf(s, 2);
        return timeText;

    }
}
