package Lab1;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Figure {
    BufferedImage image;
    int width, height;
    int[][] pixels;
    String figureType = "";

    ArrayList<Coordinates> listOfCoordinates = new ArrayList<>();

    Figure(File bmpFile) {
        try {
            image = ImageIO.read(bmpFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        width = image.getWidth();
        height = image.getHeight();
        pixels = new int[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixels[i][j] = (image.getRGB(i, j) == -1) ? 1 : 0;
            }
        }
//
//        for (int i = 0; i < width; i++) {
//            for (int j = 0; j < height; j++) {
//                System.out.print(pixels[i][j]);
//            }
//            System.out.print("\n");
//        }

        findCoordinates();
    }

    private void findCoordinates() {
        //Left-Right-Up-Down
        findCoordinatesLRUD();
        //Left-Right-Down-Up
        findCoordinatesLRDU();
        //Right-Left-Up-Down
        findCoordinatesRLUD();
        //Right-Left-Down-Up
        findCoordinatesRLDU();
        //Up-Down-Left-Right
        findCoordinatesUDLR();
        //Up-Down-Right-Left
        findCoordinatesUDRL();
        //Down-Up-Left-Right
        findCoordinatesDULR();
        //Down-Up-Right-Left
        findCoordinatesDURL();
    }

    private void findCoordinatesLRUD() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (pixels[x][y] == 0) {
                    for (Coordinates c : listOfCoordinates) {
                        if (c.x == x && c.y == y) {
                            return;
                        }
                    }
                    listOfCoordinates.add(new Coordinates(x, y));
                    return;
                }
            }
        }
        return;
    }

    private void findCoordinatesLRDU() {
        for (int x = 0; x < width; x++) {
            for (int y = height - 1; y >= 0; y--) {
                if (pixels[x][y] == 0) {
                    for (Coordinates c : listOfCoordinates) {
                        if (c.x == x && c.y == y) {
                            return;
                        }
                    }
                    listOfCoordinates.add(new Coordinates(x, y));
                    return;
                }
            }
        }
        return;
    }

    private void findCoordinatesRLUD() {
        for (int x = width - 1; x >= 0; x--) {
            for (int y = 0; y < height; y++) {
                if (pixels[x][y] == 0) {
                    for (Coordinates c : listOfCoordinates) {
                        if (c.x == x && c.y == y) {
                            return;
                        }
                    }
                    listOfCoordinates.add(new Coordinates(x, y));
                    return;
                }
            }
        }
        return;
    }

    private void findCoordinatesRLDU() {
        for (int x = width - 1; x >= 0; x--) {
            for (int y = height - 1; y >= 0; y--) {
                if (pixels[x][y] == 0) {
                    for (Coordinates c : listOfCoordinates) {
                        if (c.x == x && c.y == y) {
                            return;
                        }
                    }
                    listOfCoordinates.add(new Coordinates(x, y));
                    return;
                }
            }
        }
        return;
    }

    private void findCoordinatesUDLR() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (pixels[x][y] == 0) {
                    for (Coordinates c : listOfCoordinates) {
                        if (c.x == x && c.y == y) {
                            return;
                        }
                    }
                    listOfCoordinates.add(new Coordinates(x, y));
                    return;
                }
            }
        }
        return;
    }

    private void findCoordinatesUDRL() {
        for (int y = 0; y < height; y++) {
            for (int x = width - 1; x >= 0; x--) {
                if (pixels[x][y] == 0) {
                    for (Coordinates c : listOfCoordinates) {
                        if (c.x == x && c.y == y) {
                            return;
                        }
                    }
                    listOfCoordinates.add(new Coordinates(x, y));
                    return;
                }
            }
        }
        return;
    }

    private void findCoordinatesDULR() {
        for (int y = height - 1; y >= 0; y--) {
            for (int x = 0; x < width; x++) {
                if (pixels[x][y] == 0) {
                    for (Coordinates c : listOfCoordinates) {
                        if (c.x == x && c.y == y) {
                            return;
                        }
                    }
                    listOfCoordinates.add(new Coordinates(x, y));
                    return;
                }
            }
        }
        return;
    }

    private void findCoordinatesDURL() {
        for (int y = height - 1; y >= 0; y--) {
            for (int x = width - 1; x >= 0; x--) {
                if (pixels[x][y] == 0) {
                    for (Coordinates c : listOfCoordinates) {
                        if (c.x == x && c.y == y) {
                            return;
                        }
                    }
                    listOfCoordinates.add(new Coordinates(x, y));
                    return;
                }
            }
        }
        return;
    }


    public String getFigureType() {
        int num = listOfCoordinates.size();

        if (num == 1) {
            figureType = "Точка";
        } else if (num == 2) {
            figureType = "Прямая";
        } else if (num == 3) {
            triangleType();
            figureType = "Треугольник";
        } else if (num == 4) {
            tetragonType();
        } else {
            circleType();
        }

        return figureType;
    }

    private void circleType() {
        ArrayList<Double> listOfLength = new ArrayList<>();

        for (Coordinates c1 : listOfCoordinates) {
            for (Coordinates c2 : listOfCoordinates) {
                if (!(c1.x == c2.x && c1.y == c2.y)) {
                    double length = Math.round(Math.sqrt(Math.pow(c2.x - c1.x, 2) + Math.pow(c2.y - c1.y, 2)) * 100) / 100;

                    if (!listOfLength.contains(length)) {
                        listOfLength.add(length);
                    }
                }
            }
        }

        if (listOfLength.size() == 5) {
            System.out.print("Окружность");
        }
        else{
            System.out.print("Эллипс");
        }
    }

    private void tetragonType() {
        ArrayList<Double> listOfLength = new ArrayList<>();

        for (Coordinates c1 : listOfCoordinates) {
            for (Coordinates c2 : listOfCoordinates) {
                if (!(c1.x == c2.x && c1.y == c2.y)) {
                    double length = Math.round(Math.sqrt(Math.pow(c2.x - c1.x, 2) + Math.pow(c2.y - c1.y, 2)) * 100) / 100;

                    if (!listOfLength.contains(length)) {
                        listOfLength.add(length);
                    }
                }
            }
        }

        Collections.sort(listOfLength);

        if (listOfLength.size() == 2) {
            System.out.print("Квадрат ");
        } else if (listOfLength.size() == 3) {
            System.out.print("Прямоугольник ");
        }
    }

    private void triangleType() {
        ArrayList<Double> listOfLength = new ArrayList<>();

        for (Coordinates c1 : listOfCoordinates) {
            for (Coordinates c2 : listOfCoordinates) {
                if (!(c1.x == c2.x && c1.y == c2.y)) {
                    double length = Math.round(Math.sqrt(Math.pow(c2.x - c1.x, 2) + Math.pow(c2.y - c1.y, 2)) * 100) / 100;

                    if (!listOfLength.contains(length)) {
                        listOfLength.add(length);
                    }
                }
            }
        }

        if (listOfLength.size() == 1) {
            System.out.print("Равносторонний ");
        } else if (listOfLength.size() == 2) {
            System.out.print("Равнобедренный ");
        }

        Collections.sort(listOfLength);

        if (listOfLength.size() == 3) {
            double h = listOfLength.get(2);
            double k1 = listOfLength.get(1);
            double k2 = listOfLength.get(0);

            if (Math.pow(k1, 2) + Math.pow(k2, 2) == Math.pow(h, 2)) {
                System.out.print("Прямоугольный ");
            }
        } else if (listOfLength.size() == 2) {
            double h = listOfLength.get(1);
            double k = listOfLength.get(0);

            if (Math.pow(k, 2) + Math.pow(k, 2) == Math.pow(h, 2)) {
                System.out.print("Прямоугольный ");
            }
        }

    }
}
