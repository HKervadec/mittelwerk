#include "ShuttleA.h"

typedef void(ShuttleA:: *StatePt)(double, double, double);

void ShuttleA::postStep(double simt, double simdt, double mjd){
	StatePt state_fct[] = { &ShuttleA::STATE_TOTO,
		&ShuttleA::STATE_1 };
	(this->*state_fct[m_state])(simt, simdt, mjd);
}

void ShuttleA::maintainAltitude(double goal){
	double tl0, tl1;
	double current_altitude;
	double diff;
	tl0 = GetThrusterLevel(th_hover[0]);
	tl1 = GetThrusterLevel(th_hover[1]);
	oapiGetAltitude(GetHandle(), &current_altitude);
	diff = (current_altitude - goal) / goal;
	tl0 = max(0, min(0.5, tl0 - diff));
	tl1 = tl0;
	SetThrusterLevel(th_hover[0], max(tl0, tl0))
		;
	SetThrusterLevel(th_hover[1], tl1)
		;
}

double ShuttleA::test(int n){
	int i;
	double result;
	i = 0;
	result = 0;
	while (i < n){
		result = result + 1 / n;
		i = i + 1;
	}
	return result;
}

void ShuttleA::STATE_TOTO(double simt, double simdt, double mjd){
	if (simt > 5){
		SetThrusterLevel(th_hover[0], 1)
			;
		SetThrusterLevel(th_hover[1], 1)
			;
	}


	if (simt > 5){
		if (simt > 10){
			m_state = 1;
		}


	}


}

void ShuttleA::STATE_1(double simt, double simdt, double mjd){

	maintainAltitude(500)
		;
	SetThrusterLevel(th_main[0], 1)
		;
	SetThrusterLevel(th_main[1], 1)
		;
}

