package Lab1;

import java.io.File;

class Lab1 {
    public static void main(String[] args) {
        Figure figure = new Figure(new File("C:/Users/Zotova/IdeaProjects/Интеллектуальные системы и технологии/src/Lab1/img1.bmp"));
        System.out.println(figure.getFigureType());
    }
}

