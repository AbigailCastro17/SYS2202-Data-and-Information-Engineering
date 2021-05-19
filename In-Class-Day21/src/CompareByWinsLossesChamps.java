import java.util.Comparator;

public class CompareByWinsLossesChamps implements Comparator<BasketBallTeam> {
	
	public int compare(BasketBallTeam a, BasketBallTeam b){
		if (a.getNumberOfWins()-b.getNumberOfWins()!=0) {
		    return b.getNumberOfWins()-a.getNumberOfWins();
		}
		else{
		    if (a.getNumberOfLosses()-b.getNumberOfLosses()!=0) {
		        return a.getNumberOfLosses()-b.getNumberOfLosses();
		    }
		    else {
		        return b.getNumberOfChampionships()-a.getNumberOfChampionships();
		    }
		}
	}

}
