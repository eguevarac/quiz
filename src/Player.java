import java.util.Date;

public class Player {

    private String playerName;
    private int questions;
    private int correctAnswers;
    private int score;
    private float percentScore;



    public Player(String playerName, int questions, int correctAnswers) {
        this.playerName = playerName;
        this.questions = questions;
        this.correctAnswers = correctAnswers;
        this.score = 0;
        this.percentScore = 0;
    }
    public Player(String playerName, int questions, int correctAnswers, int score, float percentScore) {
        this.playerName = playerName;
        this.questions = questions;
        this.correctAnswers = correctAnswers;
        this.score = score;
        this.percentScore = percentScore;
    }


    @Override
    public String toString() {
        return  this.playerName + ".- Total preguntas:"+this.questions+
                " | Respuestas correctas:"+this.correctAnswers+
                " | Puntuación:"+this.score+
                " | Porcentaje de aciertos:"+this.percentScore;

    }
}

