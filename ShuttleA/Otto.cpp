#include "ShuttleA.h"
#include "Otto.h"

typedef void(ShuttleA:: *StatePt)(double, double, double);

void ShuttleA::postStep(double simt, double simdt, double mjd){
	StatePt state_fct[] = { &ShuttleA::state0, &ShuttleA::state1 };
	(this->*state_fct[m_state])(simt, simdt, mjd);
}


/* */
void ShuttleA::state0(double simt, double simdt, double mjd){
	/* Actions */
	if (simt > 5){
		SetThrusterLevel(th_hover[0], 1);
		SetThrusterLevel(th_hover[1], 1);
	}

	/* Transition */
	if (simt > 10){
		m_state = 1;
	}
}

void ShuttleA::state1(double simt, double simdt, double mjd){
	/* Actions */
	/*SetThrusterLevel(th_main[0], 0.3);
	SetThrusterLevel(th_main[1], 0.3);*/
	
	// maintainAltitude(500.);
	maintainCardinal(0);



	/* Transition */

}

/**
	Control loop to maintain the altitude to the given goal
*/
void ShuttleA::maintainAltitude(double goal){
	double tl0 = GetThrusterLevel(th_hover[0]);
	double tl1 = GetThrusterLevel(th_hover[1]);

	double current_altitude;
	if (oapiGetAltitude(GetHandle(), &current_altitude)){
		double diff = (current_altitude - goal) / goal;

		tl0 = max(0., min(0.5, tl0 - diff));
		tl1 = max(0., min(0.5, tl1 - diff));

		SetThrusterLevel(th_hover[0], tl0);
		SetThrusterLevel(th_hover[1], tl1);
	}
}


/**
	Control loop to maintain the orientation to the given goal
*/
void ShuttleA::maintainCardinal(double goal){
	double heading;

	if (oapiGetHeading(GetHandle(), &heading)){
		if (cardCmp(goal, heading) == 1){
			SetThrusterLevel(th_pod[0], 0.3);
			SetThrusterLevel(th_pod[1], 0);
		}
		else if (cardCmp(goal, heading) == -1){
			SetThrusterLevel(th_pod[0], 0);
			SetThrusterLevel(th_pod[1], 0.3);
		}
	}
}


/*
	return 0 if a == b
	1 if b is west part of a
	-1 if b is east part of a
*/
int cardCmp(double a, double b){
	if (a == b){
		return 0;
	}

	if (a <= 0){
		if (b < a || b > (a + PI)){
			return 1;
		}
		else{
			return -1;
		}
	}
	else{
		if (b > a || b < (a - PI)){
			return -1;
		}
		else{
			return 1;
		}
	}

	return 1;
}