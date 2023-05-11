
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        String playerName;
        final int NUM_TOTAL_QUESTIONS = 20, TOTAL_CATEGORIES = 4;
        int numQuestions, category;
        String[][] totalQuestions = new String[TOTAL_CATEGORIES][NUM_TOTAL_QUESTIONS];
        String[][] totalAnswers = new String[TOTAL_CATEGORIES][NUM_TOTAL_QUESTIONS];
        String maybeRepeat;
        Player player;

        Date today = new Date();
        System.out.println(today);

        questionsBox(totalQuestions);
        answersBox(totalAnswers);
        playerName = starting();
        do {
            category = choosingCategory();
            System.out.println("Te toca escoger la cantidad de preguntas que quieres responder.");
            System.out.println("Tienen que ser entre 5 y 20.");
            numQuestions = choosingNumQuestions();

            String[] questionsToPlay = new String[numQuestions];
            String[] answersToPlay = new String[numQuestions];

            System.out.println("Perfecto! Pues serán " + numQuestions + " preguntas.");
            System.out.println("Porque tú lo has querido, eh?\nNo quiero quejas después.");
            randomQuestions(category, totalQuestions, totalAnswers, questionsToPlay, answersToPlay);
            System.out.println("Ahora sí que sí, llega lo bueno!\nAaaaaaaa jugar!!!");

            int correctAnswers = 0, extraPoints = 0, correctStreak = 0, failsCounter = 0, questionIndex = 0, points = 0;
            boolean lose = false;
            do {
                System.out.println("\nPregunta número " + (questionIndex + 1) + ":");
                //Tendría que hacer por aquí el chequeo de salto de línea, creo

                formulatingQuestion(questionsToPlay[questionIndex]);

                String answer = checkingYesOrNot(answersToPlay, questionIndex, questionsToPlay);
                if (answer.equals(answersToPlay[questionIndex])) {
                    System.out.println("La respuesta es correcta!!\n");
                    System.out.println("Que siga la fiesta!");
                    correctAnswers++;
                    failsCounter = 0;
                    correctStreak++;
                    if (correctStreak > 3) {
                        extraPoints = extraPoints + 10;
                    }
                    points = points + 10;
                } else {
                    System.out.println("Por qué a nadie le sorprende que hayas fallado?");
                    System.out.println("La respuesta correcta era: " + answersToPlay[questionIndex]);
                    failsCounter++;
                    correctStreak = 0;
                }
                questionIndex++;
                if (failsCounter >= 3) {
                    lose = true;
                }


            } while (questionIndex < numQuestions && !lose);
            if (lose) {
                System.out.println("\nDing, ding, ding!");
                System.out.println("Has fallado más de dos veces seguidas!");
                System.out.println("Está claro que sólo una palabra habita en tu mente:");
                System.out.println("\nNULL!!");
                player = new Player(playerName.toUpperCase(), numQuestions, correctAnswers);

                addingScore(player);

            } else {
                System.out.println("\nDing, ding, ding!");
                System.out.println("Se acabó la tortura y ya sólo queda el recuento.");
                System.out.println("A ver, a ver...\n");
                System.out.println("Has acertado " + correctAnswers + " preguntas de " + (numQuestions) + ".");
                System.out.println("Tu puntuación final es de: " + (points + extraPoints));
                float correctPercent = percentCalculator(correctAnswers, numQuestions);
                finalConclusion(correctPercent);
                player = new Player(playerName.toUpperCase(),numQuestions,correctAnswers,(points+extraPoints),correctPercent);

                addingScore(player);
            }

            showingScore();
            System.out.println("\nQué me dices?");
            System.out.println("Echamos otra?");
            maybeRepeat = finishing();


        } while (maybeRepeat.equals("SI"));
        System.out.println("Pues nada. Hasta aquí la diversión.");
        System.out.println("Hasta la vista!");
        System.out.println("\nGAME OVER");


    }

    private static void formulatingQuestion(String questionToSplit) {



        String[] splited = questionToSplit.split("@");
        for (String s :
                splited) {
            System.out.println(s);
        }

    }

    private static void addingScore(Player player) {

        Date date = new Date();
        String today = date.toString();
        today = today.concat("--->");

        String playerScore =today.concat(player.toString());

        Path path = Paths.get("src/resources/scores.txt");

        try {
            Files.writeString(path, "\n"+playerScore, StandardOpenOption.APPEND);

        } catch (IOException e) {

            System.out.println("ERROR EN LA ESCRITURA DEL FICHERO");
        }
    }

    private static void showingScore() {
        System.out.println("\nVEAMOS LAS ESTADÍSTICAS DE TODOS LOS JUGADORES:\n");

        //para leer el archivo líea a línea
        String fileName = "src/resources/scores.txt";
        BufferedReader br;
        String line;

        try {
            br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null){
                System.out.println(line);
            }

        } catch (FileNotFoundException e) {

            System.out.println("NO SE HA ENCONTRADO EL FICHERO");

        } catch (IOException e) {

            System.out.println("ERROR DURANTE LA LECTURA DEL FICHERO");
        }


    }


    private static String starting() {
        String playerName;
        System.out.println("Bienvenido/a a Null! El Cuestionario definitivo!!");
        System.out.println("Expliquemos de qué va el asunto\n(luego no vale decir que no sabías las reglas, eh?):\n");
        System.out.println("En primer lugar, deberás escoger la categoría y número de preguntas que quieres contestar.");
        System.out.println("\nA continuación, deberás ir respondiendo una a una cada una de ellas.");
        System.out.println("La mayoría serán de SI/NO,\npero habrá otras que te darán varias opciones para que escojas la correcta.\nConfiamos en que lo harás bien.");
        System.out.println("\nCada acierto suma diez puntos a tu marcador.");
        System.out.println("Si aciertas más de tres preguntas seguidas entrarás en una racha de aciertos y\ncada respuesta puntuará doble hasta que falles.\nDOBLE!!!");
        System.out.println("\nPor otro lado, si fallas más de dos veces seguidas\nquedarás totalmente descalificado\ny justificarás con el contenido de tu cerebro\nel nombre de nuestro programa.");
        System.out.println("\nSin más dilación, vamos a ello.");
        System.out.println("Mucha suerte!!!\n");
        System.out.println("Lo primero de todo: dinos cómo te llamas!");
        playerName = Teclat.llegirString();
        System.out.println("Pues bienvenido, "+playerName+"!\n");

        return playerName;
    }

    private static String checkingYesOrNot(String[] answersToPlay, int questionIndex, String[] questionsToPlay) {
        String answer;
        answer = Teclat.llegirString();
        answer = answer.toUpperCase();
        System.out.println("has respondido " + "'" + answer + "'.");
        if (answersToPlay[questionIndex].equals("SI") || answersToPlay[questionIndex].equals("NO")) {
            while (!answer.equals("SI") && !answer.equals("NO")) {
                System.out.println("Esta es una pregunta de SI/NO.");
                System.out.println("Vamos a no tener en cuenta lo que has dicho y darte otra oportunidad:");
                System.out.println(questionsToPlay[questionIndex]);
                answer = Teclat.llegirString();
                answer = answer.toUpperCase();
            }
        }
        return answer;
    }

    private static int choosingNumQuestions() {
        int numQuestions;

        numQuestions = Teclat.llegirInt();
        while (numQuestions < 5 || numQuestions > 20) {
            System.out.println("Menos mal que la elección de preguntas no puntúa");
            System.out.println("(han de ser entre 5 y 20; indícalo en numérico).");
            System.out.println("Repitamos: Cuántas preguntas quieres?");
            numQuestions = Teclat.llegirInt();
        }
        return numQuestions;
    }


    private static int choosingCategory() {
        int category;
        do {
            System.out.println("Escoge tu categoría:");
            System.out.println("1. Bases de datos (BD).");
            System.out.println("2. Sistemas (SIS).");
            System.out.println("3. Lenguaje de marcas (LM), entornos (EN) y programación (PR).");
            System.out.println("4. FOL y EIE.");
            System.out.println("5. Remix.");
            category = Teclat.llegirInt();
            category--;
            switch (category) {
                case 0:
                    System.out.println("Un buen dato para guardar va a ser tu pésima puntuación.");
                    break;
                case 1:
                    System.out.println("A ver si estamos conectados en la misma red!");
                    break;
                case 2:
                    System.out.println("El código es la clave!");
                    break;
                case 3:
                    System.out.println("No se te ocurrió una más aburrida?");
                    break;
                case 4:
                    System.out.println("Qué atrevido/a!");
                    break;
                default:
                    System.out.println("Tienes que escoger entre 1 y 5. No creo que sea tan difícil.");
                    break;
            }
        } while (category > 4);
        return category;
    }


    private static void randomQuestions(int category, String[][] totalQuestions, String[][] totalAnswers, String[] questionsToPlay, String[] answersToPlay) {

        int randomNum, counter = 0;
        boolean remix = category == 4;

        do {
            if (remix) {
                category = (int) (Math.random() * 4);

            }
            randomNum = (int) (Math.random() * 20);
            int index = 0;
            boolean exist = false;
            do {
                if (questionsToPlay[index] != null) {
                    if (questionsToPlay[index].equals(totalQuestions[category][randomNum])) {
                        exist = true;
                    }
                }
                index++;
            } while (!exist && index < questionsToPlay.length);
            if (!exist) {
                questionsToPlay[counter] = totalQuestions[category][randomNum];
                answersToPlay[counter] = totalAnswers[category][randomNum];
                counter++;

            }


        } while (counter < questionsToPlay.length);
    }


    private static float percentCalculator(int correctAnswers, int numQuestions) {
        float correctPercent;
        correctPercent = 100 * ((float) correctAnswers / (float) numQuestions);
        DecimalFormat twoDecimals = new DecimalFormat("#.00");
        System.out.println("\nUn momento!\nVayamos más allá!");
        System.out.println("Qué porcentaje de aciertos tiene nuestro/a concursante?");
        System.out.println("Pues un maravilloso " + twoDecimals.format(correctPercent) + "%.");
        return correctPercent;


    }

    private static void finalConclusion(float correctPercent) {
        if (correctPercent <= 33.00) {
            System.out.println("\nCreo que deberías repasar un poquillo, eh?");
            System.out.println("No te quedas con un NULL, pero casi.");
            System.out.println("Digamos que las sabías pero te pudieron los nervios");
        } else if (correctPercent <= 66.00) {
            System.out.println("\nEn el filo de la navaja!");
            System.out.println("En muchos casos, el dudar mata. Has tenido suerte de que aquí sólo te llevase a perder.");
            System.out.println("Seguro que podrías haberlo hecho mejor.");
        } else if (correctPercent <= 99.00) {
            System.out.println("\nBravo! Bravo!");
            System.out.println("Todo un/a maestro/a del conocimiento!");
            System.out.println("\nAunque en un par de ellas se notó que acertaste de chiripa, eh?.");
            System.out.println("Pero bueno. La suerte forma parte del juego.\n");
            System.out.println("Enhorabuena!");
        } else if (correctPercent == 100.00) {
            System.out.println("\nHas acertado todas!!!");
            System.out.println("\nTe felicitaría, pero siento decirte que nuestros guionistas");
            System.out.println("han escogido las preguntas con una dificultad estimada para un niño de 6 años.");

        }
    }

    private static String finishing() {
        String maybeRepeat;
        maybeRepeat = Teclat.llegirString();
        maybeRepeat = maybeRepeat.toUpperCase();
        while (!maybeRepeat.equals("SI") && !maybeRepeat.equals("NO")) {
            System.out.println("En serio vas a fallar hasta esta pregunta?");
            System.out.println("Quieres jugar otra o no?");
            maybeRepeat = Teclat.llegirString();
            maybeRepeat = maybeRepeat.toUpperCase();
        }
        return maybeRepeat;

    }

    private static void questionsBox(String[][] totalQuestions) {


        String [] fileNames = {"src/resources/questions/BDQuestions.txt","src/resources/questions/SISQuestions.txt","src/resources/questions/LM_EN_PRQuestions.txt","src/resources/questions/FOL_EIEQuestions.txt"};
        BufferedReader br;
        String line;
        int y;
        int x;

        for (y = 0; y < 4; y++) {
            x=0;
            try {
                br = new BufferedReader(new FileReader(fileNames[y]));
                while ((line = br.readLine()) != null) {
                    totalQuestions[y][x] = line;
                    x++;
                }

            } catch (FileNotFoundException e) {

                System.out.println("NO SE HA ENCONTRADO EL FICHERO");

            } catch (IOException e) {

                System.out.println("ERROR DURANTE LA LECTURA DEL FICHERO");
            }
        }

    }


    private static void answersBox(String[][] totalAnswer) {


        String [] fileNames = {"src/resources/answers/BDAnswers.txt","src/resources/answers/SISAnswers.txt","src/resources/answers/LM_EN_PRAnswers.txt","src/resources/answers/FOL_EIEAnswers.txt"};
        BufferedReader br;
        String line;
        int y;
        int x;

        for (y = 0; y < 4; y++) {
            x=0;
            try {
                br = new BufferedReader(new FileReader(fileNames[y]));
                while ((line = br.readLine()) != null) {
                    totalAnswer[y][x] = line;
                    x++;
                }

            } catch (FileNotFoundException e) {

                System.out.println("NO SE HA ENCONTRADO EL FICHERO");

            } catch (IOException e) {

                System.out.println("ERROR DURANTE LA LECTURA DEL FICHERO");
            }
        }

    }
}