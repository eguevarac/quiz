
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {


        final int NUM_TOTAL_QUESTIONS = 20, TOTAL_CATEGORIES = 4;
        int numQuestions, category;
        String[][] totalQuestions = new String[TOTAL_CATEGORIES][NUM_TOTAL_QUESTIONS];
        String[][] totalAnswers = new String[TOTAL_CATEGORIES][NUM_TOTAL_QUESTIONS];
        String maybeRepeat;


        questionsBox(totalQuestions);
        answersBox(totalAnswers);


        starting();
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
                System.out.println(questionsToPlay[questionIndex]);
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
            } else {
                System.out.println("\nDing, ding, ding!");
                System.out.println("Se acabó la tortura y ya sólo queda el recuento.");
                System.out.println("A ver, a ver...\n");
                System.out.println("Has acertado " + correctAnswers + " preguntas de " + (numQuestions) + ".");
                System.out.println("Tu puntuación final es de: " + (points + extraPoints));
                float correctPercent = percentCalculator(correctAnswers, numQuestions);
                finalConclusion(correctPercent);
            }

            System.out.println("\nQué me dices?");
            System.out.println("Echamos otra?");
            maybeRepeat = finishing();


        } while (maybeRepeat.equals("SI"));
        System.out.println("Pues nada. Hasta aquí la diversión.");
        System.out.println("Hasta la vista!");
        System.out.println("\nGAME OVER");


    }

    private static void starting() {
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
        totalQuestions[0][0] = "BD: Los tres grupos de instrucciones en SQL son  el DDL, el DML y el...?\n- DEL\n- DIL\n- DCL";
        totalQuestions[0][1] = "BD: Las bases de datos se emplean para guardar información?";
        totalQuestions[0][2] = "BD: En el modelo Entidad-Relación, cada uno de los recuadros equivale a una entidad?";
        totalQuestions[0][3] = "BD: En el modelo Entidad-Relación, cada uno de los óvalos equivale a una relación?";
        totalQuestions[0][4] = "BD: En el modelo Entidad-Relación, puede una relación tener atributos?";
        totalQuestions[0][5] = "BD: Los atributos son las filas en las tablas de las bases de datos?";
        totalQuestions[0][6] = "BD: Según la regla de integridad referencial, puede tener una clave foránea un valor null?";
        totalQuestions[0][7] = "BD: Puede una clave primaria tener un valor null según la regla de integridad de entidad?";
        totalQuestions[0][8] = "BD: En SQL, qué significan las siglas DDL?\n- Data Domination language\n- Data Definition Language\n- Data Direction Language\n- Diciembre Duerme Largo";
        totalQuestions[0][9] = "BD: En SQL, qué significan las siglas DCL?\n- Data Class language\n- Donde Como luego\n- Data Control Language\n- Data Christmas Language";
        totalQuestions[0][10] = "BD: En SQL, qué significan las siglas DML?\n- Dame mas libros\n- Data Manipulation Language\n- Data Massive Language\n- Data Movement Language";
        totalQuestions[0][11] = "BD: La cardinalidad N:M siempre genera una nueva tabla?";
        totalQuestions[0][12] = "BD: En SQL, las instrucciones CREATE, DROP y ALTER pertenecen al grupo de instrucciones del DML?";
        totalQuestions[0][13] = "BD: En SQL, el comando SELECT * FROM 'nombre_tabla' nos muestra todos los elementos de la tabla mencionada?";
        totalQuestions[0][14] = "BD: Qué quieren decir las siglas SQL?\n- Standard Query Language\n- Structured Query Language\n- Sin Queso Longanizas";
        totalQuestions[0][15] = "BD: Una base de datos puede ser centralizada o...?\n- Descentralizada\n- Distribuida\n- Fragmentada";
        totalQuestions[0][16] = "BD: Una base de datos puede ser relacional o...?\n- Individual\n- De entidades\n- No relacional";
        totalQuestions[0][17] = "BD: En SQL, las instrucciones SELECT, INSERT y DELETE pertenecen al grupo de instrucciones del DML?";
        totalQuestions[0][18] = "BD: Una tupla es cada una de las filas de una tabla?";
        totalQuestions[0][19] = "BD: SQL es un SGBD?";
        totalQuestions[1][0] = "SIS: Cuál es la IP reservada para el localhost?\n- 192.168.0.0\n- 127.0.0.1\n- 1.0.0.0\n- 37434959348385";
        totalQuestions[1][1] = "SIS: De qué clase es la dirección IP 172.21.240.0?\n- Alfa\n- B\n- Omega\n- C\n- No tiene clase";
        totalQuestions[1][2] = "SIS: La dirección IP 172.16.255.255 es pública?";
        totalQuestions[1][3] = "SIS: Al hacer subnetting se amplía el número de equipos que podemos conectar a una misma red?";
        totalQuestions[1][4] = "SIS: Cuántos equipos podremos conectar a una red con una máscara 255.255.255.0?\n- 256\n- 255\n- 254\n- Ninguno";
        totalQuestions[1][5] = "SIS: La tercera generación de ordenadores se caracteriza por el microprocesador?";
        totalQuestions[1][6] = "SIS: Quién definió las partes fundamentales de un ordenador: CPU, memoria principal y unidades de entrada/salida?\n- John von Neumann\n- Charles Babbage\n- Steve Jobs\n- Charles Chapplin";
        totalQuestions[1][7] = "SIS: Hermann Hollerith es conocido por desencriptar códigos alemanes con la máquina Enigma?";
        totalQuestions[1][8] = "SIS: Cuántas generaciones de ordenadores hay?\n- 3\n- 4\n- 5\n- 6\n- Demasiadas para ser contadas";
        totalQuestions[1][9] = "SIS: Una máscara de red 255.255.240.0 es estándar?";
        totalQuestions[1][10] = "SIS: La dirección IP 10.255.255.255 es privada?";
        totalQuestions[1][11] = "SIS: En una máscara estándar de clase B pertenecen dos bytes a la porción de host?";
        totalQuestions[1][12] = "SIS: Cuánto es en decimal el siguiente número binario: 11111111?\n- 128\n- 256\n- 8\n- 64\n- No se puede pasar a decimal";
        totalQuestions[1][13] = "SIS: Cuántos bits componen un nibble?\n- 2\n- 4\n- 6\n- 8";
        totalQuestions[1][14] = "SIS: Cómo se llamaba la máquina que usaba Alan Turing?\n- UNIVAC\n- ENIAC\n- ENIGMA\n- CALCULATRÓN 3000";
        totalQuestions[1][15] = "SIS: Cuál fue el primer ordenador?\n- Apple 2\n- ENIAC\n- R2D2\n- Macintosh 666";
        totalQuestions[1][16] = "SIS: Dos direcciones IP están en la misma red si tienen la misma máscara de subred?";
        totalQuestions[1][17] = "SIS: Joseph Marie Jaquard inventó las tarjetas perforadas?";
        totalQuestions[1][18] = "SIS: Cuántos bytes tiene la dirección 192.168.0.0?\n- 256\n- 4\n- 8\n- 6\n- Depende";
        totalQuestions[1][19] = "SIS: La dirección de broadcast es la última dirección IP dentro de un rango de red?";
        totalQuestions[2][0] = "EN: El lenguaje máquina se expresa en código binario?";
        totalQuestions[2][1] = "EN: Podemos dividir la CPU en dos partes: la CU y la...?\n- SQL\n- DTD\n- ALU\n- RR";
        totalQuestions[2][2] = "EN: El código ejecutable es el código intermedio entre el código fuente y el código objeto?";
        totalQuestions[2][3] = "EN: Java tiene un lenguaje propio denominado byte code?";
        totalQuestions[2][4] = "EN: Qué significan las siglas IDE?\n- Integrated Direction Environment\n- Integrated Development Environment\n- Integrated Definition Environment\n- Inmortals Dying Exclusive";
        totalQuestions[2][5] = "EN: Un lenguaje puede ser interpretado o...?\n- Traducido\n- Compilado\n- Blasfemo\n- Directo";
        totalQuestions[2][6] = "EN: Cómo se llama el compilador que emplea la máquina virtual de Java?\n- JAT\n- JET\n- JIT\n- EEUU";
        totalQuestions[2][7] = "EN: El lenguaje de alto nivel es el que mejor puede entender la máquina?";
        totalQuestions[2][8] = "LM: Qué significan las siglas JSON?\n- Java System On\n- Javascript Object Notation\n- Java System Open Notes\n- Javascript Open Notes";
        totalQuestions[2][9] = "LM: Qué significan las siglas XML?\n- Extreme Markup Language\n- Extreme Make-up Language\n- Extreme Manual Language";
        totalQuestions[2][10] = "LM: Qué significan las siglas HTML?\n- Hambre Tendre Mucha Luego\n- Hit Time Markup Language\n- Hyper Text Markup Language";
        totalQuestions[2][11] = "LM: Un documento de texto bien formado y uno válido son lo mismo?";
        totalQuestions[2][12] = "LM: Un documento JSON está compuesto por listas y objetos?";
        totalQuestions[2][13] = "PR: Un loop while tiene que realizarse al menos una vez?";
        totalQuestions[2][14] = "PR: Empleamos un bucle for i cuando no conocemos el número de repeticiones?";
        totalQuestions[2][15] = "PR: Un String es un array de chars?";
        totalQuestions[2][16] = "PR: Una función 'void' siempre retorna algo?";
        totalQuestions[2][17] = "LM: La etiqueta <nombre>Manolo<nombre> sería correcta en XML?";
        totalQuestions[2][18] = "LM: El DTD es un lenguaje de marcas?";
        totalQuestions[2][19] = "EN: Se puede cambiar el valor de una variable mientras se ejecuta un programa en debug?";
        totalQuestions[3][0] = "EIE: Cuántos socios tiene que haber como mínimo para montar una Sociedad Civil?\n- 2\n- 3\n- No tiene";
        totalQuestions[3][1] = "EIE: La Comunidad de Bienes tiene capital mínimo?";
        totalQuestions[3][2] = "EIE: Una SL tiene un capital mínimo de 3000€?";
        totalQuestions[3][3] = "EIE: Una SLNE tiene un capital máximo de 60000€?";
        totalQuestions[3][4] = "EIE: Una SA tiene mínimo de socios?";
        totalQuestions[3][5] = "EIE: Una SA tiene que desembolsar el 100% del capital mínimo en el momento de su constitución?";
        totalQuestions[3][6] = "EIE: Una Sociedad Cooperativa tiene responsabilidad ilimitada?";
        totalQuestions[3][7] = "EIE: Una sociedad colectiva tiene mínimo de socios?";
        totalQuestions[3][8] = "EIE: En una sociedad laboral, los socios no trabajadores deben tener el 51% del capital mínimo?";
        totalQuestions[3][9] = "EIE: En una SL los socios pueden vender sus participaciones libremente?";
        totalQuestions[3][10] = "FOL: Los estibadores portuarios pertenecen a las relaciones laborales especiales?";
        totalQuestions[3][11] = "FOL: Los transportistas pertenecen a las relaciones laborales especiales?";
        totalQuestions[3][12] = "FOL: Los convenios colectivos están por encima de los reglamentos en la jerarquía de leyes?";
        totalQuestions[3][13] = "FOL: Las horas extraordinarias estructurales son obligatorias?";
        totalQuestions[3][14] = "FOL: El descanso diario de un trabajador ha de ser de mínimo 8 horas?";
        totalQuestions[3][15] = "FOL: Puede un padre solicitar el permiso de lactancia?";
        totalQuestions[3][16] = "FOL: Puede un trabajador renunciar a sus vacaciones?";
        totalQuestions[3][17] = "FOL: Los funcionarios pertenecen a las relaciones laborales especiales?";
        totalQuestions[3][18] = "FOL: El principio de norma más favorable indica que la norma de rango superior\nestablece las disposiciones mínimas para el trabajador?";
        totalQuestions[3][19] = "FOL: El principio de condición más beneficiosa indica que si existen dos o más\nnormas que regulan una misma cuestión, se aplicará aquélla que establezca\nlas condiciones más beneficiosas para el trabajador.?";


    }

    private static void answersBox(String[][] totalAnswer) {

        totalAnswer[0][0] = "DCL";
        totalAnswer[0][1] = "NO";
        totalAnswer[0][2] = "SI";
        totalAnswer[0][3] = "NO";
        totalAnswer[0][4] = "SI";
        totalAnswer[0][5] = "NO";
        totalAnswer[0][6] = "SI";
        totalAnswer[0][7] = "NO";
        totalAnswer[0][8] = "DATA DEFINITION LANGUAGE";
        totalAnswer[0][9] = "DATA CONTROL LANGUAGE";
        totalAnswer[0][10] = "DATA MANIPULATION LANGUAGE";
        totalAnswer[0][11] = "SI";
        totalAnswer[0][12] = "NO";
        totalAnswer[0][13] = "SI";
        totalAnswer[0][14] = "STRUCTURED QUERY LANGUAGE";
        totalAnswer[0][15] = "DISTRIBUIDA";
        totalAnswer[0][16] = "NO RELACIONAL";
        totalAnswer[0][17] = "SI";
        totalAnswer[0][18] = "SI";
        totalAnswer[0][19] = "NO";
        totalAnswer[1][0] = "127.0.0.1";
        totalAnswer[1][1] = "B";
        totalAnswer[1][2] = "NO";
        totalAnswer[1][3] = "NO";
        totalAnswer[1][4] = "254";
        totalAnswer[1][5] = "NO";
        totalAnswer[1][6] = "JOHN VON NEUMANN";
        totalAnswer[1][7] = "NO";
        totalAnswer[1][8] = "5";
        totalAnswer[1][9] = "NO";
        totalAnswer[1][10] = "SI";
        totalAnswer[1][11] = "SI";
        totalAnswer[1][12] = "256";
        totalAnswer[1][13] = "4";
        totalAnswer[1][14] = "ENIGMA";
        totalAnswer[1][15] = "ENIAC";
        totalAnswer[1][16] = "NO";
        totalAnswer[1][17] = "SI";
        totalAnswer[1][18] = "4";
        totalAnswer[1][19] = "SI";
        totalAnswer[2][0] = "SI";
        totalAnswer[2][1] = "ALU";
        totalAnswer[2][2] = "NO";
        totalAnswer[2][3] = "SI";
        totalAnswer[2][4] = "INTEGRATED DEVELOPMENT ENVIRONMENT";
        totalAnswer[2][5] = "COMPILADO";
        totalAnswer[2][6] = "JIT";
        totalAnswer[2][7] = "NO";
        totalAnswer[2][8] = "JAVASCRIPT OBJECT NOTATION";
        totalAnswer[2][9] = "EXTREME MARKUP LANGUAGE";
        totalAnswer[2][10] = "HYPER TEXT MARKUP LANGUAGE";
        totalAnswer[2][11] = "NO";
        totalAnswer[2][12] = "SI";
        totalAnswer[2][13] = "NO";
        totalAnswer[2][14] = "NO";
        totalAnswer[2][15] = "SI";
        totalAnswer[2][16] = "NO";
        totalAnswer[2][17] = "NO";
        totalAnswer[2][18] = "NO";
        totalAnswer[2][19] = "SI";
        totalAnswer[3][0] = "2";
        totalAnswer[3][1] = "NO";
        totalAnswer[3][2] = "SI";
        totalAnswer[3][3] = "NO";
        totalAnswer[3][4] = "NO";
        totalAnswer[3][5] = "NO";
        totalAnswer[3][6] = "NO";
        totalAnswer[3][7] = "SI";
        totalAnswer[3][8] = "NO";
        totalAnswer[3][9] = "NO";
        totalAnswer[3][10] = "SI";
        totalAnswer[3][11] = "NO";
        totalAnswer[3][12] = "NO";
        totalAnswer[3][13] = "SI";
        totalAnswer[3][14] = "NO";
        totalAnswer[3][15] = "SI";
        totalAnswer[3][16] = "NO";
        totalAnswer[3][17] = "NO";
        totalAnswer[3][18] = "NO";
        totalAnswer[3][19] = "NO";
    }
}