import java.util.Comparator;

public class CompareByPlayoffsAndSalary implements Comparator<BasketBallTeam> {
	
	public int compare(BasketBallTeam a, BasketBallTeam b){
		if (Boolean.compare(a.isPlayoffTeam(), b.isPlayoffTeam()) == 0) {
		    return (int) (a.getSalaryInMillions()-b.getSalaryInMillions());
		}
		else
		    return -Boolean.compare(a.isPlayoffTeam(), b.isPlayoffTeam());
	}

}
