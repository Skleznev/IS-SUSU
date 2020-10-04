package Lab2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Search {
    final private HashMap<File, List<String>> pair = new HashMap<>();
    final private HashMap<File, Double> relevance = new HashMap<>();

    private static final String[] stopWords = {"а", "б", "будет", "будит", "буедт", "быть", "бы", "был", "была", "были", "было", "быть", "в",
            "вам", "вас", "весь", "во", "вот", "все", "всего", "всех", "вы", "г", "д", "да", "для", "дни",
            "днями", "до", "дык", "дял", "е", "ё", "евсь", "его", "ее", "ей", "если", "есть", "еще", "ж", "же",
            "з", "за", "и", "ибо", "из", "или", "им", "имхо", "их", "й", "к", "как", "какже", "ко", "когда",
            "коотрый", "которая", "которйо", "которой", "который", "которых", "кто", "л", "м", "мегалол",
            "меня", "мля", "мне", "мной", "могли", "могу", "могут", "может", "мочь", "мы", "н", "на", "нах",
            "наш", "не", "него", "нее", "нет", "них", "но", "о", "об", "омчь", "он", "она", "они", "оно", "от",
            "ответ", "п", "по", "при", "р", "с", "свйо", "свое", "свой", "своя", "себя", "собой", "т", "тагда",
            "так", "такйо", "такой", "те", "тебя", "тем", "то", "тобой", "тогда", "того", "той", "только",
            "том", "тоьлко", "ты", "у", "уже", "упс", "ф", "х", "хороше", "ц", "ч", "чего", "чем", "что",
            "чтобы", "ш", "шта", "щ", "ъ", "ы", "ыбть", "ь", "э", "эта", "эти", "это", "этот", "ю", "я"};

    public Search() {
        String[] stringList;
        List<String> list;

        File[] filesList = (new File("C:/Users/Zotova/IdeaProjects/Интеллектуальные системы и технологии/src/Lab2/Collection")).listFiles();
        if (filesList != null) {
            for (File f : filesList) {
                if (f.isFile()) {
                    list = new ArrayList<>();

                    try {
                        Scanner scanner = new Scanner(f);
                        while (scanner.hasNext()) {
                            String line = scanner.nextLine();
                            stringList = line.split("[\\p{Punct}\\s]+");

                            for (String s : stringList) {
                                if (!Arrays.asList(stopWords).contains(s)) {
                                    list.add(new Porter().stem(s));
                                }
                            }
                        }
                        scanner.close();
                        pair.put(f, list);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public void searchSystem(String str) {
        String[] findList = str.split("[\\p{Punct}\\s]+");

        for (int i = 0; i < findList.length; i++) {
            findList[i] = (new Porter()).stem(findList[i]);
        }

        pair.forEach((k, v) -> {
            double tfIdf = 0;

            for (String term : findList) {
                tfIdf += calculateTfIdf(v, term);
            }
            if (tfIdf > 0) {
                relevance.put(k, tfIdf);
            }
        });

        if (relevance.isEmpty()) {
            System.out.println("Не найдено");
        } else {

            List<Double> mapValues = new ArrayList<>(relevance.values());
            Collections.sort(mapValues);

            System.out.println("ФАЙЛ:\t\tРЕЛЕВАНТНОСТЬ:");

            for (int i = mapValues.size() - 1; i >= 0; i--){
                int finalI = i;
                relevance.forEach((k, v) -> {
                    if (v == mapValues.get(finalI)){
                        System.out.println(k.getName() + ".txt" + "\t\t" + v);
                    }
                } );
            }
            //relevance.forEach((k, v) -> System.out.println(k.getName() + ".txt" + "\t\t" + v));
        }
    }

    private double calculateTfIdf(List<String> text, String term) {
        double tfCount = 0;
        for (String t : text) {
            if (t.equals(term)) {
                tfCount++;
            }
        }

        double docFr = 0;
        for (HashMap.Entry<File, List<String>> entry : pair.entrySet()) {
            for (String word : entry.getValue()) {
                if (word.equals(term)) {
                    docFr++;
                    break;
                }
            }
        }

        if (docFr != 0) {
            return ((tfCount / text.size()) * Math.log(((double) pair.size() / docFr)));
        } else {
            return 0;
        }
    }
}
