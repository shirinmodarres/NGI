package UI.Component;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Colors {


        private static List<Color> colorList = new ArrayList<>();

        static {
            // Define your list of colors
            colorList.add(Color.RED);
            colorList.add(Color.GREEN);
            colorList.add(Color.BLUE);
            colorList.add(Color.YELLOW);
            colorList.add(Color.ORANGE);
            // Add more colors to the list as needed
        }

        public static Color getRandomColor() {
            Random random = new Random();
            int index = random.nextInt(colorList.size());
            return colorList.get(index);
        }
    }


