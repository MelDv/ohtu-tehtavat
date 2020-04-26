package ohtu;

public class TennisGame {

    private int playerOneScore = 0;
    private int playerTwoScore = 0;
    private String playerOne;
    private String playerTwo;

    public TennisGame(String player1Name, String player2Name) {
        this.playerOne = player1Name;
        this.playerTwo = player2Name;
    }

    public void wonPoint(String player) {
        if (player == "player1") {
            playerOneScore += 1;
        } else {
            playerTwoScore += 1;
        }
    }

    public String getScore() {
        String score = "";
        if (playerOneScore == playerTwoScore) {
            score = scoreEven(score);
        } else if (playerOneScore >= 4 || playerTwoScore >= 4) {
            score = scoreMoreThanFour(score);
        } else {
            score = otherCases(score);
        }
        return score;
    }

    private String scoreEven(String score) {
        switch (playerOneScore) {
            case 0:
                score = "Love-All";
                break;
            case 1:
                score = "Fifteen-All";
                break;
            case 2:
                score = "Thirty-All";
                break;
            case 3:
                score = "Forty-All";
                break;
            default:
                score = "Deuce";
                break;                
        }
        return score;
    }

    private String scoreMoreThanFour(String score) {
        int minusResult = playerOneScore - playerTwoScore;
        if (minusResult == 1) {
            score = "Advantage player1";
        } else if (minusResult == -1) {
            score = "Advantage player2";
        } else if (minusResult >= 2) {
            score = "Win for player1";
        } else {
            score = "Win for player2";
        }
        return score;
    }

    private String otherCases(String score) {
        int tempScore;
        for (int i = 1; i < 3; i++) {
            if (i == 1) {
                tempScore = playerOneScore;
            } else {
                score += "-";
                tempScore = playerTwoScore;
            }
            switch (tempScore) {
                case 0:
                    score += "Love";
                    break;
                case 1:
                    score += "Fifteen";
                    break;
                case 2:
                    score += "Thirty";
                    break;
                case 3:
                    score += "Forty";
                    break;
            }
        }
        return score;
    }
}
