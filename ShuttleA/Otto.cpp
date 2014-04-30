#include "ShuttleA.h"

void ShuttleA::postStep(double simt, double simdt, double mjd){
	switch (m_state){
	case 0:
		state0(simt, simdt, mjd);
		break;
	case 1:
		state1(simt, simdt, mjd);
		break;
	default:
		break;
	}
	
}

void ShuttleA::state0(double simt, double simdt, double mjd){
	/* Actions */
	if (simt > 5){
		SetThrusterLevel(th_hover[0], 1.0f);
		SetThrusterLevel(th_hover[1], 1.0f);
	}

	/* Transition */
	if (simt > 10){
		m_state = 1;
	}
}

void ShuttleA::state1(double simt, double simdt, double mjd){
	/* Actions */
	SetThrusterLevel(th_main[0], 1.0f);
	SetThrusterLevel(th_main[1], 1.0f);
	SetThrusterLevel(th_hover[0], 1.0f);
	SetThrusterLevel(th_hover[1], 1.0f);


	/* Transition */

}