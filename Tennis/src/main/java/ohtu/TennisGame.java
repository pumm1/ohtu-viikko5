package ohtu;

public class TennisGame {

    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1") {
            m_score1 += 1;
        } else {
            m_score2 += 1;
        }
    }

    public String equalScores(String score) {
        if (m_score1 == 0) {
            score = "Love-All";
        } else if (m_score1 == 1) {
            score = "Fifteen-All";
        } else if (m_score1 == 2) {
            score = "Thirty-All";
        } else if (m_score1 == 3) {
            score = "Forty-All";
        } else {
            score = "Deuce";
        }
        return score;
    }

    public String fourOrMore(String score) {
        int minusResult = m_score1 - m_score2;
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

    public String elseScore(String score) {
        int tempScore = 0;
        for (int i = 1; i < 3; i++) {
            if (i == 1) {
                tempScore = m_score1;
            } else {
                score += "-";
                tempScore = m_score2;
            }
            score = switchCase(tempScore, score);
        }
        return score;
    }

    public String switchCase(int temp, String score) {
        if (temp == 0) {
            score += "Love";
        } else if (temp == 1) {
            score += "Fifteen";
        } else if (temp == 2) {
            score += "Thirty";
        } else if (temp == 3) {
            score += "Forty";
        }
        return score;
    }

    public String getScore() {
        String score = "";
        if (m_score1 == m_score2) {
            score = equalScores(score);
        } else if (m_score1 >= 4 || m_score2 >= 4) {
            score = fourOrMore(score);
        } else {
            score = elseScore(score);
        }
        return score;
    }
}
